package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("psql.driver"));
            String url = properties.getProperty("psql.url");
            String login = properties.getProperty("psql.login");
            String password = properties.getProperty("psql.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void execute(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws SQLException {
        String query = String.format("create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)");
        execute(query);
    }

    public void dropTable(String tableName) throws SQLException {
        String query = String.format("drop table %s;", tableName);
        execute(query);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String query = String.format(
                "alter table if exists %s add column if not exists %s %s;", tableName, columnName, type);
        execute(query);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String query = String.format(
                "alter table if exists %s drop column if exists %s;", tableName, columnName);
        execute(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String query = String.format(
                "alter table if exists %s rename column %s to %s;", tableName, columnName, newColumnName);
        execute(query);
    }




    public String getSheme(String tableName) throws SQLException {
        StringBuilder sheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            sheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                sheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")
                ));
            }
        }
        return sheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader readProp = new BufferedReader(new FileReader(
                "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\app.properties"))) {
            Properties properties = new Properties();
            properties.load(readProp);
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.createTable("headlight");
            tableEditor.addColumn("headlight", "intensity", "int");
            tableEditor.renameColumn("headlight", "intensity", "i");
            tableEditor.dropColumn("headlight",  "i");
            tableEditor.dropTable("headlight");
            System.out.println(tableEditor.getSheme("headlight"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
