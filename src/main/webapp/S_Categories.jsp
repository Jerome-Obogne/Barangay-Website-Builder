<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.*, com.database.*, java.lang.*" %>
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




if(session.getAttribute("admin")==null){
	
	 response.sendRedirect("Welcome");
}
else{
	
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	

	
}
%>

<%User user=(User)session.getAttribute("data");%>



 
    <div class="d-flex" id="wrapper">

        <!-- SIDE BAR OFF CANVASS -->

        <div class="bg-dark text-white" id="sidebar-wrapper">

            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">

                  <span><img src="image/logo7.png" alt="" class="img-fluid rounded-circle" height="50" width="50"></span>
                <p>BRGY WEB BUILDER</p>



            </div>

            <div class="list-group list-group-flush List-data">

                <a href="Admin" class="list-group-item list-group-item-action bg-transparent second-text">
                    <i class="fa-solid fa-gauge"></i>Dashboard
                </a>

                <a href="Admin-managepost" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-list-check"></i>Manage Post
                </a>


                <a href="Admin-categories" class="list-group-item list-group-item-action bg-transparent fw-bold active">
                    <i class="fa-solid fa-sort"></i>Category
                </a>

                <a href="Admin-Page" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-table-columns"></i>Pages
                </a>

                <a href="Admin-draft" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-user-group"></i>User
                </a>


                <a href="Admin-SiteSettings" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-gears"></i>Site Settings
                </a>

                <a href="Admin-message" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-envelope"></i>Messages
                </a>
                
                  <a href="Admin-AuditLogs" class="list-group-item list-group-item-action bg-transparent fw-bold" >
                   <i class="fa-solid fa-clock-rotate-left"></i>Audit Trails
                </a>

                <a href="Welcome" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-regular fa-eye"></i>View Website
                </a>



            </div>





        </div>

        <!-- SIDEBAR ENDS HERE -->


        <div id="page-content-wrapper">

            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-4 m-0">Admin Panel</h2>
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
                                <li><a class="dropdown-item" href="Admin-EditProfile">Edit Profile</a></li>
                                 <li><a class="dropdown-item" href="Admin-changepassword">Change Password</a></li>            
                                   <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">
   
                               

                <h3 class="post-text">Category View</h3>


                <div class="form-container">
                
                <p class="formMessages1 p-2 text-white  bg-success" style="display:none"></p>
                
                
                
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm">

                            <form action="Controller_AddCategory" method="post" id="myForm">
                                <div class="mb-3">
                             
                                
                                    <label for="" class="form-label">Add Category</label>
                                    <input type="text" name="categoryName" id="categoryName" required placeholder="Category" class="form-control">
                                    
                                       <!-- Audit trail of Data  Start here -->
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
              
                                    
                                    
                                    
                                      <p class="small text-danger mt-2" id="valid"></p>
                                    
                                </div>
                            <!--     <button type="submit" class="btn btn-sm btn-dark" onclick="addUser()">Add Category</button>
 -->						 <button type="submit" class="btn btn-sm btn-dark">Add Category</button>
                            </form>


						<% if(request.getParameter("id")!=null){
							
							int categoryId=Integer.parseInt(request.getParameter("id"));
							
							Dao_Category execution= new Dao_Category();
							
							Category category=execution.getCategory(categoryId);
							
							
							%>
						


    						 <form action="Controller_UpdateCategory" method="post" id="myUpdateForm" class="mt-5">
                                <div class="mb-3">
                                    <label for="" class="form-label">Edit Category</label>
                                    <input type="text" name="categoryName" id="categoryUpdateName"  value="<%=category.getCategoryName() %>"   class="form-control">
                                     <input type="hidden" name="categoryId" id="" value="<%=category.getCategory_id()   %>"   placeholder="CategoryId">
                                   
                                   
                                  <!-- Audit trail of Data  Start here -->
                                  
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
             					  <!-- Audit trail of Data  End here -->
              
                                    
                                    
                                   
                                   
                                   
                                    <p class="small text-danger mt-2" id="valid"></p>
                                    
                                </div>
                            <!--     <button type="submit" class="btn btn-sm btn-dark" onclick="addUser()">Add Category</button>
 -->						 <button type="submit" class="btn btn-sm btn-dark">Edit Category</button>
                            </form>



<%} %>
                        </div>



                        <div class="col-lg-6 col-md-6 col-sm" id="displayTable">
                         
                        </div>












                    </div>
                </div>


            </div>


        </div>


























        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>




 <script>
 
 $(document).ready(function(e) {

   

         $.ajax({
             url: "viewTable.jsp",
     
             success: function(data, status) {
                 $('#displayTable').html(data);
            
             }
     
     });

 });
 

    </script>


<script>

$(document).ready(function() {

    $('#myForm').on('submit', function(event) {
        event.preventDefault();



        var f = $(this).serialize();


        $.ajax({

            url: "Controller_AddCategory",
            data: f,
            type: "POST",
            success: function(data, textStatus, jqXHR) {

                console.log(data);
                console.log("successss");
            
                if (data=='true'){
                	
                	
                	/*  $(".formMessages1").show();
	            	 $(".formMessages1").text("Added Succesfully");
	            	 setTimeout(function() { $(".formMessages1").hide(); }, 5000); */
                	
                	 $('#categoryName').val('');
                
               
                    $.ajax({
                        url: "viewTable.jsp",
                
                        success: function(data, status) {
                            $('#displayTable').html(data);
                            $('#myUpdateForm').hide();	
                        }



                 
                });

                	
                	
                }
                
                
                
                
                

            },
            error: function(jqXHR, textStatus, errorThrown) {


                console.log("E");
                console.log("errror");
            }




        })



    });




});





</script>

<!-- UPDATE CATEGORY USING AJAX -->
<script>

$(document).ready(function() {

    $('#myUpdateForm').on('submit', function(event) {
        event.preventDefault();



        var f = $(this).serialize();


        $.ajax({

            url: "Controller_UpdateCategory",
            data: f,
            type: "POST",
            success: function(data, textStatus, jqXHR) {

                console.log(data);
                console.log("successss");
            
                if (data=='true'){

                	  $('#myUpdateForm').hide();	
                	
                window.location.href = "Admin-categories";
                    $.ajax({
                        url: "viewTable.jsp",
                
                        success: function(data, status) {
                            $('#displayTable').html(data);
                            $('#myUpdateForm').hide();	
                        }



                 
                });

                	
                	
                }
                
                
                
                
                

            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.log(" Update errror");
            }




        })



    });




});



</script>

        <script>
            const offcanva = document.getElementById("wrapper");
            const toggleBtn = document.getElementById("menu-toggle");

            toggleBtn.onclick = function() {
                offcanva.classList.toggle("toggled");
                console.log("ey");


            }
        </script>



</body>


</html>