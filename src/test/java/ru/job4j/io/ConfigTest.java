package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/java/ru/job4j/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "src/main/java/ru/job4j/app.comments.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("#hibernate.connection.password"));
    }

    @Test (expected = IllegalStateException.class)
    public void whenPairWithoutValue() throws IllegalStateException {
        String path = "src/main/java/ru/job4j/app.fail.properties";
        Config config = new Config(path);
        config.load();
    }
}