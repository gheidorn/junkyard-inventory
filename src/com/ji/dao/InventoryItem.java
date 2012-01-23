package com.ji.dao;

import java.io.Serializable;
import java.util.Date;

public class InventoryItem implements Serializable {
	private static final long serialVersionUID = 3874950359693711754L;

	private String id;
	private Date created;
	private String name;
	private String type;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new StringBuffer("InventoryItem (" + super.toString() + ") : ").append("id = " + this.id).toString();
	}
}
