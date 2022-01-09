package service;


public class LoginService {

    /**
     * login function
     *
     * @param username : the username
     * @param password : the password
     *
     * @return the response body if valid,
     *         null if invalid
     */
    static public String login(String username, String password) {

        final String loginURL = "http://localhost:8181/auth/login";

        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";
        String[] response;

        try {
           response = HttpService.doPost(request, loginURL);

        } catch (Exception e){
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
    static public Boolean register(String username, String password) {

        final String registerURL = "http://localhost:8181/accounts/register";

        String request =
                "{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}";

        String[] response;
        try {
            response = HttpService.doPost(request, registerURL);

        } catch (Exception e){
            return false;
        }

        // return false in case of invalid credential
        if (Integer.parseInt(response[1]) != 201) {
            return false;
        }

        return true;
    }

}
