<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.entity.*, com.database.*, java.util.* ,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
 


	<%
	Dao_WebView viewSetting = new Dao_WebView();

	List<SiteSetting> viewlist = viewSetting.viewSite();
	
	%>
	<% for (SiteSetting setting:viewlist){ %>

	<header class="container-fluid"  data-customHeader="<%=setting.getSite_banner() %>">
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
    
    <!-- navigation section start -->
    <div class="nav-section">
        <nav class="navbar navbar-expand-sm menu-color">
            <div class="containers">
                <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navigation">
<span class="navbar-toggler-icon"></span>
        </button>

 <%if (request.getSession().getAttribute("msgResetPass")!=null){ %>
	 
	 
	 <p>${msgResetPass}</p>
	 
<% request.getSession().removeAttribute("msgResetPass"); }
 
 %> 

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
     <!-- navigation section end -->
     
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

    <section class="container-fluid">
	<form action="<%=request.getContextPath() %>/Welcome" method="post">
        <div class="col-xxl-4 col-xl-4 col-lg-4 col-md-6 d-block ms-auto">
           <div class="dropdown">
            <select name="dropdownData" id="fetchval" class="form-select" onchange="this.form.submit();">
             <option value="" disabled selected>Select Category</option>
            
            <%Dao_Category getcategory=new Dao_Category(); %>
            
            <% List<Category> list1=getcategory.categoryList(); %>
            
            
              <%for (Category category:list1){ %>
                            <option value=<%=category.getCategory_id() %>
                            
                            
                            
                            <% if (request.getParameter("dropdownData")!=null){ 
                            
                            	if(category.getCategory_id() == Integer.parseInt(request.getParameter("dropdownData"))){
                            		
                            		out.println("selected");
                            		
                            		
                            	}
                            }
                            %>
                            
                            
                            
                            ><%=category.getCategoryName() %></option>
                            
                            <%} %>
             </select>
            
             </div>
                          
        </div>

       


    </section> 

    <!-- article -->
 
    
    
    
    <section class="container-fluid" id="article">
    
    <%if (request.getParameter("dropdownData")==null || Integer.parseInt(request.getParameter("dropdownData"))==1){ %>
    
    
    
    
    
    
<% Dao_View data=new Dao_View();
List<WebPost> list= data.displayPost(); %>


  <!--  Ratings STARTS HERE -->
    
    	    <script>
        $(document).ready(function() {
            $('.rating').barrating({
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
                        var  sample=$('#rating_'+ postid).val();
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
                                $('#avgrating_' + postid).text(average);
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


            <div  class="col-xxl-6 col-xl-6 col-lg-6 col-md-6 col-sm-12">


                <div class="article-main">
                    <div class="limit">
                        <img src="http://localhost:8080/CmsUploadImage/<%=post.getImage()  %>" alt="Cover Image" class="img-fluid">


                    </div>
                </div>



            </div>

            <div class="col-xxl-6 col-xl-6 col-lg-6 col-md-6 col-sm-12">

                <div class="article-content text-break">


                   <h2><%=post.getPost_title() %></h2>
                    <div class="article-text"><%=post.getPost_content() %></div>
                    <div class="article-sub text-muted">
                        <span><i class="fa-regular fa-user"></i>By <%=post.getPost_author() %></span>
                        <span><i class="fa-regular fa-calendar-days"></i><%=post.getPost_date() %></span>
                    </div>
                    
                  <!-- Ratings START HERE -->  
                    
                  <div class="rating-action">

        <select class="rating" id="rating_<%=post.getPost_id()  %>" data-id='rating_<%=post.getPost_id() %>'>
     
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
       <p class="text-muted"> Average Rating : <span id='avgrating_<%=post.getPost_id() %>'><%=averageRating%></span> </p>


        <!-- Set rating -->
        <script type='text/javascript'>
            $(document).ready(function() {
                $('#rating_<%=post.getPost_id()  %>').barrating('set', <%=value%>);
            });
        </script>
   					
   				 </div>
                    
                     <!-- Ratings ENDS HERE -->  


                    <div class="float-end ">
                        <a href="Controller_WebPageView?id=<%=post.getPost_id() %>" class="btn btn-sm btn-dark my-3 ">Read More</a>
                    </div>
                </div>

            </div>







        </div>
<%}%>

<%}else{ %>


 <% Dao_Category data=new Dao_Category();
List<WebPost> list= data.dropdownCategory(Integer.parseInt(request.getParameter("dropdownData"))); %>

   
    <section class="container-fluid" id="article">


  <!--  Ratings STARTS HERE -->
    
    	    <script>
        $(document).ready(function() {
            $('.rating').barrating({
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
                        var  sample=$('#rating_'+ postid).val();
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
                                $('#avgrating_' + postid).text(average);
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


            <div class="col-xxl-6 col-xl-6 col-lg-6 col-md-6 col-sm-12">


                <div class="article-main">
                    <div class="limit">
                        <img src="http://localhost:8080/CmsUploadImage/<%=post.getImage()  %>" alt="Cover Image" class="img-fluid">


                    </div>
                </div>



            </div>

            <div  class="col-xxl-6 col-xl-6 col-lg-6 col-md-6 col-sm-12">

                <div class="article-content text-break">


                   <h2><%=post.getPost_title() %></h2>
                    <div class="article-text"><%=post.getPost_content() %></div>
                    <div class="article-sub text-muted">
                        <span><i class="fa-regular fa-user"></i>By <%=post.getPost_author() %></span>
                        <span><i class="fa-regular fa-calendar-days"></i><%=post.getPost_date() %></span>
                    </div>
                    
                  <!-- Ratings START HERE -->  
                    
                  <div class="rating-action">

        <select class="rating" id="rating_<%=post.getPost_id()  %>" data-id='rating_<%=post.getPost_id() %>'>
     
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
       <p class="text-muted"> Average Rating : <span id='avgrating_<%=post.getPost_id() %>'><%=averageRating%></span> </p>


        <!-- Set rating -->
        <script type='text/javascript'>
            $(document).ready(function() {
                $('#rating_<%=post.getPost_id()  %>').barrating('set', <%=value%>);
            });
        </script>
   					
   				 </div>
                    
                     <!-- Ratings ENDS HERE -->  


                    <div class="float-end">
                        <a href="Controller_WebPageView?id=<%=post.getPost_id() %>" class="btn btn-sm btn-dark my-3 ">Read More</a>
                    </div>
                </div>

            </div>







        </div>
<%} %>
       
       





<%} %>
 </form>

    </section>
    
     
    
    </section>
    
   
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
     
     
     
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
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
	
	
	/* 
	let data = document.querySelector('.article-content div').textContent;
	console.log(data);
	const dataTrim = (data) => {

	    let trim = data.slice(0, 300);
	console.log(trim);
	    return trim;

	}
	let sample = dataTrim(data);

	document.querySelectorAll('.article-content div').textContent = sample + '......';

 */



 const postContent = document.querySelectorAll('.article-content .article-text');;

 for (const trimData of postContent) {


     const text = trimData.textContent;
     trimData.textContent = text.slice(0,300)+ '..';
 }






	
	
	</script>




<script>





/*
let allImage = document.querySelectorAll('.article-main');
const x = elem.map((item, index) => {
    item.id = 'myDiv${index}'

    let data = item.getAttributes("data-customvalue");

    item.style.backgroundImage = url('http://localhost:8080/CmsUploadImage/${data}');
    
    return item
})
*/
/*

	let allImage = document.querySelectorAll('.article-main');
    let list;
   
    for (const path of allImage) {

    	 var data  = path.getAttribute("data-customvalue");
   
    	 var j = 1;
    
    	 for (var c = 0; c < elem.length; c++) {

    	     elem[c].id = 'myDiv' + j;
    	     elem[c].style.backgroundImage = 'url('+ data+ ')';
    	     j++;
    	   
    	 } 

    }


*/





/*

var a=[];
for (let i = 0; i < allImage.length; i++) {

	a = allImage[i].getAttribute("data-customvalue");
  
	



	
}


**/

/*
const x = elem.map((item, index) => {
	  item.id = myDiv${index}

	  let data = item.getAttributes("data-customvalue");
	  let urlhere ="http://localhost:8080/CmsUploadImage/"${data};
	  item.style.backgroundImage = 'url('+urlhere+')';

	  return item
	})

*/

/*
let  elem = document.querySelectorAll('.article-main');
var  array="";
    for (let i = 0; i < elem.length; i++) {

       array =elem[i].getAttribute("data-customvalue");
        
    }

 */
 <%-- var g='http://localhost:8080/CmsUploadImage/<%=post.getImage() %>';

 
 var  elem = document.querySelectorAll('.article-main');
 var j = 1;
 for (var c = 0; c < elem.length; c++) {

     elem[c].id = 'myDiv' + j;
     elem[c].style.backgroundImage = 'url(' + g + ')';
     j++;
 }
 --%>
 
 



</script>






</body>
</html>