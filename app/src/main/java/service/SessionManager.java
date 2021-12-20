package service;

import data.Account;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class SessionManager {

    static public void initSession(HttpSession session)
    {
        if(session.getAttribute("cartService") == null)
            session.setAttribute("cartService", new CartService(new ArrayList<>()));
    }
}
