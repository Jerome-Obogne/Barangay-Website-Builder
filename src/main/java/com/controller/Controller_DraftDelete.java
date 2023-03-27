package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Delete;
import com.entity.Logs;


@WebServlet("/Controller_DraftDelete")
public class Controller_DraftDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation=new Operation();
		Dao_Delete execution=new Dao_Delete();
		
		int postId=Integer.parseInt(request.getParameter("delete_id"));
		
		
		/* -----  Audit Trail  Code Start Here ---- */
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String fullName = name + " "  + lastName;
		String actionName="Archived";
		String actionAffected="Draft";
		Logs log =new Logs(fullName,role,actionName,actionAffected);
		/* -----  Audit Trail  Code Start Here ---- */
		
		
		int archived=execution.archiveData(postId);
		if (archived!=0) {
			
		
			trail.audit_log(log);	 //execute audit log data	
			response.sendRedirect("Admin-draft");
			
		} else {
			response.getWriter().print(" NOT DELETED");
		}
		
	}

}
