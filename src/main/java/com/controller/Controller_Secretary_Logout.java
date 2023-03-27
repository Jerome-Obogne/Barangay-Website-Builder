package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.Dao_AuditTrail;
import com.entity.Logs;
import com.entity.User;


@WebServlet("/Controller_Secretary_Logout")
public class Controller_Secretary_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			HttpSession session=request.getSession(false);
		
		User user=(User)session.getAttribute("secretaryData");
		
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String fullName=user.getFirstName() +" "+  user.getLastName();
		String auditrole=user.getRole();
		String actionName="Logout User";
		String actionAffected="Logout";
		Logs log =new Logs(fullName,auditrole,actionName,actionAffected);
		
		
		
		if (session != null) {

			trail.audit_log(log);
			session.invalidate();

			response.sendRedirect(request.getContextPath() + "/Welcome");

		}
	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
