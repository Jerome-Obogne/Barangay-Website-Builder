package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Users;
import com.entity.Logs;


@WebServlet("/Controller_UserDelete")
public class Controller_UserDelete extends HttpServlet {
	
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Operation operation=new Operation();
			
		try {
			int userId=Integer.parseInt(request.getParameter("delete_id"));
			
			Dao_Users execution= new Dao_Users();
			

			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Deleted";
			String actionAffected="User";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			int delete=execution.deleteUser(userId);
			
			
			if (delete!=0) {
				trail.audit_log(log);	 //execute audit log data	
				request.getSession().setAttribute("msg_Del", "Delete Message Succesful");
				response.sendRedirect("Admin-All-users");
			}
			
			else {
				
				request.getSession().setAttribute("msg_Del", "Delete Fail");
				response.sendRedirect("Admin-All-users");
				
				
			}
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Controller_UserDelete" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}
