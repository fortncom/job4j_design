package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] arg = s.split(";");
                if (arg.length > 1) {
                    users.add(new User(arg[0], arg[1]));
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("psql.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("psql.url2"),
                cfg.getProperty("psql.login"),
                cfg.getProperty("psql.password")
        )) {
            createTable(cnt);
            for (User user : users) {
                System.out.println(user);
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private void createTable(Connection cnt) {
        try (Statement statement = cnt.createStatement()) {
            String query = String.format("create table if not exists %s(%s, %s, %s);",
                    "users",
                    "id serial primary key",
                    "name varchar(255)",
                    "email varchar(255)");
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + ", email='" + email + '\''
                    + '}';
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("src\\main\\java\\ru\\job4j\\app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "src\\main\\resources\\dump.txt");
        db.save(db.load());
    }
}
