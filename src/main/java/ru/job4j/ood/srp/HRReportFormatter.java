package ru.job4j.ood.srp;

import java.util.Comparator;
import java.util.List;

public class HRReportFormatter implements ReportFormatter {

    Comparator<Double> comparator;

    public HRReportFormatter(Comparator<Double> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String format(List<Employee> employees) {
        employees.sort((o1, o2) -> comparator.compare(o2.getSalary(), o1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }

}
