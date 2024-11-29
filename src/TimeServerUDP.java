import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TimeServerUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(37)) {
            System.out.println("Time server is listening on port 37");
            while (true) {
                byte[] buffer = new byte[1];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                long currentTimeMillis = System.currentTimeMillis();
                long secondsSinceEpoch = currentTimeMillis / 1000;
                byte[] timeBytes = new byte[4];
                for (int i = 3; i >= 0; i--) {
                    timeBytes[i] = (byte) (secondsSinceEpoch & 0xFF);
                    secondsSinceEpoch >>= 8;
                }

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                DatagramPacket response = new DatagramPacket(timeBytes, timeBytes.length, clientAddress, clientPort);
                socket.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}