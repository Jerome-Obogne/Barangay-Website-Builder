package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.SiteSetting;

public class Dao_Settings {

	
	public SiteSetting data() {
		
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		SiteSetting settings=null;
		
		
		
		
		try {
			
			String query="SELECT * FROM site_setting";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				
				settings=new SiteSetting(Integer.parseInt(rs.getString("site_id")),rs.getString("site_title"), rs.getString("site_logo"),
						rs.getString("site_banner"), rs.getString("site_tagline"),rs.getString("site_mainImage"),rs.getString("site_mainTitle"),
						rs.getString("site_button_Title"), rs.getString("site_button_link"));
				
			
			}
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Setting  data method fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return settings;
	}
	
	
	public int siteUpdate(SiteSetting settings) {
		
		int update=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;

		
		try {
			String query="UPDATE site_setting SET site_title=?, site_tagline=?, site_mainTitle=?, site_button_Title=? , site_button_link=? WHERE site_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, settings.getSite_title());
			ps.setString(2, settings.getSite_tagline());
			ps.setString(3, settings.getSite_mainTitle());
			ps.setString(4, settings.getSite_buttonTitle());
			ps.setString(5, settings.getSite_buttonLink());
			ps.setInt(6, settings.getSite_id());
			update=ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.out.println("Setting  siteUpdate method fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
	}
	
	public int updateHeader(int siteId, String headerImage) {
		
		int update=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;

		
		try {
			String query="UPDATE site_setting SET site_banner=? WHERE site_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, headerImage);
			ps.setInt(2, siteId);
			
			update=ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.out.println("Setting  updateHeader method fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
	}
	
	public int updateSiteLogo(int siteId, String siteLogo) {
		
		int update=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;

		
		try {
			String query="UPDATE site_setting SET site_logo=? WHERE site_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, siteLogo);
			ps.setInt(2, siteId);
			
			update=ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.out.println("Setting  updateSiteLogo method fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
	}
	
	
public int updateMainBackground(int siteId, String mainBackgroundImage) {
		
		int update=0;
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;

		
		try {
			String query="UPDATE site_setting SET site_mainImage=? WHERE site_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, mainBackgroundImage);
			ps.setInt(2, siteId);
			
			update=ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			System.out.println("Setting  updateSiteLogo method fail" + e.getMessage());
			e.printStackTrace();
		}
		
		return update;
		
		
	}
	
	
	
}
