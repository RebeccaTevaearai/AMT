package Model;

import java.net.URL;

public interface LoginInterface {


    public String login(String username, String password);

    public String register(String username, String password);

    public String doPost(String string, String stringUrl) throws Exception;
}
