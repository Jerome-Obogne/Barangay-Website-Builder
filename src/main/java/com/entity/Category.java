package com.entity;

import java.util.Date;

public class Category {

	private int category_id;
	private String categoryName;

	
	
	
	public Category(int category_id, String categoryName) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
	}




	public int getCategory_id() {
		return category_id;
	}




	public String getCategoryName() {
		return categoryName;
	}
	
	
	
	
}
