package service;

import data.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class SessionManager {

    static public void initSession(HttpSession session)
    {
        if(session.getAttribute("cartService") == null)
            session.setAttribute("cartService", new CartService(new ArrayList<>()));
    }

    /**
     * verify jwt validity
     * @param jwt : the token to verify
     * @return true if correct
     *         false if not correct
     */
    static public Boolean checkJWT(String jwt) {

        //TODO

        return true;
    }
}
