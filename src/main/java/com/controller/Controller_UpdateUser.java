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
import com.entity.User;


@WebServlet("/Controller_UpdateUser")
public class Controller_UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Operation operation = new Operation();  //all operation method inside here....
		response.setContentType("text/html");
		
		  try {
			  	int userId=Integer.parseInt(request.getParameter("user_id"));
				String username=request.getParameter("username");
				String email=request.getParameter("email");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String addr=request.getParameter("address");
				String sex=request.getParameter("sex");
				String dob=request.getParameter("dob");
				String password=request.getParameter("password");
				String role=request.getParameter("role");
				String status=request.getParameter("status");
				if (operation.checkInput(username) || operation.checkInput(email) || operation.checkInput(firstName)
						|| operation.checkInput(lastName) || operation.checkInput(addr) || operation.checkInput(sex)
						|| operation.checkInput(dob) || operation.checkInput(password) || operation.checkInput(role)) {
					
					request.getSession().setAttribute("msgUpdate", "All fields are Required");
					
					response.sendRedirect("Admin-updateuser?id="+ userId);

				}
				else if (password.length()<8) {
					
					request.getSession().setAttribute("msgUpdate", "Password Need 8 Characters or More");
					
					response.sendRedirect("Admin-updateuser?id="+ userId);

					
				}else if (!operation.pattern(operation.regex_pass, password)) {
					
					request.getSession().setAttribute("msgUpdate", "Need to contain special characters and Letters and numbers");
					
					response.sendRedirect("Admin-updateuser?id="+ userId);
					
					
					
				}else if (!operation.pattern(operation.owasp_email, email)) {
					
					request.getSession().setAttribute("msgUpdate", "Need Valid email");
					
					response.sendRedirect("Admin-updateuser?id="+ userId);
					
				}
				

				else {
					
					byte[]salt=operation.salt();  // generated a salt value to add for hashing the password
					String salt_value=operation.bytesTostringhex(salt);  // convert from byte to Stringhex 
					String hashPassword=operation.hashValue(password, salt_value); // convert the plain text password to hash password 
				
					
					Dao_Users execution= new Dao_Users();
					User user= new User(userId,firstName,lastName,sex,addr,dob,email,username,hashPassword,salt_value,role,status);
					
					// Audit trail data
					Dao_AuditTrail trail=new Dao_AuditTrail();
					String auditname=request.getParameter("name");
					String auditlastName=request.getParameter("lastName");
					String auditrole=request.getParameter("role1");
					String fullName = auditname + " "  + auditlastName;
					String actionName="Updated";
					String actionAffected="User";
					Logs log =new Logs(fullName,auditrole,actionName,actionAffected);
					
					int update=execution.updateUserData(user);
						
						if (update!=0) {
							
							trail.audit_log(log);	 //execute audit log data	
							request.getSession().setAttribute("msgUpdate", "Update Successful");
							System.out.println("updated");
							response.sendRedirect("Admin-All-users");
							
							
							
						} else {

							request.getSession().setAttribute("msgUpdate", "Update Fail");
							System.out.println("fail");
							response.sendRedirect("Admin-updateuser?id="+ userId);
								
							
							
						}

				}
			  
				
				
			  
			  
			  
			  
			
		} catch (Exception e) {
			
			System.out.println("Controller_Updateuser Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
