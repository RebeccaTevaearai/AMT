<%@ page import="data.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 15.01.2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<% ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");%>
<br>
<h1>Categories</h1>
<br>
<% for(Category category : categories){ %>

    <div>
        <p>Id : <%=category.getId()%></p>
        <p>Nom : <%=category.getName()%></p>
        <a class="button" href="<%=application.getContextPath() %>/categories/<%=category.getId()%>">Modifier</a>
    </div>
    <br>

<%}%>

<a class="button" href="<%=application.getContextPath() %>/categories/">Nouvelle categorie</a>
<% if (request.getAttribute("msg") != null) {%>
<div><%= request.getAttribute("msg") %></div>
<% } %>

<%@include file="footer.jsp"%>