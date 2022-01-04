package service;

import data.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;


public class SessionManager {

    static public void initSession(HttpSession session)
    {
        if(session.getAttribute("cartService") == null)
            session.setAttribute("cartService", new CartService(new ArrayList<>()));
    }

    static public Boolean isSessionValid(HttpSession session, String securityLevel) {

        Object token = session.getAttribute("jwt");

        if (token == null) {
            return false;
        }

        String[] chunks = token.toString().split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        String signature = new String(decoder.decode(chunks[2]));

        String secret = "czvFbg2kmvqbcu(7Ux+c";
        SignatureAlgorithm sa = HS256;

        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), sa.getJcaName());
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            return false;
            //throw new Exception("Could not verify JWT token integrity!");
        } else {
            return true;
        }

        /*
        try {
            Jws<Claims> jwtClaims = validateJWT(signature);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         */
    }

    /**
     * verify jwt signature
     * @param jwtString : the token to validate
     * @return
     */
    static public boolean validateJWT(String jwtString) throws Exception {

        String secret = "czvFbg2kmvqbcu(7Ux+c";

        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), sa.getJcaName());

        /*
        try {
            String secret = "czvFbg2kmvqbcu(7Ux+c";

            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret.getBytes()),
                    SignatureAlgorithm.HS256.getJcaName());

            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);
            return jwt;

        } catch (Exception e) {
            throw new Exception();
        }

         */

    return true;

    }


}
