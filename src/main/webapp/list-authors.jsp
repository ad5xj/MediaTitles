<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
  <head>
    <title>Author Management </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  </head>

  <body>
    <div>
      <div>
        <div class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
          <a href="https://www.javaguides.net" class="navbar-brand"> User Management App</a>
        </div>
        <ul class="navbar-nav">
          <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
          <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered" style="width: 70%; border-collapse: collapse; margin-left: 12%;">
          <thead>
            <tr>
              <th style="width: 5%;">ID</th>
              <th style="width: 15%;">First Name</th>
              <th style="width: 15%;">Last Name</th>
              <th style="width: 25%;">NOTE</th>
            </tr>
          </thead>
        <tbody>

        <c:forEach var="author" items="${listAuthor}">
        <tr>
          <td><c:out value="${Author.authid}" /></td>
          <td><c:out value="${Author.firstname}" /></td>
          <td><c:out value="${Author.lastname}" /></td>
          <td><c:out value="${Author.authnote}" /></td>
          <td>
            <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
            &nbsp;&nbsp;&nbsp;&nbsp; 
            <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
          </td>
        </tr>
        </c:forEach>
        <!-- } -->
      </tbody>
    </table>
  </div>
 </div>
 </body>
</html>