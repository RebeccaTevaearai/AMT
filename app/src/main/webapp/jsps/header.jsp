<%@ page import="service.CartService" %>
<head>
    <title>PECHEUR</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <link href="<%=application.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet" media="all">
    <link href="<%=application.getContextPath() %>/css/cart.css" type="text/css" rel="stylesheet" media="all">

    <!-- <link rel="stylesheet" href="/css/style.css" type="text/css" media="all" /> -->
    <!--[if lte IE 6]><link rel="stylesheet" href="<%=application.getContextPath() %>/css/ie6.css" type="text/css" media="all" /><![endif]-->
    <script src="<%=application.getContextPath() %>/js/jquery-1.4.1.min.js" type="text/javascript"></script>
    <script src="<%=application.getContextPath() %>/js/jquery.jcarousel.pack.js" type="text/javascript"></script>
    <script src="<%=application.getContextPath() %>/js/jquery-func.js" type="text/javascript"></script>
</head>
<body>
<!-- Shell -->
<div class="shell">
    <!-- Header -->
    <div id="header">
        <h1 id="logo"><a href="#">shoparound</a></h1>
        <!-- Cart -->
        <div id="cart"> <a href="<%=application.getContextPath() %>/cart" class="cart-link">Votre panier</a>
            <div class="cl">&nbsp;</div>
            <% CartService cartService = (CartService) request.getAttribute("cartService");%>
            <span>Articles: <strong><%=cartService.articlesQuantity()%></strong></span> &nbsp;&nbsp; <span>Cost: <strong><%=cartService.total()%> CHF</strong></span> </div>
        <!-- End Cart -->
        <!-- Navigation -->
        <div id="navigation">
            <ul>
                <li><a href="<%=application.getContextPath() %>">Home</a></li>
                <li><a href="<%=application.getContextPath() %>/loginn">Login</a></li>
                <li><a href="<%=application.getContextPath() %>/accounttt">My Account</a></li>
                <li><a href="<%=application.getContextPath() %>/test">The Test</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
        <!-- End Navigation -->
    </div>
    <!-- End Header -->