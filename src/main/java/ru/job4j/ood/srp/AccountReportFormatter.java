package ru.job4j.ood.srp;

import java.util.List;

public class AccountReportFormatter implements ReportFormatter {

    @Override
    public String format(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(String.format("%s pуб.", (int) employee.getSalary())).append(";");
        }
        return text.toString();
    }

}
