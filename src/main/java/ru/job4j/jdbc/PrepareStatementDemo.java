package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemo {

    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        try (BufferedReader readProp = new BufferedReader(new FileReader(
                "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\app.properties"))) {
            Properties properties = new Properties();
            properties.load(readProp);
            Class.forName(properties.getProperty("psql.driver"));
            String url = properties.getProperty("psql.url");
            String login = properties.getProperty("psql.login");
            String password = properties.getProperty("psql.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(Wheel wheel) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "insert into wheels(radius) values (?)")) {
            statement.setInt(1, wheel.getRadius());
            result = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Wheel wheel) {
        boolean result = false;
        try (PreparedStatement statement =
             connection.prepareStatement(
             "update wheels set radius = ? where id = ?")) {
            statement.setInt(1, wheel.getRadius());
            statement.setInt(2, wheel.getId());
            result = statement.executeUpdate() > 0;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "delete from wheels where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Wheel> findAll() {
        List<Wheel> wheel = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "select * from wheels")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    wheel.add(new Wheel(
                            resultSet.getInt("id"),
                            resultSet.getInt("radius")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wheel;
    }

    public static void main(String[] args) throws Exception {
        PrepareStatementDemo psd = new PrepareStatementDemo();
        System.out.println(psd.delete(4));
        System.out.println(psd.insert(new Wheel(4, 18)));
        System.out.println(psd.update(new Wheel(4, 20)));
        System.out.println(psd.findAll());
    }
}
