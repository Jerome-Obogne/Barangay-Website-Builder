package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class Dao_ForgotPassword {

	
	
	public boolean checkEmail(String email) {		// use to check email if exist in the Database in order to determine whichc message will give in reset password link
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps= null;
		ResultSet rs=null;
		
		try {
			String query="SELECT email FROM user WHERE email=?";
			ps=con.prepareStatement(query);
			ps.setString(1, email);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				return true; 				//return true if the email exist in the database
				
			}
			
	
			
		} catch (Exception e) {
			System.out.println("Dao_ForgotPassword checkEmail method fail  " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public int deleteData(String email) {				// use to delete data in reset password table  that use in forgot password controller
		Connection con=new CmsDatabase().database();
		PreparedStatement ps= null;
		
		int deleted=0;
		
		try {
			String query="DELETE FROM reset_password WHERE email=?";
			ps=con.prepareStatement(query);
			ps.setString(1, email);
			
			deleted=ps.executeUpdate();
		
	
			
		} catch (Exception e) {
			System.out.println("Dao_ForgotPassword deleteData method fail  " + e.getMessage());
			e.printStackTrace();
		}
		 
		return deleted;
		
		
	}
	
	public int insertResetPasswordData(User user) {		// insert data in reset password table  in order to validate token if valid that  store in database and if time is not expired
		
		int insertData=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps= null;
		
		try {
			String query="INSERT INTO reset_password () VALUES(?,?,?,?);";
			ps=con.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getToken());
			ps.setTimestamp(3, user.getIntime());
			ps.setTimestamp(4, user.getExptime());
			
			insertData=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_ForgotPassword insertResetPasswordData method fail  " + e.getMessage());
			e.printStackTrace();
		}
		
		return insertData;
		
		
	}
	
	
	
	
	
}
