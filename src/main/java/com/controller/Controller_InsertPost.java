package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Post;
import com.entity.Logs;
import com.entity.Post;


@WebServlet("/Controller_InsertPost")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB

public class Controller_InsertPost extends HttpServlet {

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			Operation operation= new Operation();  // all method used in this program
			
		
			
			try {
				
				String postTitle= request.getParameter("postTitle");
				String postContent=request.getParameter("content");
				String postAuthor=request.getParameter("author");
				String postStatus=request.getParameter("postStatus");
				int postUser_id=Integer.parseInt(request.getParameter("user_id"));
				int postCategory_id=Integer.parseInt(request.getParameter("category"));
				Date now=new Date();
				java.sql.Date date=new java.sql.Date(now.getTime());				
				
				
				//--------Use in file upload START HERE-------
				
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
					System.out.println("Problem fail in file upload \n" +e.getMessage());
					e.printStackTrace();
				}
				
				//--------Use in file upload END HERE-------
				
				if (operation.checkInput(postTitle) || operation.checkInput(postAuthor) || operation.checkInput(postContent) || operation.checkInput(postStatus) ||
						filepart==null) {
					
					operation.deleteFile(uploadpath);
					
						request.getSession().setAttribute("insertMsg", "All field are required");
						response.sendRedirect("Admin-addpost");
					
				} else {
						
						Post data= new Post(postTitle,postContent,postAuthor,postImage,date,postStatus,postCategory_id,postUser_id);
						Dao_Post execution=new Dao_Post();
						
						// Audit trail data
						Dao_AuditTrail trail= new Dao_AuditTrail();		//audit trail of action
						String name=request.getParameter("name");
						String role=request.getParameter("role");
						String fullName = name + " "  + postAuthor;
						String actionName="Added";
						String actionAffected="Post";
						Logs log =new Logs(fullName,role,actionName,actionAffected);
					
					
						int insert=execution.postInsert(data);
						
						if (insert!=0) {
							
							
							trail.audit_log(log);	//execute to insert audit data 
							request.getSession().setAttribute("insertMsg2", "New post has been added");
							response.sendRedirect("Admin-addpost");
							
						} else {
							request.getSession().setAttribute("insertMsg", "Failed Insert");
							response.sendRedirect("Admin-addpost");
						}

				}
						
				
			} catch (Exception e) {
				System.out.println("Controller_InsertPost Fail " + e.getMessage());
			e.printStackTrace();
			}
		
		
	}

}
