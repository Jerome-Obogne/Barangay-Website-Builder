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
import com.database.Dao_UnPublish;
import com.entity.Post;


@WebServlet("/Controller_UpdateConfirmPost")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*50,      // 10MB
maxRequestSize=1024*1024*100)   // 50MB
public class Controller_UpdateConfirmPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			Operation operation=new Operation();			//all method use here
		try {
			int postId=Integer.parseInt(request.getParameter("postId"));
			String confirmpostTite=request.getParameter("postTitle");
			int category=Integer.parseInt(request.getParameter("category"));
			String postStatus=request.getParameter("postStatus");
			String confirmpostContent=request.getParameter("content");
			
			
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
				System.out.println(" Controller_UpdateDraft Problem fail in file upload \n" +e.getMessage());
				e.printStackTrace();
			}
			
			
				if (operation.checkInput(confirmpostTite)|| operation.checkInput(confirmpostContent) || operation.checkInput(postStatus) || 
						filepart==null) {
					
						operation.deleteFile(uploadpath); //delete file upload if all field are not input
						request.getSession().setAttribute("msgConfirmPost", "All field are required");
						response.sendRedirect(request.getContextPath() +"/Admin-editconfirmpost?id=" + postId);
					
				} else {
					
					String oldfile=request.getParameter("oldfile");
					String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
					
					Dao_UnPublish execution= new Dao_UnPublish();
					Post confirmPostData=new Post(postId,confirmpostTite, postImage, postStatus,confirmpostContent,category);
					
						if (!operation.checkInput(postImage)) {
								
								operation.deleteFile(oldpath);  // if image file is not null delete the old image to replace it
								
								int update=execution.updateConfirmPost(confirmPostData);
									
									if (update!=0) {		//Update Confirm Post to Publish or Unpublish
										
										request.getSession().setAttribute("msgConfirmPost", "<script> alert('Update Succesful') </script>");
										response.sendRedirect("Admin-confirmpost");
										
										
									} else {				//Fail to Update

										operation.deleteFile(uploadpath);
										request.getSession().setAttribute("msgConfirmPost", "Update Fail");
										response.sendRedirect(request.getContextPath() +"/Admin-editconfirmpost?id=" + postId);
									}
								
								
						} else {			//if file is nulll use the previous file to Update
							
							Post confirmPostData2=new Post(postId,confirmpostTite, oldfile, postStatus,confirmpostContent,category);
							int update=execution.updateConfirmPost(confirmPostData2);
							
								if (update!=0) {
									
									request.getSession().setAttribute("msgConfirmPost", "<script> alert('Update Succesful') </script>");
									response.sendRedirect("Admin-confirmpost");
									
								} else {

									request.getSession().setAttribute("msgConfirmPost", "Update Fail");
									response.sendRedirect(request.getContextPath() +"/Admin-editconfirmpost?id=" + postId);
								}
							
						}
					

				}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Controller_UpdateConfirmPost Fail "  + e.getMessage());
			e.printStackTrace();
		}
	
	}

}
