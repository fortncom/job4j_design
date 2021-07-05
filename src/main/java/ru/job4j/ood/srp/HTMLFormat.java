package ru.job4j.ood.srp;

import java.util.List;

public class HTMLFormat<T extends Employee> implements Format<T> {
    @Override
    public String reform(List<T> list) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>");
        text.append("<html>");
        text.append("<head><title>Employees</title></head>");
        text.append("<body>");
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : list) {
            text.append(System.lineSeparator())
                    .append("<br>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        text.append("</body>");
        text.append("</html>");
        return text.toString();
    }
}
