package controller;

import data.Article;
import service.ArticleQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "articleDetailedServlet", urlPatterns = "/articles/*")
public class ArticleDetailedController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] stringId = req.getPathInfo().split("/");

        Integer id = Integer.parseInt(stringId[1]);
        Article article = new ArticleQueries().getArticleById(id.longValue());

        req.setAttribute("article",article);
        req.getRequestDispatcher("../jsps/detailed.jsp").forward(req,resp);
    }
}
