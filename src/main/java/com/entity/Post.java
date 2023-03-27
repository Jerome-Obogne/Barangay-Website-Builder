package com.entity;

import java.io.Serializable;
import java.util.Date;

public class Post  implements Serializable {

	private int post_id;
	private String post_title;
	private String post_content;
	private String post_author;
	private String post_image;
	private Date post_date;
	private String post_status;
	private int postCat_id;
	private int postUser_id;
	private String categoryName;
	
	


	public Post(int post_id, String post_title, String post_content, String post_author, String post_image,
			Date post_date, String post_status, int postCat_id, int postUser_id) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_author = post_author;
		this.post_image = post_image;
		this.post_date = post_date;
		this.post_status = post_status;
		this.postCat_id = postCat_id;
		this.postUser_id = postUser_id;
	}
	


	//	Insert Post Attribute
	public Post(String post_title, String post_content, String post_author, String post_image, Date post_date,
			String post_status, int postCat_id, int postUser_id) {
	
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_author = post_author;
		this.post_image = post_image;
		this.post_date = post_date;
		this.post_status = post_status;
		this.postCat_id = postCat_id;
		this.postUser_id = postUser_id;
	}

	
	


       /* Atttribute use to Manage/View Post*/
	public Post(int post_id, String post_author, String post_title, String post_image,  int postCat_id,   String categoryName,
			String post_status, Date post_date, String post_content) {
		super();
		this.post_id = post_id;
		this.post_author = post_author;
		this.post_title = post_title;
		this.post_image = post_image;
		this.postCat_id = postCat_id;
		this.categoryName = categoryName;
		this.post_status = post_status;
		this.post_date = post_date;
		this.post_content = post_content;
	}

	
	
	
	
		/* Attribute for Update Post*/
					
	public Post(int post_id, String post_title, String post_image, String post_status, String post_content,
			int postCat_id) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_image = post_image;
		this.post_status = post_status;
		this.post_content = post_content;
		this.postCat_id = postCat_id;
	}



	public int getPost_id() {
		return post_id;
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



	public Date getPost_date() {
		return post_date;
	}



	public String getPost_status() {
		return post_status;
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


	
	
	
	
	
	
	
	
	
	
	
}
