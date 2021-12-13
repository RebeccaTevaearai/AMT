<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sess = request.getSession(false);
    if (sess != null) {
        response.sendRedirect("/account");
        return;
    }
%>
<html>
<head>
    <title>login</title>
</head>
<body>
<br>
<h1>Login</h1>
<br>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <label>Username</label><br>
    <input type="text" name="username"><br>
    <label>Password</label><br>
    <input type="text" name="password"><br>
    <input type="submit" value="Submit">
</form>
<br>
<% if (request.getAttribute("msg") != null) {%>
    <div><%= request.getAttribute("msg") %></div>
<% } %>
<br>
</body>
</html>
