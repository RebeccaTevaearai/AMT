package controller;

import data.Article;
import service.AddArticleService;
import service.ArticleQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleCategoryServlet", value = "/articleCategory")
public class ArticleCategoryController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idArticle = 0;
        int idCategory = 0;
        try {
            idArticle = Integer.parseInt(req.getParameter("idArticle"));
            idCategory = Integer.parseInt(req.getParameter("idCategory"));;

            if(AddArticleService.addCategoryToArticle((long) idArticle,(long) idCategory)) {
                req.setAttribute("msg", "success: Category added to the article");
                req.getRequestDispatcher("/management").forward(req, resp);
            }else{
                req.setAttribute("msg", "error: the category was already asigned");
                req.getRequestDispatcher("/management").forward(req, resp);
            }
        }catch (Exception e) {
            req.setAttribute("msg", "error: invalid parameters");
            req.getRequestDispatcher("/management").forward(req, resp);
        }


    }
}
