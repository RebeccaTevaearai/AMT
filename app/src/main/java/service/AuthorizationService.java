package service;

import data.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;


public class AuthorizationService {

    static public void initSession(HttpSession session)
    {
        if(session.getAttribute("cartService") == null)
            session.setAttribute("cartService", new CartService(new ArrayList<>()));
        ((CartService) session.getAttribute("cartService")).sync();
    }

    static public void setCartAccount(HttpSession session, JSONObject account)
    {
        ((CartService) session.getAttribute("cartService")).setAccount(new Account((Long)account.get("id"),
                (String) account.get("username"),(String) account.get("role")));
    }

    static public void resetCart(HttpSession session)
    {
        session.setAttribute("cartService", new CartService(new ArrayList<>()));
    }


    static public Boolean isUserAllowed(String ressource, String token) {
        String authorizationURL = "http://localhost:8181/session/authorization";

        String request =
                "{\n" +
                        "  \"ressource\": \"" + ressource + "\",\n" +
                        "  \"token\": \"" + token + "\"\n" +
                        "}";
        String[] response;

        try {
            response = HttpService.doPost(request, authorizationURL);

        } catch (Exception e){
            return false;
        }

        return Integer.parseInt(response[1]) == 200;
    }

}
