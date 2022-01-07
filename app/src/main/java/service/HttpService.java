package service;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpService {

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
    static public String[] doPost(String string, String stringUrl) throws Exception {
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

            return response;

        } catch (Exception e) {
            throw new Exception();
        }
    }

}
