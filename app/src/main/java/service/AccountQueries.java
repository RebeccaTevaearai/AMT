package service;

import data.Account;
import data.Article;
import data.Category;
import data.Image;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountQueries {

    public Account getAccountById(Long id)
    {
        Account account = null;
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Account` WHERE `id` = ?",
                    new ArrayList<String>() {{ add(id.toString()); }});

            if(resultSet.next())
                account = new Account(resultSet.getLong("id"),resultSet.getString("email"),
                        resultSet.getString("role"));
            else{
                return null;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return account;
    }

    public boolean createAccount(Long id, String email, String role)
    {
        Account account = null;
        try {
            DatabaseConnection.doQueryUpdate("INSERT INTO `Account` (`id`, `email`, `role`) VALUES (?, ?, ?);",
                    new ArrayList<String>() {{ add(id.toString()); add(email); add(role); }});

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
