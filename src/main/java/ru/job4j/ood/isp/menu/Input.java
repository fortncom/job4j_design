package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public interface Input {

    String ask(String question);
}

class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}


class StubInput implements Input {

    private final String[] answer;
    private int count = 0;

    public StubInput(String[] answer) {
        this.answer = answer;
    }

    @Override
    public String ask(String question) {
        System.out.println(question);
        return answer[count++];
    }
}