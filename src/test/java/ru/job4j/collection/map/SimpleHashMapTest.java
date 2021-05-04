package ru.job4j.collection.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddDuplicateThenTrue() {
        SimpleHashMap<User, Integer> map = new SimpleHashMap<>();
        User user = new User("Tom", 1, new
                GregorianCalendar(1972, 12, 21));
        map.insert(user, 1);
        assertTrue(map.insert(user, 1));
    }

    @Test
    public void whenKeyAbsentThenNull() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        assertNull(map.get("second"));
    }

    @Test
    public void whenInsertF1ThenGet1() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("F", 1);
        Integer rsl = map.get("F");
        assertThat(rsl, is(1));
    }

    @Test
    public void whenLoadMore75PercentThenSize32() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        StringBuilder str = new StringBuilder("f");
        int i = 1;
        while (map.length < 15) {
            str.append(i++);
            map.insert(str.toString(), 1);
        }
        Integer rsl = map.getCapacity();
        assertThat(rsl, is(32));
    }

    @Test
    public void whenInsertF1AndDeleteAndDeleteFThenTrueAndFalse() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("F", 1);
        assertTrue(map.delete("F"));
        assertFalse(map.delete("F"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("F", 1);
        map.insert("S", 2);
        map.insert("T", 3);
        Iterator<SimpleHashMap.Entry<String, Integer>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}