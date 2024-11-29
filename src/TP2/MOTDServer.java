package TP2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;

public class MOTDServer {
    private static final List<String> MESSAGES = List.of(
            "Welcome !",
            "Keep Cool and Code On !",
            "J'adore Java!",
            "Veni, vidi, vici.",
            "Vive Cafeyn ! :) <3"
    );

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(8008)) {
            System.out.println("MOTD server is listening on port 8008");
            Random random = new Random();

            while (true) {
                byte[] buffer = new byte[4];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                // Select a random message
                int lineNumber = random.nextInt(MESSAGES.size());
                String message = MESSAGES.get(lineNumber);

                byte[] messageBytes = message.getBytes("UTF-8");
                ByteBuffer responseBuffer = ByteBuffer.allocate(4 + messageBytes.length);
                responseBuffer.putInt(lineNumber);
                responseBuffer.put(messageBytes);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                DatagramPacket response = new DatagramPacket(responseBuffer.array(), responseBuffer.array().length, clientAddress, clientPort);
                socket.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}