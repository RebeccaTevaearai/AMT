package controller;

import data.Article;
import data.Category;
import service.ArticleModel;
import service.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "articleServlet", value = "",
initParams = {
        @WebInitParam(name = "categories", value = "")
})
public class ArticleController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArticleModel articleModel = new ArticleModel();
        CategoryModel cm = new CategoryModel();

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
        ArrayList<Article> articles = articleModel.getArticleByCatergories(categoriesFilter);

        req.setAttribute("categories", categories);
        req.setAttribute("articles", articles);
        ArticleDetailedController a = new ArticleDetailedController();
        a.doGet(req,resp);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
