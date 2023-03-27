package com.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.Menu;
import com.entity.Post;


public class Dao_Post {

	
	public int postInsert(Post post) {	//method for post insert
		
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
			ps.setDate(5, (Date) post.getPost_date());
			ps.setString(6, post.getPost_status());
			ps.setInt(7, post.getPostCat_id());
			ps.setInt(8, post.getPostUser_id());
			
			insert=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_Post Fail " + e.getMessage());
			e.printStackTrace();
			
		}
		
		return insert;
		
		
	}
	
	
	
	
	/* Page insert*/
	
	public int pageInsert(Menu page) { // method for page insert

		int insert = 0;
		Connection con = new CmsDatabase().database();
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO menu(menu_title,menu_content,menu_author,menu_images,menu_created,menu_user_id) VALUES(?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, page.getMenuTitle());
			ps.setString(2, page.getMenuContent());
			ps.setString(3, page.getMenuAuthor());
			ps.setString(4, page.getMenu_images());
			ps.setTimestamp(5, page.getMenu_created());
			ps.setInt(6, page.getUserId());
			
			
			
			insert = ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Dao_Post pageInsert Fail " + e.getMessage());
			e.printStackTrace();

		}

		return insert;

	}
	
	
	/* Page Update*/
	
	// Get Specific Page to Update
	
	public Menu getPageData(int id) {
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Menu menu=null;
		
		
		try {
			
			String query="SELECT m.menu_id, m.menu_title, m.menu_content, m.menu_author, m.menu_images \r\n"
					+ "FROM menu m INNER JOIN user u \r\n"
					+ "ON m.menu_user_id=u.user_id WHERE m.menu_id=?;";
			
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
	while (rs.next()) {
				
		menu=new Menu(Integer.parseInt(rs.getString("menu_id")), rs.getString("menu_title"), rs.getString("menu_content"),rs.getString("menu_author"),
				rs.getString("menu_images"));

				
			}
			
		
		
		} catch (Exception e) {
			System.out.println("pageUpdate Fail " + e.getMessage());
			e.printStackTrace();
		}

		return menu;
		
	}
	
	/* Page Update*/
	
	
	public int updatePages(Menu menu) {
		

		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		int update=0;
		try {
			
			String query="UPDATE menu SET menu_title=?, menu_content=?, menu_images=? WHERE menu_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, menu.getMenuTitle());
			ps.setString(2, menu.getMenuContent());
			ps.setString(3, menu.getMenu_images());
			ps.setInt(4, menu.getMenuId());
			
			
			update=ps.executeUpdate();
			
			
		} catch (Exception e) {
			
			System.out.println("updatePages Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return update;
		
		
	}
	
	/* Page Update To Archive/Delete Status */
	
	public int archivePage(int pageId) {
		

		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		int update=0;
		try {
			
			String query="UPDATE menu SET menu_status='Archived' WHERE menu_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, pageId);
			
			
			update=ps.executeUpdate();
			
			
		} catch (Exception e) {
			
			System.out.println("archivePage Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return update;
		
		
	}
	
	
   /* Page delete*/
	
	
	public int deletePages(int id) {
		

		int delete=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		try {
			
			String query="DELETE FROM menu WHERE menu_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			delete=ps.executeUpdate();
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Dao_Post deletePages Fail  " +e.getMessage());
		}
		
		return delete;
		
	
	}
	
	
	
	

	
}
