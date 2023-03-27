package com.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Secretary_Post {

	private int postId;
	private String post_title;
	private String post_content;
	private String post_author;
	private String post_image;
	private String post_status;
	private Timestamp post_created;
	private String menu_create;
	private String menu_updated;
	private int postCat_id;
	private int postUser_id;
	private String categoryName;
	
	
	private Date created;
	private Date updated;
	
	

	
	
	
	
	//attribute to view all post in Manage post view of secretary page
	
	public Secretary_Post(int postId, String post_author, String post_title, String post_image, int postCat_id,
			String categoryName, String post_status, Date created, Date updated, String post_content) {
		super();
		this.postId = postId;
		this.post_author = post_author;
		this.post_title = post_title;
		this.post_image = post_image;
		this.postCat_id = postCat_id;
		this.categoryName = categoryName;
		this.post_status = post_status;
		this.created = created;
		this.updated = updated;
		this.post_content = post_content;
	}
	
	
	
	// attribute to  view specific  post by secretary
	
	public Secretary_Post(int postId, String post_image, String post_title, String post_content, String post_author,
			Date updated) {
		super();
		this.postId = postId;
		this.post_image = post_image;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_author = post_author;
		this.updated = updated;
	}


	



	public Secretary_Post(String post_title, String post_content, String post_author, String post_image, Date updated,
			String post_status, int postCat_id, int postUser_id) {
		super();
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_author = post_author;
		this.post_image = post_image;
		this.updated = updated;
		this.post_status = post_status;
		this.postCat_id = postCat_id;
		this.postUser_id = postUser_id;
	}
	
	
	
	



	public Secretary_Post(int postId, String post_title, String post_image, String post_status, String post_content,
			int postCat_id) {
		super();
		this.postId = postId;
		this.post_title = post_title;
		this.post_image = post_image;
		this.post_status = post_status;
		this.post_content = post_content;
		this.postCat_id = postCat_id;
	}



	public int getPostId() {
		return postId;
	}
	public String getPost_title() {
		return post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public String getPost_author() {
		return post_author;
	}
	public String getPost_image() {
		return post_image;
	}
	public String getPost_status() {
		return post_status;
	}
	public Timestamp getPost_created() {
		return post_created;
	}
	public String getMenu_create() {
		return menu_create;
	}
	public String getMenu_updated() {
		return menu_updated;
	}
	public int getPostCat_id() {
		return postCat_id;
	}
	public int getPostUser_id() {
		return postUser_id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}
	
	
	
	
	
	
}
