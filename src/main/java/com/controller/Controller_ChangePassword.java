package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_ChangePassword;
import com.entity.Logs;


@WebServlet("/Controller_ChangePassword")
public class Controller_ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Controller_ChangePassword() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		adminChangePassword(request,response);
	
		
	
	}
	
	
	protected void adminChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Operation operation=new Operation();
			response.setContentType("text/html");
		try {
			
			int userId=Integer.parseInt(request.getParameter("userId"));
			String password=request.getParameter("password");
			String confirmPassword=request.getParameter("confirmPass");
			
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String auditname=request.getParameter("name");
			String auditlastName=request.getParameter("lastName");
			String auditrole=request.getParameter("role");
			String fullName = auditname + " "  + auditlastName;
			String actionName="Change Password";
			String actionAffected="Change password Page";
			Logs log =new Logs(fullName,auditrole,actionName,actionAffected);
			

			String msg="";
			if (operation.checkInput(password) || operation.checkInput(confirmPassword)) {
				
				msg="Required";
			}
			else if (password.length()<8) {
				
				msg="passlength";   // 8 Characters or More
				
			}
			else if (!password.equals(confirmPassword)) {
				
				msg="PassNotMatch";
			}
			else if (!operation.pattern(operation.regex_pass, password)) {
				
				msg="passRegex";   	//Need to contain special characters and Letters and numbers
			}
			else {
				
				String salt_value=operation.bytesTostringhex(operation.salt());  //generate salt value for hashing password
				String hashpassword=operation.hashValue(password, salt_value);
				
				Dao_ChangePassword execution = new Dao_ChangePassword();
				
				int changepass=execution.changePassword(hashpassword, salt_value, userId);
				
				
					if (changepass!=0) {
						
						trail.audit_log(log);
						msg="Success";
					}
					else {
						
						msg="Fail";
						
						
						
					}
				
				
				
			}
			
			

			response.getWriter().write(msg);
			
			
		} catch (Exception e) {
			System.out.println("Controller_ChangePassword  adminChangePassword fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}


}
