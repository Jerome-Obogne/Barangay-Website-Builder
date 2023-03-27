package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_EditProfile;
import com.entity.Logs;
import com.entity.User;


@WebServlet("/Controller_EditProfile")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class Controller_EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("EditAction");
		
		switch (action.toLowerCase()) {
		case "admineditprofile":

			adminEditProfile(request, response);

			break;

		case "secretaryeditprofile":

			secretaryEditProfile(request, response);
			
			break;
			

		case "treasurereditprofile":

			treasurerEditProfile(request, response);
			
			break;	
			
			
			
		}
	}
	
	
	
	
	protected void adminEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Operation operation= new Operation();			// all method used here
			Dao_EditProfile execution= new  Dao_EditProfile();
			HttpSession session=request.getSession();
		try {
			int userId=Integer.parseInt(request.getParameter("user_id"));
		
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String addr=request.getParameter("address");
			String dob=request.getParameter("dob");
			String role=request.getParameter("role");
		
			
			Part filepart=request.getPart("file");
			String postImage=filepart.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ postImage;
			
			try {
				
				FileOutputStream fos= new FileOutputStream(uploadpath);	
				InputStream stream=filepart.getInputStream();
				byte[]data=new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println("  Controller_EditProfile Problem fail in file upload \n" +e.getMessage());
				e.printStackTrace();
			}
			
			
		
			if (operation.checkInput(username) || operation.checkInput(email) || operation.checkInput(firstName) || operation.checkInput(lastName)
					|| operation.checkInput(addr) || operation.checkInput(dob) ) {
				
				operation.deleteFile(uploadpath);
				request.getSession().setAttribute("msgEditProfile", "All Field are Required");
				response.sendRedirect("Admin-EditProfile");
				
			}
			else {
				
				User user= new User(userId,email,firstName,lastName,addr,dob,username,postImage);
				String oldfile=request.getParameter("oldfile");
				String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
				
				
				// Audit trail data
				Dao_AuditTrail trail=new Dao_AuditTrail();
				
				String fullName = firstName + " "  + lastName;
				String actionName="Update";
				String actionAffected="Profile";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
				
				
				
				
				
				
					if(!operation.checkInput(postImage)) {    // if image is not null deelete the old file to add the new File
						
						operation.deleteFile(oldpath);
						
						
						
						int update=execution.updateEditProfile(user);
						
						
						if (update!=0) {
							session.setAttribute("data", user);
							trail.audit_log(log);
							request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
							response.sendRedirect("Admin-EditProfile");
						}
						else {
							
							operation.deleteFile(uploadpath);
							request.getSession().setAttribute("msgEditProfile", "Fail to Update");
							response.sendRedirect("Admin-EditProfile");
							
						}
						
	
					}
					
					else {						// else  if  image is null  use the previous files
						

						User user2= new User(userId,email,firstName,lastName,addr,dob,username,oldfile);
						
						int update= execution.updateEditProfile(user2);
						
							if (update!=0) {
								session.setAttribute("data", user2);
								trail.audit_log(log);
								request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
								response.sendRedirect("Admin-EditProfile");
								
								
							}
							else {
								
								request.getSession().setAttribute("msgEditProfile", "Fail to Update");
								response.sendRedirect("Admin-EditProfile");
						
							}
						
					}
				
				
				
				
			}
				
			
		} catch (Exception e) {

			System.out.println("Controller_EditProfile fail" + e.getMessage());
		}
	}
	protected void secretaryEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation= new Operation();			// all method used here
		Dao_EditProfile execution= new  Dao_EditProfile();
		HttpSession session=request.getSession();
	try {
		int userId=Integer.parseInt(request.getParameter("user_id"));
	
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String addr=request.getParameter("address");
		String dob=request.getParameter("dob");
		String role=request.getParameter("role");
	
		
		Part filepart=request.getPart("file");
		String postImage=filepart.getSubmittedFileName();
		String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ postImage;
		
		try {
			
			FileOutputStream fos= new FileOutputStream(uploadpath);	
			InputStream stream=filepart.getInputStream();
			byte[]data=new byte[stream.available()];
			stream.read(data);
			fos.write(data);
			fos.close();
			
		} catch (Exception e) {
			System.out.println("  Controller_EditProfile Problem fail in file upload \n" +e.getMessage());
			e.printStackTrace();
		}
		
		
	
		if (operation.checkInput(username) || operation.checkInput(email) || operation.checkInput(firstName) || operation.checkInput(lastName)
				|| operation.checkInput(addr) || operation.checkInput(dob) ) {
			
			operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msgEditProfile", "All Field are Required");
			response.sendRedirect("Secretary_profile");
			
		}
		else {
			
			User user= new User(userId,email,firstName,lastName,addr,dob,username,postImage);
			String oldfile=request.getParameter("oldfile");
			String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
			
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			
			String fullName = firstName + " "  + lastName;
			String actionName="Update";
			String actionAffected="Profile";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			
				if(!operation.checkInput(postImage)) {    // if image is not null deelete the old file to add the new File
					
					
					
					
					
						if(!postImage.equals("default.png") && !oldfile.equals("default.png") ) {   
							
							//if file is not equals to default.png & old file is not default.png
							// if oldpath is not equals to  default.png process yung pagpalit ng file na buburain yung old file

							
							int update=execution.updateEditProfile(user);
							operation.deleteFile(oldpath);
							
							if (update!=0) {
								session.setAttribute("secretaryData", user);
								trail.audit_log(log);
								request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
								response.sendRedirect("Secretary_profile");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msgEditProfile", "Fail to Update");
								response.sendRedirect("Secretary_profile");
								
							}
						}
						else {         			//same process mag u update pa din  pero hindi mag de delete ng old files sa path nya         
							
							
							int update=execution.updateEditProfile(user);
							operation.deleteFile(oldpath);
							
							if (update!=0) {
								session.setAttribute("secretaryData", user);
								trail.audit_log(log);
								request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
								response.sendRedirect("Secretary_profile");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msgEditProfile", "Fail to Update");
								response.sendRedirect("Secretary_profile");
								
							}
							
							
						}
					
					
					
					
					

				}
				
				else {						// else  if  image is null  use the previous files
					

					User user2= new User(userId,email,firstName,lastName,addr,dob,username,oldfile);
					
					int update= execution.updateEditProfile(user2);
					
						if (update!=0) {
							session.setAttribute("secretaryData", user2);
							trail.audit_log(log);
							request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
							response.sendRedirect("Secretary_profile");
							
							
						}
						else {
							
							request.getSession().setAttribute("msgEditProfile", "Fail to Update");
							response.sendRedirect("Secretary_profile");
					
						}
					
				}
			
			
			
			
		}
			
		
	} catch (Exception e) {

		System.out.println("Controller_EditProfile fail" + e.getMessage());
	}
	
	}
	
