package ru.job4j.io.chat;

import java.io.*;
import java.util.*;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> listToWrite;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean chatOn = true;
        boolean silence = false;
        List<String> answers = answers();
        listToWrite = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Добро пожаловать в чат.");
            while (chatOn) {
                String question = scanner.nextLine();
                listToWrite.add(question);
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
                    int random = (int) (Math.random() * answers.size());
                    String answer = answers.get(random);
                    listToWrite.add(answer);
                    System.out.println(answer);
                }
            }
        }
        write();
    }

    private List<String> answers() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            String data;
            while ((data = in.readLine()) != null) {
                answers.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private boolean write() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))) {
            for (String s : listToWrite) {
                out.write(s);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/resources/chatLog.txt",
                "src/main/resources/answer.txt");
        cc.run();
    }
}
