<%@ page import="data.Category" %>
<%@ page import="data.Article" %>
<%@ page import="java.util.ArrayList" %>
<!-- Main -->
  <div id="main">
    <div class="cl">&nbsp;</div>
    <!-- Content -->
    <div id="content">
      <!-- Content Slider -->
      <div id="slider" class="box">
        <div id="slider-holder">
          <ul>
            <li><a href="#"><img src="css/images/slide1.jpg" alt="" /></a></li>
            <li><a href="#"><img src="css/images/slide1.jpg" alt="" /></a></li>
            <li><a href="#"><img src="css/images/slide1.jpg" alt="" /></a></li>
            <li><a href="#"><img src="css/images/slide1.jpg" alt="" /></a></li>
          </ul>
        </div>
        <div id="slider-nav"> <a href="#" class="active">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> </div>
      </div>
      <!-- End Content Slider -->
      <!-- Products -->
      <div class="products">
        <div class="cl">&nbsp;</div>
        <ul>

          <% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");%>
          <% if (articles.isEmpty()){%>
          <%= "Aucun article trouvé"%>
          <% }for(Article article : articles){ %>


          <li><a href="<%=application.getContextPath() %>/articles/<%= article.getId() %>">
            <img src="css/images<% if(!article.getImages().isEmpty()){ %><%=article.getImages().get(0).getPath()%><% }else{ %><%="/default.png"%><% } %>"
            salt="" /></a>
            <div class="product-info">
              <h3><% article.getName(); %></h3>
              <div class="product-desc">
                <h4>
                  <% if(!article.getCategories().isEmpty()){ %>
                    <%= article.getCategories().get(0).getName() %>
                  <% }else{ %>
                    <%= "None" %>
                  <%} %>
                </h4>
                <p><%= article.getDescription() %></p>
                <strong class="price"><%= article.getPrice() %> CHF</strong> </div>
                <form name="addToCartSubmit" method="post" action="<%=application.getContextPath() %>/cart">
                  <input name="id" value="<%=article.getId()%>" hidden>
                  <input name="quantity" value="1" hidden/>
                  <button class="button" type="submit">Ajouter au panier</button>
                </form>
            </div>
          </li>
          <% } %>
        </ul>
        <div class="cl">&nbsp;</div>
      </div>
      <!-- End Products -->
    </div>
    <!-- End Content -->
    <!-- Sidebar -->
    <div id="sidebar">
      <!-- Search -->
      <div class="box search">
        <h2>Search by <span></span></h2>
        <div class="box-content">
          <form action="#" method="post">
            <label>Keyword</label>
            <input type="text" class="field" />
            <label>Category</label>
            <select class="field">
              <option value="">-- Select Category --</option>
            </select>
            <div class="inline-field">
              <label>Price</label>
              <select class="field small-field">
                <option value="">$10</option>
              </select>
              <label>to:</label>
              <select class="field small-field">
                <option value="">$50</option>
              </select>
            </div>
            <input type="submit" class="search-submit" value="Search" />
            <p> <a href="#" class="bul">Advanced search</a><br />
              <a href="#" class="bul">Contact Customer Support</a> </p>
          </form>
        </div>
      </div>
      <!-- End Search -->
      <!-- Categories -->
      <div class="box categories">
        <h2>Categories <span></span></h2>
        <div class="box-content">
          <ul>
            <% ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
              for(Category category : categories){ %>
            <li value="<%= category.getId() %>"><a href="?categories=<%= category.getName() %>"><%= category.getName() %></a></li>
            <% } %>
          </ul>
        </div>
      </div>
      <!-- End Categories -->
    </div>
    <!-- End Sidebar -->
    <div class="cl">&nbsp;</div>
  </div>
  <!-- End Main -->
  <!-- Side Full -->