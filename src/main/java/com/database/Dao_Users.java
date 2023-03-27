package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Post;
import com.entity.User;

public class Dao_Users {

	
			/* view all user*/	
	public List<User> viewListofUser(){
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User> list= new ArrayList<>();
		User listOfUser=null;
		try {
			
			 String query="SELECT  *, concat_ws(' ',last_name,first_name) AS Name FROM user WHERE user_id>1 ORDER BY user_id DESC";
			 ps=con.prepareStatement(query);
			 rs=ps.executeQuery();
			 
			 while (rs.next()) {
				
				 listOfUser=new User(Integer.parseInt(rs.getString("user_id")), rs.getString("first_name"),rs.getString("last_name"),
						 rs.getString("sex"),rs.getString("address"), rs.getString("date_of_birth"), rs.getString("user_image"),
						 rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("salt_value"),
						 rs.getString("role"), rs.getString("status"), rs.getString("Name"));
				
				 list.add(listOfUser);
			}
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_User viewListofUser failed " +  e.getMessage());
			e.printStackTrace();
		}
		
		return list;
		
		
		
		
	}
	 /* getData of Specific user*/	
	public User getUserData(int id) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		User userData=null;
		
		try {
			String query = "SELECT * FROM user WHERE user_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			
			while (rs.next()) {
				
				 userData=new User(Integer.parseInt(rs.getString("user_id")), rs.getString("first_name"),rs.getString("last_name"),
						 rs.getString("sex"),rs.getString("address"), rs.getString("date_of_birth"), rs.getString("user_image"),
						 rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("salt_value"),
						 rs.getString("role"), rs.getString("status"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println(" Dao_User getUsertData Method Problem " +e.getMessage());
		}
		
	
		return userData;
		

		
	}
	
	/* Update User Data */	
	
	
	public int updateUserData(User user) {
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
	
		int update=0;
	
		try {
			
			 String query="UPDATE user SET username=?, email=?, first_name=?, last_name=?, address=?, sex=?, date_of_birth=?, password=?, salt_value=?, role=?,status=?" +
			 "WHERE user_id=?";
			 ps=con.prepareStatement(query);
			 ps.setString(1, user.getUsername());
			 ps.setString(2, user.getEmail());
			 ps.setString(3, user.getFirstName());
			 ps.setString(4, user.getLastName());
			 ps.setString(5, user.getAddress());
			 ps.setString(6, user.getSex());
			 ps.setString(7, user.getDob());
			 ps.setString(8, user.getPassword());
			 ps.setString(9, user.getSalt_value());
			 ps.setString(10, user.getRole());
			 ps.setString(11, user.getStatus());
			 ps.setInt(12, user.getUserId());
			 
			 
			 update=ps.executeUpdate();
			 
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_User updateUserData failed " +  e.getMessage());
			e.printStackTrace();
		}
		
			return update;
	}
	
	public int deleteUser(int id) {
		
		
		
	
		int delete=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		try {
			
			String query="DELETE FROM user WHERE user_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			delete=ps.executeUpdate();
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao_User deleteUser Fail  " +e.getMessage());
		}
		
		return delete;
		
		
	}
	
	
	
	
	
	
	
	
	
}
