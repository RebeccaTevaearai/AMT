package controller;

import data.Category;
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

@WebServlet(name = "AdminCategoriesServlet", value = "/categories")
public class AdminCategoriesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuth(req)) {
            ArrayList<Category> categories = new CategoryQueries().getAllCategory();

            req.setAttribute("categories", categories);
            req.getRequestDispatcher("WEB-INF/jsps/categories.jsp").forward(req, resp);
        }else{
            req.setAttribute("msg", "error: access denied");
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    private boolean checkAuth(HttpServletRequest req) {
        HttpSession session = req.getSession();
        try {
            if (AuthorizationService.isUserAllowed("categories.jsp", session.getAttribute("jwt").toString())) {
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

}
