import java.io.*;
import java.net.*;

class Receiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(15000);
        byte[] rcvdata = new byte[1];
        int expectedSeqNum = 0;

        while (true) {
            DatagramPacket packet = new DatagramPacket(rcvdata, rcvdata.length);
            socket.receive(packet);
            byte rcvbyte = packet.getData()[0];

            
            if (rcvbyte == '#') {
                System.out.println("End of transmission received. Closing connection.");
                break;
            }

            if ((int) rcvbyte == expectedSeqNum) {
                System.out.println("Received Packet with sequence number: " + (int) rcvbyte);

                byte[] ackdata = new byte[1];
                ackdata[0] = (byte) expectedSeqNum;

                DatagramPacket ackPacket = new DatagramPacket(
                        ackdata, ackdata.length, packet.getAddress(), packet.getPort());
                socket.send(ackPacket);

                System.out.println("Sent ACK for sequence number: " + expectedSeqNum);
                expectedSeqNum++;
            } else {
                System.out.println("Unexpected packet received. Closing connection.");
                break;
            }
        }

        socket.close();
    }
}

