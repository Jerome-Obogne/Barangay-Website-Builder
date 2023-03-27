package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Category;
import com.entity.Logs;


@WebServlet("/Controller_CategoryDelete")
public class Controller_CategoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	// use to update the data in Category to archived/deleted status  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Operation operation=new Operation();
		Dao_Category execution= new Dao_Category();
		
		int catId=Integer.parseInt(request.getParameter("delete_id")); 	//get the parameter of category Id to update as an archived status
		

		
		/* -----  Audit Trail Code Start Here ----- */
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String fullName = name + " "  + lastName;
		String actionName="Deleted";
		String actionAffected="Category";
		Logs log =new Logs(fullName,role,actionName,actionAffected);
		
		/* -----  Audit Trail Code End Here ----- */
		

		int archived = execution.archiveCategory(catId);
		if (archived != 0) {
			trail.audit_log(log); // audit trail process
			response.sendRedirect("Admin-categories");
		} else {

			response.getWriter().print("Fail to Delete");

		}
		
		
		
	}

}
