import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TimeClientUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, 37);
            socket.send(request);

            byte[] responseBuffer = new byte[4];
            DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(response);

            long secondsSinceEpoch = 0;
            for (int i = 0; i < 4; i++) {
                secondsSinceEpoch = (secondsSinceEpoch << 8) | (responseBuffer[i] & 0xFF);
            }

            System.out.println("Time received from server: " + secondsSinceEpoch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
