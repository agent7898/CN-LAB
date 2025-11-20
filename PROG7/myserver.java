import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;

class myserver
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss= new ServerSocket(8000);
		Socket ns=ss.accept();
		DataInputStream din = new DataInputStream(ns.getInputStream());
		DataOutputStream dout = new DataOutputStream(ns.getOutputStream());
		String fname=din.readUTF();
		System.out.println("Filename received "+fname);
		Charset cs=Charset.forName("UTF-8");
		List<String> lines=Files.readAllLines(Paths.get(fname),cs);
		
		for(int i=0;i<lines.size();i++)
		{
			dout.writeUTF(lines.get(i));
			dout.flush();
		}
		dout.writeUTF("stop");
		System.out.println("file sent successfully!!");
		din.close();
		ns.close();
		ss.close();
	}
}
