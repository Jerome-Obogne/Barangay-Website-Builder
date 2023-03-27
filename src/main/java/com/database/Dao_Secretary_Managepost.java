package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Post;
import com.entity.Secretary_Post;
import com.entity.WebPost;

public class Dao_Secretary_Managepost {

	
	public List<Secretary_Post> viewSecretaryPost(int userid){		//View all post of Secretary	
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Secretary_Post> list = new ArrayList<>();
		Secretary_Post getAllPost = null;
	
		try {

			String query = "SELECT p.post_id ,p.post_author, p.post_title, p.post_image, p.category_id, c.category_name, p.post_status, p.post_created, p.post_updated, p.post_content FROM cms.post p  \r\n"
					+ "INNER JOIN category c ON p.category_id=c.category_id\r\n"
					+ "INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_status='Publish' AND u.user_id=?";

			ps = con.prepareStatement(query);
			ps.setInt(1, userid);

			rs = ps.executeQuery();

			while (rs.next()) {

			
				getAllPost=new Secretary_Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")),
						rs.getString("category_name"), rs.getString("post_status"), rs.getDate("post_created"), rs.getDate("post_updated"), rs.getString("post_content"));  

				list.add(getAllPost);

			}

		} catch (Exception e) {

			System.out.println("Dao_Secretary_Managepost viewSecretaryPost  Fail to Load" + e.getMessage());
			e.printStackTrace();

		}

		return list;
	
	}
	
	
public List<Secretary_Post> viewSpecificPost(int userid){			//viewSpecific Post and Treasurer
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Secretary_Post> list = new ArrayList<>();
		Secretary_Post getSpecificPost = null;
	
		try {

			String query = "SELECT p.post_id, p.post_image, p.post_title, p.post_content, p.post_author, p.post_updated \r\n"
					+ "FROM cms.post p INNER JOIN category c ON p.category_id=c.category_id\r\n"
					+ " INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=? AND p.post_status='Publish'";

			ps = con.prepareStatement(query);
			ps.setInt(1, userid);

			rs = ps.executeQuery();

			while (rs.next()) {

				getSpecificPost = new Secretary_Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_image"),
						rs.getString("post_title"), rs.getString("post_content"), rs.getString("post_author"),
						rs.getDate("post_updated"));

				
				
				
				list.add(getSpecificPost);

			}

		} catch (Exception e) {

			System.out.println("Dao_Secretary_Managepost viewSecretaryPost  Fail to Load" + e.getMessage());
			e.printStackTrace();

		}

		return list;
	
	}
	
	


		//View Draft From Secretary


	public List<Secretary_Post> viewDraft(int userid){
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Secretary_Post> list = new ArrayList<>();
		Secretary_Post getAllDraft = null;
	
		try {

		String query="SELECT p.post_id, p.post_author, p.post_title, p.post_image, p.category_id, c.category_name, p.post_status, p.post_created, p.post_updated ,p.post_content\r\n"
				+ " FROM cms.post p\r\n"
				+ "INNER JOIN category c ON c.category_id=p.category_id \r\n"
				+ "INNER JOIN user u  ON u.user_id=p.user_id WHERE u.user_id=? AND p.post_status='Draft'";

			ps = con.prepareStatement(query);
			ps.setInt(1, userid);

			rs = ps.executeQuery();

			while (rs.next()) {

				
				getAllDraft=new Secretary_Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")),
						rs.getString("category_name"), rs.getString("post_status"), rs.getDate("post_created"), rs.getDate("post_updated"), rs.getString("post_content"));  
				
				
				
				list.add(getAllDraft);
				
				
				
				

			}

		} catch (Exception e) {

			System.out.println("Dao_Secretary_Managepost viewDraft  Fail to Load" + e.getMessage());
			e.printStackTrace();

		}

		return list;
		
		
		
		
	}
	
	
	
	//Get the draftData of Secretary
	
	
	public Secretary_Post getDraftData(int id) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Secretary_Post drafData=null;
		try {
			String query = "\r\n"
					+ "SELECT p.post_id ,p.post_author, p.post_title, p.post_image, p.category_id, c.category_name, p.post_status, p.post_created,  p.post_updated, p.post_content\r\n"
					+ " FROM cms.post p \r\n"
					+ "INNER JOIN category c ON p.category_id=c.category_id\r\n"
					+ "INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while (rs.next()) {
					
				
				drafData=new Secretary_Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")),
						rs.getString("category_name"), rs.getString("post_status"), rs.getDate("post_created"), rs.getDate("post_updated"), rs.getString("post_content"));  

			
			}
			
			
			
		} catch (Exception e) {
			System.out.println(" Dao Draft getDraftData Method Problem " +e.getMessage());
		}
		
	
		return drafData;
		
		
		
		
		
	}
	
	
	
	

		


}
