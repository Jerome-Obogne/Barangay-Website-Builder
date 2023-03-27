package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Post;
import com.entity.Logs;


@WebServlet("/Controller_PageDelete")
public class Controller_PageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation=new Operation();
		Dao_Post execution=new Dao_Post();

		int postId=Integer.parseInt(request.getParameter("delete_id"));
	
	
		
		
		/* -----  Audit Trail  Code Start Here ---- */
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String fullName = name + " "  + lastName;
		String actionName="Archived";
		String actionAffected="Page";
		Logs log =new Logs(fullName,role,actionName,actionAffected);
		
		/* -----  Audit Trail  Code End Here ---- */
		
		
		int archived=execution.archivePage(postId);
		
		if (archived!=0) {
		
			
			trail.audit_log(log); //execute audit log data
			response.sendRedirect("Admin-Page");
			
			
		} else {
			request.getSession().setAttribute("deleteMsg", "Fail to Delete");
			response.sendRedirect("Admin-Page");
		}
		
		
		
	}

}
