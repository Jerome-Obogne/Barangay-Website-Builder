<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.*,com.database.Dao_View" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CDN BOOTSTRAP -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <!-- css links  -->


    <link rel="stylesheet" href="css/admin.css">


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



<%User user=(User)session.getAttribute("data4");%>



	<div class="d-flex" id="wrapper">

        <!-- SIDE BAR OFF CANVASS -->

        <div class="bg-dark text-white" id="sidebar-wrapper">

            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">

              
                <span><img src="image/logo7.png" alt="" class="img-fluid rounded-circle"></span>
                <p>BRGY WEB BUILDER</p>



            </div>

            <div class="list-group list-group-flush List-data">

                <a href="SuperAdmin" class="list-group-item list-group-item-action bg-transparent second-text active">
                    <i class="fa-solid fa-gauge"></i>Dashboard
                </a>
                <a href="#" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-list-check"></i>Manage Post
                </a>

                <a href="#" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-sort"></i>Category
                </a>

             
                <a href="#" class="list-group-item list-group-item-action bg-transparent fw-bold">
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
                    <p class="fs-4 m-0">Admin Panel</p>
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
                                <li><a class="dropdown-item" href="#">Edit Profile</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">


                <h3 class="post-text">DASHBOARD</h3>

                <div class="row g-3 my-2">

                    <div class="col-lg-4">


                        <div class="d-flex justify-content-around align-items-center p-3 bg-white Dashboard">
                            <i class="fa-regular fa-chart-bar fs-1"></i>
                            <div>


                                <h3 class="fs-5">Post Published</h3>

								<%!int data;%>
								<%
								Dao_View execution = new Dao_View();

								data = execution.totalPublish();
								%>

								<%
								if (data != 0) {
								%>

								<p class="fs-5"><%=data%></p>

								<%
								} else {
								%>
								<p class="fs-5"><%=data%></p>

								<%} %>

							</div>


                        </div>

                    </div>



                    <div class="col-lg-4">

                        <div class="d-flex justify-content-around align-items-center p-3 bg-white Dashboard">
                            <i class="fa-regular fa-chart-bar fs-1"></i>
                            <div>

                                <h3 class="fs-5">Categories</h3>
                              
                                <%data=execution.totalCategory(); %>
                                
                                
                             
								<%
								if (data != 0) {
								%>

								<p class="fs-5"><%=data%></p>

								<%
								} else {
								%>
								<p class="fs-5"><%=data%></p>

								<%} %>


                            </div>


                        </div>



                    </div>
                    <div class="col-lg-4">
                        <div class="d-flex justify-content-around align-items-center p-3 bg-white Dashboard">
                            <i class="fa-solid fa-users fs-1"></i>
                            <div>

                                <h3 class="fs-5">Users</h3>
                                
                                <%data=execution.totalUser(); %>
                                
                                	<%
								if (data != 0) {
								%>

								<p class="fs-5"><%=data%></p>

								<%
								} else {
								%>
								<p class="fs-5"><%=data%></p>

								<%} %>
                                
                                
                                
                                
                              

                            </div>



                        </div>
                        <div class="col-lg-3">


                        </div>




                    </div>



                </div>

            </div>


        </div>


























        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
     <script src="<%=request.getContextPath()%>/js/external.js"></script>







</body>


</html>