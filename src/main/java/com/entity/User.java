package com.entity;

import java.sql.Timestamp;

public class User {
		private int userId;
		private String firstName;
		private String lastName;
		private String sex;
		private String address;
		private String dob;
		private String email;
		private String username;
		private String password;
		private String salt_value;
		private String role;
		private String status;
		private String user_image;
		
		private String fullName;  //user for concat name 
		
     /*		use for forgot password attribute START HERE */
		
		private String token;
		private Timestamp intime;
		private Timestamp exptime;
	
		public User(String username, String password) {  // use in username and password
			super();
			this.username = username;
			this.password = password;
		}
		
	
		//attribute or data use for admin in edit profile
	
		public User(int userId, String firstName, String lastName, String email, String user_image, String username, String password,
				String salt_value, String role , String address, String dob) {       
												
			
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.user_image=user_image;
			this.username = username;
			this.password = password;
			this.salt_value = salt_value;
			this.role = role;
			this.address = address;
			this.dob = dob;
		}






		//Attribute for List of All Users

		public User(int userId, String firstName, String lastName, String sex, String address, String dob,
				String user_image, String email, String username, String password, String salt_value, String role,
				String status, String fullName) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.sex = sex;
			this.address = address;
			this.dob = dob;
			this.user_image = user_image;
			this.email = email;
			this.username = username;
			this.password = password;
			this.salt_value = salt_value;
			this.role = role;
			this.status = status;
			this.fullName = fullName;
		}
		
		
		
		
		
		
		
		/*Attribute for Creating User */

		public User(String username, String email, String firstName, String lastName, String address, String sex,
				String dob, String password, String salt_value, String role,String status) {
			super();
			this.username = username;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.sex = sex;
			this.dob = dob;
			this.password = password;
			this.salt_value = salt_value;
			this.role = role;
			this.status=status;
		}
		
		
			/* Attribute to  get Update User*/

		public User(int userId, String firstName, String lastName, String sex, String address, String dob,
				String user_image, String email, String username, String password, String salt_value, String role, String status) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.sex = sex;
			this.address = address;
			this.dob = dob;
			this.user_image = user_image;
			this.email = email;
			this.username = username;
			this.password = password;
			this.salt_value = salt_value;
			this.role = role;
			this.status=status;
		}

		//Update UserData
		public User(int userId, String firstName, String lastName, String sex, String address, String dob,
				 String email, String username, String password, String salt_value, String role, String status) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.sex = sex;
			this.address = address;
			this.dob = dob;
		
			this.email = email;
			this.username = username;
			this.password = password;
			this.salt_value = salt_value;
			this.role = role;
			this.status=status;
		}
		
		//Admin Edit Profile 
		

		public User(int userId, String email, String firstName, String lastName, String address, String dob, String username,
				String user_image) {
			super();
			this.userId = userId;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.dob = dob;
			this.username = username;
			this.user_image = user_image;
		}


		
		
		
		// Use for Forgot Password  insert in database 
		public User(String email, String token, Timestamp intime, Timestamp exptime) {
			super();
			this.email = email;
			this.token = token;
			this.intime = intime;
			this.exptime = exptime;
		}



		public int getUserId() {
			return userId;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public String getSex() {
			return sex;
		}
		public String getAddress() {
			return address;
		}
		public String getDob() {
			return dob;
		}
		public String getEmail() {
			return email;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public String getSalt_value() {
			return salt_value;
		}
		public String getRole() {
			return role;
		}
		public String getStatus() {
			return status;
		}	public String getUser_image() {
			return user_image;
		}


		public String getFullName() {
			return fullName;
		}


		public String getToken() {
			return token;
		}


		public Timestamp getIntime() {
			return intime;
		}


		public Timestamp getExptime() {
			return exptime;
		}
		
		
		
		
		
	
}
