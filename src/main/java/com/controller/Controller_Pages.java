package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
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
import com.entity.Menu;


@WebServlet("/Controller_Pages")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class Controller_Pages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action =request.getParameter("addPages");
			
		switch (action.toLowerCase()) {
		case "addpages":
			
				addPages(request,response);
				
			break;
			
		case "updatepages":
			
			updatePages(request,response);
			
		break;

		default:
			break;
		}
		
	}
	
	
	
	
	

	protected void addPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			Operation operation = new Operation();  //All methods are here
			
		
		try {
			
			String pageTitle= request.getParameter("pageTitle");
			String pageContent=request.getParameter("content");
			String pageAuthor=request.getParameter("author");
			
			int pageUser_id=Integer.parseInt(request.getParameter("user_id"));
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
			
			
			if (operation.checkInput(pageTitle) || operation.checkInput(pageContent) ||
					filepart==null) {
				
				operation.deleteFile(uploadpath);
				
					request.getSession().setAttribute("addPageMsg", "All field are required");
					response.sendRedirect("Admin-addpage");
				
			} 
			
			else {
				
				Menu menu= new Menu(pageTitle,pageContent,pageAuthor,new Timestamp(System.currentTimeMillis()),postImage,pageUser_id);
				Dao_Post execution=new Dao_Post();
				
				// Audit trail data
				Dao_AuditTrail trail= new Dao_AuditTrail();		//audit trail of action
				String name=request.getParameter("name");
				String role=request.getParameter("role");
				String fullName = name + " "  + pageAuthor;
				String actionName="Added";
				String actionAffected="Page";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
			
				
				int insert=execution.pageInsert(menu);
				
					if (insert!=0) {
						
						trail.audit_log(log);	//execute to insert audit data 
						request.getSession().setAttribute("addPageMsg1", "New Page has been added");
						response.sendRedirect("Admin-addpage");
	
						
					} else {

						System.out.println("FAIL");
						request.getSession().setAttribute("addPageMsg", "Fail to Insert");
						response.sendRedirect("Admin-addpage");
						
					}
				
				
				
				
			}
			
			
			

			
		} catch (Exception e) {
			System.out.println("Controller_Pages Fail " + e.getMessage());
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
		
	}
	

	protected void updatePages(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Operation operation = new Operation();  //All methods are here
		
		try {
			
			int menuId=Integer.parseInt(request.getParameter("menuId"));
			String menuTitle=request.getParameter("menuTitle");
			String menuContent=request.getParameter("content");
			
			
			
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
			
				if (operation.checkInput(menuTitle) || operation.checkInput(menuContent) || filepart ==null) {
					
					
					operation.deleteFile(uploadpath);
					
					request.getSession().setAttribute("editPageMsg", "All field are required");
					response.sendRedirect("Admin-editpage?id="+ menuId);
				
					
					
				} else {
					
					String oldfile=request.getParameter("oldfile");
					String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
					
					// Audit trail data
					Dao_AuditTrail trail= new Dao_AuditTrail();		//audit trail of action
					String name=request.getParameter("name");
					String lastname=request.getParameter("lastName");
					String role=request.getParameter("role");
					String fullName = name + " "  + lastname;
					String actionName="Update";
					String actionAffected="Page";
					Logs log =new Logs(fullName,role,actionName,actionAffected);
					
					Menu menu=new Menu(menuId,menuTitle,menuContent,postImage);
					Dao_Post execution=new Dao_Post();	
					if (!operation.checkInput(postImage)) {    // if image file is not null delete the old image to replace it 
						
						
							operation.deleteFile(oldpath);
							
							int update=execution.updatePages(menu);
							
								if (update!=0) {
									
									trail.audit_log(log);		//execute audit log data
									request.getSession().setAttribute("editPageMsg", "Page updated");
									response.sendRedirect("Admin-Page");	
								
									
									
									
									
								} else {
									
									
									operation.deleteFile(uploadpath);
									
									request.getSession().setAttribute("editPageMsg", "Fail to Update");
									response.sendRedirect("Admin-editpage?id="+ menuId);
								
									
									

								}
					
							
						
						
						
					} else { // if file is null proceed to upload the previous file
						
							 
						Menu menu2=new Menu(menuId,menuTitle,menuContent,oldfile);
						int update=execution.updatePages(menu2);
							
							if(update!=0) {
								
								trail.audit_log(log);		//execute audit log data
								request.getSession().setAttribute("editPageMsg", "Page updated");
								response.sendRedirect("Admin-Page");	
							
								
							}
							
							else {
								
								request.getSession().setAttribute("editPageMsg", "Fail to Update");
								response.sendRedirect("Admin-editpage?id="+ menuId);
							
								
								
								
							}
						

					}
					
						
					
					

				}
			
			
			
			
			
		
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Controller_Pages updatePages Fail " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
