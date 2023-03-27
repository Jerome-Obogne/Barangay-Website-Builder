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
import com.database.Dao_Settings;
import com.entity.Logs;



@WebServlet("/Controller_ImageUpdate")
@MultipartConfig

public class Controller_ImageUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action=request.getParameter("action");
		
		switch (action.toLowerCase()) {
		case "updateheader":

			updateHeader(request, response);

			break;

		case "updatesitelogo":

			updateSiteLogo(request, response);
			break;

		case "updatemainbackground":

			updateMainBackground(request, response);
			break;

		
			
		}
		
	}
	
	

	protected void updateHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Operation operation=new Operation();
		try {
		
			int siteId=Integer.parseInt(request.getParameter("siteId"));
			String oldheaderfile=request.getParameter("oldHeaderFile");   //oldheaderFile
			
			Part file=request.getPart("header");
			String headerFile=file.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ headerFile;
			
			try {
				
				FileOutputStream fos= new FileOutputStream(uploadpath);	
				InputStream stream=file.getInputStream();
				byte[]data=new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println("Problem fail in file upload UpdateHeader Method \n" +e.getMessage());
				e.printStackTrace();
			}
			
			Dao_Settings execution=new Dao_Settings();
			String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldheaderfile;
			
			

			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Update";
			String actionAffected="Site Setting";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			
			int update=0;
			if (!operation.checkInput(headerFile)) {
				
				operation.deleteFile(oldpath);
				 
				update=execution.updateHeader(siteId, headerFile);
				
				
				  if (update!=0) {
					  trail.audit_log(log);	 //execute audit log data	
					  response.sendRedirect("Admin-SiteSettings");
				  
				  } else {
				  
					  response.getWriter().print("Fail");
				  
				  }
				 
				
				
				
			} else {
				
				update=execution.updateHeader(siteId, oldheaderfile);
				
				 if (update!=0) {
					 trail.audit_log(log);	 //execute audit log data	
					 response.sendRedirect("Admin-SiteSettings");
				  
				  } else {
				  
					  response.getWriter().print("Fail");
				  
				  }
				
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("updateHeader servlet Method Fail" + e.getMessage());
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	protected void updateSiteLogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation=new Operation();
		try {
		
			int siteId=Integer.parseInt(request.getParameter("siteId"));
			String oldLogofile=request.getParameter("oldSiteLogoFile");  // oldlogoFile
			
			Part file=request.getPart("siteLogo");
			String siteFile=file.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ siteFile;
			
			try {
				
				FileOutputStream fos= new FileOutputStream(uploadpath);	
				InputStream stream=file.getInputStream();
				byte[]data=new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println("Problem fail in file upload UpdateSiteLogo \n" +e.getMessage());
				e.printStackTrace();
			}
			
			Dao_Settings execution=new Dao_Settings();
			String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldLogofile ;
			
			
			
			// Audit trail data
						Dao_AuditTrail trail=new Dao_AuditTrail();
						String name=request.getParameter("name");
						String lastName=request.getParameter("lastName");
						String role=request.getParameter("role");
						String fullName = name + " "  + lastName;
						String actionName="Update";
						String actionAffected="Site Setting";
						Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			int update=0;
			
			if (!operation.checkInput(siteFile)) {
				
				operation.deleteFile(oldpath);
				 
				update=execution.updateSiteLogo(siteId, siteFile);
				
				
				  if (update!=0) {
					  trail.audit_log(log);	 //execute audit log data	
					  response.sendRedirect("Admin-SiteSettings");
				  
				  } else {
				  
					  response.getWriter().print("Fail UploadSiteLogo");
					  
				  }
				 
				
				
				
			} else {
				
				update=execution.updateSiteLogo(siteId, oldLogofile);
				
				 if (update!=0) {
					 trail.audit_log(log);	 //execute audit log data	
					 response.sendRedirect("Admin-SiteSettings");
				  } else {
				  
					  response.getWriter().print("Fail UploadSiteLogo");
				  
				  }
				
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("updateSiteLogo servlet Method Fail" + e.getMessage());
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	
protected void updateMainBackground(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation=new Operation();
		try {
		
			int siteId=Integer.parseInt(request.getParameter("siteId"));
			String oldMainBackground=request.getParameter("oldMainBackgroundFile");  // oldMainBackgroundFile
			
			Part file=request.getPart("mainBackground");
			String siteMainImage=file.getSubmittedFileName();
			String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ siteMainImage;
			
			try {
				
				FileOutputStream fos= new FileOutputStream(uploadpath);	
				InputStream stream=file.getInputStream();
				byte[]data=new byte[stream.available()];
				stream.read(data);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println("Problem fail in file upload UpdateSiteLogo \n" +e.getMessage());
				e.printStackTrace();
			}
			
			Dao_Settings execution=new Dao_Settings();
			String oldpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+ oldMainBackground ;
			
			// Audit trail data
						Dao_AuditTrail trail=new Dao_AuditTrail();
						String name=request.getParameter("name");
						String lastName=request.getParameter("lastName");
						String role=request.getParameter("role");
						String fullName = name + " "  + lastName;
						String actionName="Update";
						String actionAffected="Site Setting";
						Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			int update=0;
			
			if (!operation.checkInput(siteMainImage)) {
				
				operation.deleteFile(oldpath);
				 
				update=execution.updateMainBackground(siteId, siteMainImage);
				
				
				  if (update!=0) {
					  trail.audit_log(log);	 //execute audit log data	
					  response.sendRedirect("Admin-SiteSettings");
				  
				  } else {
				  
					  response.getWriter().print("Fail UploadSiteLogo");
				  
				  }
				 
				
				
				
			} else {
				
				update=execution.updateMainBackground(siteId, oldMainBackground);
				
				 if (update!=0) {
					 trail.audit_log(log);	 //execute audit log data	
					 response.sendRedirect("Admin-SiteSettings");
				  
				  } else {
				  
					  response.getWriter().print("Fail UploadSiteLogo");
				  
				  }
				
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("updateMainBackground servlet Method Fail" + e.getMessage());
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	
	
	

}
