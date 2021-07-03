package ru.job4j.ood.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine  implements Report {

    private Store store;
    private ReportFormatter formatter;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public ReportEngine(Store store, ReportFormatter formatter) {
        this.store = store;
        this.formatter = formatter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        if (formatter == null) {
            StringBuilder text = new StringBuilder();
            text.append("Name; Hired; Fired; Salary;");
            for (Employee employee : employees) {
                text.append(System.lineSeparator())
                        .append(employee.getName()).append(";")
                        .append(employee.getHired()).append(";")
                        .append(employee.getFired()).append(";")
                        .append(employee.getSalary()).append(";");
            }
            return text.toString();
        }
        return formatter.format(employees);
    }
}
