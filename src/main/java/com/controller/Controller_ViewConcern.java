package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_PostConcerns;
import com.entity.Concern;

@WebServlet("/Controller_ViewConcern")
public class Controller_ViewConcern extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int messageId = Integer.parseInt(request.getParameter("id"));
		
		Dao_PostConcerns execution=new Dao_PostConcerns();
		
		Concern userConcern=execution.userConcerns(messageId);
		request.getSession().setAttribute("userConcern", userConcern);
		response.sendRedirect("Admin-ViewMessage");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
