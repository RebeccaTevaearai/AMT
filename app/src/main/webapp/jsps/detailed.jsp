<%@ page import="data.Article" %>
<%@ page import="data.Image" %>
<%@ page import="data.Category" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 22.11.2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<% Article article = (Article) request.getAttribute("article");%>
<div id="main">
    <div class="cl">&nbsp;</div>
    <!-- Content -->
    <div id="content">
        <div class="product-detail">
        <% if(article != null){ %>
        <h1><%= article.getName()%> </h1>
        <ul>
            <%if(article.getCategories().isEmpty()){%>
            <%= "<li>Aucune</li>" %>
            <%}for (Category category : article.getCategories())
            {%>
            <li>
                <p><%=category.getName()%></p>
            </li>
            <%}%>
        </ul>
        <p>Prix : <%= article.getPrice()%> CHF</p>
        <p>Quantité réstante : <%=article.getQuantity()%> </p>
        <p><%= article.getDescription()%></p>
        <ul>
            <%if(article.getImages().isEmpty()){%>
            <%= "<li><img src=\"css/images/default.png\" /></li>" %>
            <%}for (Image image : article.getImages())
                {%>
                <li>
                    <img src="<%=application.getContextPath() %>/css/images<%=image.getPath()%>" />
                </li>
            <%}%>
        </ul>
        <%}else {%>
        <%="L'article recherché n'existe pas"%>
        <% }%>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
