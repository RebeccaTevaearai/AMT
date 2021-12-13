<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession sess = request.getSession(false);
    if (sess == null) {
        response.sendRedirect("/login");
        return;
    }
%>

<html>
<head>
    <title>Account page</title>
</head>
<body>
<br>
<br>
<h1><%= session.getAttribute("username") %> account</h1>
<div><%= session.getAttribute("token") %></div>
<div><%= session.getAttribute("id") %></div>
<div><%= session.getAttribute("role") %></div>
<br>
<br>
<form action="${pageContext.request.contextPath}/login" method="GET">
    <input type="hidden" name="logout" value="1"><br>
    <input type="submit" value="Logout">
</form>
<br>
<br>
</body>
</html>
