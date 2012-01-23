package com.ji.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.ji.dao.InventoryItem;
import com.ji.mvc.form.InventoryItemForm;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	protected static Log log = LogFactory.getLog(InventoryController.class);

	public static String VIEW_ADD_INVENTORY = "inventory/add";
	public static String VIEW_INVENTORY_LIST = "inventory/list";
	public static String VIEW_INVENTORY_ITEM = "inventory/view";

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addItemForm(Model model) {
		model.addAttribute(new InventoryItemForm());
		return VIEW_ADD_INVENTORY;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItemFormSubmit(@Valid InventoryItemForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return VIEW_ADD_INVENTORY;
		}

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity inventoryItem = new Entity("InventoryItem");
		inventoryItem.setProperty("inventoryItemId", form.getInventoryItemId());
		inventoryItem.setProperty("created", new Date(System.currentTimeMillis()));
		inventoryItem.setProperty("description", form.getDescription());
		inventoryItem.setProperty("name", form.getName());
		inventoryItem.setProperty("type", form.getType());
		Key generatedKey = datastore.put(inventoryItem);

		if (log.isDebugEnabled()) {
			log.debug("put new item into datasource with key " + generatedKey);
		}

		model.addAttribute("success", "item.added");
		model.addAttribute(new InventoryItemForm());
		return VIEW_ADD_INVENTORY;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		String page = request.getParameter("page");
		if (page == null) {

			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("InventoryItem");
			// List<Entity> items =
			// datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
			List<InventoryItem> items = new ArrayList<InventoryItem>();

			PreparedQuery pq = datastore.prepare(query);

			for (Entity result : pq.asIterable()) {
				log.debug("name = " + result.getProperty("name"));
				InventoryItem i = new InventoryItem();
				i.setCreated((Date) result.getProperty("created"));
				i.setDescription((String) result.getProperty("description"));
				i.setId((String) result.getProperty("inventoryItemId"));
				i.setName((String) result.getProperty("name"));
				i.setType((String) result.getProperty("type"));
				items.add(i);
			}

			if (log.isDebugEnabled()) {
				log.debug("found " + items.size() + " inventory items");
			}
			PagedListHolder<InventoryItem> pagedItemList = new PagedListHolder<InventoryItem>(items);
			pagedItemList.setPageSize(3);
			request.getSession().setAttribute("pagedItemList", pagedItemList);
		} else {
			@SuppressWarnings("unchecked")
			PagedListHolder<InventoryItem> pagedItemList = (PagedListHolder<InventoryItem>) request.getSession().getAttribute("pagedItemList");
			if ("next".equals(page)) {
				pagedItemList.nextPage();
			} else if ("previous".equals(page)) {
				pagedItemList.previousPage();
			} else if (StringUtils.isNotEmpty(page) && StringUtils.isNumeric(page)) {
				pagedItemList.setPage(Integer.valueOf(page).intValue() - 1);
			} else {
				// use existing state of PagedListHolder in session
			}
		}

		return VIEW_INVENTORY_LIST;
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("InventoryItem").addFilter("inventoryItemId", FilterOperator.EQUAL, id);
		PreparedQuery pq = datastore.prepare(query);
		Entity result = pq.asSingleEntity();
		InventoryItem i = new InventoryItem();
		i.setCreated((Date) result.getProperty("created"));
		i.setDescription((String) result.getProperty("description"));
		i.setId((String) result.getProperty("inventoryItemId"));
		i.setName((String) result.getProperty("name"));
		i.setType((String) result.getProperty("type"));

		if (log.isDebugEnabled()) {
			log.debug("found " + i);
		}

		model.addAttribute(i);
		return VIEW_INVENTORY_ITEM;
	}
}
