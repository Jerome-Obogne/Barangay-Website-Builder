<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page errorPage="error.jsp"%>
         <%@ page import="com.entity.* ,com.database.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CDN BOOTSTRAP -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">


    <!-- css links  -->


    <link rel="stylesheet" href="css/style.css">


    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>


    <title>Barangay Website Builder</title>
</head>

<body>





<%
	Dao_WebView viewSetting = new Dao_WebView();

	List<SiteSetting> viewlist = viewSetting.viewSite();
	
	%>
	<% for (SiteSetting setting:viewlist){ %>






    <header class="container-fluid" data-customHeader="<%=setting.getSite_banner() %>">
        <div class="row justify-content-between">

          
            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-6 col-xxl-6">




                 <div class="header-content">

                   
                   <img src="http://localhost:8080/CmsUploadImage/<%=setting.getSite_logo() %>" alt="Logo"  class="img-fluid">
                    <div class="header-title">

                      <h2><%=setting.getSite_title() %></h2>
                    </div>
                </div>



            </div>
        </div>

    </header>
    <!-- navigation section -->
    <div class="nav-section">
        <nav class="navbar navbar-expand-sm menu-color">
            <div class="containers">
                <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navigation">
<span class="navbar-toggler-icon"></span>
        </button>



                <div class="collapse navbar-collapse" id="navigation">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a href="Welcome" class="nav-link">Home</a>
                        </li>
                       
                          
                        <% 
                        Dao_Menu menu=new Dao_Menu();
                        List<Menu> menuList=menu.listofMenu();  %>
                        
                        <% for(Menu listofMenu:menuList){ %>
                        
                        <li class="nav-item">
                            <a href="Menu?id=<%=listofMenu.getMenuId() %>" class="nav-link"><%=listofMenu.getMenuTitle() %></a>
                        </li>
                        
                        
                        <%} %>
                      
                       
                        <li class="nav-item">
                            <a href="Postconcern" class="nav-link">Message Us</a>
                        </li>


                    </ul>
				<div class="sidenav ms-auto">
  					
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a href="Login" class="btn btn-dark">Login</a>
                        </li>
                    </ul>
                    
					</div>
                </div>





            </div>
        </nav>

    </div>
    <!-- main background -->
    <section class="main-Background" data-customValue="<%=setting.getSite_mainImage()%>">

        <div class="main-content text-center">


			<div class="mainTitle">

				<h1><%=setting.getSite_mainTitle()%></h1>

			</div>

				<a class="btn btn-dark" href="<%=setting.getSite_buttonLink()%>"><%=setting.getSite_buttonTitle()%></a>

        </div>








    </section>


<%} %>


    <!-- POST STARTS HERE -->
    
    <section class="container-md my-5">

<%List<WebPost> list=(List) request.getSession().getAttribute("webView");

for(WebPost webpost:list){
%>
        <div class="col-xxl-12 col-xl-12 col-lg-12  viewBackground rounded-2" id="mainImage" data-customValue="<%=webpost.getImage() %>">
 
			

        </div>

        <div class="content-view mt-3 text-break">
            <h3><%=webpost.getPost_title()%></h3>

            <div class="article-sub text-muted mb-5">
                <span><i class="fa-regular fa-user"></i>By <%=webpost.getPost_author()%></span>
                <span><i class="fa-regular fa-calendar-days"></i><%=webpost.getPost_date()%></span>
            </div>


            <p><%=webpost.getPost_content() %></p>


        </div>



<%} %>


    </section>

    <!-- POST ENDS  HERE -->


     <!-- FOOTER STARTS HERE -->
   
    <footer>

		<%
		Dao_Footer footerData = new Dao_Footer();

		FooterSetting footer = footerData.getFooterData();
		%>
		
		<div class="container-fluid">




            <div class="row justify-content-between">

                <div class="col-xxl-4 col-xl-4 col-lg-4 col-md-4 col-sm-12">

                    <h5 class="footerHeader"><%=footer.getFooter_title() %></h5>
                    <p class="footer-text"><%=footer.getFooter_paragraph() %></p>



                </div>

                <div class="col-xxl-3 col-xl-3 col-lg-3 col-md-4 col-sm-12">
                    <h5 class="footerHeader">
                       <%=footer.getFooter_title_col2() %>
                    </h5>

                    <div class="footer-list">
                        <ul class="nav flex-column">
                        <li><%=footer.getFooter_serviceList1() %></li>
                        <li><%=footer.getFooter_serviceList2() %></li>
                        <li><%=footer.getFooter_serviceList3() %></li>
                        <li><%=footer.getFooter_serviceList4() %></li>
                        </ul>
                    </div>

                </div>
                <div  class="col-xxl-4 col-xl-4 col-lg-4 col-md-4 col-sm-12">

                    <h5 class="footerHeader"><%=footer.getFooter_title_col3() %></h5>
                    <div class="footer-list">
                        <ul class="nav flex-column">
                            <li><i class="fa-solid fa-phone"></i><%=footer.getFooter_contactList1() %></li>
                            <li><i class="fa-brands fa-twitter"></i><%=footer.getFooter_contactList2() %></li>
                            <li><i class="fa-brands fa-facebook"></i><%=footer.getFooter_contactList3() %></li>
                            <li><i class="fa-brands fa-instagram"></i><%=footer.getFooter_contactList4() %></li>
                        </ul>
                    </div>



                </div>



            </div>


        </div>
       


    </footer>
  <div class="container-fluid footer-title text-center">
        <span class="text-muted">Powered By &copy; Brgy Website Builder</span>
    </div>
    
    
     <!-- FOOTER ENDS HERE -->  
     
    <!-- CDN FOR JS  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js " integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8 " crossorigin="anonymous "></script>
 
 <script>
	
	let bgHeader = document.querySelector("header").getAttribute("data-customHeader");


	  let header = 'http://localhost:8080/CmsUploadImage/' + bgHeader;

	  document.querySelector('header').style.backgroundImage = 'url(' + header + ')';
	console.log(header);
	</script>



	<script>
	
	let bgImage = document.querySelector(".main-Background").getAttribute("data-customValue");


	  let data = 'http://localhost:8080/CmsUploadImage/' + bgImage; 

	  document.querySelector('.main-Background').style.backgroundImage = 'url(' + data + ')';
	console.log(data);
	</script>
	
	
 
	<script>
	
	let bgImage2 = document.querySelector(".viewBackground").getAttribute("data-customValue");


	  let data2 = 'http://localhost:8080/CmsUploadImage/' + bgImage2;
console.log(data2);
	  document.getElementById('mainImage').style.backgroundImage = 'url(' + data2 + ')';
	
	</script>
	

</body>


</html>