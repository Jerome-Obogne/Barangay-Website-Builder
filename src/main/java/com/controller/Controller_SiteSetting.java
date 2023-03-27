package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_AuditTrail;
import com.database.Dao_Settings;
import com.entity.Logs;
import com.entity.SiteSetting;


@WebServlet("/Controller_SiteSetting")
public class Controller_SiteSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			/* Start Here  --Parameter from SiteSetting -- */
			
			int siteId=Integer.parseInt(request.getParameter("siteId"));
			String siteTitle=request.getParameter("siteTitle");
			String siteTagline=request.getParameter("siteTagline");
			String siteMainTitle=request.getParameter("siteMainTitle");
			String siteButtonTitle=request.getParameter("siteButtonTitle");
			String siteButtonLinks=request.getParameter("siteButtonLinks");
			
			/* End Here  --Parameter from SiteSetting -- */
			
			SiteSetting setting=new SiteSetting(siteId,siteTitle,siteTagline,siteMainTitle ,siteButtonTitle,siteButtonLinks); // attribute use
			
			Dao_Settings execution=new Dao_Settings();	//create object for Dao_Settings
			
		
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Update";
			String actionAffected="Site Setting";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			
			
			
			
			
			int update=execution.siteUpdate(setting);		// method to update the site settings
			
			if(update!=0) {
				
				trail.audit_log(log);	 //execute audit log data	
				request.getSession().setAttribute("msgSite1", "Update Succesfully");
				response.sendRedirect("Admin-SiteSettings");
				
			}else {
				request.getSession().setAttribute("msgSite", "Fail to Update");
				response.sendRedirect("Admin-SiteSettings");
				response.getWriter().print("Fail");
				System.out.println("Fail");
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Controller_SiteSetting " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}
