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

@WebServlet(name = "AdminCategoryServlet", value = "/categories/*")
public class AdminCatergoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuth(req, resp)) {

            String[] stringId = req.getPathInfo().split("/");

            Category category = null;
            int id = 0;
            try {
                id = Integer.parseInt(stringId[1]);
                category = new CategoryQueries().getCategoryById((long) id);
            } catch (Exception e) {
            }

            req.setAttribute("category", category);
            req.getRequestDispatcher("../jsps/category.jsp").forward(req, resp);
        }else{
            req.setAttribute("msg", "error: access denied");
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkAuth(req, resp);

        if(req.getParameter("id") != null)
            new CategoryQueries().updateCategory((long) Integer.parseInt(req.getParameter("id")), req.getParameter("name"));
        else
            new CategoryQueries().createCategory(req.getParameter("name"));

        resp.sendRedirect(req.getContextPath() + "/categories");
    }

    private boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if (AuthorizationService.isUserAllowed("category.jsp", session.getAttribute("jwt").toString())) {
                return true;
            }

        } catch (Exception e) {}
        return false;
    }
}
