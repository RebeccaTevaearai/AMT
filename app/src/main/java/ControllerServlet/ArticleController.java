package ControllerServlet;

import Data.Article;
import Data.Category;
import Model.ArticleModel;
import Model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

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
        for(String s : filter.split(";"))
        {
            Category c = cm.getCategoryByName(s);
            if(c != null)
            {
                categoriesFilter.add(c);
            }
        }
        ArrayList<Article> articles = articleModel.getArticleByCatergories(new Category[0]);

        HttpSession session = req.getSession();
        session.setAttribute("id",1);
        req.setAttribute("name", articles.get(0).getName());
        req.setAttribute("description", articles.get(0).getDescription());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
