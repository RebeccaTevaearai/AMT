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


@WebServlet(name = "AdminCategoryDeleteServlet", value = "/categories/delete/*")
public class AdminCategoryDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if(AuthorizationService.isUserAllowed("category.jsp", session.getAttribute("jwt").toString())) {
                String[] stringId = req.getPathInfo().split("/");

                try {
                    int id = Integer.parseInt(stringId[1]);
                    new CategoryQueries().deleteCategory((long) id);
                } catch (Exception e) {
                }

                resp.sendRedirect(req.getContextPath() + "/categories");
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
}
