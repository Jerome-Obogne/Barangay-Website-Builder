package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_AuditTrail;
import com.database.Dao_Category;
import com.entity.Logs;

@WebServlet("/Controller_AddCategory")
public class Controller_AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
//		String categoryName = request.getParameter("categoryTitle");

	String categoryName = request.getParameter("categoryName");
		Dao_Category execution = new Dao_Category();
		
		
		// Audit trail data
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String fullName = name + " "  + lastName;
		String actionName="Added";
		String actionAffected="Categories";
		Logs log =new Logs(fullName,role,actionName,actionAffected);
		
		
		
		
String msg="";
		int add = execution.addCategory(categoryName);

		if (add != 0) {
			
			trail.audit_log(log);	 //execute audit log data	
			msg="true";
		
			
		} else {
			//response.getWriter().write("Failed");
			msg="false";

		}
		response.getWriter().write(msg);
	}

}
