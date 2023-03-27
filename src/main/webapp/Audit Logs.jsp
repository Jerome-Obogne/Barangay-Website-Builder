<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.Logs, com.entity.User , java.util.*, com.database.Dao_AuditTrail,com.database.CmsDatabase, java.sql.*"  %>
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

                <a href="Admin" class="list-group-item list-group-item-action bg-transparent second-text ">
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

                <a href="Admin-draft" class="list-group-item list-group-item-action bg-transparent fw-bold ">
                    <i class="fa-solid fa-file"></i>Draft
                </a>

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-user-group"></i>User
                </a>


                <a href="Admin-SiteSettings" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-gears"></i>Site Settings
                </a>

                <a href="Admin-message" class="list-group-item list-group-item-action bg-transparent fw-bold" >
                    <i class="fa-solid fa-envelope"></i>Messages
                </a>
                
                <a href="Admin-AuditLogs" class="list-group-item list-group-item-action bg-transparent fw-bold active" >
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
            
            	<% Dao_AuditTrail execution = new Dao_AuditTrail();
							
						
							
						%>
            
            
       <%
       
   	int page_no;
       if(request.getParameter("page_no")!=null){
    	   
    	 page_no=Integer.parseInt(request.getParameter("page_no"));
    	   
       } 
       else{
    	   
    	  page_no=1;
    	   
       }
       
       	int total_records_per_page=30;
       	
       	int offset= (page_no - 1 ) * total_records_per_page;
       
       	int previous_page= page_no - 1;
       	
       	int next_page = page_no + 1;
       	
       
       	int totalRecords=execution.totalCountofLogs();
       	
       double  total_no_of_pages=Math.ceil((double)totalRecords / total_records_per_page);
       	
       
       System.out.print(total_no_of_pages + "  " +  totalRecords);
       	
       	
       	
       	
       %>     
            
            
            
            
           <div class="container-fluid">


                <h3 class="post-text">Administrator Logs</h3>

                <!-- Form Container Starts Here -->
                <div class="form-container">

					<!-- Table Responsive Start Here -->
                    <div class="table-responsive">
                    
					<%	List<Logs> list=execution.listOfAuditLogs(offset,total_records_per_page); %>


						<table class="table table-striped">

                            <thead>
                                <tr>

                                    <th scope="col">User</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Activity</th>
                                    <th scope="col">Affected</th>
                                    <th scope="col">Date/Time</th>





                                </tr>
                            </thead>

                            <tbody>
								
								<%
								for (Logs log : list) {
									
								%>

								<tr>

                                    <td><%=log.getAuditUser() %></td>
                                    <td><%=log.getAuditRole()  %></td>
                                    <td>
                                       <%=log.getActionName() %>
                                    </td>
                                    <td><%=log.getAction_affected()  %></td>
                                   <td><%=log.getDateTime()  %></td>


                                </tr>


								<%} %>


							</tbody>



                        </table>


<nav aria-label="Page navigation example">
  <ul class="pagination">
   
   <%if (page_no <= 1){ %>
     <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
    		
  
    <%}
   
   else{
 System.out.print(previous_page);  %>
    
    <li class="page-item"><a class="page-link" href="Audit Logs.jsp?page_no=<%=previous_page %>">Previous</a></li>
    
    
   
    
    <%} %>
    
    
    <%for (int i=1; i<=total_no_of_pages ; i++){ %>
    
    
    <%if (page_no != i){ %>
      <li class="page-item"><a class="page-link" href="Audit Logs.jsp?page_no=<%=i%>"><%=i %></a></li>
    <%}
    
    
    else{%>
  
     <li class="page-item active"><a class="page-link" href="Audit Logs.jsp?page_no=<%=i%>"><%=i%></a></li>
  
    <%} %>
    
    
    <%} %>
    
    <% if ((page_no >= total_no_of_pages)){ %>
    
     <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
    
    <%}
    else{%>
    
      <li class="page-item"><a class="page-link" href="Audit Logs.jsp?page_no=<%=next_page %>">Next</a></li>
    
    <%} %>
   
  </ul>
</nav>
<div class="">
	
	<%if (total_no_of_pages == 0){ 
		
		page_no=0; %>
		
		
		<p> Page <%=page_no %> of <%=(int)total_no_of_pages %></p>
	
	
	<%}
	else{%>
		
			
		<p> Page <%=page_no %> of <%=(int)total_no_of_pages %></p>
	<% }%>
	


</div>

                    </div>


                    <!-- Table Responsive Ends Here -->


                </div>

                <!-- Form Container Ends Here -->


            </div>


        </div>



        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
 
 
 
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