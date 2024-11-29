package TP2;

import java.io.*;
import java.net.*;

public class NanoServeurWeb {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7007)) {
            System.out.println("Server is listening on port 8080");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());

                    // Lire la requête HTTP
                    String line = in.readLine();
                    if (line != null && line.startsWith("GET")) {
                        String[] parts = line.split(" ");
                        String fileName = parts[1].substring(1); // Remove leading slash

                        // Lire les autres lignes de l'en-tête
                        while (!(line = in.readLine()).isEmpty()) {
                            // Ignorer les autres lignes
                        }

                        File file = new File(fileName);
                        if (!fileName.contains("/") && file.exists() && !file.isDirectory()) {
                            // Envoyer l'en-tête HTTP 200 OK
                            out.println("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nConnection: close\r\n\r\n");
                            // Envoyer le contenu du fichier
                            BufferedReader fileReader = new BufferedReader(new FileReader(file));
                            String fileLine;
                            while ((fileLine = fileReader.readLine()) != null) {
                                out.println(fileLine);
                            }
                            fileReader.close();
                        } else {
                            // Envoyer l'en-tête HTTP 404 Not Found
                            out.println("HTTP/1.1 404 Not Found\r\nContent-Type: text/plain\r\nConnection: close\r\n\r\n");
                            out.println("Fichier introuvable");
                        }
                        out.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}