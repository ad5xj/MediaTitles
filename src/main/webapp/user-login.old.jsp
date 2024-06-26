<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <style>
    body {font-family: Arial, Helvetica, sans-serif;}

    form {border: 3px solid #f1f1f1;}

		input[type=text], input[type=password] 
		{
		  width: 100%;
		  padding: 12px 20px;
		  margin: 8px 0;
		  display: inline-block;
		  border: 1px solid #ccc;
		  box-sizing: border-box;
		}
		
		button 
		{
		  background-color: #04AA6D;
		  color: white;
		  padding: 14px 20px;
		  margin: 8px 0;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		}
		button:hover { opacity: 0.8; }
		
    img.avatar { width: 40%; border-radius: 50%; }

    span.psw   { float: right; padding-top: 16px; }

    #main-container { width: 40%; text-align: center; margin-left: 20%; max-width: 940px; min-width: 320px; }

		.cancelbtn { width: auto;  padding: 10px 18px; background-color: #f44336; }
		
		.imgcontainer {  text-align: center;  margin: 24px 0 12px 0; }
		
		
		.container { padding: 16px;	}
		
		
		/* Change styles for span and cancel button on extra small screens */
		@media screen and (max-width: 300px) 
		{
		  span.psw { display: block; float: none; }
		  .cancelbtn { width: 100%; }
		}
		</style>
  </head>
 <body>
   <div id="main-container" >
	   <h2>Login Form</h2>
	   <form action="UserLoginServlet" method="GET" style="border-width: 1px; border-color: #aaa; border-radius: 7px;">
	     <div class="imgcontainer"><img src="Images/people.png" alt="Avatar" class="avatar"></div>
	     <div class="container">
	       <label for="login"><b>Login ID</b></label>
	       <input type="text" placeholder="Enter Logon ID" name="login" required>
	       <label for="psw"><b>Password</b></label>
	       <input type="password" placeholder="Enter Password" name="passwd" required>
	       <button type="submit">Login</button>
	     </div>
	     <div class="container" style="background-color:#f1f1f1">
	       Register <a href="register.jsp">Now?</a>&nbsp;&nbsp;&nbsp;Forgot <a href="#">password?</a>
	     </div>
	   </form>
	 </div>
 </body>
</html>