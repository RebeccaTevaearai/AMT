package controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.LoginService;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet that manage the login
 */
@WebServlet(name = "loginServlet", value = "/login")
public class LoginController extends HttpServlet {

    /**
     * LoginController init
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     *
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     *
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String loginResponse = LoginService.login(username, password);

        if (loginResponse == null) {
            req.setAttribute("msg", "error: bad credentials");
            req.getRequestDispatcher("/loginpage").forward(req,resp);

        } else {
            Object obj = JSONValue.parse(loginResponse);
            JSONObject jsonObj = (JSONObject)obj;

            if (jsonObj == null) {
                req.setAttribute("msg", "error: bad credentials");

                req.getRequestDispatcher("/loginpage").forward(req,resp);
            }else {

                String jwt = (String) jsonObj.get("token");
                JSONObject account = (JSONObject) jsonObj.get("account");
                session.setAttribute("username", username);
                session.setAttribute("jwt", jwt);

                AuthorizationService.setCartAccount(session, account);

                req.getRequestDispatcher("/account").forward(req, resp);
            }

        }
    }
}
