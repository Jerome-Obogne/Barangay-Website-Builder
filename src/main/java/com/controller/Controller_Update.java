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
import javax.servlet.http.Part;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Update;
import com.entity.Logs;
import com.entity.Post;


@WebServlet("/Controller_Update")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class Controller_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Controller_Update() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation= new Operation();  // all method used in this program
	
		try {
			int postId=Integer.parseInt(request.getParameter("postId"));
			String postTitle=request.getParameter("postTitle");
			int  category=Integer.parseInt(request.getParameter("category"));
			String postStatus=request.getParameter("postStatus");
			String postContent=request.getParameter("content");
			
			
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
				System.out.println(" Controller Update Problem fail in file upload \n" +e.getMessage());
				e.printStackTrace();
			}
			
			
			if (operation.checkInput(postTitle) ||  operation.checkInput(postContent) || operation.checkInput(postStatus) 
				||	filepart==null) {
				
				operation.deleteFile(postImage);
				request.getSession().setAttribute("msg_update", "All field are required");
				System.out.println("TRUE");
				response.sendRedirect(request.getContextPath() +"/Admin-editpost?id=" + postId);

			} else {
				
				String oldfile=request.getParameter("oldfile");
				String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
				
				Dao_Update execution= new Dao_Update();
				
				
				// Audit trail data
				Dao_AuditTrail trail=new Dao_AuditTrail();
				String name=request.getParameter("name");
				String lastName=request.getParameter("lastName");
				String role=request.getParameter("role");
				String fullName = name + " "  + lastName;
				String actionName="Updated";
				String actionAffected="Post";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
				
				
				
				
				
				
				
				Post postData= new Post(postId,postTitle,postImage,postStatus,postContent,category);
				
				
				if (!operation.checkInput(postImage)) {		
					
					operation.deleteFile(oldpath);  // if image file is not null delete the old image to replace it 
					

						int update=execution.updatePost(postData);
					
						if(update!=0) {
							trail.audit_log(log);		//execute audit log data
							request.getSession().setAttribute("msg_update", "Post updated");
							response.sendRedirect("Admin-managepost");	
						
						
						
						}else {
						
							operation.deleteFile(uploadpath);
							request.getSession().setAttribute("msg_update", "Update Fail and Check if your File Name is Not default.png");
							System.out.println("3");
							response.sendRedirect(request.getContextPath() +"/Admin-editpost?id=" + postId);
						}
					
				
				}
				else {				// if file is null proceed to upload the previous file
						Post postData2= new Post(postId,postTitle,oldfile,postStatus,postContent,category);
						int update=execution.updatePost(postData2);
						
						if(update!=0) {
							trail.audit_log(log);		//execute audit log data
							request.getSession().setAttribute("msg_update", "Post has been updated");
							response.sendRedirect("Admin-managepost");	
						
						
						
						}else {
						
							request.getSession().setAttribute("msg_update", "Update Fail");
							System.out.println("3");
							response.sendRedirect(request.getContextPath() +"/Admin-editpost?id=" + postId);
					}
					
					
					
				}
				
				
				
				
				
			}
			
			
		
			
		} catch (Exception e) {
			
			System.out.println("Controller_Update fail" + e.getMessage());
			e.printStackTrace();
		}
	
	}

}
