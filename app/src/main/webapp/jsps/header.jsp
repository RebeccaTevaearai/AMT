<head>
    <title>Shop Around</title>
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
        <div id="cart"> <a href="#" class="cart-link">Your Shopping Cart</a>
            <div class="cl">&nbsp;</div>
            <span>Articles: <strong>4</strong></span> &nbsp;&nbsp; <span>Cost: <strong>$250.99</strong></span> </div>
        <!-- End Cart -->
        <!-- Navigation -->
        <div id="navigation">
            <ul>
                <li><a href="<%=application.getContextPath() %>" class="active">Home</a></li>
                <li><a href="#">Support</a></li>
                <li><a href="#">My Account</a></li>
                <li><a href="#">The Store</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
        <!-- End Navigation -->
    </div>
    <!-- End Header -->