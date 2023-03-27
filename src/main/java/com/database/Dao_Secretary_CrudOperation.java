package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.entity.Secretary_Post;

public class Dao_Secretary_CrudOperation {

	
	public int  insertPost(Secretary_Post post) {   //method to insert secretary post
		
		int insert=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		
		try {
			String query="INSERT INTO post(post_title,post_content,post_author,post_image,post_created,post_status,category_id,user_id) VALUES(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(query);
			ps.setString(1, post.getPost_title());
			ps.setString(2, post.getPost_content());
			ps.setString(3, post.getPost_author());
			ps.setString(4, post.getPost_image());
			ps.setDate(5, post.getUpdated());
			ps.setString(6, post.getPost_status());
			ps.setInt(7, post.getPostCat_id());
			ps.setInt(8, post.getPostUser_id());
			
			insert=ps.executeUpdate();
	
			
		} catch (Exception e) {
			
			System.out.println("Dao_Secretary_CrudOperation insertPost Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		return insert;
		
	}
	
	
	//Update Secretary Draft 
	
	
	public int updateDraftData(Secretary_Post data) {
		
		
		int update=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		Date date=new Date();
		java.sql.Date dateUpdated=new java.sql.Date(date.getTime());
		try {
			String query="UPDATE post SET post_title=?, post_image=?, post_status=?,  post_content=? ,  category_id=? , post_updated=? WHERE post_id=?;";
			ps=con.prepareStatement(query);
			ps.setString(1,data.getPost_title());
			ps.setString(2,data.getPost_image());
			ps.setString(3,data.getPost_status());
			ps.setString(4,data.getPost_content());
			ps.setInt(5,data.getPostCat_id());
			ps.setDate(6,dateUpdated);
			ps.setInt(7,data.getPostId());
			
			update=ps.executeUpdate();
			
			
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_Secretary_CrudOperation updateDraftData Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
		
	}
	
	
	
	
	
	
}
