package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    private SimpleArray<Integer> simpleArray;

    @Before
    public void setUp() {
        Integer[] integers = new Integer[]{1, 5, 8, 9};
        simpleArray = new SimpleArray<>(integers,100);
    }


    @Test
    public void whenAddElement() {
        simpleArray.add(24);
        Integer expected = 24;
        Integer rsl = simpleArray.get(4);
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void whenRemoveElement() {
        simpleArray.remove(2);
        Integer[] expected = new Integer[]{1, 5, 9};
        Integer[] rsl = new Integer[] {simpleArray.get(0), simpleArray.get(1), simpleArray.get(2)};
        Assert.assertArrayEquals(expected, rsl);
    }

    @Test
    public void sequentialHasNextInvocationReturnTrue() {
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
    }
}