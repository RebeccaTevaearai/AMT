<br>
<br>
<h1><%= session.getAttribute("username") %> account</h1>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/logout" method="GET">
    <input type="submit" value="Logout">
</form>
<br>
<br>