protected void treasurerEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation= new Operation();			// all method used here
		Dao_EditProfile execution= new  Dao_EditProfile();
		HttpSession session=request.getSession();
	try {
		int userId=Integer.parseInt(request.getParameter("user_id"));
	
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String addr=request.getParameter("address");
		String dob=request.getParameter("dob");
		String role=request.getParameter("role");
	
		
		Part filepart=request.getPart("file");
		String postImage=filepart.getSubmittedFileName();
		String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ postImage;
		
		try {
			
			FileOutputStream fos= new FileOutputStream(uploadpath);	
			InputStream stream=filepart.getInputStream();
			byte[]data=new byte[stream.available()];
			stream.read(data);
			fos.write(data);
			fos.close();
			
		} catch (Exception e) {
			System.out.println("  Controller_EditProfile Problem fail in file upload \n" +e.getMessage());
			e.printStackTrace();
		}
		
		
	
		if (operation.checkInput(username) || operation.checkInput(email) || operation.checkInput(firstName) || operation.checkInput(lastName)
				|| operation.checkInput(addr) || operation.checkInput(dob) ) {
			
			operation.deleteFile(uploadpath);
			request.getSession().setAttribute("msgEditProfile", "All Field are Required");
			response.sendRedirect("Treasurer-profile");
			
		}
		else {
			
			User user= new User(userId,email,firstName,lastName,addr,dob,username,postImage);
			String oldfile=request.getParameter("oldfile");
			String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
			
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			
			String fullName = firstName + " "  + lastName;
			String actionName="Update";
			String actionAffected="Profile";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			
				if(!operation.checkInput(postImage)) {    // if image is not null deelete the old file to add the new File
					
					
					
					
					
						if(!postImage.equals("default.png") && !oldfile.equals("default.png") ) {   
							
							//if file is not equals to default.png & old file is not default.png
							// if oldpath is not equals to  default.png process yung pagpalit ng file na buburain yung old file

							
							int update=execution.updateEditProfile(user);
							operation.deleteFile(oldpath);
							
							if (update!=0) {
								session.setAttribute("treasurerData", user);
								trail.audit_log(log);
								request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
								response.sendRedirect("Treasurer-profile");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msgEditProfile", "Fail to Update");
								response.sendRedirect("Treasurer-profile");
								
							}
						}
						else {         			//same process mag u update pa din  pero hindi mag de delete ng old files sa path nya         
							
							
							int update=execution.updateEditProfile(user);
							operation.deleteFile(oldpath);
							
							if (update!=0) {
								session.setAttribute("treasurerData", user);
								trail.audit_log(log);
								request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
								response.sendRedirect("Treasurer-profile");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msgEditProfile", "Fail to Update");
								response.sendRedirect("Treasurer-profile");
							}
							
							
						}
					
					
					
					
					

				}
				
				else {						// else  if  image is null  use the previous files
					

					User user2= new User(userId,email,firstName,lastName,addr,dob,username,oldfile);
					
					int update= execution.updateEditProfile(user2);
					
						if (update!=0) {
							session.setAttribute("treasurerData", user2);
							trail.audit_log(log);
							request.getSession().setAttribute("msgEditProfile1", "Update Profile Succesfully");
							response.sendRedirect("Treasurer-profile");
							
							
						}
						else {
							
							request.getSession().setAttribute("msgEditProfile", "Fail to Update");
							response.sendRedirect("Treasurer-profile");
					
						}
					
				}
			
			
			
			
		}
			
		
	} catch (Exception e) {

		System.out.println("Controller_EditProfile treasurerEditProfile fail" + e.getMessage());
	}
}

}
