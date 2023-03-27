<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <!-- CDN BOOTSTRAP -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">


    <!-- css links  -->


    <link rel="stylesheet" href="css/style.css">


    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/f8210108fd.js" crossorigin="anonymous"></script>

    <title>Barangay Website Builder</title>
<body>

    <div class="container-fluid">


        <div class="Loginform-container forgotPassword">

            <div class="forgotPassword-text">
                <img src="image/logo6.png" alt="" class="img-fluid d-block mx-auto">


                <h1>Forgot Password</h1>
                
                 <p class="text-muted"> Enter your e-mail address and we`ll send you a link to reset your password. </p>
            </div>

            <!-- <p class="text-success text-center">We send you a link to reset your password</p> -->

            <form action="Controller_PasswordReset" method="post">
                <div class="mb-3">
                    <label for="" class="form-label">Email <span>*</span></label>

                    <input type="text" name="email" id="" class="form-control">

                </div>
                <div class="text-center my-3">
                	<input type="hidden" name="forgotPasswordAction" value="forgotPassword">
                	
                	<% if (request.getSession().getAttribute("msgEmail2")!=null){ %>
                	
                		<p class="text-success text-center">${msgEmail2}</p>


					<%
					request.getSession().removeAttribute("msgEmail2");
					}
                	else if (request.getSession().getAttribute("msgEmail")!=null){
					%>
					<p class="text-danger text-center">${msgEmail}</p>

					<%
					request.getSession().removeAttribute("msgEmail");
					}
					%>
					<button type="submit" class="btn btn-dark w-100">Send Reset Link</button>
                </div>


            </form>
        </div>


    </div>


</body>
</html>