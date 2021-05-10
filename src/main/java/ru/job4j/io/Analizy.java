package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder logUnavailable = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new FileOutputStream(target))) {
            out.write("csv");
            out.write(System.lineSeparator());
            String line = in.readLine();
            while (line != null) {
                if (line.equals("")) {
                    line = in.readLine();
                    continue;
                }
                int status = Integer.parseInt(line.split(" ")[0]);
                if (status > 300) {
                    String s = line.split(" ")[1];
                    logUnavailable.append(String.format("%s;", s));
                    while (status > 300 && line != null) {
                        line = in.readLine();
                        if (line != null) {
                            if (line.equals("")) {
                                line = in.readLine();
                            }
                            status = Integer.parseInt(line.split(" ")[0]);
                        }
                    }
                    if (line != null) {
                        s = line.split(" ")[1];
                        logUnavailable.append(String.format("%s;%n", s));
                        continue;
                    }
                }
                line = in.readLine();
            }
            out.write(logUnavailable.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
