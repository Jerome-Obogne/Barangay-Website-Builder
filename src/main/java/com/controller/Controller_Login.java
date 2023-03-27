package com.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Login;
import com.entity.Logs;
import com.entity.User;

@WebServlet("/Controller_Login")
public class Controller_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			Operation operation = new Operation();			//All method use for operation
		    Dao_Login execution = new Dao_Login();			//Database execution
		    Dao_AuditTrail trail=new Dao_AuditTrail();		//Database for audit trail of action
		    ResultSet rs=null;								//ResultSet in Database
		    HttpSession session=request.getSession();  		//create session for user
		    String	role;
		try {

			
			String username = request.getParameter("user");
			String password = request.getParameter("pass");

			if (operation.checkInput(username) || operation.checkInput(password)) {

				request.getSession().setAttribute("msg", "*Input all field");
				response.sendRedirect("Login");

			} else {
				
				String saltValue = execution.getSalt(username);
				String hashPassword = operation.hashValue(password, saltValue);

				User user = new User(username, hashPassword);    //User Entity
			
				rs=execution.login(user);
				
				if (rs.next()) {
					
					role=rs.getString("role");
					
					String auditUser=rs.getString("first_name") + " " + rs.getString("last_name");
					String actionName="Login";
					String actionAffected="Login Page";
					
					
					switch (role) {
					case "Brgy. Captain":
							
							
							User data=new User(Integer.parseInt(rs.getString("user_id")), rs.getString("first_name"), rs.getString("last_name"),
									rs.getString("email"),rs.getString("user_image"), rs.getString("username") ,rs.getString("password"),
									rs.getString("salt_value"),rs.getString("role"),rs.getString("address"), rs.getString("date_of_birth"));
							
							session.setAttribute("data", data);
							session.setAttribute("admin", username);
							
							
							
							Logs log=new Logs(auditUser,role,actionName,actionAffected);		// Audit Log Entity	
							trail.audit_log(log);											//execute to insert audit Logs
							response.sendRedirect("Admin");
						break;

				

					case "Brgy. Secretary":
						
						String auditUser1=rs.getString("first_name") + " " + rs.getString("last_name");
						String actionName1="Login";
						String actionAffected1="Login Page";
						
						
						
						
						User data3=new User(Integer.parseInt(rs.getString("user_id")), rs.getString("first_name"), rs.getString("last_name"),
								rs.getString("email"),rs.getString("user_image"), rs.getString("username") ,rs.getString("password"),
								rs.getString("salt_value"),rs.getString("role"),rs.getString("address"), rs.getString("date_of_birth"));				
						

						session.setAttribute("secretaryData", data3);
						session.setAttribute("secretary", username);
						
						Logs log2=new Logs(auditUser,role,actionName,actionAffected);		// Audit Log Entity	
						trail.audit_log(log2);											//execute to insert audit Logs
						response.sendRedirect("Secretary");
						
						break;
				case "Brgy. Treasurer":
						
					User data4=new User(Integer.parseInt(rs.getString("user_id")), rs.getString("first_name"), rs.getString("last_name"),
							rs.getString("email"),rs.getString("user_image"), rs.getString("username") ,rs.getString("password"),
							rs.getString("salt_value"),rs.getString("role"),rs.getString("address"), rs.getString("date_of_birth"));				
					
						
						String auditUser2=rs.getString("first_name") + " " + rs.getString("last_name");
						String actionName2="Login";
						String actionAffected2="Login Page";
					

						session.setAttribute("treasurerData", data4);
						session.setAttribute("treasurer", username);
						
						Logs log3=new Logs(auditUser,role,actionName,actionAffected);		// Audit Log Entity	
						trail.audit_log(log3);					
						
						
						
						response.sendRedirect("Treasurer");
						
						break;
					}
					
				
					
					
				} else {
					
					request.getSession().setAttribute("msg", "*Username and Password is incorrect");
					session.setAttribute("usernameError", username);
					response.sendRedirect("Validate");

				}
				
				
				
			}
		} 
		
		catch (Exception e) {
		   	System.out.println("Controller_Login Fail to Execute " + e.getMessage());
		   	e.printStackTrace();
		}

	}

}
