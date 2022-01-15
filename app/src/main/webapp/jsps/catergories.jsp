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


<%@include file="footer.jsp"%>