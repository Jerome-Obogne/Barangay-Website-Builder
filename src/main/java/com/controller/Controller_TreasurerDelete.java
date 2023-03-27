package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Delete;
import com.entity.Logs;


@WebServlet("/Controller_TreasurerDelete")
public class Controller_TreasurerDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
Operation operation=new Operation();
		
		
		int postId=Integer.parseInt(request.getParameter("delete_id"));
		String file=request.getParameter("file");
		
		
		Dao_Delete execution=new Dao_Delete();
		
		
		// Audit trail data
		Dao_AuditTrail trail=new Dao_AuditTrail();
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String fullName = name + " "  + lastName;
		String actionName="Deleted";
		String actionAffected="Draft";
		Logs log =new Logs(fullName,role,actionName,actionAffected);
		

		
		
		
		
		int delete=execution.deleteData(postId);
		String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+file;
		if (delete!=0) {
			
			operation.deleteFile(uploadpath);
			trail.audit_log(log);	 //execute audit log data	
			response.sendRedirect("Treasurer-draft");
			
		} else {
			response.getWriter().print(" NOT DELETED");
		}
	}

}
