<%@ page import="Data.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= (String)request.getAttribute("name") %>
    <%= (String)request.getAttribute("description") %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet </a>
</body>
</html>