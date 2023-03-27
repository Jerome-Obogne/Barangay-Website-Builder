package com.entity;

import java.sql.Date;

public class WebPost {
		
		private int post_id;
		private String image;
		private String post_title;
		private String post_content;
		private String post_author;
		private Date post_date;
		
		
		public WebPost(int post_id, String image, String post_title, String post_content, String post_author,
				Date post_date) {
			super();
			this.post_id = post_id;
			this.image = image;
			this.post_title = post_title;
			this.post_content = post_content;
			this.post_author = post_author;
			this.post_date = post_date;
		}


		public int getPost_id() {
			return post_id;
		}


		public String getImage() {
			return image;
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


		public Date getPost_date() {
			return post_date;
		}
	
	
		
		
		
		
	
}
