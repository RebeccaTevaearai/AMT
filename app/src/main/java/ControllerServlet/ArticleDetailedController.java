package ControllerServlet;

import Data.Article;
import Model.ArticleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "articleDetailedServlet", urlPatterns = "/article",
        initParams = {
        @WebInitParam(name = "id", value = "")
})

public class ArticleDetailedController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");

        Integer id = Integer.parseInt(stringId);
        Article article = new ArticleModel().getArticleById(id.longValue());

        req.setAttribute("article",article);
        req.getRequestDispatcher("jsps/detailed.jsp").forward(req,resp);
    }
}
