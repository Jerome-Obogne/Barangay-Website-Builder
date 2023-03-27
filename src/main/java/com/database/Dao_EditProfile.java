package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.entity.User;

public class Dao_EditProfile {
	
	public int updateEditProfile(User user) {
		
		
		Connection con= new CmsDatabase().database();
		PreparedStatement ps =null;
		
		
		int update=0;
		try {
		
			String query="UPDATE user  SET first_name=?, last_name=?, address=?, date_of_birth=? , user_image=?, email=?, username=?  WHERE user_id=?";
			ps=con.prepareStatement(query);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getDob());
			ps.setString(5, user.getUser_image());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getUsername());
			ps.setInt(8, user.getUserId());
		
			update=ps.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Dao_EditProfile updateEditProfile fail  " + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
	}

}
