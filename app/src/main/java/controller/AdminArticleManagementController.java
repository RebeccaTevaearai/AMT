package controller;

import service.AddArticleService;
import service.ImageQueries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet (name="ArticleManagementServlet", value="/articleManagement")
public class AdminArticleManagementController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            //if (AuthorizationService.isUserAllowed("/articleManagement",
            //        session.getAttribute("jwt").toString())) {

                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String quantity = req.getParameter("quantity");
                String price = req.getParameter("price");

                if (quantity == null) {
                    quantity = "0";
                }

                if (price == null) {
                    price = "0";
                }

                //verifie les paramètres
                if (AddArticleService.verifyName(name)
                        && AddArticleService.verifyDescription(description)
                        && AddArticleService.verifyQuantity(quantity)
                        && AddArticleService.verifyPrice(price) ) {

                    //ajouter dans la db
                    try {
                        Long id = AddArticleService.addArticle(name, description, price, quantity);

                        if (price.equals("0")) {
                            // ajouter watermark preview
                            ImageQueries.addImageToArticle(id, "/big/preview.jpg");

                        } else if (quantity.equals("0")) {
                            // ajouter image watermark indisponible
                            ImageQueries.addImageToArticle(id, "/big/indisponible.jpg");
                        }

                    } catch(Exception e) {
                        req.setAttribute("msg", "error: could not insert into database");
                        req.getRequestDispatcher("/management").forward(req, resp);
                    }

                    //retourner un message de confirmation
                    req.setAttribute("msg", "success: Article added");
                    req.getRequestDispatcher("/management").forward(req, resp);

                } else {
                    req.setAttribute("msg", "error: invalid parameters");
                    req.getRequestDispatcher("/management").forward(req, resp);
                }
/*
            } else {
                req.setAttribute("msg", "error: access denied");
                req.getRequestDispatcher("/").forward(req, resp);
            }
 */
        } catch (Exception e) {
            req.setAttribute("msg", "error: token not valid");
            req.getRequestDispatcher("/home").forward(req, resp);
        }

    }
}
