package TP2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class MOTDClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MOTDClient <server> <port>");
            return;
        }

        String server = args[0];
        int port = Integer.parseInt(args[1]);

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(server);

            // Envoyer une requête
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(0); // The content of the request is not important in this case
            DatagramPacket request = new DatagramPacket(buffer.array(), buffer.array().length, serverAddress, port);
            socket.send(request);

            // Recevoir la réponse
            byte[] initialBuffer = new byte[1024];
            DatagramPacket initialResponse = new DatagramPacket(initialBuffer, initialBuffer.length);
            socket.receive(initialResponse);

            // Determiner la longueur du message
            int actualLength = initialResponse.getLength();
            byte[] responseBuffer = new byte[actualLength];
            System.arraycopy(initialBuffer, 0, responseBuffer, 0, actualLength);

            // Extraire le numéro de ligne et le message
            ByteBuffer responseByteBuffer = ByteBuffer.wrap(responseBuffer);
            int receivedLineNumber = responseByteBuffer.getInt();
            String message = new String(responseBuffer, 4, actualLength - 4, "UTF-8").trim();

            // Afficher le message en bleu
            String blueMessage = "\u001B[34m" + message + "\u001B[0m";
            System.out.println("MOTD received: " + blueMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}