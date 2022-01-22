<%@ page import="data.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="data.Article" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 15.01.2022
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<% Category category = (Category) request.getAttribute("category");%>
<br><br>
<h1>Categorie</h1>
<form action="<%=application.getContextPath() %>/categories/<% if(category != null){ %><%=category.getId()%><% } %>" method="post">
    <% if(category != null){ %>
    <input name="id" value="<%=category.getId()%>" hidden>
    <br>
    <h3><%=category.getId()%></h3>
    <br>
    <% } %>
    <input name="name" value="<% if(category != null){ %><%=category.getName()%><% } %>" type="text">
    <button class="button" type="submit">Enregister</button>
</form>
<br><br>
<% if(category != null){ %>
<form action="<%=application.getContextPath() %>/categories/delete/<%=category.getId()%>" method="post">
    <button class="button" type="submit" >Supprimer</button>
</form>
<br><br>
<% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");%>
<h1>Article affecté par la suppression</h1>
<% for(Article article : articles){ %>
<br>
<div>
    <p>Id : <%=article.getId()%></p>
    <p>Nom : <%=article.getName()%></p>
</div>

<%}%>
<% } %>

<%@include file="footer.jsp"%>