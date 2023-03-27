package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caller.Email;
import com.caller.Operation;
import com.database.Dao_ForgotPassword;
import com.database.Dao_ResetPassword;
import com.entity.User;


@WebServlet("/Controller_PasswordReset")
public class Controller_PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action =request.getParameter("forgotPasswordAction");		//button action
			
		switch (action.toLowerCase()) {
		case "forgotpassword":												//forgotPassword parameter value
				
				forgotPassword(request,response);
			
			break;

		case "resetpassword":
																			//resetPassword parameter value
				resetPassword(request,response);
				
			break;

		default:
			break;
		}
		
	}
	
	
	protected void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation = new Operation(); // all method used here
		Email mail = new Email();				//email api in java use to reset password
		Dao_ForgotPassword execution=new Dao_ForgotPassword();  // database forgot password	
		try {
			String email=request.getParameter("email");
			
			if (operation.checkInput(email)) {
				
				request.getSession().setAttribute("msgEmail", "Input Email Field");
				response.sendRedirect("Forgotpassword");
			}
			else {
				
				if(execution.checkEmail(email)) {		// if email is exist process to next step 
					
					execution.deleteData(email);		//delete the data in a table to avoid any duplication  if possible and then to insert after this delete
					
					java.sql.Timestamp inTime=new java.sql.Timestamp(new java.util.Date().getTime());
					Calendar cal=Calendar.getInstance();
					cal.add(Calendar.MINUTE, 3);
					java.sql.Timestamp expTime= new Timestamp(cal.getTime().getTime());
					
					String token=operation.bytesTostringhex(operation.salt());  // generate a token for reset password
					
					
					/*Create a link  to forgot password email*/
					
					String link="http://localhost:8081/Barangay-Web-Builder/Resetpassword";
					String subject="Barangay Website Builder Password Reset"; 
					String message="<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "\r\n"
							+ "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Document</title>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "</head>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "<body>\r\n"
							+ "    <section style=\" background-color: #ECEEF1; max-width: 700px; display: block; margin: 100px auto; padding: 20px 0px; text-align: center; border-radius: 5px; line-height: 1.5; box-sizing: border-box;\">\r\n"
							+ "\r\n"
							+ "        <div style=\"font-family: Georgia, 'Times New Roman', Times, serif;\">\r\n"
							+ "            <div class=\"resetpassbg\">\r\n"
							+ "\r\n"
							+ "                <div class=\"subjective-email\">\r\n"
							+ "                    <h1 class=\"title-logo\" style=\" margin-bottom: 30px;\r\n"
							+ "                    color:#00000; font-size: 2.5rem;\">[ Brgy. Website Builder ]</h1>\r\n"
							+ "                    <h3 style=\" font-weight: bold;\">Heads Up</h3>\r\n"
							+ "\r\n"
							+ "                </div>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "                <h3>Hello,</h3>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "                <p style=\" font-size: .9rem;  padding: 15px 0px;\r\n"
							+ "                    word-spacing: 1.5px;\">We've received a request to reset the password for the <b>Brgy Website Builder</b> <br> account associated with <b>"+ email +".</b> No changes <br> have been made to your account yet.</p>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "                <p style=\"font-size: 1rem;\">You can reset your password by clicking the link below:</p>\r\n"
							+ "\r\n"
							+ "                <button class=\" btn btn-primary\" style=\" margin: 20px 0px;  background-color: #000000; padding: 10px 40px; border:none; border-radius: 5px; font-size: 1.2rem; \"><a href="+ link +"?key=" + token + " style=\" color: white;\r\n"
							+ "                        text-decoration: none;\">Reset Password</a> </button>\r\n"
							+ "\r\n"
							+ "                <section style=\" font-size: .9rem;  padding: 15px 0px;\r\n"
							+ "                word-spacing: 1.5px;\">\r\n"
							+ "                    <p>If your didnt make this request, please disregard this email</p>\r\n"
							+ "\r\n"
							+ "                    <p>Please note that this link will expire within 10 minutes.</p>\r\n"
							+ "                    <p>After 10 mins, you must submit a new password reset request</p>\r\n"
							+ "\r\n"
							+ "                </section>\r\n"
							+ "\r\n"
							+ "                <h5><b>Need further Assistance?</b></h6>\r\n"
							+ "                    <a href=\"\" style=\" color: #0099ff;\">Contact Us</a>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "            </div>\r\n"
							+ "        </div>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "    </section>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "</body>\r\n"
							+ "\r\n"
							+ "</html>";
					
					
					User data=new User(email,token,inTime,expTime);
					int insert=execution.insertResetPasswordData(data);
					
						if (insert!=0) {
							mail.sendEmail(email, subject, message);
							request.getSession().setAttribute("msgEmail2", "Please Check Your Email and Click On The Provided Link to Reset Your Password");
							response.sendRedirect("Forgotpassword");
						}
						else {
							
							execution.deleteData(email);
							request.getSession().setAttribute("msgEmail", "Fail to Insert Data!! Please Check Your Email If Link is Provided or Not, If Link Is Broken Try Again");
							System.out.println("Fail to Insert Data");
							response.sendRedirect("Forgotpassword");
							
						}
					
					
					
					
					
				}
				else {
					
					request.getSession().setAttribute("msgEmail", "FAIL TO FIND");
					System.out.println("fail to find");
					response.sendRedirect("Forgotpassword");
					
					
				}
				
				
				
				
				
			}
			
			
			
			
		} 
		
		catch (Exception e) {
			System.out.println("forgotPassword method fail " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	protected void resetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email;
		Operation operation =new Operation();			//all methods use here
		Dao_ResetPassword execution=new Dao_ResetPassword(); // database reset password method
		Dao_ForgotPassword execute=new Dao_ForgotPassword();
		HttpSession session=request.getSession();
		ResultSet rs=null;
		try {
			String token=request.getParameter("key");			
			String password=request.getParameter("password");
			String confirmpass=request.getParameter("confirmPassword");
			
			java.sql.Timestamp currentTime=new java.sql.Timestamp(new java.util.Date().getTime());  //current time 
			
			rs=execution.checkToken(token);
			
			if (rs.next()) {														//check if token is existing
				email=rs.getString("email");
				java.sql.Timestamp exptime = rs.getTimestamp("expTime");
				
					if(currentTime.before(exptime)) {							//check if time is not expired then proceed to resetpasword 
						
						if (operation.checkInput(password) || operation.checkInput(confirmpass)) {	 //check if passwrod is not null
							
							System.out.println("All field are required in Reset Password");
							request.getSession().setAttribute("msgResetPass", "All field are required");
							response.sendRedirect("Resetpassword?key=" + rs.getString("token"));
							
							
						}
						else if(!password.equals(confirmpass)) {								//check if password  equals to confirmpassword
							System.out.println("Passwrod doesnt match");
							request.getSession().setAttribute("msgResetPass", "Password doesnt match");
							response.sendRedirect("Resetpassword?key="+ rs.getString("token"));
							
						}
						else if (!operation.pattern(operation.regex_pass, password)) {			//check the password using regex
							request.getSession().setAttribute("msgResetPass", "Need to contain special characters and Letters and numbers");
							response.sendRedirect("Resetpassword?key="+ rs.getString("token"));
						}
						else {
							
							String salt_value=operation.bytesTostringhex(operation.salt());			//generate salt for hashing password
							String hashpassword=operation.hashValue(password, salt_value);			//hash the password
							
							String validate=execution.updateResetPassword(email, hashpassword, salt_value);
							
							
								if (validate.equals("Success")) {									//check if update Account is Succesful
									
									session.setAttribute("success", "success");
									System.out.println("SUCCESSS");
									request.getSession().setAttribute("msgResetPass", "Reset Password Succesful");
									response.sendRedirect("Login");
									
				
								} else {
									System.out.println("FAIL");
									request.getSession().setAttribute("msgResetPass", "Fail to Change Pass'");
									response.sendRedirect("Resetpassword?key="+ rs.getString("token"));
								}
								
							
							
								
						}
						
						
					}
					else {
						System.out.println("SET TIME OF LINK EXPIRE");
						execute.deleteData(email); 			//delete the data inside data when link is expire
						request.getSession().setAttribute("msgResetPass", "<script> alert('SET TIME OF LINK EXPIRE TRY AGAIN TO RESET YOUR PASSWORD'); </script>");
						session.removeAttribute("success");
						response.sendRedirect("Welcome");
						
						
						
						
						
					}
				
				
				
			} else {
				System.out.println("TOKEN DOESNT MATCH TRY TO REACH THE LINK IN THE EMAIL PROVIDED");
				request.getSession().setAttribute("msgResetPass", "<script> alert('Token Doesnt Match Try to Reach the Link on the Email your provide'); </script>");
				response.sendRedirect("Welcome");
			}
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("resetPassword method fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
}
