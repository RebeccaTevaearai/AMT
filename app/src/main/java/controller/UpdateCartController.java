package controller;

import data.Article;
import data.CartArticle;
import service.AuthorizationService;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cartUpdateServlet", urlPatterns = "/cart/update")
public class UpdateCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AuthorizationService.initSession(session);
        CartService cartService = ((CartService) session.getAttribute("cartService"));

        ArrayList<Article> toDel = new ArrayList<>();

        for(CartArticle cartArticle : cartService.getArticles())
        {
            if(req.getParameter("quantity" + cartArticle.getArticle().getId()) == null)
            {
                toDel.add(cartArticle.getArticle());
            }else if(Integer.parseInt(req.getParameter("quantity" + cartArticle.getArticle().getId()))
                    != cartArticle.getQuantity()){
                cartService.updateQuantity(cartArticle.getArticle(),
                        Integer.parseInt(req.getParameter("quantity" + cartArticle.getArticle().getId())));
            }
        }

        for(Article article : toDel)
        {
            cartService.deleteArticle(article);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
