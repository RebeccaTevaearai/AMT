

<br>
<h1>Login</h1>
<br>
<form class="formulaire" action="${pageContext.request.contextPath}/login" method="POST">
    <label>Username</label><br>
    <input type="text" name="username"><br>
    <label>Password</label><br>
    <input type="password" name="password"><br>
    <input type="submit" value="Submit">
</form>
<br>
<form action="${pageContext.request.contextPath}/register" method="GET">
    <input type="submit" value="Create account">
</form>
<br>
<% if (request.getAttribute("msg") != null) {%>
    <div><%= request.getAttribute("msg") %></div>
<% } %>
<br>
