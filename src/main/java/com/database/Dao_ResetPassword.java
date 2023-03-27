package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao_ResetPassword {

	
	
	public boolean validateToken(String key) {			//method use to validate the token if existing or not
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String query="SELECT token FROM reset_password WHERE token=?";
			ps=con.prepareStatement(query);
			ps.setString(1, key);
			
			rs=ps.executeQuery();
			
			if (rs.next()) {
				
				return true;			//return true if token is existing
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_ResetPassword validateToken method fail "+ e.getMessage());
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public ResultSet checkToken(String token) {		//method to check token,exptime in reset password controller
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String query="SELECT email,token ,expTime FROM reset_password WHERE token=?";
			ps=con.prepareStatement(query);
			ps.setString(1, token);
			
			rs=ps.executeQuery();
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_ResetPassword checkToken method fail "+ e.getMessage());
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	
	
	public String updateResetPassword(String email, String hash_password, String salt_value ) {			//update the account in resetPassword
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		try {
			String query="UPDATE user SET password=?,salt_value=? WHERE email=?";
			ps=con.prepareStatement(query);
			ps.setString(1, hash_password);
			ps.setString(2, salt_value);
			ps.setString(3, email);
			
			int update=ps.executeUpdate();
			
			if(update!=0) {
				
				String query2="DELETE FROM reset_password WHERE email=?";
				ps=con.prepareStatement(query2);
				ps.setString(1, email);
			
				ps.executeUpdate();
				
				return "Success";				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_ResetPassword updateResetPassword method fail "+ e.getMessage());
			e.printStackTrace();
		}
		
		return "Fail";
	}
}
