package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dao_Rating;


@WebServlet("/Controller_Rating")
public class Controller_Rating extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Controller_Rating() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			response.setContentType("text/html");  // set the datatype in ajax to html
			
			int userid=4;
			int postId=Integer.parseInt(request.getParameter("postid"));
			int ratings=Integer.parseInt(request.getParameter("rating"));
			
			
			System.out.println(postId + " Post Id of the Post");
			System.out.println(ratings + " Rating in the Post");
			
			
			Dao_Rating execution=new Dao_Rating();
			
			int updateRating=execution.ratings(postId, userid, ratings);
			double displayStarRating=0;
			 displayStarRating=execution.getAverageRating(postId);  // get the Average rating of the Post
			 String msg="fail";
			if (updateRating!=0) {
				
				response.getWriter().print(displayStarRating);    // print the average Rating of the Post
					
			} else {
				
				response.getWriter().print(msg);				//Print Message Fail
			}
			
		
			System.out.println("The Average Rating of the Post in Controller_Ratings  is = " + displayStarRating );
			
			
		} catch (Exception e) {
			
			System.out.println("Controller_Rating Fail " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
	}

}
