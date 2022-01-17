package controller;

import data.Article;
import data.Category;
import service.ArticleQueries;
import service.CategoryQueries;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller that manage the articles
 */
@WebServlet(name = "articleServlet", urlPatterns = "/home",
initParams = {
        @WebInitParam(name = "categories", value = "")
})
public class ArticleController extends HttpServlet {

    /**
     * ArticleController init
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Get articles by categories
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ArticleQueries articleQueries = new ArticleQueries();
        CategoryQueries cm = new CategoryQueries();

        ArrayList<Category> categories = cm.getAllCategory();
        String filter = req.getParameter("categories");
        ArrayList<Category> categoriesFilter = new ArrayList<>();
        if(filter != null) {
            for (String s : filter.split(";")) {
                Category c = cm.getCategoryByName(s);
                if (c != null) {
                    categoriesFilter.add(c);
                }
            }
        }
        ArrayList<Article> articles = articleQueries.getArticleByCatergories(categoriesFilter);

        req.setAttribute("categories", categories);
        req.setAttribute("articles", articles);

        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req,resp);
    }
}
