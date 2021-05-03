package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.statistics.Analize.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    List<User> previous;
    List<User> current;

    @Before
    public void init() {
        User tom = new User(0, "Tom");
        User rik = new User(1, "Rik");
        User den = new User(2, "Den");
        User nil = new User(3, "Nil");
        previous = List.of(rik, tom);
        User rikChanged = new User(1, "Ric");
        current = List.of(rikChanged, den, nil);
    }

    @Test
    public void whenAdd2Then2() {
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.added, is(2));
    }

    @Test
    public void whenDelete1tThen1() {
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.deleted, is(1));
    }

    @Test
    public void whenChanged1Then1() {
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previous, current);
        assertThat(rsl.changed, is(1));
    }
}