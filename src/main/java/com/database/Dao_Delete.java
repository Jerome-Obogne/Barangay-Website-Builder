package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Dao_Delete {
	
	public int deleteData(int id) {
		
		int delete=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		try {
			
			String query="DELETE FROM post WHERE post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			delete=ps.executeUpdate();
			
		
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao_Delete Fail  " +e.getMessage());
		}
		
		return delete;
	}
	
	
public int archiveData(int id) {
		
		int archived=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		try {
			
			String query="UPDATE post SET post_status='Archived' WHERE post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			archived=ps.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao_Delete Fail  " +e.getMessage());
		}
		
		return archived;
	}
	

}
