package service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;


public class AuthorizationService {

    static public void initSession(HttpSession session)
    {
        if(session.getAttribute("cartService") == null)
            session.setAttribute("cartService", new CartService(new ArrayList<>()));
    }

    static public Jws<Claims> parseJWT(String token) {
        String secret = "2jXSDPoSuJmhXLBhhoYZgxHmonoeOOg40zdpnVMkCGI=";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parser()
                .setSigningKey(hmacKey)
                .parseClaimsJws(token);

        return jwt;
    }

    static public Boolean isUserAllowed(String ressource, String token) throws Exception {
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
            throw new Exception();
        }

        if (Integer.parseInt(response[1]) != 200) {
            throw new Exception();
        }

        return true;
    }

}
