package com.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDataReader {

    public static List<String> getUrlsFromDatabase() {
        List<String> urls = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdata", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT url FROM url_table");

            while (resultSet.next()) {
                urls.add(resultSet.getString("url"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urls;
    }
}
