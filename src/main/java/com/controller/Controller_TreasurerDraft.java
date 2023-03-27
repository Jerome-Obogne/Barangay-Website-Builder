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
import com.database.Dao_Secretary_CrudOperation;
import com.entity.Logs;
import com.entity.Secretary_Post;


@WebServlet("/Controller_TreasurerDraft")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)
public class Controller_TreasurerDraft extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String action=request.getParameter("editdraft");
		
		switch (action.toLowerCase()) {
		
		
		case "updatedraft":
				
				updateDraftForReview(request,response);
				
			break;
			
		case "draft":
					
			 	submitDraft(request,response);
			 	
			break;

		default:
			break;
		}
		
		
	}
	
	
	
	protected void updateDraftForReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Operation operation= new Operation();  // all method used in this program
		
		try {
			
			int draftId=Integer.parseInt(request.getParameter("postId"));
			String draftTitle=request.getParameter("postTitle");
			int  category=Integer.parseInt(request.getParameter("category"));
			String postStatus="Unpublish";
			String draftContent=request.getParameter("content");
			
			
			Part filepart=request.getPart("file");
			String postImage=filepart.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ postImage;
			
			
			
			try {

				FileOutputStream fos = new FileOutputStream(uploadpath);
				InputStream stream = filepart.getInputStream();
				byte[] data = new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();

			} catch (Exception e) {
				System.out.println(" Controller_UpdateDraft Problem fail in file upload \n" + e.getMessage());
				e.printStackTrace();
			}
			

			
			if (operation.checkInput(draftTitle) ||  operation.checkInput(draftContent) || operation.checkInput(postStatus) 
				||	filepart==null) {
				
				
				operation.deleteFile(postImage);
				request.getSession().setAttribute("msg_draft", "All field are required");
				System.out.println("TRUE");
				response.sendRedirect(request.getContextPath() +"/Treasurer-EditDraft?id=" + draftId);
					
				}
			
			else {
				
				
				
				String oldfile=request.getParameter("oldfile");
				String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
					
				// Audit trail data
				Dao_AuditTrail trail=new Dao_AuditTrail();
				String name=request.getParameter("name");
				String lastName=request.getParameter("lastName");
				String role=request.getParameter("role");
				String fullName = name + " "  + lastName;
				String actionName="Updated";
				String actionAffected="Draft";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
					
					
					Secretary_Post postData=new  Secretary_Post(draftId,draftTitle,postImage,postStatus,draftContent,category);
					Dao_Secretary_CrudOperation execution =new Dao_Secretary_CrudOperation();
					
					if (!operation.checkInput(postImage)) {
						operation.deleteFile(oldpath);   // if image file is not null delete the old image to replace it 
						
						int update=execution.updateDraftData(postData);
						
							if(update!=0) {
								
								trail.audit_log(log);	 //execute audit log data	
								request.getSession().setAttribute("msg_draft", "Update Succesful");
								response.sendRedirect("Treasurer-draft");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msg_draft", "Update Fail");
								System.out.println("3");
								response.sendRedirect(request.getContextPath() +"/Treasurer-EditDraft?id=" + draftId);
								
							}
						
						
						
						
						
						
					}
					else {
						
						Secretary_Post postData2=new  Secretary_Post(draftId,draftTitle,oldfile,postStatus,draftContent,category);
						int update=execution.updateDraftData(postData2);
							
							if (update!=0) {
								trail.audit_log(log);	 //execute audit log data	
								request.getSession().setAttribute("msg_draft", "Update Succesful");
								response.sendRedirect("Treasurer-draft");
							}
							else {
								request.getSession().setAttribute("msg_draft", "Update Fail in Draft");
							
								response.sendRedirect(request.getContextPath() +"/Treasurer-EditDraft?id=" + draftId);
								
							}
						
						
					}
					
					
					
				}
			
			
		} catch (Exception e) {
			System.out.println("Controller_SecretaryDraft updateDraftForReview fail" + e.getMessage());
			e.printStackTrace();
		}
		

		
		

	}

	protected void submitDraft(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	Operation operation= new Operation();  // all method used in this program
		
		try {
			
			int draftId=Integer.parseInt(request.getParameter("postId"));
			String draftTitle=request.getParameter("postTitle");
			int  category=Integer.parseInt(request.getParameter("category"));
			String postStatus="Draft";
			String draftContent=request.getParameter("content");
			
			
			Part filepart=request.getPart("file");
			String postImage=filepart.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ postImage;
			
			
			
			try {

				FileOutputStream fos = new FileOutputStream(uploadpath);
				InputStream stream = filepart.getInputStream();
				byte[] data = new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();

			} catch (Exception e) {
				System.out.println(" Controller_UpdateDraft Problem fail in file upload \n" + e.getMessage());
				e.printStackTrace();
			}
			

			
			if (operation.checkInput(draftTitle) ||  operation.checkInput(draftContent) || operation.checkInput(postStatus) 
				||	filepart==null) {
				
				
				operation.deleteFile(postImage);
				request.getSession().setAttribute("msg_draft", "All field are required");
				System.out.println("TRUE");
				response.sendRedirect(request.getContextPath() +"/Treasurer-EditDraft?id=" + draftId);
					
				}
			
			else {
				
				
				
				String oldfile=request.getParameter("oldfile");
				String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldfile;
					
				// Audit trail data
				Dao_AuditTrail trail=new Dao_AuditTrail();
				String name=request.getParameter("name");
				String lastName=request.getParameter("lastName");
				String role=request.getParameter("role");
				String fullName = name + " "  + lastName;
				String actionName="Updated";
				String actionAffected="Draft";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
					
					
					Secretary_Post postData=new  Secretary_Post(draftId,draftTitle,postImage,postStatus,draftContent,category);
					Dao_Secretary_CrudOperation execution =new Dao_Secretary_CrudOperation();
					
					if (!operation.checkInput(postImage)) {
						operation.deleteFile(oldpath);   // if image file is not null delete the old image to replace it 
						
						int update=execution.updateDraftData(postData);
						
							if(update!=0) {
								
								trail.audit_log(log);	 //execute audit log data	
								request.getSession().setAttribute("msg_draft", "Update Succesful");
								response.sendRedirect("Treasurer-draft");
							}
							else {
								
								operation.deleteFile(uploadpath);
								request.getSession().setAttribute("msg_draft", "Update Fail");
								
								response.sendRedirect(request.getContextPath() +"/Treasurer-EditDraft?id=" + draftId);
								
							}
						
						
						
						
						
						
					}
					else {
						
						Secretary_Post postData2=new  Secretary_Post(draftId,draftTitle,oldfile,postStatus,draftContent,category);
						int update=execution.updateDraftData(postData2);
							
							if (update!=0) {
								trail.audit_log(log);	 //execute audit log data	
								request.getSession().setAttribute("msg_draft", "Update Succesful");
								response.sendRedirect("Treasurer-draft");
							}
							else {
								request.getSession().setAttribute("msg_draft", "Update Fail in Draft");
							
								response.sendRedirect(request.getContextPath() +"/Secretary-EditDraft?id=" + draftId);
								
							}
						
						
					}
					
					
					
				}
			
			
		} catch (Exception e) {
			System.out.println("Controller_SecretaryDraft submitDraft fail" + e.getMessage());
			e.printStackTrace();
		}
		

		
		

		

	}
	
	
	
	
	
	

}
