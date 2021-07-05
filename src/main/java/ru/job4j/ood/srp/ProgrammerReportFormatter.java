package ru.job4j.ood.srp;

import java.util.List;

public class ProgrammerReportFormatter implements ReportFormatter {

    private Format<Employee> format;

    public ProgrammerReportFormatter(Format<Employee> format) {
        this.format = format;
    }

    @Override
    public String format(List<Employee> employees) {
        return format.reform(employees);
    }

}
