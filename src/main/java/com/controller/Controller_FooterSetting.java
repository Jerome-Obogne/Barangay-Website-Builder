package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Footer;
import com.entity.FooterSetting;
import com.entity.Logs;


@WebServlet("/Controller_FooterSetting")
public class Controller_FooterSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action=request.getParameter("footerAction");
		
		switch (action.toLowerCase()) {
		case "footerdata":
			   
			footerColumn1(request,response);
			break;

		case "footerdata1":
			 footerColumn2(request,response);
			break;
			
		case "footerdata2":
			 footerColumn3(request,response);
			break;
			
			
		default:
			break;
		}
		
		
	}
	
	
	protected void footerColumn1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Operation operation =new Operation();
		try {
			
			int footerId=Integer.parseInt(request.getParameter("footerId"));
			String footerTitle=request.getParameter("footerTitleCol1");
			String footerParagraph=request.getParameter("footerParagraph");
			
			
			FooterSetting footerData= new FooterSetting(footerId,footerTitle,footerParagraph);
			Dao_Footer execution=new Dao_Footer();
			
			if(operation.checkInput(footerTitle) || operation.checkInput(footerParagraph)) {
				request.getSession().setAttribute("msgFooter", "All Fields are required");
				response.sendRedirect("Admin-SiteSettings");
				
			}
			else {
				
				// Audit trail data
				Dao_AuditTrail trail=new Dao_AuditTrail();
				String name=request.getParameter("name");
				String lastName=request.getParameter("lastName");
				String role=request.getParameter("role");
				String fullName = name + " "  + lastName;
				String actionName="Update";
				String actionAffected="Site Setting Column 1";
				Logs log =new Logs(fullName,role,actionName,actionAffected);
				
				
				
				
				int update=execution.updateFooterColumn1(footerData);
				
				if (update!=0) {
			
					request.getSession().setAttribute("msgFooter1", "Succesfully Updated");
					 trail.audit_log(log);	 //execute audit log data	
					response.sendRedirect("Admin-SiteSettings");
					
					
				} else {

					request.getSession().setAttribute("msgFooter", "Fail to Update");
					response.sendRedirect("Admin-SiteSettings");
					
				}
				
			}
			
			
			
			
			
		}
		
		catch (Exception e) {
			System.out.println("footerColumn1 Controller Fail " + e.getMessage());
		e.printStackTrace();
		}
		
		
		
	}
	
	protected void footerColumn2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation =new Operation();
	try {
		
		int footerId=Integer.parseInt(request.getParameter("footerId"));
		String footerTitleCol2=request.getParameter("footerTitleCol2");
		String footerServiceList1=request.getParameter("footerServiceList1");
		String footerServiceList2=request.getParameter("footerServiceList2");
		String footerServiceList3=request.getParameter("footerServiceList3");
		String footerServiceList4=request.getParameter("footerServiceList4");
		
		
		
		FooterSetting footerData=new FooterSetting(footerId,footerTitleCol2,footerServiceList1,footerServiceList2,footerServiceList3,footerServiceList4);
		Dao_Footer execution=new Dao_Footer();
		
		if(operation.checkInput(footerTitleCol2) || operation.checkInput(footerServiceList1) || operation.checkInput(footerServiceList2)
				|| operation.checkInput(footerServiceList3) || operation.checkInput(footerServiceList4)) {
			request.getSession().setAttribute("msgFooter2", "All Fields are required");
			response.sendRedirect("Admin-SiteSettings");
		}
		else {
			
			// Audit trail data
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Update";
			String actionAffected="Site Setting Column 2";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			
			
			
			int update=execution.updateFooterColumn2(footerData);
			
			if (update!=0) {
				
				request.getSession().setAttribute("msgFooter_2", "Updated Succesfully");
				 trail.audit_log(log);	 //execute audit log data	
				response.sendRedirect("Admin-SiteSettings");
				
				
			} else {

				request.getSession().setAttribute("msgFooter2", "Fail to Update");
				response.sendRedirect("Admin-SiteSettings");
				
			}
			
		}
		
		
		
		
		
	}
	
	catch (Exception e) {
		System.out.println("footerColumn2 Controller Fail " + e.getMessage());
	e.printStackTrace();
	}
	
	
	
	
	
}
	
	protected void footerColumn3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation =new Operation();
	try {
		
		int footerId=Integer.parseInt(request.getParameter("footerId"));
		String footerTitleCol3=request.getParameter("footerTitleCol3");
		String footerContactList1=request.getParameter("footerContactList1");
		String footerContactList2=request.getParameter("footerContactList2");
		String footerContactList3=request.getParameter("footerContactList3");
		String footerContactList4=request.getParameter("footerContactList4");
		
		
		
		FooterSetting footerData=new FooterSetting(footerId,footerTitleCol3,footerContactList1,footerContactList2,footerContactList3,footerContactList4);
		Dao_Footer execution=new Dao_Footer();
		
		if(operation.checkInput(footerTitleCol3) || operation.checkInput(footerContactList1) || operation.checkInput(footerContactList2)
				|| operation.checkInput(footerContactList3) || operation.checkInput(footerContactList4)) {
			request.getSession().setAttribute("msgFooter3", "All Fields are required");
			response.sendRedirect("Admin-SiteSettings");
			
		}
		else {
			
			// Audit trail data
						Dao_AuditTrail trail=new Dao_AuditTrail();
						String name=request.getParameter("name");
						String lastName=request.getParameter("lastName");
						String role=request.getParameter("role");
						String fullName = name + " "  + lastName;
						String actionName="Update";
						String actionAffected="Site Setting Column 3";
						Logs log =new Logs(fullName,role,actionName,actionAffected);
						
						
			
			
			
			int update=execution.updateFooterColumn3(footerData);
			
			if (update!=0) {
				 trail.audit_log(log);	 //execute audit log data	
				request.getSession().setAttribute("msgFooter_3", "Update Succesfully");
				response.sendRedirect("Admin-SiteSettings");
				
				
			} else {

				request.getSession().setAttribute("msgFooter3", "Fail to Update");
				response.sendRedirect("Admin-SiteSettings");
				
			}
			
		}
		
		
		
		
		
	}
	
	catch (Exception e) {
		System.out.println("footerColumn3 Controller Fail " + e.getMessage());
	e.printStackTrace();
	}
	
	
	
	
	
}

}
