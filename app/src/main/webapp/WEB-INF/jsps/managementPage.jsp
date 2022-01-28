<br>
<h1>Articles management</h1>
<br>
<br>
<h2>Add an article</h2>
<br>

<form class="formulaire" action="${pageContext.request.contextPath}/articleManagement" method="POST">
    <label>Name</label><br>
    <input type="text" name="name" maxlength="250" required> <label>required</label> <br>
    <label>Description</label><br>
    <input type="text" name="description" maxlength="250" required> <label>required</label> <br>
    <label>Price CHF</label><br>
    <input type="number" name="price" min="0" max="100000000"> <br>
    <label>Quantity</label> <br>
    <input type="number" name="quantity" min="0" max="10000000"> <br>
    <input type="submit" value="Submit">
</form>

<br>
<br>
<% if (request.getAttribute("msg") != null) {%>
<div><%= request.getAttribute("msg") %></div>
<% } %>
<br>
<h2>Add an Image to an Article</h2>
<br>

<form class="formulaire" enctype="multipart/form-data" action="${pageContext.request.contextPath}/articleImage" method="POST">
    <label>Article id</label><br>
    <input type="number" name="id" min="0" required><br>
    <label>Image</label> <br>
    <input type="file" name="image" required> <br><br>
    <input type="submit" value="Submit">
</form>

<br>
<h2>Add an Category to an Article</h2>
<br>

<form class="formulaire" action="${pageContext.request.contextPath}/articleCategory" method="POST">
    <label>Article id</label><br>
    <input type="number" name="idArticle"><br>
    <label>Category id</label> <br>
    <input type="number" name="idCategory"> <br><br>
    <input type="submit" value="Submit">
</form>
<br>

