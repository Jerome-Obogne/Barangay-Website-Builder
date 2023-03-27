package com.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caller.Operation;
import com.database.Dao_PostConcerns;


@WebServlet("/Controller_PostConcern")
public class Controller_PostConcern extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Controller_PostConcern() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");  // set the datatype in ajax to html
		Operation operation =new Operation(); // all methods inside here
		try {
			String msg="";
			
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String message=request.getParameter("message");
			
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(message);
			
			
			if (operation.checkInput(name) || operation.checkInput(email) || operation.checkInput(message)) {
				
				msg="required";
			}
			else if (!operation.pattern(operation.owasp_email, email)) {
				
				msg="invalidEmail";
				
			}
			else {
				
				Dao_PostConcerns execution=new Dao_PostConcerns();  //  Database method to Insert In Post Concerns
				
				
				int insertConcern=execution.addConcerns(name, email, message);  // attribute for post concerns input;
				
				
				if (insertConcern!=0) {
					
					msg="success";
					
				}
				else {
					msg="Fail to Send Concerns";
				}
				
				
				
				
				
				
			}
			
			
			response.getWriter().print(msg);
			
			
			
		} catch (Exception e) {
		
			System.out.println("Controller_PostConcern Problem Fail");
		}
		
		
		
	}

}
