package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class LoginModel implements LoginInterface {
    private String loginURL = "http://localhost:9090/auth/login";
    private String registerURL = "http://localhost:9090/accounts/register";

    @Override
    public String login(String username, String password) {
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
        }
        return response;
    }

    @Override
    public String register(String username, String password) {
        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";
        return null;
    }

    @Override
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
            response += code;
        }
        return response;
    }
}
