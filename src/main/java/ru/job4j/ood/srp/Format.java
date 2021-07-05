package ru.job4j.ood.srp;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface Format<T> {

    public String reform(List<T> list);
}
