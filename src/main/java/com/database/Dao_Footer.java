package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.entity.FooterSetting;

public class Dao_Footer {

	
	public FooterSetting getFooterData(){
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
	
		FooterSetting data=null;
		

		try {
			String query="SELECT * FROM footer_setting";
			ps=con.prepareStatement(query);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				data=new FooterSetting(Integer.parseInt(rs.getString("footer_id")), rs.getString("footer_title_col1"),rs.getString("footer_paragraph_col1"),
				rs.getString("footer_title_col2"),rs.getString("footer_services1_col2"),rs.getString("footer_services2_col2"),rs.getString("footer_services3_col2"),
				rs.getString("footer_services4_col2"),rs.getString("footer_title_col3"),rs.getString("footer_contact1_col3"), rs.getString("footer_contact2_col3"),
				rs.getString("footer_contact3_col3"),rs.getString("footer_contact4_col3"));
				
			}
		
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Dao_Footer getFooterData Fail" + e.getMessage() );
		}
		
		return data;
		
		
		
	}
	
	public int updateFooterColumn1(FooterSetting footer) {
		
		int update = 0;
		Connection con = new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String query="UPDATE footer_setting SET footer_title_col1=?, footer_paragraph_col1=? WHERE footer_id=?";
			
			ps=con.prepareStatement(query);
			ps.setString(1, footer.getFooter_title());
			ps.setString(2, footer.getFooter_paragraph());
			ps.setInt(3, footer.getFooter_id());
			
			update=ps.executeUpdate();
			

		} catch (Exception e) {

			System.out.println("Dao_Footer updateFooterColumn1 Fail" + e.getMessage());
		}

		return update;

	}
	
	public int updateFooterColumn2(FooterSetting footer) {

		int update = 0;
		Connection con = new CmsDatabase().database();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE footer_setting SET footer_title_col2=?, footer_services1_col2=?, footer_services2_col2=?, footer_services3_col2=?, footer_services4_col2=? WHERE footer_id=?";

			ps = con.prepareStatement(query);
			ps.setString(1, footer.getFooter_title_col2());
			ps.setString(2, footer.getFooter_serviceList1());
			ps.setString(3, footer.getFooter_serviceList2());
			ps.setString(4, footer.getFooter_serviceList3());
			ps.setString(5, footer.getFooter_serviceList4());
			ps.setInt(6, footer.getFooter_id());

			update = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println("Dao_Footer updateFooterColumn2 Fail" + e.getMessage());
		}

		return update;

	}
	
	
	public int updateFooterColumn3(FooterSetting footer) {

		int update = 0;
		Connection con = new CmsDatabase().database();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE footer_setting SET footer_title_col3=?, footer_contact1_col3=?, footer_contact2_col3=?, footer_contact3_col3=?, footer_contact4_col3=? WHERE footer_id=?";

			ps = con.prepareStatement(query);
			ps.setString(1, footer.getFooter_title_col2());
			ps.setString(2, footer.getFooter_serviceList1());
			ps.setString(3, footer.getFooter_serviceList2());
			ps.setString(4, footer.getFooter_serviceList3());
			ps.setString(5, footer.getFooter_serviceList4());
			ps.setInt(6, footer.getFooter_id());

			update = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println("Dao_Footer updateFooterColumn3 Fail" + e.getMessage());
		}

		return update;

	}
	
	
	
	
	
	
}
