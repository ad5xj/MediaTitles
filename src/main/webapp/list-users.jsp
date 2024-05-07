<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="org.ad5xj.model.UserStr" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
   <title>Insert title here</title>
   <!-- General Stylesheet links -->
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <!--  SITE STYLES  -->
   <link rel="stylesheet" href="CSS/basestyle.css">
   <link rel="stylesheet" href="CSS/index.css">
    <!-- PAGE STYLES -->
    <style>
      body { width: 100%; margin: 0px; padding: 0px; position: top; }
      h1 { text-align: center; }
      h5 { text-align: center; }
      td {color: black; font-family: Inconsolata; font-size: .95em; }
    </style>
 </head>
 <body>
   <header>
    <h1>List Of All Users</h1>
   </header>
   <div class="bg-mynav container-fluid" style="width: 100%; background: linear-gradient(#cccccc, #fafafa);">
     <p style="text-align: center;">Report presented: <%= new Date() %></p>
   </div>
   <div class="d-flex bd-highlight mb-3">
     <div class="p-2 bd-highlight" style="margin-left: 25%;">
       <button type="button" class="btn btn-primary" onclick="showUserCreateBox()">Create</button>
     </div>
   </div>
   <hr>
   <div class="table-responsive">
	   <table class="table table-bordered" style="width: 70%; border-collapse: collapse; margin-left: 12%;">
	    <thead>
	      <tr>
	        <th style="width: 5%;">ID</th>
	        <th style="width: 10%;">Login</th>
	        <th style="width: 25%;">Name</th>
	        <th style="width: 15%;">Password</th>
	        <th style="width: 5%;">Priv_Lvl</th>
	      </tr>
	    </thead>
	     <tbody id="mytable">
	     <c:forEach var="user" items="listUser">
	       <tr>
	         <td>${User.userid}</td>
           <td>${User.login}</td>
           <td>${User.name}</td>
           <td>${User.password}</td>
           <td>${User.privLvl}</td>
	       </tr>
       </c:forEach>
	     </tbody>
	   </table>
	 </div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
 </body>
</html>