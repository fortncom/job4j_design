package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ImplGeneratorTest {

    @Test
    public void produce() {
        String template = "I am ${name}, who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Tom");
        args.put("subject", "you");
        ImplGenerator generator = new ImplGenerator();
        String rsl = generator.produce(template, args);
        String expected = "I am Tom, who are you?";
  }
}