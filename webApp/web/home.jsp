<%@page import="dbConnection.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8;
 charset=US-ASCII"  %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII; charset=UTF-8">
  <title>Title</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Hi <%=user.getName() %></h3>
<strong>Your Email</strong>: <%=user.getEmail() %><br>
<strong>Your Country</strong>: <%=user.getCountry() %><br>
<br>
<form action="Logout" method="post">
  <input type="submit" value="Logout" >
</form>
</body>
</html>