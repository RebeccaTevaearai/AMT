<%@ page import="data.Article" %>
<%@ page import="data.CartArticle" %>
<%@include file="header.jsp"%>
<script>
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
</script>
			<!-- Main -->
			<div id="main">

				<div class="cartContainer">
					<div class="listArticles">
						<%if(cartService.getArticles().isEmpty()){%>
						<%="Votre panier est vide"%>
						<%}else{%>
						<table id="summary">
							<tr>
								<th class="title">Article</th>
								<th class="title">Prix</th>
								<th class="title">Quantité</th>
								<th class="title">Total</th>
								<th class="title">Action</th>
							</tr>

							<%for(CartArticle cartArticle : cartService.getArticles()){%>
								<tr>
									<td class="cartImg">
										<section class="articleName"><%=cartArticle.getArticle().getName()%></section>
										<section class="articleImgCont">
											<% %>
											<img class="articleImg" src="<%=application.getContextPath() %>/css/images/<% if(!cartArticle.getArticle().getImages().isEmpty()){ %><%=cartArticle.getArticle().getImages().get(0).getPath()%><% }else{ %><%="/default.png"%><% } %>" alt="image">
										</section>
									</td>
									<td><p id="price<%=cartArticle.getArticle().getId()%>"><%=cartArticle.getArticle().getPrice()%> CHF</p></td>
									<td>
										<input id="quantity<%=cartArticle.getArticle().getId()%>" onchange="updateTotal(<%=cartArticle.getArticle().getId()%>)" type="number" class="" name="quantity" min="1" max="10" value="<%=cartArticle.getQuantity()%>" size="4"/>
									</td>
									<td>
										<p id="total<%=cartArticle.getArticle().getId()%>"><%=cartArticle.getArticle().getPrice()*cartArticle.getQuantity()%> CHF</p>
									</td>
									<td>
										<button class="button delButn" type="submit" value="Supprimer"/>
									</td>
								</tr>
							<%}%>
						</table>
						<%}%>
					</div>
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
				</div>
				<!----  End Main        -->

<%@include file="footer.jsp"%>