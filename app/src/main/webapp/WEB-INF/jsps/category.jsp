<%@ page import="data.Category" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 15.01.2022
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<% Category category = (Category) request.getAttribute("category");%>
<h1>Categorie</h1>
<form action="<%=application.getContextPath() %>/categories/<% if(category != null){ %><%=category.getId()%><% } %>" method="post">
    <% if(category != null){ %>
    <input name="id" value="<%=category.getId()%>" hidden>
    <h3><%=category.getId()%></h3>
    <% } %>
    <input name="name" value="<% if(category != null){ %><%=category.getName()%><% } %>" type="text">
    <button class="button" type="submit">Enregister</button>
</form>
<% if(category != null){ %>
<form action="<%=application.getContextPath() %>/categories/delete/<%=category.getId()%>" method="post">
    <button class="button" type="submit" >Supprimer</button>
</form>
<% } %>

<%@include file="footer.jsp"%>