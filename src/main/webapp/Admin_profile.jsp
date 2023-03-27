<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page errorPage="error.jsp"%>
    <%@ page import=" com.entity.User, com.database.Dao_Users, java.lang.*" %>
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


                <a href="Admin-categories" class="list-group-item list-group-item-action bg-transparent fw-bold active">
                    <i class="fa-solid fa-sort"></i>Category
                </a>

                <a href="Admin-Page" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-table-columns"></i>Pages
                </a>

                <a href="Admin-draft" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold" data-bs-toggle="collapse" data-bs-target="#demo">
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







                <h3 class="post-text">Profile</h3>
                
				<%
				if (request.getSession().getAttribute("msgEditProfile") != null) {
				%>

				<p class="bg-danger p-2 text-white">${msgEditProfile}</p>
				<%
				request.getSession().removeAttribute("msgEditProfile1");
				}
				else if (request.getSession().getAttribute("msgEditProfile1") != null){
				%>
				<p class="bg-success p-2 text-white">${msgEditProfile1}</p>
				<%
				request.getSession().removeAttribute("msgEditProfile1");
				
				} %>

				<div class="form-container">

                    <form action="Controller_EditProfile" method="post" enctype="multipart/form-data">
							
							<input type="hidden" name="user_id" placeholder="userId" value="<%=user.getUserId()  %>">

 			   <!-- Audit trail of Data  Start here -->
              
            	     
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                


                        <div class="row mb-3">
                            <label for="inputEmail3" class=" col-sm-2 col-form-label">Username</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="" name="username" value="<%=user.getUsername() %>" autofocus >
                            </div>
                        </div>


                        <div class="row mb-3">
                            <label for="" class="col-lg-2 col-form-label">Email</label>

                            <div class="col-lg-6">
                                <input type=text  id="" placeholder="" class="form-control" name="email" value="<%=user.getEmail() %>" >

                            </div>

                        </div>

                        <div class="row mb-3">

                            <label for="" class=" col-lg-2 col-form-label">First Name</label>

                            <div class="col-lg-6">
                                <input type="text"  id="" placeholder="" class="form-control" name="firstName" value="<%=user.getFirstName()%>">

                            </div>



                        </div>

                        <div class="row mb-3">

                            <label for="" class=" col-lg-2 col-form-label">Last Name</label>

                            <div class="col-lg-6">
                                <input type="text"  id="" placeholder="" class="form-control" name="lastName" value="<%=user.getLastName()  %>">

                            </div>



                        </div>
                        
                        
                        <div class="row mb-3">

                            <label for="" class=" col-lg-2 col-form-label">Address</label>

                            <div class="col-lg-6">
                                <input type="text"  id="" placeholder="Calatagan, Virac Catanduanes" class="form-control" name="address" value="<%=user.getAddress()  %>">

                            </div>



                        </div>
                        
                        
                        
                        
                      
                        <div class="row mb-3">

                            <label for="" class="col-sm-2 col-form-label">BirthDay</label>


                            <div class="col-lg-6">

                                <input type="date"  id="" class="form-control" name="dob" value="<%=user.getDob() %>">


                            </div>


                        </div>

						<div class="row mb-3">

							<label for="" class="col-sm-2 col-form-label">Profile Picture</label>


							<div class="col-lg-6">
							
								   <div class="col-lg-6">

                                <img src="http://localhost:8080/CmsUploadImage/<%=user.getUser_image()%>" alt="" class="img-fluid rounded-2 img-thumbnail mb-2" height="100" width="100">
                                
								 <input class="form-control" type="file" name="file">
 								<input type="hidden" name="oldfile" id="" class="form-control" value="<%=user.getUser_image()%>">
                            </div>

								
							
							</div>


						</div>


                        <div class="mt-5">


                            <button type="submit" class="btn btn-sm  btn-dark" name="EditAction" value="adminEditProfile">
                                    Update Profile
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