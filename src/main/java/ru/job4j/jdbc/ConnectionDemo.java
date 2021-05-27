package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalStateException("Path to data not specified. args.length = 0");
        }
        try (BufferedReader readProp = new BufferedReader(new FileReader(args[0]))) {
            Properties properties = new Properties();
            properties.load(readProp);
            Class.forName(properties.getProperty("psql.driver"));
            String url = properties.getProperty("psql.url");
            String login = properties.getProperty("psql.login");
            String password = properties.getProperty("psql.password");
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
