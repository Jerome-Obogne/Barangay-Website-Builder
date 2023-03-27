package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Menu;


public class Dao_Menu {

	
	
	public List<Menu> manageMenu(){
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Menu> list = new ArrayList<>();
		Menu display = null;
		
		
		try {
			String query="SELECT m.menu_id, m.menu_title, m.menu_content, m.menu_author,  DATE_FORMAT(m.menu_created,'%Y-%m-%d %h:%i:%p') AS menuDate,m.menu_updated, menu_images, u.user_id \r\n"
					+ "		FROM menu m INNER JOIN user u ON m.menu_user_id=u.user_id WHERE menu_status='Unarchived';";
			
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				
				display=new Menu(Integer.parseInt(rs.getString("menu_id")),rs.getString("menu_title"), rs.getString("menu_content"), rs.getString("menu_author"),
						rs.getString("menuDate"), rs.getString("menu_updated"), rs.getString("menu_images"), Integer.parseInt(rs.getString("user_id")));
				
				
				list.add(display);
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_Menu manageMenu Fail to Load" + e.getMessage());
		}
		
		return list;
		
		
		
	}
	
	//  Menu Bar List   in Front End Part 
	public List<Menu> listofMenu(){
		
		Connection con = new CmsDatabase().database();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Menu> list = new ArrayList<>();
		Menu display = null;
		
		try {
			
			String query="SELECT menu_id, menu_title FROM menu WHERE menu_status='Unarchived'";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				
				display= new Menu(Integer.parseInt(rs.getString("menu_id")), rs.getString("menu_title"));
				
				list.add(display);
				
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_Menu manageMenu Fail to Load" + e.getMessage());
		}
		
		return list;
		
		
		
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
