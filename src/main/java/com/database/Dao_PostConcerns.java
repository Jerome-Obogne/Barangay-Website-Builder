package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Concern;

public class Dao_PostConcerns {


	
	 /* Insert Concern Method in Database */
	public int addConcerns(String name, String email, String message) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		int insert=0;
		
		try {
			String query="INSERT INTO concerns(name,email,message) VALUES(?,?,?)";
			ps=con.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, message);
			
			insert=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_PostConcerns addConcerns Method Fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return insert;
	}
	
	
	 /* View Data in  Concern Table */

	public List<Concern> getConcerns(){
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List <Concern> list =new ArrayList<>();
		Concern concerns= null;
		try {
			
			String query="SELECT message_id,name,email,message,isDelete, DATE_FORMAT(dateTime, '%M %e %Y  %h:%i:%p') AS DateTime \r\n"
					+ "FROM cms.concerns WHERE isdelete='notDeleted' ORDER BY message_id DESC";
			
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				concerns=new Concern(Integer.parseInt(rs.getString("message_id")), rs.getString("name"), rs.getString("email"),
						rs.getString("message"), rs.getString("DateTime"));
				
				list.add(concerns);
				
			}
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_PostConcerns getConcerns Method Fail" + e.getMessage());
			e.printStackTrace();

		}
		
		return list;
	}
	
	
	/*Get specific Concerns*/
	
	public Concern userConcerns(int messageId) {
	

		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		 Concern concerns=null;
		try {
			String query="SELECT * FROM concerns WHERE message_id=?";
		
			ps=con.prepareStatement(query);
			ps.setInt(1, messageId);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				
				concerns=new Concern(Integer.parseInt(rs.getString("message_id")), rs.getString("name"), rs.getString("email"),
						rs.getString("message"), rs.getString("datetime"));
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Dao_PostConcerns userConcerns Method Fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return concerns;
		
	}
	
   /* Soft Delete the Message*/
	
	public int deleteConcerns(int messageId) {
		
		int softDelete=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		
		try {
			
			String query="UPDATE concerns SET isDelete='Deleted' WHERE message_id=? ";
			ps=con.prepareStatement(query);
			ps.setInt(1, messageId);
			
			softDelete=ps.executeUpdate();
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_PostConcerns deleteConcerns Method Fail" + e.getMessage());
			e.printStackTrace();
			
		}
		return softDelete;
		
	}
	
	/* Count the Number of Concerns*/
	
	public int totalNumberofConcerns() {
		
		int total=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String query="SELECT COUNT(*) FROM concerns WHERE isDelete='notDeleted'";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				total=rs.getInt(1);
				
			}
			
			
			
		} catch (Exception e) {
			
		}
		
		return total;
		
	}
	
	
}
