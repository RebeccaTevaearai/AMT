<br>
<h1>Account creation</h1>
<br>
<form action="${pageContext.request.contextPath}/register" method="POST">
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
