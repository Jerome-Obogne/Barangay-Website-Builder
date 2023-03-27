<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
     <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.* ,com.database.Dao_Secretary_Managepost, java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CDN BOOTSTRAP -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <!-- css links  -->


    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">


    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>


    <title>Barangay Website Builder</title>
</head>



<body>

<%




if(session.getAttribute("treasurer")==null){
	
	 response.sendRedirect("Welcome");
}
else{
	
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	

	
}
%>






<% User user=(User)session.getAttribute("treasurerData");%>	 <!-- User data using session -->


    <div class="d-flex" id="wrapper">

        <!-- SIDE BAR OFF CANVASS -->

        <div class="bg-dark text-white" id="sidebar-wrapper">

            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">

                <span><img src="image/logo7.png" alt="" class="img-fluid rounded-circle" height="50" width="50"></span>
                <p>BRGY WEB BUILDER</p>




            </div>

            <div class="list-group list-group-flush List-data">

             
                <a href="Treasurer" class="list-group-item list-group-item-action bg-transparent second-text">
                    <i class="fa-solid fa-gauge"></i>Dashboard
                </a>
                
                <a href="Treasure-managepost" class="list-group-item list-group-item-action bg-transparent fw-bold active " data-bs-toggle="collapse" data-bs-target="#demo">
                    <i class="fa-solid fa-list-check"></i>Manage Post <i class="fa-solid fa-caret-down"></i>
                </a>
                <ul class="postNav navbar-nav  collapse" id="demo">
                    <li class="nav-item">
                        <a href="Treasurer-managepost" class="nav-link">All Post</a>
                    </li>
                    <li class="nav-item">
                        <a href="Treasurer-addpost" class="nav-link">Add Post</a>
                    </li>
                    
                    

                </ul>




                <a href="Treasurer-draft" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="<%=request.getContextPath() %>/Welcome" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-regular fa-eye"></i>View Website
                </a>



            </div>





        </div>

        <!-- SIDEBAR ENDS HERE -->


        <div id="page-content-wrapper">

            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-4 m-0">Treasurer Panel</h2>
                </div>


                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navigation">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                             <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                               <img src="http://localhost:8080/CmsUploadImage/<%=user.getUser_image()%>" alt="" class="img-fluid rounded-circle sample">
                                <%=user.getUsername() %>
                            </a>
                           <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="Treasurer-profile">Edit Profile</a></li>
                                <li><a class="dropdown-item" href="Treasurer-changepassword">Change Password</a></li>
                               <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Treasurer_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">


                <h3 class="post-text">MANAGE POST VIEW</h3>
			
			
		
			
                <div class="form-container">

                    <div class="table-responsive">

		     <%
		     int count=0;
		     
		     Dao_Secretary_Managepost execution= new  Dao_Secretary_Managepost();    //Treasurer Data 
		     
		     	List<Secretary_Post> list =execution.viewSecretaryPost(user.getUserId());
		     
		     %>
					
                        <table class="table table-image">

                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Author</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Cover Image</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Status</th>
                                     <th scope="col">Date Created</th>
                                    <th scope="col">Date Published</th>
                                    <th scope="col">Action</th>


                                </tr>
                            </thead>
                        
                            <tbody>
                     
                     <%for (Secretary_Post post:list){
                    	 count++; %>
                     
                                <tr>
                                    <th scope="row"><%=count%></th>
                                 
                                    <td><%=post.getPost_author() %></td>
                                    <td><%=post.getPost_title() %></td>
                                  <td><img src="http://localhost:8080/CmsUploadImage/<%=post.getPost_image() %>" alt="Cover Image"  class="img-fluid img-thumbnail"> </td>
                                    <td><%=post.getCategoryName() %></td>
                                    <td><%=post.getPost_status() %></td>
                                    <td><%=post.getCreated() %></td>
                                    <td><%=post.getUpdated() %></td>
                                    <td>

                                        <div class="formBtn">
                                            <a href="Controller_Treasurer_ViewOnly?id=<%=post.getPostId() %>" class="btn btn-sm btn-primary">View</a>
                                            
                                             
                                         		
                                        </div>
                                    </td>


                                </tr>

						<%} %>


                            </tbody>

				







                        </table>









                    </div>













                </div>


            </div>


        </div>

</div>



        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js " integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM " crossorigin="anonymous "></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>



     <script src="<%=request.getContextPath()%>/js/external.js"></script>
   
  

</body>


</html>