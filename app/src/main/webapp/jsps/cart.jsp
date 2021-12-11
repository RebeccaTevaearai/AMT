<%@include file="header.jsp"%>
			<!-- Main -->
			<div id="main">

				<div class="cartContainer">
					<div class="listArticles">
						<table id="summary">
							<tr>
								<th class="title">Article</th>
								<th class="title">Prix</th>
								<th class="title">Quantité</th>
								<th class="title">Total</th>
							</tr>
							<tr>
								<td class="cartImg">
									<section class="articleName">[Link]Article name</section>
									<section class="articleImgCont">
										<img class="articleImg" src="css/images/big1.jpg" alt="image">
									</section>
								</td>
								<td>12.00 CHF</td>
								<td>
									<input type="number" class="" name="quantity" min="1" max="10" value="1" size="4">
								</td>
								<td>24 CHF</td>
								<td>
									<button class="button delButn" type="submit" value=""/>
								</td>
							</tr>
						</table>
					</div>
					<div class="checkout">
						<table>
							<tr>
								<td class="title">Total articles: </td>
								<td>100 CHF</td>
							</tr>
							<tr>
								<td class="title">Frais de port: </td>
								<td>8.90 CHF</td>
							</tr>
							<tr><td></td></tr>
							<tr>
								<td class="title">Total: </td>
								<td>200 CHF</td>
							</tr>						
						</table>
						<input class="button checkoutBtn" type="submit" value="checkout"/>
					</div>
				</div>
				<!----  End Main        -->

<%@include file="footer.jsp"%>