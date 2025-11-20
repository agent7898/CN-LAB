import java.util.*;
import java.lang.String;

class rsa
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the message:");
		String msg = in.nextLine();
		System.out.println("Enter two large prime numbers p&q");
		int p = in.nextInt();
		int q = in.nextInt();
		int n = p*q;
		int phi = (p-1) * (q-1);
		int e,k=0;
		do{
			System.out.println("Enter a value e such that e is relatively prime to "+phi);
			e=in.nextInt();
		}while(gcd(phi,e)!=1);
		for(k=1;((phi*k)+1)%e!=0;k++);
		int d = ((phi*k)+1)/e;
		System.out.println("\nThe public key is ("+e+","+n+")\n");
		System.out.println("d="+d);
		System.out.println("Cipher text is:");
		int[] cipher = new int[50];
		int i;
		for(i=0;i<msg.length();i++)
		{
			int s=(int)msg.charAt(i);
			cipher[i] = enc(s,e,n);
			System.out.print(cipher[i]+"\t");
		}
		String msg1="";
		for(i=0;i<msg.length();i++)
		{
			int m1=enc(cipher[i],d,n);
			msg1+=(char)m1;
		}
		System.out.println("\nThe decrypted msg is "+"'"+msg1+"'");
	}
	
	public static int enc(int m,int a,int b)
	{
		int c=1;
		for(int j=0;j<a;j++)
			c=(c*m)%b;
		return c;
	}
	
	public static int gcd(int m,int n)
	{
		while(n!=0)
		{
			int r=m%n;
			m=n;
			n=r;
		}
		return m;
	}
}
