package TP2;

import java.io.*;
import java.net.*;

public class NanoClientWeb {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java NanoClientWeb <server> <file>");
            return;
        }

        String server = args[0];
        String fileName = args[1];

        try (Socket socket = new Socket(server, 7007)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Envoyer la requête HTTP
            out.println("GET /" + fileName + " HTTP/1.1\r\nConnection: close\r\n\r\n");
            out.flush();

            // Lire la réponse du serveur
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}