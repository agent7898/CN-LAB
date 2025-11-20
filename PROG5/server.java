import java.io.*;
import java.net.*;

class Sender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(16000);
        InetAddress receiverAddress = InetAddress.getLocalHost();
        int receiverPort = 15000;
        int windowSize = 4;

        byte[] data = "Hello,".getBytes();
        int base = 0;
        int nxtseqnum = 0;
        byte[] nxtseq;

        while (nxtseqnum < data.length) {
            if (nxtseqnum < base + windowSize) {
                // ✅ send sequence number, not data
                nxtseq = new byte[]{ (byte) nxtseqnum };

                DatagramPacket packet = new DatagramPacket(
                        nxtseq, 1, receiverAddress, receiverPort);
                socket.send(packet);
                System.out.println("Sent packet with sequence number: " + nxtseqnum);

                byte[] ackdata = new byte[1];
                DatagramPacket ackPacket = new DatagramPacket(ackdata, ackdata.length);
                socket.receive(ackPacket);
                int ack = ackdata[0];
                System.out.println("Received Ack: " + ack);

                if (ack >= base) {
                    base = ack + 1;
                }
                nxtseqnum++;
            }
        }

        // Send end-of-transmission marker
        nxtseq = new byte[]{ (byte) '#' };
        DatagramPacket packet = new DatagramPacket(
                nxtseq, 1, receiverAddress, receiverPort);
        socket.send(packet);
        socket.close();
    }
}

