package ru.job4j.io.chat;

import java.io.*;
import java.util.*;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean chatOn = true;
        boolean silence = false;
            List<String> answers = new ArrayList<>();
            StringJoiner joiner = new StringJoiner(System.lineSeparator());
            try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
                String data;
                while ((data = in.readLine()) != null) {
                    answers.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Добро пожаловать в чат.");
            while (chatOn) {
                String question = scanner.nextLine();
                joiner.add(question);
                if (question.equalsIgnoreCase(OUT)) {
                    chatOn = false;
                    System.out.println("Чат завершён.");
                    continue;
                }
                if (question.equalsIgnoreCase(STOP)) {
                    silence = true;
                } else if (question.equalsIgnoreCase(CONTINUE)) {
                    silence = false;
                }
                if (!silence) {
                    int random = (int) (Math.random() * answers.size() - 1);
                    String answer = answers.get(random);
                    joiner.add(answer);
                    System.out.println(answer);
                }
            }
            out.write(String.valueOf(joiner));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/resources/chatLog.txt",
                "src/main/resources/answer.txt");
        cc.run();
    }
}
