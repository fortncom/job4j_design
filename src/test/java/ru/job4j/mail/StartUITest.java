package ru.job4j.mail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenAddTwoUsersWithDuplicateMailThenMergeToOneUser() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "user1", "xxx@ya.ru,foo@gmail.com, ,lol@mail.ru",
                        "0", "user2", "foo@gmail.com, ups@pisem.net",
                        "1"}
        );
        Base base = new Base();
        List<UserAction> actions = new ArrayList<>(List.of(
                new AddAction(out),
                new ExitAction(new StubOutput())));
        new StartUI(new StubOutput()).init(in, base, actions);
        String separator = System.lineSeparator();
        assertThat(out.toString(), is("=== Add a new User ====" + separator
                + "Все доступные пользователи:" + separator
                + "User{name='user1', mails=[, lol@mail.ru, xxx@ya.ru, foo@gmail.com]}" + separator
                + "=== Add a new User ====" + separator
                + "Все доступные пользователи:" + separator
                + "User{name='user1', mails=[, ups@pisem.net, lol@mail.ru, xxx@ya.ru, foo@gmail.com]}" + separator));
    }

    @Test
    public void whenAddTwoUsersWithoutDuplicateMailThenTwoUser() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "user1", "xxx@ya.ru,foo@gmail.com, ,lol@mail.ru",
                        "0", "user2", "ups@pisem.net",
                        "1"}
        );
        Base base = new Base();
        List<UserAction> actions = new ArrayList<>(List.of(
                new AddAction(out),
                new ExitAction(new StubOutput())));
        new StartUI(new StubOutput()).init(in, base, actions);
        String separator = System.lineSeparator();
        assertThat(out.toString(), is("=== Add a new User ====" + separator
                + "Все доступные пользователи:" + separator
                + "User{name='user1', mails=[, lol@mail.ru, xxx@ya.ru, foo@gmail.com]}" + separator
                + "=== Add a new User ====" + separator
                + "Все доступные пользователи:" + separator
                + "User{name='user1', mails=[, lol@mail.ru, xxx@ya.ru, foo@gmail.com]}" + separator
                + "User{name='user2', mails=[ups@pisem.net]}" + separator));
    }

    @Test
    public void whenAdd2UsersAndDelete1User() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "user1", "xxx@ya.ru, foo@gmail.com, lol@mail.ru",
                        "0", "user2", "ups@pisem.net",
                        "1", "user1",
                        "2"}
        );
        Base base = new Base();
        List<UserAction> actions = new ArrayList<>(List.of(
                new AddAction(new StubOutput()),
                new DeleteAction(out),
                new ExitAction(new StubOutput())));
        new StartUI(new StubOutput()).init(in, base, actions);
        String separator = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete User ====" + separator
                + "Пользователь удален успешно." + separator
                + "Все доступные пользователи:" + separator
                + "User{name='user2', mails=[ups@pisem.net]}" + separator));
    }

}
