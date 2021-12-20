<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Account page</title>
</head>
<body>
<br>
<br>
<h1><%= session.getAttribute("username") %> account</h1>
<br>
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
