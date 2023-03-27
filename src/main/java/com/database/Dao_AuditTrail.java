package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Logs;

public class Dao_AuditTrail {

	
	
  public void audit_log (Logs log) {
	  
	  Connection con=new CmsDatabase().database();
	  PreparedStatement ps=null;
	  
		try {
			
			String query="INSERT INTO audit_trail (user,role,action_name,action_affected) VALUES(?,?,?,?)";
			ps=con.prepareStatement(query);
		
			ps.setString(1, log.getAuditUser());
			ps.setString(2, log.getAuditRole());
			ps.setString(3, log.getActionName());
			ps.setString(4, log.getAction_affected());
			
			ps.executeUpdate();
			

		} catch (Exception e) {

			System.out.println("Dao_AuditTrail audit_log fail " + e.getMessage());
			e.printStackTrace();

		}
	  
	  
  }
  
  public List <Logs> listOfAuditLogs(int start, int end){
	  
	  Connection con=new CmsDatabase().database();
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  Logs log=null;
	  List<Logs> list=new ArrayList<>();
	  
	  try {
		  
		  String query="SELECT audit_id, user,role,action_name,action_affected, DATE_FORMAT(action_datetime, '%Y-%m-%d %h:%i:%p') AS auditTime FROM audit_trail ORDER BY audit_id DESC LIMIT ?,?";
		  ps=con.prepareStatement(query);
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  rs=ps.executeQuery();
		  
		  while (rs.next()) {
			  
			  log=new Logs(Integer.parseInt(rs.getString("audit_id")), rs.getString("user"), rs.getString("role"),rs.getString("action_name")
					  ,rs.getString("action_affected"),rs.getString("auditTime"));
			  
			  list.add(log);
			  
			  
		  }
		  
		  

		} catch (Exception e) {
			System.out.println("Dao_AuditTrail listOfAuditLogs fail " + e.getMessage());
			e.printStackTrace();

		}
	  
	  return list;

  }
  
  public int totalCountofLogs() {

	  Connection con=new CmsDatabase().database();
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  int totalCount=0;
		try {
			String query="SELECT COUNT(*) AS totalRecord FROM audit_trail";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			
			
			while(rs.next()) {
				
				totalCount=rs.getInt(1);
				
				
			}
					
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao_AuditTrail totalCountofLogs fail " + e.getMessage());

		}
		
		
		return totalCount;
		
		
		
}
}
