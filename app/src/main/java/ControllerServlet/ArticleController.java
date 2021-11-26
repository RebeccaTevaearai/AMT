package ControllerServlet;

import Data.Article;
import Model.ArticleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "articleServlet", value = "")
public class ArticleController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleModel articleModel = new ArticleModel();
        Article article = null;

        try {
            article = articleModel.getArticleByName("Super Canne");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("id",1);
        req.setAttribute("name",article.getName());
        req.setAttribute("description",article.getDescription());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
