<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.*, java.util.*,com.database.* , java.sql.*" %> 

	 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
	  <!-- CDN BOOTSTRAP -->

    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">

    <!-- CDN BOOTSTRAP fontawesome -->

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <link rel="stylesheet" href="plugins/fontawesome-stars.css">

    <!-- css links  -->


    <link rel="stylesheet" href="css/style.css">


    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	 <script src="plugins/jquery.barrating.min.js"></script>
	 
	   <title>Barangay Website Builder</title>
    
 


</head>

<body>

	 <%!  int  req; %>
  <%
  
 
  
  if (request.getParameter("request")!=null){
	  
	  req=Integer.parseInt(request.getParameter("request"));
	  	
	  
  } %>
 
 
 <% Dao_Category data=new Dao_Category();
List<WebPost> list= data.dropdownCategory(req); %>

   
    <section class="container-md" id="article">

  <!--  Ratings STARTS HERE -->
    
    	    <script>
        $(document).ready(function() {
            $('.rating1').barrating({
                theme: 'fontawesome-stars',
           //     readonly: true, // make the rating ready-only?
                onSelect: function(value, text, event) {

                     
                    // Get element id by data-id attribute
                    var el = this;
                    var el_id = el.$elem.data('id');
                   
                    // rating was selected by a user
                    if (typeof(event) !== 'undefined') {

                        var split_id = el_id.split("_");
                        var postid = split_id[1]; // postid
                        var  sample=$('#rating_1'+ postid).val();
                        console.log(sample);
                        // AJAX Request
                        $.ajax({
                            url: 'Controller_Rating',
                            type: 'post',
                            data: {
                                postid: postid,
                                rating: value
                            },
                            success: function(data) {
                                // Update average
                             alert("You rate " + sample + " out of 5")
                             window.location.reload();
                            
                                var average = data;
                                $('#avgrating_1' + postid).text(average);
                            }
                        });
                    }
                }
            });


        });
    </script>
    
      <!--  Ratings ENDS HERE -->







   <%for (WebPost post:list){ %>
        <div class="row justify-content-around mb-4 contain">


            <div class="col-lg-6 col-md-6 col-sm-12">


                <div class="article-main">
                    <div class="limit">
                        <img src="http://localhost:8080/CmsUploadImage/<%=post.getImage()  %>" alt="Cover Image" class="img-fluid">


                    </div>
                </div>



            </div>

            <div class="col-lg-6 col-md-6 col-sm-12">

                <div class="article-content text-break">


                   <h2><%=post.getPost_title() %></h2>
                    <div class="article-text"><%=post.getPost_content() %></div>
                    <div class="article-sub text-muted">
                        <span><i class="fa-regular fa-user"></i>By <%=post.getPost_author() %></span>
                        <span><i class="fa-regular fa-calendar-days"></i><%=post.getPost_date() %></span>
                    </div>
                    
                  <!-- Ratings START HERE -->  
                    
                  <div class="rating-action">

        <select class="rating1" id="rating_1<%=post.getPost_id()  %>" data-id='rating_1<%=post.getPost_id() %>'>
     
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select>



<%Dao_Rating rating =new Dao_Rating();

int userid=4;

double averageRating=rating.getAverageRating(post.getPost_id());   // get the average rating
int value = rating.getAverageRatings(post.getPost_id());    //set the overall ratings in the star rating
int ratings=rating.getRating(post.getPost_id(),userid ); 
String average="No Rating Yet";



%>

        <div style='clear: both;'></div>
       <p class="text-muted"> Average Rating : <span id='avgrating_1<%=post.getPost_id() %>'><%=averageRating%></span> </p>


        <!-- Set rating -->
        <script type='text/javascript'>
            $(document).ready(function() {
                $('#rating_1<%=post.getPost_id()  %>').barrating('set', <%=value%>);
            });
        </script>
   					
   				 </div>
                    
                     <!-- Ratings ENDS HERE -->  


                    <div class="float-end ">
                        <a href="Controller_WebPageView?id=<%=post.getPost_id() %>" class="btn btn-sm btn-dark my-3 ">View Details</a>
                    </div>
                </div>

            </div>







        </div>
<%} %>
       





    </section>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
	
       </body>
       
       
</html>
   