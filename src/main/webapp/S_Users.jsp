<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page errorPage="error.jsp"%>
    <%@ page import=" com.entity.User , com.database.Dao_Users, java.util.*" %>
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
	if (session.getAttribute("admin") == null) {

		response.sendRedirect("Welcome");
	} else {

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


                <a href="Admin-categories" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-sort"></i>Category
                </a>

                <a href="Admin-Page" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-table-columns"></i>Pages
                </a>





                <a href="Admin-draft" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold active" data-bs-toggle="collapse" data-bs-target="#demo">
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
                                 <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">


                <h3 class="post-text">LIST OF USERS</h3>

				<%
				if (request.getSession().getAttribute("msgUpdate") != null) {
				%>
				
				<p class="bg-success p-2 text-white">${msgUpdate}</p>
				<%
				request.getSession().removeAttribute("msgUpdate");}
				%>

				<div class="form-container">

                    <div class="table-responsive">
                    
						<%
						int count = 0;
						Dao_Users listOfUser = new Dao_Users();
						List<User> list = listOfUser.viewListofUser();
						%>
						
						<table class="table table-image">

                            <thead>
                                <tr>
                                    <th scope="col">Image</th>
                                    <th scope="col">Username</th>

                                    <th scope="col">Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Action</th>


                                </tr>
                            </thead>
    
                            <tbody>
                            
                            <%!int id; %>
                            <%!String file; %>
						<% for(User userList:list){
							count++;
						
							%>
                                <tr>
                                	<td style="display: none;"><%=userList.getUserId()   %></td>
                                    <td scope="row"><img src="http://localhost:8080/CmsUploadImage/<%=userList.getUser_image()   %>" alt="Cover Image"  class="img-fluid img-thumbnail"></th>
                                    <td><%=userList.getUsername() %></td>
                                    <td><%=userList.getFullName() %></td>
                                    <td><%=userList.getEmail() %></td>
                                    <td><%=userList.getStatus() %></td>
                                    <td><%=userList.getRole() %></td>
                                    <td>

                                        <div class="formBtn">

                                            <a href="Admin-updateuser?id=<%=userList.getUserId() %>" class="btn btn-sm btn-success">Edit</a>
                                            <a type="button" class="btn btn-sm btn-secondary deleteBtn">Archive</a>
                                        </div>
                                    </td>


                                </tr>
	<%} %>
                            </tbody>




						






						</table>


<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> Confirm Archive User </h5>
                    <button type="button"  class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form action="<%=request.getContextPath() %>/Controller_UserDelete" method="post">

                    <div class="modal-body">

                        <input type="hidden" name="delete_id" id="delete_id">
                        
                        
                           <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
						
                        <p>Are you sure you want to archive this User?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" name="deletedata" class="btn btn-danger">Archive</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
  








                    </div>











                </div>

            </div>




        </div>


    </div>


























    <!-- CDN FOR JS  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>



    <script>
      
      $(document).ready(function () {

          $('.deleteBtn').on('click', function () {

              $('#deletemodal').modal('show');

              $tr = $(this).closest('tr');

              var data = $tr.children("td").map(function () {
                  return $(this).text();
              }).get();

              console.log(data);

              $('#delete_id').val(data[0]);

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