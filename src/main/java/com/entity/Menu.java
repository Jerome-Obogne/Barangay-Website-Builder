package com.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Menu {

	
	private int menuId;
	private int userId;
	private String menuTitle;
	private String menuContent;
	private String menuAuthor;
	private Timestamp menu_created;
	private String menu_create;
	private String menu_updated;

	private Date created;
	private Date updated;
	
	private String menu_images;
	
	public Menu(int menuId, String menuTitle, String menuContent, String menuAuthor, String menu_create,
			String menu_updated, String menu_images, int userId) {
		
		super();
		this.menuId = menuId;
		this.menuTitle = menuTitle;
		this.menuContent = menuContent;
		this.menuAuthor = menuAuthor;
		this.menu_create = menu_create;
		this.menu_updated = menu_updated;
		this.menu_images = menu_images;
		this.userId = userId;
	}
	
	
	

		//ADD PAGES 
	
	public Menu(String menuTitle, String menuContent, String menuAuthor, Timestamp menu_created,
			String menu_images, int userId) {
		super();
	
		this.menuTitle = menuTitle;
		this.menuContent = menuContent;
		this.menuAuthor = menuAuthor;
		this.menu_created = menu_created;
		this.menu_images = menu_images;
		this.userId = userId;
	}
	
	
	

		//attribute for Specific Pages
	//Atribute for Update pages

	public Menu(int menuId, String menuTitle, String menuContent, String menu_images) {
		super();
		this.menuId = menuId;
		this.menuTitle = menuTitle;
		this.menuContent = menuContent;
		this.menu_images = menu_images;
	}


	
	
	
	
	
	public Menu(int menuId, String menuTitle, String menuContent, String menuAuthor, String menu_images) {
		super();
		this.menuId = menuId;
		this.menuTitle = menuTitle;
		this.menuContent = menuContent;
		this.menuAuthor = menuAuthor;
		this.menu_images = menu_images;
	}

	
	
	


	public Menu(int menuId, String menuTitle) {
		super();
		this.menuId = menuId;
		this.menuTitle = menuTitle;
	}




	public int getMenuId() {
		return menuId;
	}
	



	public int getUserId() {
		return userId;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public String getMenuContent() {
		return menuContent;
	}
	public String getMenuAuthor() {
		return menuAuthor;
	}
	public Timestamp getMenu_created() {
		return menu_created;
	}
	public String getMenu_updated() {
		return menu_updated;
	}
	public String getMenu_images() {
		return menu_images;
	}




	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}
	
	public String getMenu_create() {
		return menu_create;
	}

	
	
	
	
	
	
	
	
	
	
}
