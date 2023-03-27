package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Menu;
import com.entity.Post;
import com.entity.WebPost;

public class Dao_View {
     
	public List<Post> viewAllPost() {  		/*View All Post In Manage Post table */

		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Post> list = new ArrayList<>();
		Post getAllPost = null;
		try {

			String query = "CALL manageView()";
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				getAllPost = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")), rs.getString("category_Name"),
						rs.getString("post_status"), rs.getDate("post_updated"), rs.getString("post_content"));

				list.add(getAllPost);
			}

		} catch (Exception e) {

			System.out.println("View All Post Fail to Load" + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}

	public List<WebPost> displayPost() {	  /*Display All post in HOME PAGE*/		

		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<WebPost> list = new ArrayList<>();
		WebPost display = null;
		try {

			String query = "SELECT p.post_id, p.post_image, p.post_title, p.post_content, p.post_author, p.post_updated  FROM cms.post p INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_status='Publish' AND datediff(NOW(),date(p.post_created))<=7 ORDER BY p.post_id DESC LIMIT 5";
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				display = new WebPost(Integer.parseInt(rs.getString("post_id")), rs.getString("post_image"),
						rs.getString("post_title"), rs.getString("post_content"), rs.getString("post_author"),
						rs.getDate("post_updated"));

				list.add(display);
			}

		} catch (Exception e) {

			System.out.println("Display All Post Fail to Load" + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}
	
	public List<Post> viewOnlyPost(int post_id) {  		/*View Only One Post In Manage Post table */

		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Post> list = new ArrayList<>();
		Post getAllPost = null;
		try {

			String query = "SELECT p.post_id ,p.post_author, p.post_title, p.post_image,p.category_id ,c.category_name, p.post_status, p.post_updated, p.post_content FROM cms.post p  INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=? AND p.post_status='Publish';";
			ps = con.prepareStatement(query);
			ps.setInt(1, post_id);
			rs = ps.executeQuery();

			while (rs.next()) {

				getAllPost = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_author"),
						rs.getString("post_title"), rs.getString("post_image"), Integer.parseInt(rs.getString("category_id")), rs.getString("category_Name"),
						rs.getString("post_status"), rs.getDate("post_updated"), rs.getString("post_content"));

				list.add(getAllPost);
			}

		} catch (Exception e) {

			System.out.println("View Only Post Fail to Load" + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}
	
		/*WebPageView*/
	
	
	public List<WebPost> viewPagePost(int post_id) {  		/*View Only One Post In Front End of the System */

		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<WebPost> list = new ArrayList<>();
		WebPost webpost = null;
		try {
			String query = "SELECT p.post_id, p.post_image, p.post_title, p.post_content, p.post_author, p.post_updated FROM cms.post p INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_id=? AND p.post_status='Publish';";
		
			ps = con.prepareStatement(query);
			ps.setInt(1, post_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				webpost = new WebPost(Integer.parseInt(rs.getString("post_id")), rs.getString("post_image"),
						rs.getString("post_title"), rs.getString("post_content"), rs.getString("post_author"),
						rs.getDate("post_updated"));

				list.add(webpost);
			
			}

		} catch (Exception e) {

			System.out.println("View Front Page  Post Fail to Load" + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}
	
	
	/* Count the number of Post Publish to Display in Dashboard*/
	
	public int totalPublish() {
		
		int totalNumberofPublish=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String query="SELECT COUNT(post_status) AS totalPublish FROM post  WHERE post_status='Publish'";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				totalNumberofPublish=Integer.parseInt(rs.getString("totalPublish"));
				
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Problem Fail in Dao View totalPublish method " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return totalNumberofPublish;
		
		
	}
	
	/* Count the number of Category to Display in Dashboard*/
	
	public int totalCategory() {
		
		int totalNumberofCategory=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String query="SELECT count(*) AS totalCategory FROM cms.category WHERE category_status='Unarchived';";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				totalNumberofCategory=Integer.parseInt(rs.getString("totalCategory"));
				
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Problem Fail in Dao View totalCategory method " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return totalNumberofCategory;
		
		
	}
	
	// total number of user in dashboard page
	
public int totalUser() {
		
		int totalNumberofUser=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String query="SELECT COUNT(*) AS totalUser FROM user;";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				totalNumberofUser=Integer.parseInt(rs.getString("totalUser"));
				
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Problem Fail in Dao View totalUser method " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return totalNumberofUser;
		
		
	}
	

	
	/* Menu Page View*/
	
	
public List<Menu> viewOnlyPage(int post_id) {  		/*View Only One Page In Page View table */

	Connection con = new CmsDatabase().database();
	ResultSet rs = null;
	PreparedStatement ps = null;
	List<Menu> list = new ArrayList<>();
	Menu getAllPost = null;
	try {

		String query="SELECT m.menu_id, m.menu_title, m.menu_content,m.menu_images FROM menu m INNER JOIN user u ON m.menu_user_id=u.user_id WHERE m.menu_id=?";
		ps=con.prepareStatement(query);
		ps.setInt(1, post_id);
		
		rs=ps.executeQuery();
		
		
		while(rs.next()) {
			
			
			
			getAllPost= new Menu(Integer.parseInt(rs.getString("menu_id")), rs.getString("menu_title"), rs.getString("menu_content"),rs.getString("menu_images"));
			
			list.add(getAllPost);
			
		
		}
		
		
		
	} catch (Exception e) {

		System.out.println("viewOnlyPage Fail to Load" + e.getMessage());
		e.printStackTrace();
	}
	return list;

}
	
	
}
