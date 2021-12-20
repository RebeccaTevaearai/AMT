package Model;

import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class LoginModel {
    private String loginURL = "http://localhost:9090/auth/login";
    private String registerURL = "http://localhost:9090/accounts/register";


    public Cookie login(String username, String password) {
        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";
        String response = "";

        try {
           response = doPost(request, loginURL);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        String token = parse(response);
        if (token == null) {
            return null;
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(30);
        return cookie;
    }


    public String register(String username, String password) {
        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        String response = "";
        try {
            response = doPost(request, registerURL);

        } catch (Exception e){
            e.printStackTrace();

        }
        return response;
    }


    public String doPost(String string, String stringUrl) throws Exception {
        String response= "";
        int code = 0;
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "*/*");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            byte[] input = string.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();
            os.close();

            code = con.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder res = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                res.append(responseLine.trim());
            }
            response = res.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
            //response += code;
        }
        return response;
    }

    private String parse(String response) {
        String token = null;
        Object obj = JSONValue.parse(response);

        JSONObject jsonObj = (JSONObject)obj;
        token = (String)jsonObj.get("token");

        return token;
    }
}
