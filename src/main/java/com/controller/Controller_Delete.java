package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_AuditTrail;
import com.database.Dao_Update;
import com.entity.Logs;


@WebServlet("/Controller_Delete")
public class Controller_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
	
	
	}

		// use to update the data in Manage Post to archived/deleted status  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//		String file=request.getParameter("file");
		//		System.out.println(file);
		//		Dao_Delete execution=new Dao_Delete();
		
		//		int delete=execution.deleteData(postId);
		//		String uploadpath="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\CmsUploadImage\\"+file;
		
			Operation operation=new Operation();
			Dao_Update execution=new Dao_Update();  
			
			int postId=Integer.parseInt(request.getParameter("delete_id"));
	
			/* -----  Audit Trail  Code Start Here ---- */
			Dao_AuditTrail trail=new Dao_AuditTrail();
			String name=request.getParameter("name");
			String lastName=request.getParameter("lastName");
			String role=request.getParameter("role");
			String fullName = name + " "  + lastName;
			String actionName="Archived";
			String actionAffected="Post";
			Logs log =new Logs(fullName,role,actionName,actionAffected);
			
			/* -----  Audit TrailCode End Here ---- */

			int archived=execution.archiveData(postId);
			if (archived != 0) {

				// operation.deleteFile(uploadpath);

				trail.audit_log(log); // execute audit log data
				response.sendRedirect("Admin-managepost");

			} else {
				response.getWriter().print("NOT DELETED");
			}			

	}

}
