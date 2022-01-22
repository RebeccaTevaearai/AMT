package controller;

import data.Article;
import data.Category;
import service.ArticleQueries;
import service.AuthorizationService;
import service.CategoryQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminCategoryServlet", value = "/categories/*")
public class AdminCatergoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if(AuthorizationService.isUserAllowed("category.jsp", session.getAttribute("jwt").toString())) {

                String[] stringId = req.getPathInfo().split("/");

                Category category = null;
                ArrayList<Article> articles = new ArrayList<>();
                int id = 0;
                try {
                    id = Integer.parseInt(stringId[1]);
                    category = new CategoryQueries().getCategoryById((long) id);
                    Category finalCategory = category;
                    articles = new ArticleQueries().getArticleByCatergories(new ArrayList<Category>() {{add(finalCategory);}});
                } catch (Exception e) {}

                req.setAttribute("category", category);
                req.setAttribute("articles", articles);
                req.getRequestDispatcher("../WEB-INF/jsps/category.jsp").forward(req, resp);

            }else{
                req.setAttribute("msg", "error: access denied");
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "error: access denied");
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if(AuthorizationService.isUserAllowed("category.jsp", session.getAttribute("jwt").toString())) {

                if (req.getParameter("id") != null) {
                    if (!new CategoryQueries().updateCategory((long) Integer.parseInt(req.getParameter("id")), req.getParameter("name"))) {
                        ArrayList<Category> categories = new CategoryQueries().getAllCategory();

                        req.setAttribute("categories", categories);
                        req.setAttribute("msg", "error: doublon");
                        req.getRequestDispatcher("../WEB-INF/jsps/categories.jsp").forward(req, resp);
                    } else {
                        ArrayList<Category> categories = new CategoryQueries().getAllCategory();

                        req.setAttribute("categories", categories);
                        req.setAttribute("msg", "success: Category modify");
                        req.getRequestDispatcher("../WEB-INF/jsps/categories.jsp").forward(req, resp);
                    }
                } else {
                    if (!new CategoryQueries().createCategory(req.getParameter("name"))) {
                        ArrayList<Category> categories = new CategoryQueries().getAllCategory();

                        req.setAttribute("categories", categories);
                        req.setAttribute("msg", "error: doublon");
                        req.getRequestDispatcher("../WEB-INF/jsps/categories.jsp").forward(req, resp);
                    } else {
                        ArrayList<Category> categories = new CategoryQueries().getAllCategory();

                        req.setAttribute("categories", categories);
                        req.setAttribute("msg", "success: Category added");
                        req.getRequestDispatcher("../WEB-INF/jsps/categories.jsp").forward(req, resp);
                    }
                }
            }else{
                req.setAttribute("msg", "error: access denied");
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "error: access denied");
            resp.sendRedirect(req.getContextPath() + "/home");
        }

        resp.sendRedirect(req.getContextPath() + "/categories");
    }

    private boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if () {
                return true;
            }

        } catch (Exception e) {}
        return false;
    }
}
