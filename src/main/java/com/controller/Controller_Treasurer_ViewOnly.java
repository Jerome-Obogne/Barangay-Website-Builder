package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_Secretary_Managepost;
import com.entity.Secretary_Post;


@WebServlet("/Controller_Treasurer_ViewOnly")
public class Controller_Treasurer_ViewOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	Dao_Secretary_Managepost execution=new Dao_Secretary_Managepost();
		
		int id=Integer.parseInt(request.getParameter("id"));
		 
		List<Secretary_Post> list= execution.viewSpecificPost(id);
	
		request.getSession().setAttribute("treasurer-view", list);
		response.sendRedirect("Treasurer-view");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
