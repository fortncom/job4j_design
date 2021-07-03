package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new AccountReportFormatter());
        StringBuilder expect = new StringBuilder();
                expect.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                        .append(worker.getName()).append(";")
                        .append(worker.getHired()).append(";")
                        .append(worker.getFired()).append(";")
                        .append(String.format("%s pуб.", (int) worker.getSalary())).append(";");
        assertThat(engine.generate(employee -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ProgrammerReportFormatter());
        StringBuilder expect = new StringBuilder()
        .append("<!DOCTYPE html>")
        .append("<html>")
        .append("<head><title>Employees</title></head>")
        .append("<body>")
        .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("<br>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
        .append("</body>")
        .append("</html>");
        assertThat(engine.generate(employee -> true), is(expect.toString()));
    }

}
