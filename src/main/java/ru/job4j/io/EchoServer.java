package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!(str.isEmpty())) {
                        if (str.contains("?msg")) {
                            if (str.toLowerCase().contains("bye")) {
                                out.write("Server closed.".getBytes());
                                server.close();
                            } else if (str.toLowerCase().contains("hello")) {
                                out.write("Hello, dear friend.".getBytes());
                            } else {
                                out.write("What".getBytes());
                            }
                            System.out.println(str);
                        }
                        str = in.readLine();
                    }
                }
            }
        }
    }
}
