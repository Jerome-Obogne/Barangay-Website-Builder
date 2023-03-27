package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class Dao_Login {
	
	public ResultSet login(User user) {

	Connection	con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try
		{
			String query="SELECT *  FROM user WHERE username=? AND password=? AND status='Enable'";
			ps=con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs=ps.executeQuery();
			
			
			
		} catch (Exception e) {

			System.out.println("Login Database Problem " + e.getMessage());
		}

		finally {

		}
		return rs;	

	}
	
	/* Method to Get the Salt Value in DB  */
	
public String getSalt(String username) {
	
		Connection con= new CmsDatabase().database();
		PreparedStatement ps = null;
		String data="";
		
		try {
			String query="SELECT username,salt_value FROM user WHERE username=?";
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			
		while (rs.next()) {
			
			data=rs.getString("salt_value");
			
		}
			
		
		} catch (Exception e) {
			
			System.out.println("Get salt method problem" + e.getMessage());
			e.printStackTrace();
		}
		
			
		return data;
	}

	
}
	