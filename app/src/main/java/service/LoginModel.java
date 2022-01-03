package service;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class LoginModel {
    private String loginURL = "http://localhost:9090/auth/login";
    private String registerURL = "http://localhost:9090/accounts/register";

    /**
     * login function
     *
     * @param username : the username
     * @param password : the password
     *
     * @return the response body if valid,
     *         null if invalid
     */
    public String login(String username, String password) {
        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";
        String[] response;

        try {
           response = doPost(request, loginURL);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        // return null in case of invalid credential
        if (Integer.parseInt(response[1]) != 200) {
            return null;
        }

        return response[0];
    }

    /**
     * register function
     *
     * @param username : the username
     * @param password : the password
     *
     * @return
     */
    public Boolean register(String username, String password) {
        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        String[] response;
        try {
            response = doPost(request, registerURL);

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        // return false in case of invalid credential
        if (Integer.parseInt(response[1]) != 201) {
            return false;
        }

        return true;
    }

    /**
     * Perform a post request to the given url
     *
     * @param string : the request
     * @param stringUrl : the destination url
     *
     * @return a String[] array,
     *          array[0] = the post response
     *          array[1] = the response code
     *
     * @throws Exception
     */
    public String[] doPost(String string, String stringUrl) throws Exception {
        String[] response = {"", ""};

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

            response[1] = Integer.toString(con.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder res = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                res.append(responseLine.trim());
            }
            response[0] = res.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return response;
    }

    public String parse(String response) {
        Object obj = JSONValue.parse(response);
        JSONObject jsonObj = (JSONObject)obj;

        return (String)jsonObj.get("token");
    }
}
