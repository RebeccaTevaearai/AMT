<%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 22.11.2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div id="main">
    <div class="cl">&nbsp;</div>
    <!-- Content -->
    <div id="content">
        <h1>Detailed <%= (String)request.getAttribute("id")%></h1>
    </div>
</div>

<%@include file="footer.jsp"%>
