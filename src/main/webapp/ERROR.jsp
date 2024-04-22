<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<title>Error</title>
	</head>
	<body>
	  <h1 align="center">Error</h1>
	  <h2 align="center"><%=exception.getMessage() %><br/> </h2>
	</body>
</html>