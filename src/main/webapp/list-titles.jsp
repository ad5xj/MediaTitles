<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.ad5xj.model.TitleStr" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
  <head>
    <title>Insert title here</title>
   <!-- General Stylesheet links -->
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   <!--  SITE STYLES  -->
   <link rel="stylesheet" href="CSS/basestyle.css">
   <link rel="stylesheet" href="CSS/index.css">
    <!-- PAGE STYLES -->
    <style>
    </style>
  </head>
  <body>
    <h1 style="text-align: center;">Media Titles List</h1>
    <hr style="margin-left: 5%; width: 90%; border-width: 1px; border-color: #ccc; ">
    <div id="main-container" style="width: 90%; margin-left: 5%;">
	    <div class="table-responsive">
	       <table class="table table-bordered" style="width: 70%; border-collapse: collapse; margin-left: 12%;">
	        <thead>
	          <tr>
	            <th style="width: 5%;">ID</th>
	            <th style="width: 25%;">Title</th>
	            <th style="width: 20%;">Author</th>
	            <th style="width: 15%;">Media</th>
	            <th style="width: 20%;">Notes</th>
	          </tr>
	        </thead>
         <tbody id="mytable">
         <c:forEach var="org.ad5xj.model.TitleStr" items="${listTitles}">
           <tr>
             <td><c:out value="${TitleStr.titleid_s}" /></td>
             <td><c:out value="${TitleStr.title_s}" /></td>
             <td><c:out value="${TitleStr.author_s}" /></td>
             <td><c:out value="${TitleStr.media_s}" /></td>
             <td><c:out value="${TitleStr.note_s}" /></td>
           </tr>
         </c:forEach>
         </tbody>
	       </table>
	    </div>
	  </div>
  </body>
</html>