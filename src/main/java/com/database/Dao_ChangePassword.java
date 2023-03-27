package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Dao_ChangePassword {
	
	
		public int changePassword(String password, String salt_value, int id) {
			 
			int update=0;
			
			Connection con= new CmsDatabase().database();
			PreparedStatement ps= null;
			
			
			try {
				
				String query="UPDATE user SET password=?, salt_value=? WHERE user_id=?";
				ps=con.prepareStatement(query);
				ps.setString(1, password);
				ps.setString(2, salt_value);
				ps.setInt(3, id);
				
				
				update=ps.executeUpdate();
				
				
				
				
			} catch (Exception e) {
				System.out.println(" Dao_ChangePassword changePassword fail  " + e.getMessage());
			}
			
			return update;
			
		}
	
	

}
