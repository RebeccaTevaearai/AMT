
<%@ page import="service.SessionManager" %>

<% if (!SessionManager.checkJWT(session.getAttribute("jwt").toString())) {
    request.getRequestDispatcher("/").forward(request, response);
}%>
<br>
<h1>Test Page</h1>
<br>
<br>
<br>
<% if (request.getAttribute("msg") != null) {%>
<div><%= request.getAttribute("msg") %></div>
<% } %>
<br>