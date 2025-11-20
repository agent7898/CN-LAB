import java.util.*;
import java.io.*;
import java.lang.*;

class leaky{
	public static void main(String[] args) throws InterruptedException{
		int csize=0;
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter the number of packets");
		int n = in.nextInt();
		System.out.println("\nEnter the bucket size");
		int size = in.nextInt();
		System.out.println("Enter the output rate");
		int or = in.nextInt();
		System.out.println("\nEnter the time interval between packets");
		int time = in.nextInt();
		Random r = new Random();
		for(int i=0;i<n;i++){
			int psize = r.nextInt(size + 100);
			System.out.println("new packet arrived");
			System.out.println("Packet number:"+(i+1)+",Packet Size="+psize);
			if(psize>size)
				System.out.println("Packet is too large... Non-conforming packet Discarding");
			else if((psize+csize)>size)
				System.out.println("Bucket Overflow... Non-conforming packet Discarding");
			else
				csize+=psize;
			for(int j=0;j<time;j++){
				if(csize>or){
					System.out.println(or+"bytes transmitted");
					csize-=or;
				}
				else if(csize==0)
					System.out.println("Bucket Empty... no bytes to transmit");
				else{
					System.out.println("remaining "+csize+" bytes tranmitted");
					csize=0;
				}
				Thread.sleep(1000);
			}
			System.out.println("Timer expired");
			if(csize!=0)
				System.out.println(csize + "bytes still remain in bucket");
		}
		System.out.println("\nNo more new packets");
		if(csize!=0)
			System.out.print("\nTransmission of "+csize+" bytes remaining in the bucket is starting\n");
		while(csize>0){
			if(csize>or){
				System.out.println(or+" bytes transmitted");
				csize-=or;
			}
			else{
				System.out.println("remaining "+csize+" bytes transmitted");
				csize=0;
			}
			Thread.sleep(1000);
		}
		System.out.print("\nBucket Empty... Transmission Complete\n");
	}
}
		
		
