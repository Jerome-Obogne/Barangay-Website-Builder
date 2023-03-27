package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Email;
import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_PostConcerns;
import com.entity.Logs;


@WebServlet("/Controller_PostConcerns2")
public class Controller_PostConcerns2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("messageAction");
		
		switch (action.toLowerCase()) {
		case "messagedata":
			
			messageReply(request,response);
			
			break;

		case "messagedelete":
			
			deleteMessage(request,response);
			break;
		default:
			break;
		}
		
		
	}

	
	
	
	protected void messageReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation = new Operation(); //All method used here
		Email mail=new Email();
		String subject="Brgy Website Builder Administrator Response";
		try {
			int messageId=Integer.parseInt(request.getParameter("messageId"));
			String email=request.getParameter("email");
			String message=request.getParameter("message");
			String submit= request.getParameter("messageSubmit");
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Reply";
			String actionAffected="Messages";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			if (operation.checkInput(email) || operation.checkInput(message)) {
				
				request.getSession().setAttribute("messageRep", "Input all fields");
				response.sendRedirect("Admin-MessagePage?id=" + messageId);
			}
			else {

				trail.audit_log(log);	 //execute audit log data
				mail.sendEmail(email, subject, message);
				request.getSession().setAttribute("messageRep1", "Message Reply Succesfully");
				response.sendRedirect("Admin-message");
				
				
			}
	
			
			
			
		} catch (Exception e) {
			
			System.out.println("Controller_PostConcerns2 messageReply method Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	
protected void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation = new Operation(); //All method used here
		Dao_PostConcerns  execution =new Dao_PostConcerns();
		try {
			int messageId=Integer.parseInt(request.getParameter("delete_id"));
			System.out.println(messageId + "this is the id");
			int softDelete= execution.deleteConcerns(messageId);
			
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Delete";
			String actionAffected="Messages";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			if (softDelete!=0) {
				trail.audit_log(log);	 //execute audit log data
				request.getSession().setAttribute("deleteMsg", "Delete Message Succesfully");
				response.sendRedirect("Admin-message");
			}
			else {
				response.getWriter().print("NOT DELETED");
			}
			
				
				
	
	
			
			
			
		} catch (Exception e) {
			
			System.out.println("Controller_PostConcerns2 deleteMessage method Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
}
