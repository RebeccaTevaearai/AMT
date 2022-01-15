package controller;

import data.Article;
import service.ArticleQueries;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller that manage articles details
 */
@WebServlet(name = "articleDetailedServlet", urlPatterns = "/articles/*")
public class ArticleDetailedController extends HttpServlet {

    /**
     * ArticleDetailedController init
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Get article's details
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String[] stringId = req.getPathInfo().split("/");
        int id = 0;
        try {
            id = Integer.parseInt(stringId[1]);
        }catch (Exception e) {}

        Article article = new ArticleQueries().getArticleById((long) id);

        req.setAttribute("article",article);
        req.getRequestDispatcher("../jsps/detailed.jsp").forward(req,resp);
    }
}
