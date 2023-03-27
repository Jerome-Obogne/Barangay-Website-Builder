package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_View;
import com.entity.WebPost;


@WebServlet("/Controller_WebPageView")
public class Controller_WebPageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dao_View view=new Dao_View();			//fetc the data coming from database to display webpage view
		int postId=Integer.parseInt(request.getParameter("id"));
		
		List<WebPost> list=view.viewPagePost(postId);
		
		request.getSession().setAttribute("webView", list);
		response.sendRedirect("Viewpost");
		
		
	}
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
		System.out.println("done");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
		System.out.println("D0ne");
	}

}
