package ru.job4j.ood.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JSONFormat<T extends Employee> implements Format<T> {

    @Override
    public String reform(List<T> list) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(list);
    }
}
