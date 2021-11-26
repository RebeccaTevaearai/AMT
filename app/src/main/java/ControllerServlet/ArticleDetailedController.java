package ControllerServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "articleDetailedServlet", value = "/article",
        initParams = {
            @WebInitParam(name = "id", value = "1"),
        }
)
public class ArticleDetailedController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.getInteger(getInitParameter("id"));

        req.setAttribute("id",id);
        req.getRequestDispatcher("jsps/detailed.jsp").forward(req,resp);
    }
}
