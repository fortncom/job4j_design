package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        MaxMin  maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(1, 2, 44, 5, 6, 7);
        Integer rsl = maxMin.max(list, Integer::compareTo);
        assertThat(rsl, is(44));
    }

    @Test
    public void min() {
        MaxMin  maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(1, 2, 44, 5, 6, 7);
        Integer rsl = maxMin.min(list, Integer::compareTo);
        assertThat(rsl, is(1));
    }
}