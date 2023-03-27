<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<%if(session.getAttribute("admin")!=null) {
	
	response.sendRedirect("Admin");
}
else{
    
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");

	response.setDateHeader("Expires", -1);
	
	
}
%>


    <div class="container-fluid">


        <div class="Loginform-container">

            <div class="text-center">

                <img src="image/logo6.png" alt="Sign in Image" class="img-fluid">
                <h3>Sign In</h3>

            </div>
            <form action="<%=request.getContextPath() %>/Controller_Login" method="post" class="LoginForm">

                <div class=" col-lg-12 mb-3">
                    <input type="text" class="form-control" placeholder="Username" name="user" value="${usernameError}">
                </div>
                <div class=" col-lg-12 mb-3 password-toggle">
                    <div id="toggle"></div>
                    <input type="password" id="password" class="form-control" placeholder="Password" name="pass">


                </div>
	
	<div>
		<%if (request.getSession().getAttribute("msg")!=null){ %>
			
		<p class="text-danger text-center py-2">${msg}</p>
		
		<%} request.getSession().removeAttribute("msg"); %>
	</div>
	

                <div class="my-4">
                    <button type="submit" name="" class="btn btn-dark float-end">Login</button>
                </div>
                <div class="clearfix"> </div>




                <a href="Forgotpassword" class="float-end my-3">Forgot Password</a>


                <div class="clearfix"> </div>
            </form>




        </div>


    </div>




        <script>
        const password = document.getElementById("password");

        const toggle = document.getElementById("toggle");


        toggle.addEventListener("click", () => {

            if (password.type === 'password') {
                password.setAttribute('type', 'text');
                toggle.classList.add('hide');
            } else {
                password.setAttribute('type', 'password');
                toggle.classList.remove('hide');

            }



        });
    </script>
    

   
</body>

</html>