package controller;


import service.ArticleQueries;
import service.CartService;
import service.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        SessionManager.initSession(session);
        req.setAttribute("cartService", session.getAttribute("cartService"));

        req.getRequestDispatcher("./jsps/cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        SessionManager.initSession(session);

        ((CartService) session.getAttribute("cartService")).addArticle(
                new ArticleQueries().getArticleById((long) Integer.parseInt(req.getParameter("id"))),
                Integer.parseInt(req.getParameter("quantity"))
        );

        resp.sendRedirect(req.getRequestURI());
    }
}
