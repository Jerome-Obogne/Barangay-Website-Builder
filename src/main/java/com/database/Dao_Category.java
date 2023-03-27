package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Category;
import com.entity.Post;
import com.entity.WebPost;

public class Dao_Category {

	
	public List<Category> categoryList() {		//list of Categories

		Connection con = new CmsDatabase().database();
		List<Category> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Category category=null;
//	Date now=new Date();
//	java.sql.Date date=new java.sql.Date(now.getTime());				
	
		try {
			 String query="SELECT * FROM category WHERE category_status='Unarchived'";
			 ps=con.prepareStatement(query);
			 rs=ps.executeQuery();
	
			 while (rs.next()) {
				
				 category=new Category(Integer.parseInt(rs.getString("category_id")),rs.getString("category_name"));
				 list.add(category);
				
			}
			

		} 
		
		catch (Exception e) {
			
			System.out.println("Dao_Category categoryList  Fail " + e.getMessage() );
			e.printStackTrace();

		}

		return list;
	}
	
	public List<Category> getCategoryList(int id) {		//list of Categories inside add post

		Connection con = new CmsDatabase().database();
		List<Category> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Category category=null;
//	Date now=new Date();
//	java.sql.Date date=new java.sql.Date(now.getTime());				
	
		try {
			 String query="SELECT * FROM category WHERE category_id=?";
			 ps=con.prepareStatement(query);
			 ps.setInt(1, id);
			 rs=ps.executeQuery();
	
			 while (rs.next()) {
				
				 category=new Category(Integer.parseInt(rs.getString("category_id")),rs.getString("category_name"));
				 list.add(category);
				
			}
			

		} 
		
		catch (Exception e) {
			
			System.out.println("Dao Category getCategoryList Method Fail " + e.getMessage() );
			e.printStackTrace();

		}

		return list;
	}
	
	
	
	public List<Category> listOfCategories() {		//list of Categories not included the default uncategories

		Connection con = new CmsDatabase().database();
		List<Category> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Category category=null;
//	Date now=new Date();
//	java.sql.Date date=new java.sql.Date(now.getTime());				
	
		try {
			 String query="SELECT * FROM category WHERE category_id>4 AND category_status='Unarchived' ";
			 ps=con.prepareStatement(query);
			 rs=ps.executeQuery();
	
			 while (rs.next()) {
				
				 category=new Category(Integer.parseInt(rs.getString("category_id")),rs.getString("category_name"));
				 list.add(category);
				
			}
			

		} 
		
		catch (Exception e) {
			
			System.out.println("Dao_Category listOfCategories  Fail " + e.getMessage() );
			e.printStackTrace();

		}

		return list;
	}
	
	
	
	
	
	
	
	
	
	/* DAO ADD CATEGORY*/
	
	
	public int addCategory(String categoryName) {
		Connection con = new CmsDatabase().database();
		PreparedStatement ps = null;
	
		int add=0;
		try {
			
			String query="INSERT INTO category(category_name) VALUES (?)";
			ps=con.prepareStatement(query);
			
			ps.setString(1, categoryName);
			
			add=ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Dao Category addCategory Method Fail " + e.getMessage() );
			e.printStackTrace();
		}
		
		return add;
	}
	
	
	public Category getCategory(int id) {        // getCategory for Update use in Super Admin 
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Category category=null;

		try {
			
			String query="SELECT * FROM category WHERE category_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {

				category = new Category(Integer.parseInt(rs.getString("category_id")), rs.getString("category_name"));

			}

		} catch (Exception e) {
			System.out.println("Dao Category getCategory Method Fail " + e.getMessage() );
			e.printStackTrace();
		}
		
		
		
		return  category;
		
	}
	
	
	public int updateCategory(int id, String categoryName) {				//Update Category table
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		 int update=0;
		 
		try {
			
			String query="UPDATE category SET category_name=? WHERE category_id=? ";
			ps=con.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setInt(2, id);
			
			update=ps.executeUpdate();

		} 
		
		catch (Exception e) {
			System.out.println("Dao Category updateCategory Method Fail " + e.getMessage() );
			e.printStackTrace();
			
		}
		
		return update;
		

	}
	
	/* DAO UPDATE CATEGORY TO ARCHIVE/DELETE STATUS*/
	
	public int archiveCategory(int id) {				//Update Category table to Archive/Unarchived
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		 int archived=0;
		 
		try {
			
			String query="UPDATE category SET category_status='Archived' WHERE category_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			
			archived=ps.executeUpdate();

		} 
		
		catch (Exception e) {
			System.out.println("Dao Category archiveCategory Method Fail " + e.getMessage() );
			e.printStackTrace();
			
		}
		
		return archived;
		

	}
	
	
	
	
	
public int deleteCategory(int id) {				//Delete Category table
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		 int delete=0;
		 
		try {
			
			String query="DELETE FROM category WHERE category_id=? ";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
		
			
			delete=ps.executeUpdate();

		} 
		
		catch (Exception e) {
			System.out.println("Dao Category deleteCategory Method Fail " + e.getMessage() );
			e.printStackTrace();
			
		}
		
		return delete;
		

	}
	
	

	
public List<Category> showCategoryList(int id) {		//show category list in edit post

	Connection con = new CmsDatabase().database();
	List<Category> list = new ArrayList<>();
	ResultSet rs = null;
	PreparedStatement ps = null;
	Category category=null;
//Date now=new Date();
//java.sql.Date date=new java.sql.Date(now.getTime());				

	try {
		 String query="SELECT * FROM category WHERE NOT category_id=? AND category_status='Unarchived'";
		 ps=con.prepareStatement(query);
		 ps.setInt(1, id);
		 rs=ps.executeQuery();

		 while (rs.next()) {
			
			 category=new Category(Integer.parseInt(rs.getString("category_id")),rs.getString("category_name"));
			 list.add(category);
			
		}
		

	} 
	
	catch (Exception e) {
		
		System.out.println("Dao_Category categoryList  Fail " + e.getMessage() );
		e.printStackTrace();

	}

	return list;
}

	
	

		/*Categories in Front End */


public List<WebPost> dropdownCategory(int id) {		//show category in front end

	Connection con = new CmsDatabase().database();
	List<WebPost> list = new ArrayList<>();
	WebPost display = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
//Date now=new Date();
//java.sql.Date date=new java.sql.Date(now.getTime());				

	try {
		String query = "SELECT p.post_id, p.post_image, p.post_title, p.post_content, p.post_author, p.post_updated  FROM cms.post p INNER JOIN category c ON p.category_id=c.category_id INNER JOIN user u ON p.user_id=u.user_id WHERE p.post_status='Publish' AND c.category_id=? AND datediff(NOW(),date(p.post_created))<=7 ORDER BY p.post_id DESC LIMIT 5";
		 ps=con.prepareStatement(query);
		 ps.setInt(1, id);
		 rs=ps.executeQuery();

		 while (rs.next()) {
			
				display = new WebPost(Integer.parseInt(rs.getString("post_id")), rs.getString("post_image"),
						rs.getString("post_title"), rs.getString(
								"post_content"), rs.getString("post_author"),
						rs.getDate("post_updated"));

				list.add(display);
			
		}
		

	} 
	
	catch (Exception e) {
		
		System.out.println("Dao_Category dropdownCategory  Fail " + e.getMessage() );
		e.printStackTrace();

	}

	return list;
}


	

	
	
	

}
