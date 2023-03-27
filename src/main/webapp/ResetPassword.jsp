<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.database.Dao_ResetPassword, com.database.Dao_ForgotPassword, java.sql.*"  %>
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


<%
if(session.getAttribute("success")!=null){
	response.sendRedirect("Welcome");
	session.removeAttribute("success");
	
	
}

else{
	
	 
	 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	 response.setHeader("Pragma", "no-cache");
	 response.setHeader("Expires", "0");
	 response.setDateHeader("Expires", -1);

	
}

%>




    <div class="container-fluid">

<%!String email; %>

<% 
String token=request.getParameter("key");
Dao_ResetPassword execution=new Dao_ResetPassword();

//boolean validate=execution.validateToken(token);
ResultSet rs=null;

	rs=execution.checkToken(token);
	java.sql.Timestamp currentTime=new java.sql.Timestamp(new java.util.Date().getTime());  //current time 

%>
<% if (rs.next()){ 
	email=rs.getString("email");
	java.sql.Timestamp exptime = rs.getTimestamp("expTime");
	
	if(currentTime.before(exptime)){
%>



   <div class="Loginform-container forgotPassword">

            <div class="forgotPassword-text">
                <img src="image/logo6.png" alt="" class="img-fluid d-block mx-auto">


                <h1>Reset Password</h1>
            </div>

            <!-- <p class="text-success text-center">We send you a link to reset your password</p> -->

            <form action="Controller_PasswordReset" method="post">
                <div class="mb-3">
                    <label for="" class="form-label">Password <span>*</span></label>
					<input type="hidden" name="key" value="<%=request.getParameter("key") %>">	
                    <input type="password" name="password" id="" class="form-control">

                </div>
                <div class="mb-3">
                    <label for="" class="form-label">Confirm Password <span>*</span></label>

                    <input type="password" name="confirmPassword" id="" class="form-control">

                </div>
                <div class="text-center my-3">
               	   <input type="hidden" name="forgotPasswordAction" value="resetPassword">
               	   
               	   <% if(request.getSession().getAttribute("msgResetPass")!=null){ %>
               	   
               	   	<p class="text-danger text-center">${msgResetPass}</p>
               	   
               	   
               	   <%request.getSession().removeAttribute("msgResetPass");} %>
               	   
                    <button type="submit" class="btn btn-dark w-100">Reset Password</button>
                </div>


            </form>
        </div>

</div>
   
<%}
	else{
	Dao_ForgotPassword execute=new Dao_ForgotPassword();
	execute.deleteData(email);
	%>
		
	<div class="text-center lh-2">
	<p>LINKS ARE BROKEN TRY AGAIN TO RESET YOUR PASSWORD</p>	
	<a href="Welcome">TRY AGAIN</a>
	</div>	

	
	<%
	}
}
else {%>

	<div class="text-center lh-2">
	<p>THIS LINK IS INVALID</p>	
	<a href="Welcome">TRY AGAIN</a>
	</div>	




<%}%>
 
</body>
</html>