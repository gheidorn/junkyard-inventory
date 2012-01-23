package com.ji.mvc.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class InventoryItemForm {

	private static String[] TYPES = { "", "Car Part", "Truck Part",
			"Miscellaneous" };

	@NotBlank(message = "inventoryItemId.notblank")
	@Length(max = 5, message = "inventoryItemId.length")
	private String inventoryItemId;

	@NotBlank(message = "name.notblank")
	@Length(max = 20, message = "name.length")
	private String name;

	@NotBlank
	@Length(max = 255, message = "description.length")
	private String description;

	@NotBlank
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getTypes() {
		return TYPES;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}
}
