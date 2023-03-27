package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_Delete;


@WebServlet("/Controller_ConfirmPostDel")
public class Controller_ConfirmPostDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("deleteAction");
		
		switch (action.toLowerCase()) {
		case "deleteconfirmpost":
					DeleteConfirmPost(request,response);
			break;

		default:
			break;
		}
		
		
		
	}

	protected void DeleteConfirmPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Operation operation=new Operation();	//all method used here
		try {
			
			int post_id=Integer.parseInt(request.getParameter("delete_id"));
			String file=request.getParameter("file");	// file name to be deleted
			Dao_Delete execution=new Dao_Delete();		//delete method in database;
			
			int delete=execution.deleteData(post_id);
			
			if (delete!=0) {
				  
				operation.deleteFile(file);
				response.sendRedirect("Admin-confirmpost");
			} else {
				
				request.getSession().setAttribute("confirmPostDel", "CANT DELETE DATA");
				response.sendRedirect("Admin-confirmpost");

			}
					
			
			
			
		} catch (Exception e) {
			System.out.println("Controller_ConfirmPostDel" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
}
