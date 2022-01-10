package controller;


import service.ArticleQueries;
import service.CartService;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller that manage the cart
 */
@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartController extends HttpServlet {

    /**
     * Get cart data
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.getRequestDispatcher("./jsps/cart.jsp").forward(req,resp);
    }

    /**
     * Add articles to cart
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ((CartService) session.getAttribute("cartService")).addArticle(
                new ArticleQueries().getArticleById((long) Integer.parseInt(req.getParameter("id"))),
                Integer.parseInt(req.getParameter("quantity"))
        );

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
