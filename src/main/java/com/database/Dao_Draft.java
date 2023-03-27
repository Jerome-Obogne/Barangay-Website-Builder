package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Post;

public class Dao_Draft {

			/*Method to getAllDraft*/	
	public List<Post> getAllDraft(){
		
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Post> list = new ArrayList<>();
		Post getAllDraft = null;
		
		
		try {
			
			String query="CALL draftView()";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				getAllDraft = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")), rs.getString("category_Name"),
						rs.getString("post_status"), rs.getDate("post_updated"), rs.getString("post_content"));

				list.add(getAllDraft);
			}
	
			
			
		} catch (Exception e) {
			
			System.out.println("getAllDraft Method Fail" + e.getMessage());
		}
		
		return list;
	}
	
	
	public Post getDraftData(int id) {
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Post drafData=null;
		try {
			String query = "SELECT p.post_id ,p.post_author, p.post_title, p.post_image, p.category_id, c.category_name, p.post_status, p.post_created, p.post_content FROM cms.post p  INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				

				drafData = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"),  Integer.parseInt(rs.getString("category_id")),rs.getString("category_Name"),
						rs.getString("post_status"), rs.getDate("post_created"), rs.getString("post_content"));
			}
			
			
			
		} catch (Exception e) {
			System.out.println(" Dao Draft getDraftData Method Problem " +e.getMessage());
		}
		
	
		return drafData;
		
		
		
	}
	
	public int updateDraft(Post post) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		int update=0;
		Date date=new Date();
		java.sql.Date dateUpdated=new java.sql.Date(date.getTime());
		try {
		
			
			String query="UPDATE post SET post_title=?, post_image=?, post_status=?,  post_content=? ,  category_id=? , post_updated=? WHERE post_id=?;";
			ps=con.prepareStatement(query);
			ps.setString(1,post.getPost_title());
			ps.setString(2,post.getPost_image());
			ps.setString(3,post.getPost_status());
			ps.setString(4,post.getPost_content());
			ps.setInt(5,post.getPostCat_id());
			ps.setDate(6,dateUpdated);
			ps.setInt(7,post.getPost_id());
			
			update=ps.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(" Dao Draft updateDraft Method Problem " +e.getMessage());
		}
		
		return update;
		
	}

	
	
}
