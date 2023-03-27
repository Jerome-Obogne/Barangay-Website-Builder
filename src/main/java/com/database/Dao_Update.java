package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.entity.Post;

public class Dao_Update {

	
	
	public int updatePost(Post post) {
		Connection con = new CmsDatabase().database();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date  date= new Date();
		java.sql.Date dateUpdated=new java.sql.Date(date.getTime());
		int update=0;
			
		try {
			String query="UPDATE post p JOIN category c ON p.category_id=c.category_id SET p.post_title=?, p.post_image=?, p.post_status=?,  p.post_content=? ,  p.category_id=? , p.post_updated=? WHERE p.post_id=?;";
			ps=con.prepareStatement(query);
			ps.setString(1,post.getPost_title());
			ps.setString(2,post.getPost_image());
			ps.setString(3,post.getPost_status());
			ps.setString(4,post.getPost_content());
			ps.setInt(5,post.getPostCat_id());
			ps.setDate(6,dateUpdated);
			ps.setInt(7,post.getPost_id());
			
			update=ps.executeUpdate();
		
	
			
			
		} 
		
		catch (Exception e) {
			System.out.println(" Dao Update updatePost Method Problem " +e.getMessage());
		}

		return update;
	}
	
	
	

	
	public Post getData(int id) {   // fetch the data Use for Edit in ManagePost Table
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Post postData=null;
		try {
			String query = "SELECT p.post_id ,p.post_author, p.post_title, p.post_image, p.category_id, c.category_name, p.post_status, p.post_created, p.post_content FROM cms.post p  INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				

				postData = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"),  Integer.parseInt(rs.getString("category_id")),rs.getString("category_Name"),
						rs.getString("post_status"), rs.getDate("post_created"), rs.getString("post_content"));
			}
			
			
			
		} catch (Exception e) {
			System.out.println(" Dao Update getData Method Problem " +e.getMessage());
		}
		
		
		
		return postData;
		
	}
	
	// Update to Archive Manage Post
	
	
	public int archiveData(int id) {

		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		int archived=0;
		try {
			String query="UPDATE post SET post_status='Archived' WHERE post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			archived=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println(" Dao Update archiveData Method Problem " +e.getMessage());
		}
		
		return archived;
		
		
	}
	
	
	
	
}
