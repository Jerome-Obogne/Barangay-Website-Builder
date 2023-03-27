package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class Dao_AddUser {

	
	public String createUser(String username,String email, User user) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs=null;
		ResultSet rs2=null;
	
		try {
			
			String query="SELECT 1 FROM user WHERE username=?";
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			
			rs=ps.executeQuery();
		
			String query2="SELECT 1 FROM user WHERE email=?";
			ps=con.prepareStatement(query2);
			ps.setString(1, email);
			
			
			rs2=ps.executeQuery();
			
			if (rs.next()) {
				return "Username";
			}

			
			else if (rs2.next()) {
				
				return "Email";
			}
			else {
				
				String query3="INSERT INTO user (first_name,last_name,sex,address,date_of_birth,email,username,password,salt_value,role,status)" +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				
				ps1=con.prepareStatement(query3);
				ps1.setString(1, user.getFirstName());
				ps1.setString(2, user.getLastName());
				ps1.setString(3, user.getSex());
				ps1.setString(4, user.getAddress());
				ps1.setString(5, user.getDob());
				ps1.setString(6, user.getEmail());
				ps1.setString(7, user.getUsername());
				ps1.setString(8, user.getPassword());
				ps1.setString(9, user.getSalt_value());
				ps1.setString(10, user.getRole());
				ps1.setString(11, user.getStatus());
			
				int create=0;
				create=ps1.executeUpdate();
				
				if (create!=0) {
					
					System.out.println("CREATE ACCOUNT SUCCESFUL");
					return "Success";
					
				}
				
			
			}
		
	
		} catch (Exception e) {
			System.out.println("Dao_AddUser createUser Fail " + e.getMessage());
			e.printStackTrace();
		}
		
	
		return "Error";
		
	}
}
