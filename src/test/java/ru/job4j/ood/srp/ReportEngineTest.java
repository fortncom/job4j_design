package ru.job4j.ood.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
    public void whenProgrammerGeneratedToHTML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ProgrammerReportFormatter(new HTMLFormat<>()));
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

    @Test
    public void whenProgrammerGeneratedToJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ivan", now, now, 1100);
        store.add(worker);
        store.add(worker2);
        List<Employee> list = new ArrayList<>();
        list.add(worker);
        list.add(worker2);
        Report engine = new ReportEngine(store, new ProgrammerReportFormatter(new JSONFormat<>()));
        Gson gson = new GsonBuilder().create();
        String expected = gson.toJson(list);
        assertThat(engine.generate(employee -> true), is(expected));
    }
}
