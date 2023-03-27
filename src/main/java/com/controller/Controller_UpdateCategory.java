package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_AuditTrail;
import com.database.Dao_Category;
import com.entity.Logs;


@WebServlet("/Controller_UpdateCategory")
public class Controller_UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");  // set the datatype in ajax to html
		try {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			String categoryName = request.getParameter("categoryName");

			Dao_Category execution = new Dao_Category();		//Update Category
			
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Edit";
			String actionAffected="Categories";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			
			
			int update=execution.updateCategory(categoryId, categoryName);
			String msg="";	
			if (update!=0) {
				
				
				
				
				trail.audit_log(log);		//execute audit log data
//				request.getSession().setAttribute("msgUpdate", "Update Succesful");
//				response.sendRedirect("SA-categories");
				
				msg="true";
//				
			} else {

//				request.getSession().setAttribute("msgUpdate", "Fail to Update");
//				response.sendRedirect("SA-categories");
//				
				msg="false";
			}
			
			response.getWriter().write(msg);
			
			
		} 
		catch (Exception e) {
		
			System.out.println("Controller_UpdateCategory Fail " + e.getMessage() );
			e.printStackTrace();
			
		}
		
		
	}

}
