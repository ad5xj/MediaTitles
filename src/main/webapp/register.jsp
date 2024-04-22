<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>User Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  </head>
	
  <body>
    <div class="container">
      <div class="row text-center" style="color: tomato;">
        <h2>User Registration with JSP, Servlet and Hibernate</h2>
      </div>
      <hr>
      <div class="row col-md-10 col-md-offset-3">
        <div class="card card-body">
          <h2>User Register Form</h2>
          <div class="col-md-8 col-md-offset-3">
            <form action="<%=request.getContextPath()%>/register" method="post">
              <div class="form-group">
                <label for="uname">First Name:</label> 
                <input type="text" 
                       id="uname" 
                       class="form-control" 
                       placeholder="First Name"
                       name="firstName"
                       required>
	          </div>
              <div class="form-group">
                <label for="uname">Last Name:</label> 
                <input type="text"
                       id="uname"
                       name="lastName"
                       class="form-control" 
                       placeholder="last Name"
	                    required>
              </div>
              <div class="form-group">
                <label for="uname">User Name:</label> 
                <input id="username"
                       name="username" 
                       class="form-control"   
                       type="text"
                       placeholder="User Name"
	                     required>
              </div>
	
              <div class="form-group">
                <label for="uname">Password:</label> 
                <input id="password"
                       name="password"
                       class="form-control"   
                       type="password"
	                   placeholder="Password"
	                   required>
	          </div>
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
          </div>
	   </div>
	  </div>
	</div>
  </body>
</html>