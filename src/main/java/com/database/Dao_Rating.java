package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao_Rating {
	
	
	public double getAverageRating(int postId) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		double average=0;
		try {
			String query="SELECT round(AVG(rating),2) AS roundedAverage FROM post_rating WHERE rating_post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				average=rs.getDouble(1);
				System.out.println(average + " Rating ");
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("getAverageRating fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return average;
		
		
	}
	
public int  getAverageRatings(int postId) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int average=0;
		try {
			String query="SELECT round(AVG(rating),2) AS roundedAverage FROM post_rating WHERE rating_post_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				average=rs.getInt(1);
				System.out.println(average + " GetAverageRating in Integer Format");
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("getAverageRating fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return average;
		
		
	}
	
	
	
	public int ratings(int postId,int userId, int rating) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		ResultSet rs=null;
		int rate=0;
		try {
			String query2="INSERT INTO post_rating(userid,rating_post_id,rating) VALUES(?,?,?)";
			

			ps2=con.prepareStatement(query2);
			ps2.setInt(1, userId);
			ps2.setInt(2, postId);
			ps2.setInt(3, rating);
			
			rate=ps2.executeUpdate();
		
			
		
			
		} catch (Exception e) {
			System.out.println("ratings fail " + e.getMessage());
			e.printStackTrace();
		}
		
	 return rate;
		
		
	}



public int getRating(int postId, int userId) {
		
		Connection con=new CmsDatabase().database();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int ratings=0;
		try {
			String query="SELECT * FROM post_rating WHERE rating_post_id=? AND userid=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, userId);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				
				ratings=rs.getInt(4);
				System.out.println( " Get the Exact Ratings =  "  +  ratings);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("getAverageRating fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return ratings;
		
		
	}
	
	
	
	
	
	
	

}
