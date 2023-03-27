package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.SiteSetting;

public class Dao_WebView {

	public List<SiteSetting> viewSite(){
		
		Connection con=new CmsDatabase().database();
		List<SiteSetting> list=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		SiteSetting settings=null;
		
		
		
		try {
			String query="SELECT * FROM site_setting";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				settings=new SiteSetting(Integer.parseInt(rs.getString("site_id")),rs.getString("site_title"), rs.getString("site_logo"),
						rs.getString("site_banner"), rs.getString("site_tagline"),rs.getString("site_mainImage"),rs.getString("site_mainTitle"),
						rs.getString("site_button_Title"), rs.getString("site_button_link"));
				
				list.add(settings);
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Dao_WebView viewSite method Problem");
			e.printStackTrace();
		}
		
		return list;
		
	} 
	
	
	
	
}
