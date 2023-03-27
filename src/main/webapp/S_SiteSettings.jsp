<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page errorPage="error.jsp"%>
    <%@ page import="com.entity.*,com.database.*" %>
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

              
                <span><img src="image/logo7.png" alt="" class="img-fluid rounded-circle"></span>
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

                <a href="Admin-All-users" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-user-group"></i>User
                </a>


                <a href="Admin-SiteSettings" class="list-group-item list-group-item-action bg-transparent fw-bold  active">
                    <i class="fa-solid fa-gears"></i>Site Settings
                </a>

                <a href="Admin-message" class="list-group-item list-group-item-action bg-transparent fw-bold">
                    <i class="fa-solid fa-envelope"></i>Messages
                </a>
                
                  <a href="Admin-AuditLogs" class="list-group-item list-group-item-action bg-transparent fw-bold" >
                     <i class="fa-solid fa-clock-rotate-left"></i>Audit Trails
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
                                <li><a class="dropdown-item" href="Admin-EditProfile">Edit Profile</a></li>
                                 <li><a class="dropdown-item" href="Admin-changepassword">Change Password</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Controller_Logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">

				<h4>SITE SETTING</h4>


			

				<%
				Dao_Settings execution = new Dao_Settings();
				SiteSetting settings = execution.data();
				%>
				
				<div class="form-container my-4">
                    <div class="row">

                        <div class="col-sm col-md-6 col-lg-6 col-xl-6 col-xxl-6">


                            <form action="<%=request.getContextPath()%>/Controller_SiteSetting" method="post">
                            
                            
                            
                            
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                            
                            
                            
                            
                                <div class="mb-3">
                                    <label for="" class="form-label">Site Title:</label>

                                    <input type="text" class="form-control" name="siteTitle" value="<%=settings.getSite_title()  %> ">
                                    <input type="hidden" name="siteId" value="<%=settings.getSite_id() %>" placeholder="siteId">
                                    
                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Site Tagline:</label>
                                    <input type="text" class="form-control" name="siteTagline" value="<%=settings.getSite_tagline()  %>">

                                </div>
                                
                                    <div class="mb-3">
                                    <label for="" class="form-label">Main Title:</label>
                                    <input type="text" class="form-control" name="siteMainTitle" value="<%=settings.getSite_mainTitle()  %>">

                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Button Title:</label>
                                    <input type="text" class="form-control" name="siteButtonTitle" value="<%=settings.getSite_buttonTitle()    %>">

                                </div>

                                <div class="mb-3">
                                    <label for="">Button Links:</label>
                                    <input type="text" class="form-control" name="siteButtonLinks" value="<%=settings.getSite_buttonLink() %>" >

                                </div>

                                <div>

									<%
									if (request.getSession().getAttribute("msgSite1") != null) {
									%>
									<p class="bg-success p-2 text-white">${msgSite1}</p>


									<%
									request.getSession().removeAttribute("msgSite1");
									} 
									else if (request.getSession().getAttribute("msgSite") != null) {
									%>
									
									<p class="bg-danger p-2 text-white">${msgSite}</p>
									<%
									request.getSession().removeAttribute("msgSite");
									}
									%>
									<button type="submit" class="btn btn-sm btn-dark">Update</button>

                                </div>



                            </form>













                        </div>
                        <div class="col-sm col-md-6 col-lg-6 col-xl-6 col-xxl-6">



                            <form action="<%=request.getContextPath() %>/Controller_ImageUpdate" method="post" class="mb-4" enctype="multipart/form-data">
                            
                            
                            
                                         
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                                <div class="mb-3">
                                    <div class="my-2">
                                       <div class="cover-image"> <img src="http://localhost:8080/CmsUploadImage/<%=settings.getSite_banner()  %>" alt="Banner" class="img-fluid"></div>
                                    </div>

                                  
                                    <div>
                                      <label for="" class="form-label">Header Background:</label>
                                        <input type="file" class="form-control" name="header">
                                        <input type="hidden" name="siteId" value="<%=settings.getSite_id()  %>" placeholder="siteId">
                                         <input type="hidden" name="oldHeaderFile" value="<%=settings.getSite_banner() %>" placeholder="oldHeaderFile">
                                        
                                    </div>
                                    <sub class="text-muted">(Recommended Image Resolution for better result: 1200 x 600)</sub>
                                </div>

                                <div class="my-2">
								    <input type="hidden" name="action" value="updateheader">
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>

                                </div>



                            </form>

                            <form action="<%=request.getContextPath() %>/Controller_ImageUpdate" class="mb-4"  method="post" enctype="multipart/form-data" >
                               
                               
                               
                                                             
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                               
                               
                               
                               
                               
                               
                               
                               
                               
                                <div class="mb-3">

									<div class="my-2">
										<div class="cover-image">
											<img
												src="http://localhost:8080/CmsUploadImage/<%=settings.getSite_logo()%>"
												alt="Site Logo" class="img-fluid">
										</div>
									</div>

									<label for="" class="form-label">Site Logo:</label>
                                    <input type="file" class="form-control" name="siteLogo">
                                    <input type="hidden" name="siteId" value="<%=settings.getSite_id() %>" placeholder="siteId">
                                     <input type="hidden" name="oldSiteLogoFile" value="<%=settings.getSite_logo() %>" placeholder="oldSiteLogoFile">
                                    <sub class="text-muted">(Recommended Image Resolution for better result)</sub>
                                </div>

                                <div class="my-2">
									<input type="hidden" name="action" value="updatesitelogo">
									
							
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>
	    							
                                </div>



                            </form>
                            <form action="<%=request.getContextPath() %>/Controller_ImageUpdate"  method="post" enctype="multipart/form-data" >
                               
                               
                               
                                                             
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                               
                                <div>

									<div class="my-2">
										<div class="cover-image">
											<img
												src="http://localhost:8080/CmsUploadImage/<%=settings.getSite_mainImage()%>"
												alt="Site Main Image" class="img-fluid">
										</div>
									</div>


									<label for="" class="form-label">Main Background:</label>
                                    <input type="file" class="form-control" name="mainBackground">
                                    <input type="hidden" name="siteId" value="<%=settings.getSite_id()%>" placeholder="siteId">
                                      <input type="hidden" name="oldMainBackgroundFile" value="<%=settings.getSite_mainImage()%>" placeholder="oldSiteLogoFile">
                                    <sub class="text-muted">(Recommended Image Resolution for better result: 1280 x 640)</sub>
                                </div>


                                <div class="my-2">
									<input type="hidden" name="action" value="updatemainbackground">
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>

                                </div>
                            </form>








                        </div>



                    </div>








                </div>
                
                 <h4>FOOTER SETTING</h4>
				<%
				Dao_Footer data = new Dao_Footer();

				FooterSetting footer = data.getFooterData();
				%>


				<!-- start of form-container -->
                <div class="form-container my-4">

                    <!-- start of row -->
                    <div class="row">



                        <div class="col-sm col-md-6 col-lg-6">



                            <h6>Column 1</h6>

                            <form action="<%=request.getContextPath() %>/Controller_FooterSetting" method="post">
                            
                                                          
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                            
                            
                            

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Title</label>
                                    <input type="text" class="form-control" name="footerTitleCol1" value="<%=footer.getFooter_title() %>">
                                    <input type="hidden" name="footerId" value="<%=footer.getFooter_id() %>">
                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Paragraph:</label>
                                    <input type="text" class="form-control" name="footerParagraph" value="<%=footer.getFooter_paragraph() %>">


                                </div>

                                <div>
                                    <input type="hidden" name="footerAction" value="footerData">
                                    
                                    
                                    <%
									if (request.getSession().getAttribute("msgFooter1") != null) {
									%>
									<p class="bg-success p-2 text-white">${msgFooter1}</p>


									<%
									request.getSession().removeAttribute("msgFooter1");
									} 
									else if (request.getSession().getAttribute("msgFooter") != null) {
									%>
									
									<p class="bg-danger p-2 text-white">${msgFooter}</p>
									<%
									request.getSession().removeAttribute("msgFooter");
									}
									%>
									
                                    
                                    
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>
                                </div>


                            </form>









                        </div>




                        <div class="col-sm col-md-6 col-lg-6">



                            <h6>Column 2</h6>

                            <form action="<%=request.getContextPath() %>/Controller_FooterSetting"  method="post">
                            
                            
                                                          
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                            
                            
                            
                            
                            

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Title</label>
                                    <input type="text" class="form-control" name="footerTitleCol2" value="<%=footer.getFooter_title_col2() %>">
                                    <input type="hidden" name="footerId" value="<%=footer.getFooter_id() %>">
                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Services List 1:</label>
                                    <input type="text" class="form-control" name="footerServiceList1" value="<%=footer.getFooter_serviceList1() %>">


                                </div>
                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Services List 2:</label>
                                    <input type="text" class="form-control" name="footerServiceList2" value="<%=footer.getFooter_serviceList2() %>">


                                </div>
                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Services List 3:</label>
                                    <input type="text" class="form-control" name="footerServiceList3" value="<%=footer.getFooter_serviceList3() %>">


                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Services List 4:</label>
                                    <input type="text" class="form-control" name="footerServiceList4" value="<%=footer.getFooter_serviceList4() %>">


                                </div>




                                <div>
                                    <input type="hidden" name="footerAction" value="footerData1">
                                    
                                    
                                    
                                        <%
									if (request.getSession().getAttribute("msgFooter_2") != null) {
									%>
									<p class="bg-success p-2 text-white">${msgFooter_2}</p>


									<%
									request.getSession().removeAttribute("msgFooter_2");
									} 
									else if (request.getSession().getAttribute("msgFooter2") != null) {
									%>
									
									<p class="bg-danger p-2 text-white">${msgFooter2}</p>
									<%
									request.getSession().removeAttribute("msgFooter2");
									}
									%>
                                    
                                    
                                    
                                    
                                    
                                    
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>
                                </div>


                            </form>











                        </div>









                    </div>

                    <!-- end of row -->

                    <!-- start of row -->
                    <div class="row">



                        <div class="col-sm col-md-6 col-lg-6">



                            <h6>Column 3</h6>

                            <form action="<%=request.getContextPath() %>/Controller_FooterSetting" method="post">
                            
                            
                                                          
                <!-- Audit trail of Data  Start here -->
              
            	        <input type="hidden" name="lastName" placeholder="Author lastname" value="<%=user.getLastName()  %>">
                    	<input type="hidden" name="name" placeholder="Author firstName" value="<%=user.getFirstName()  %>">
                    	<input type="hidden" name="role" placeholder="Author role" value="<%=user.getRole() %>">
              
               <!-- Audit trail of Data  End here -->
                            
                            
                            

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Title</label>
                                    <input type="text" class="form-control" name="footerTitleCol3" value="<%=footer.getFooter_title_col3() %>">
                                 <input type="hidden" name="footerId" value="<%=footer.getFooter_id() %>">
                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Contact List 1:</label>
                                    <input type="text" class="form-control" name="footerContactList1" value="<%=footer.getFooter_contactList1() %>">


                                </div>
                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Contact List 2:</label>
                                    <input type="text" class="form-control" name="footerContactList2"  value="<%=footer.getFooter_contactList2() %>">


                                </div>
                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Contact List 3:</label>
                                    <input type="text" class="form-control" name="footerContactList3"  value="<%=footer.getFooter_contactList3() %>">


                                </div>

                                <div class="mb-3">
                                    <label for="" class="form-label">Footer Services List 4:</label>
                                    <input type="text" class="form-control" name="footerContactList4"  value="<%=footer.getFooter_contactList4() %>">


                                </div>




                                <div>
                                    <input type="hidden" name="footerAction" value="footerData2">
                                    
                                         
                                        <%
									if (request.getSession().getAttribute("msgFooter_3") != null) {
									%>
									<p class="bg-success p-2 text-white">${msgFooter_3}</p>


									<%
									request.getSession().removeAttribute("msgFooter_3");
									} 
									else if (request.getSession().getAttribute("msgFooter3") != null) {
									%>
									
									<p class="bg-danger p-2 text-white">${msgFooter3}</p>
									<%
									request.getSession().removeAttribute("msgFooter3");
									}
									%>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    <button type="submit" class="btn btn-sm btn-dark">Update</button>
                                </div>


                            </form>




                        </div>









                    </div>
                    <!-- end of row -->


                </div>

                <!-- end of form-container -->
                
				
				
				

			</div>


        </div>


























        <!-- CDN FOR JS  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
     <script src="<%=request.getContextPath()%>/js/external.js"></script>







</body>


</html>