import java.net.*;
import java.util.*;
class MyClient
{
public static void main(String args[])throws Exception
{
DatagramSocket ds=new DatagramSocket(16000);
byte[] buffer=new byte[100];
Scanner in=new Scanner(System.in);
System.out.println("Chat Application Started!... Type message to send and bye to quit");
String str="",str2;
do
{
System.out.print("client says: ");
str2=in.nextLine();
java.util.Arrays.fill(buffer,(byte)0);
for(int i=0;i<str2.length();i++)
buffer[i]=(byte)str2.charAt(i);
ds.send(new
DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(),15000));
if(!str2.equals("bye"))
{
System.out.print("Server says:");
DatagramPacket p=new DatagramPacket(buffer,buffer.length);
ds.receive(p);
str="";
str=new String(p.getData());
System.out.println(str);
}
}while(!str2.equals("bye"));
System.out.println("Closing chat application");
ds.close();
}
}
