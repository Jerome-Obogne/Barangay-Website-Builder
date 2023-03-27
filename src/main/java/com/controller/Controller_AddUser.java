package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AddUser;
import com.database.Dao_AuditTrail;
import com.entity.Logs;
import com.entity.User;


@WebServlet("/Controller_AddUser")
public class Controller_AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Operation operation = new Operation();  //all operation method inside here....
			response.setContentType("text/html");
		
		
		try {
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String addr=request.getParameter("address");
			String sex=request.getParameter("sex");
			String dob=request.getParameter("dob");
			String password=request.getParameter("password");
			String confirmpass=request.getParameter("confirmPass");
			String role=request.getParameter("role");
			String status=request.getParameter("status");
			
			String msg="";
			
			System.out.println(role);
			
			
			
			if (operation.checkInput(username) || operation.checkInput(email) || operation.checkInput(firstName) ||
					operation.checkInput(lastName) || operation.checkInput(addr) || operation.checkInput(sex) ||
					operation.checkInput(dob) || operation.checkInput(password) || operation.checkInput(confirmpass) || operation.checkInput(role)) {
					
				
				msg="required"; //All field are required
				
				
			} else if (password.length()<8) {
				
				
				msg="passlength";   // 8 Characters or More
				
				
			}
			else if (!password.equals(confirmpass)) {
				
					msg="PassNotMatch";
				
			}else if (!operation.pattern(operation.regex_pass, password)) {
				
				msg="passRegex";   	//Need to contain special characters and Letters and numbers
			} else if(!operation.pattern(operation.owasp_email, email)) {
				msg="validEmail";    // Need Valid email;
			}
				
				else {
					 
					// hash the password First start here
					
					byte[]salt=operation.salt();
					String salt_value=operation.bytesTostringhex(salt);
					String hashpassword=operation.hashValue(password, salt_value);
					
					// hash the password First end here
			
					Dao_AddUser execution= new Dao_AddUser();
					User users=new User(username,email,firstName,lastName, addr, sex, dob,hashpassword,salt_value,role,status);
					
					
					// Audit trail data
					Dao_AuditTrail trail=new Dao_AuditTrail();
					String auditname=request.getParameter("name");
					String auditlastName=request.getParameter("lastName");
					String auditrole=request.getParameter("role1");
					String fullName = auditname + " "  + auditlastName;
					String actionName="Added";
					String actionAffected="User";
					Logs log =new Logs(fullName,auditrole,actionName,actionAffected);
					
					
					
					
					
					
					String validate=execution.createUser(username, email, users);
					
						if (validate.equals("Email")) {
							
							
							msg="Email";  //already exist
							
						} else if (validate.equals("Username")) {
							
							msg="Username"; //already exist
							
						} else if(validate.equals("Success")) {
							
							trail.audit_log(log);	 //execute audit log data	
							msg="Success"; // is done
							
						}
						else {
							
							msg="Error"; //already exist
							
						}
				
				
				

			}
			
			
			
			
			
			response.getWriter().write(msg);
			

			
		} catch (Exception e) {

			System.out.println("Controller_AddUser Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
