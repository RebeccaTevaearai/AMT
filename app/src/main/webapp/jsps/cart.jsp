<%@ page import="data.Article" %>
<%@ page import="data.CartArticle" %>
<%@include file="header.jsp"%>
<script>
	let nbElement = <%=cartService.getArticles().size()%>;

	function updateTotal(id){
		let quantity = document.getElementById("quantity"+id).value;
		let price = document.getElementById("price"+id).innerHTML;
		let totalArticle = document.getElementById("total"+id).innerHTML;
		let total = document.getElementById("total").innerHTML;
		let totalDouble = parseFloat(quantity) * parseFloat(price);
		let diff = totalDouble - parseFloat(totalArticle);
		total = parseFloat(total) + diff;
		let fees =parseFloat(document.getElementById("fees").innerHTML);
		document.getElementById("total").innerHTML = (total).toFixed(1) + " CHF";
		document.getElementById("total"+id).innerHTML = (totalDouble).toFixed(1) + " CHF";
		document.getElementById("totalWithFees").innerHTML = (total+fees).toFixed(1) + " CHF";
	}
	let parent;
	function deleteArticle(id)
	{
		let totalArticle = document.getElementById("total"+id).innerHTML;
		let total = document.getElementById("total").innerHTML;
		total = parseFloat(total) - parseFloat(totalArticle);
		let fees =parseFloat(document.getElementById("fees").innerHTML);
		document.getElementById("total").innerHTML = (total).toFixed(1) + " CHF";
		document.getElementById("totalWithFees").innerHTML = (total+fees).toFixed(1) + " CHF";
		let article = document.getElementById(id);
		console.log(nbElement);
		if(nbElement === 1)
		{
			parent = article.parentNode.parentNode.parentNode;
			parent.removeChild(article.parentNode.parentNode);
			parent.appendChild(document.createTextNode('Votre panier est vide'));

		}else{
			article.parentNode.removeChild(article);
		}
		nbElement--;
	}
</script>
			<!-- Main -->
			<div id="main">

				<div class="cartContainer">
					<%if(cartService.getArticles().isEmpty()){%>
					<%="Votre panier est vide"%>
					<%}else{%>
					<form method="post" action="<%=application.getContextPath() %>/cart/update">
						<div class="listArticles">
							<table id="summary">
								<tr>
									<th class="title">Article</th>
									<th class="title">Prix</th>
									<th class="title">Quantité</th>
									<th class="title">Total</th>
									<th class="title">Action</th>
								</tr>

								<%for(CartArticle cartArticle : cartService.getArticles()){%>
									<tr id="<%=cartArticle.getArticle().getId()%>">
										<td class="cartImg">
											<section class="articleName"><%=cartArticle.getArticle().getName()%></section>
											<section class="articleImgCont">
												<% %>
												<img class="articleImg" src="<%=application.getContextPath() %>/css/images/<% if(!cartArticle.getArticle().getImages().isEmpty()){ %><%=cartArticle.getArticle().getImages().get(0).getPath()%><% }else{ %><%="/default.png"%><% } %>" alt="image">
											</section>
										</td>
										<td><p id="price<%=cartArticle.getArticle().getId()%>"><%=cartArticle.getArticle().getPrice()%> CHF</p></td>
										<td>
											<input id="quantity<%=cartArticle.getArticle().getId()%>" name="quantity<%=cartArticle.getArticle().getId()%>" onchange="updateTotal(<%=cartArticle.getArticle().getId()%>)" type="number" class="" name="quantity" min="1" max="10" value="<%=cartArticle.getQuantity()%>" size="4"/>
										</td>
										<td>
											<p id="total<%=cartArticle.getArticle().getId()%>"><%=cartArticle.getArticle().getPrice()*cartArticle.getQuantity()%> CHF</p>
										</td>
										<td>
											<button class="button delButn" onclick="deleteArticle(<%=cartArticle.getArticle().getId()%>)" type="submit" value="Supprimer"/>
										</td>
									</tr>
								<%}%>
							</table>
						</div>

						<button class="button checkoutBtn" type="submit" >Enregister</button>
					</form>
					<div class="checkout">
						<table>
							<tr>
								<td class="title">Total articles: </td>
								<td id="total"><%=cartService.total()%> CHF</td>
							</tr>
							<tr>
								<td class="title">Frais de port: </td>
								<td id="fees">8.9 CHF</td>
							</tr>
							<tr><td></td></tr>
							<tr>
								<td class="title">Total: </td>
								<td id="totalWithFees"><%=cartService.total()+8.90%> CHF</td>
							</tr>						
						</table>

						<form method="get" action="<%=application.getContextPath() %>/checkout">
							<button class="button checkoutBtn" type="submit" >Payer</button>
						</form>
					</div>
					<%}%>
				</div>
				<!----  End Main        -->

<%@include file="footer.jsp"%>