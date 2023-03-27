package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_View;
import com.entity.Post;


@WebServlet("/Controller_ViewOnly")
public class Controller_ViewOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
int id=Integer.parseInt(request.getParameter("id"));
		
		Dao_View view=new Dao_View();
		List<Post> list=view.viewOnlyPost(id);
	
		
		request.getSession().setAttribute("view", list);
		response.sendRedirect("Admin-view");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
