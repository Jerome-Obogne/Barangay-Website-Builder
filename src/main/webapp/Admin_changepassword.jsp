<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page errorPage="error.jsp"%>
    <%@ page import=" com.entity.User" %>
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
	if(session.getAttribute("admin") == null) {

			response.sendRedirect(request.getContextPath() + "/Login");
		}

		else {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");

			response.setDateHeader("Expires", -1);

		}
	%>




	<%
	User user = (User) session.getAttribute("data");
	%>


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


                <a href="Admin-categories" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-sort"></i>Category
                </a>

                <a href="Admin-Page" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-table-columns"></i>Pages
                </a>

                <a href="Admin-draft" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold  active" data-bs-toggle="collapse" data-bs-target="#demo">
                    <i class="fa-solid fa-user-group"></i>User <i class="fa-solid fa-caret-down"></i>
                </a>
                <ul class="postNav navbar-nav  collapse" id="demo">
                    <li class="nav-item">
                        <a href="Admin-All-users" class="nav-link">All User</a>
                    </li>
                    <li class="nav-item">
                        <a href="Admin-adduser" class="nav-link">Add User</a>
                    </li>

                </ul>

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
                                <li><a class="dropdown-item" href="<%=request.getContextPath() %>/Controller_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">


                <h3 class="post-text">Change Password</h3>


                <div class="form-container">
                
               
                        <p class="formMessages1 p-2 text-white  bg-success" style="display:none"></p>

                        <p class="formMessages2 p-2 text-white bg-danger" style="display:none"></p>

               

                    <form action="Controller_ChangePassword" method="post" id="changepasswordForm">
                    
                    
                    
                    
               <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                    
                      <input type="hidden" name="userId" value="<%=user.getUserId() %>" placeholder="User Id">
                    

                       <div class="row mb-3">

                            <label for="" class=" col-lg-2 col-form-label">Password</label>


                            <div class="col-lg-6">

                                <input type="password" name="password" id="password" class="form-control">

                            </div>


                        </div>

                        <div class="row mb-3">

                            <label for="" class=" col-lg-2 col-form-label">Confirm Password</label>


                            <div class="col-lg-6">

                                <input type="password" name="confirmPass" id="" class="form-control">

                            </div>


                        </div>







                        <div class="mt-5">


                            <button type="submit" class="btn btn-sm  btn-dark">
                                    Change Password
                                    </button>

                        </div>







                       

                       



                    </form>




                </div>



            </div>


        </div>


























        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script>
	 
	 $(document).ready(function() {

		    $('#changepasswordForm').on('submit', function(event) {
		        event.preventDefault();

		        var f = $(this).serialize();

		        $.ajax({

		        	url: "Controller_ChangePassword",
		            data: f,
		            type: "POST",
		            success: function(data, textStatus, jqXHR) {

	            		
		                console.log(data);
		                console.log("successss");
		            switch(data.toLowerCase()){
		            
		            	case 'required':

		            		
		            		 $(".formMessages2").show();
		            		$(".formMessages2").text("All fields are required");
		            		setTimeout(function() { $(".formMessages2").hide(); }, 5000);
		                	break;
		            	case 'passlength':

		            	
		            		
		            		 $(".formMessages2").show();
			            	 $(".formMessages2").text("Password should be 8 Characters or More");
			            	 setTimeout(function() { $(".formMessages2").hide(); }, 5000);
		                	break;
		                	
		            	case 'passnotmatch':

		            	
		            		
		            		 $(".formMessages2").show();
			            	 $(".formMessages2").text("Password doesnt match");
			            	 setTimeout(function() { $(".formMessages2").hide(); }, 5000);
		                	break;
		                	
		            	case 'passregex':

		            	
		            		 $(".formMessages2").show();
			            	 $(".formMessages2").text("Need to contain special characters and Letters and numbers");
			            	 setTimeout(function() { $(".formMessages2").hide(); }, 5000);
		                	break;
		                	
		          
		                	
		              	case 'success':

		            	
		            		 $(".formMessages1").show();
			            	 $(".formMessages1").text("Change Password Succesfully");
			            	 setTimeout(function() { $(".formMessages1").hide(); }, 5000);
		            		$('input').val('');
		            	
		            		
		                	break; 
		                	

		            
		            }
		            
	
		            },
		            error: function(jqXHR, textStatus, errorThrown) {

		                console.log("Error in ChangePassword");
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