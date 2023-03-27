<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="com.entity.*, com.database.*, java.util.* ,java.sql.*" %>
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

    <!-- Google Material Icon -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>
    <title>Barangay Website Builder</title>
</head>

<body>

    <!-- HEADER STARTS HERE -->
    
    <%
	Dao_WebView viewSetting = new Dao_WebView();

	List<SiteSetting> viewlist = viewSetting.viewSite();
	
	%>
	<% for (SiteSetting setting:viewlist){ %>
    
    <header class="container-fluid" data-customHeader="<%=setting.getSite_banner() %>">
        <!-- ROW STARTS HERE -->
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

        <!-- ROW STARTS HERE -->

    </header>
    <!-- HEADER ENDS HERE -->

    <!-- navigation  section STARTS HERE -->
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
                            <a href="S_PostConcern.jsp" class="nav-link">Message Us</a>
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
	<%} %>

    <!-- navigation  section ENDS HERE -->



    <!-- PostConcern START HERE -->
    
    <section class="container-fluid" id="Post-Concern">


        <div class="postConcern-title">
            <h3>GET IN TOUCH WITH US</h3>
        </div>

        <!-- FORM CONTAINER STARTS HERE -->
        <div class="form-container">
            <div>

                <!-- START OF ROW DIV -->
                <div class="row">

                    <div class="col-sm-12 col-md-6 col-lg-6 col-xl-6 col-xxl-6">

                        <div class="postConcern-text">
                            <h4>If you have concern please message us, <br> So we can help you more quickly.</h4>
                            <p>Calatagan Proper Virac,Catanduanes 4800</p>
                        </div>
                    </div>



                    <div class="col-sm-12 col-md-6 col-lg-6 col-xl-6 col-xxl-6 my-sm-3">

                        <div class="postConcern-container">

                            <!-- MESSAGE FORM STARTS HERE -->
                            
                            <form action="Controller_PostConcern" method="post" id="messageForm">

                                <div class="row mb-3 justify-content-between">
                                    <label for="" class="col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label">Name<span></span></label>

                                    <div class="col-sm-10 col-md-10 col-lg-10 col-xl-10">
                                        <input type="text" class="form-control" name="name" placeholder="">

                                    </div>

                                </div>

                                <div class="row mb-3 justify-content-between">
                                    <label for="" class="col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label">Email<span></span></label>

                                    <div class="col-sm-10 col-md-10 col-lg-10 col-xl-10">
                                        <input type="text" class="form-control" name="email" placeholder="">

                                    </div>

                                </div>

                                <div class="row mb-3 justify-content-between">
                                    <label for="" class="col-sm-2 col-md-3 col-lg-2 col-xl-2 col-form-label">Message<span></span></label>

                                    <div class="col-sm-10 col-md-9 col-lg-10 col-xl-10">
                                        <textarea name="message" id="" cols="20" rows="10" class="form-control"> </textarea>

                                    </div>

                                </div>
		
  				    <p class="formMessages1 p-2 text-white text-center  bg-success" style="display:none"></p>

                    <p class="formMessages2 p-2 text-white text-center bg-danger" style="display:none"></p>



                                <div class="my-2 float-end">
                                
                                
                                
                                
                                
                                    <button type="submit" class="btn btn-dark btn-sm btn-postConcern" id="Send">
                                <i class="material-icons" id="postConcern-paper-plane">send  </i> Send Message

                            </button>
                                </div>

                            </form>

                            <!-- MESSAGE FORM ENDS HERE -->




                        </div>


                    </div>












                </div>
                <!-- END OF ROW DIV -->




            </div>

        </div>
        <!-- FORM CONTAINER ENDS HERE -->



    </section>
    <!-- PostConcern ENDS HERE -->



    <!-- Footer STARTS HERE -->
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
    
    
    

    <!-- Footer ENDS HERE -->

    <!-- CDN FOR JS  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js " integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8 " crossorigin="anonymous "></script>


    <!-- JQUERY CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
	<script>
		let bgHeader = document.querySelector("header").getAttribute(
				"data-customHeader");

		let header = 'http://localhost:8080/CmsUploadImage/' + bgHeader;

		document.querySelector('header').style.backgroundImage = 'url(' + header + ')';
		console.log(header);
	</script>



<!-- AJAX METHOD -->
	<script>
	
		function loadBtn(){
			
		     
            let sendBtn = document.getElementById("Send");
            var timeleft = 5;



            sendBtn.textContent = "Please Wait " + timeleft + "s";
            sendBtn.disabled = true;
            var setTimer = setInterval(function timeCount() {

                timeleft--;


                sendBtn.textContent = "Please Wait " + timeleft + "s";

                if (timeleft <= 0) {


                    sendBtn.innerHTML =   '<i class="material-icons" id="postConcern-paper-plane">send  </i>Send Message';
                    sendBtn.disabled = false;

                    clearInterval(setTimer);
                    timeleft = 5;
                }


            }, 1000);
			
			
			
			
			
		}
		
		
		const checkInput = input =>{
			
			return input===0 || input ==='' || input < 1;
			
			
		}
	
	
	
        $(document).ready(function() {

            $('#messageForm').on('submit', function(event) {

                event.preventDefault();

                
                var textArea=$('textarea').val();
                var input =$('input').val();
                
                if(checkInput(input) || checkInput(textArea)){
                	
                	 $(".formMessages2").show();
	            	 $(".formMessages2").text("All Fields are Required");
	            	 setTimeout(function() { $(".formMessages2").hide(); }, 5000);
                	
                	
                }
                

                var messageForm = $(this).serialize();

                $.ajax({

                    url: "Controller_PostConcern",
                    data: messageForm,
                    type: "POST",
                    success: function(data, textStatus, jqXHR) {

                        console.log(data);
                      
                        console.log("success");

                        if(data ==="success"){
                        	
                        	
                        	
                        	
                        	 $(".formMessages1").show();
			            	 $(".formMessages1").text("Send Message Succesfully");
			            	 setTimeout(function() { $(".formMessages1").hide(); }, 5000);
                        	$('input').val('');
                        	$('textarea').val('');	
                        	loadBtn();
                        	
              
                        	
                        }
                      /*   else if (data === "required"){
                        	
                        	alert("All Fields are required");
                        	$('input').css;
                        	loadBtn();
                        	
                        }
                        */
                        else if (data === "invalidEmail"){
                        	
                        	
                        	
                        	 $(".formMessages2").show();
        	            	 $(".formMessages2").text("Email is Invalid");
        	            	 setTimeout(function() { $(".formMessages2").hide(); }, 5000);
                        	
                        	loadBtn();
                        	
                        } 
                     /*    else{
                        	
                        	alert("Fail to Insert" + data);
                        }
                         */
                       

                    	
                    	


                    },
                    error: function(jqXHR, textStatus, errorThrown) {

                        alert("ERROR THROWN");
                    }




                })





            });





           
        });
      
    </script>
    

</body>


</html>