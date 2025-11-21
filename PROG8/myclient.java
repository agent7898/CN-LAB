//Develop a program on a datagram socket for client/server to display the messages
//on client side, typed at the server side.
import java.net.*;
import java.util.*;
class MyServer
{
    public static void main(String args[])throws Exception
    {
        DatagramSocket ds=new DatagramSocket(15000);
        byte[] buffer=new byte[100];
        Scanner in = new Scanner(System.in);
        String str2;
        do
        {
            DatagramPacket p=new DatagramPacket(buffer,buffer.length);
            ds.receive(p);
            String str=new String(p.getData());
            System.out.println("client says: "+str);
            System.out.print("Server says:");
            str2=in.nextLine();
            java.util.Arrays.fill(buffer,(byte)0);
            for(int i=0;i<str2.length();i++)
                buffer[i]=(byte)str2.charAt(i);
            ds.send(new DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(),16000));
        }while(!str2.equals("bye"));
        System.out.println("Closing chat application");
        ds.close();
    } 
}
