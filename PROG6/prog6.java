import java.util.*;

class bellman{
	public static class node{
		int distance;
		char phop;
	}
	
	public static void main(String[] args){
		int i,j,n;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of nodes");
		n=in.nextInt();
		node[][] rt=new node[n][n];
		int [][] g=new int[n][n];
		char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l'};
		for(i=0;i<n;i++)
			for(j=0; j<n; j++)
				rt[i][j]=new node();
		System.out.println("Enter the adjacent matrix:");
		for(i=0;i<n;i++)
			for(j=0; j<n; j++)
				g[i][j]=in.nextInt();
		System.out.println("Enter the distance:");
		for(i=0;i<n;i++){
			System.out.println("The distance between node "+ch[i]+" and");
			for(j=0;j<n;j++){
				if(g[i][j]==1){
					System.out.println("node "+ch[j]+" is:");
			rt[i][j].distance=in.nextInt();
				}
				else
					rt[i][j].distance=999;
				rt[i][j].phop=ch[i];
			}
		}
		int[] ad=new int[n];
		int adc= 0,choice;
		do{
			adc=0;
			System.out.println("1.Routing table information\n2.Routing table\n3.Exit");
			choice=in.nextInt();
			switch(choice){
				case 1: System.out.println("Enter the node for which routing table should be constructed:");
				int id=in.nextInt();
				id--;
				System.out.println("The neighbours of "+ch[id]+" are:");
				for(i=0;i<n;i++){
					if(g[id][i]==1){
						ad[adc++]=i;
						System.out.println(ch[i]);
					}
				}
				for(i=0;i<n;i++){
					if(id!=i){
						int small=rt[id][i].distance;
						int chosen=id;
						for(j=0;j<adc;j++){
							int total=rt[id][ad[j]].distance + rt[ad[j]][i].distance;
							if(total<small){
								small=total;
								chosen=ad[j];
								rt[id][i].phop = ch[chosen];
							}
							rt[id][i].distance = small;
						}
						System.out.println("The Smallest distance from:"+ch[id]+" to "+ch[i]+" is "+small);
						System.out.println("The previous hop is "+rt[id][i].phop);
					}
					else
						rt[id][i].distance= 0;
						
				}
				break;
				
				case 2: for(i=0;i<n;i++){
						for(j=0; j<n;j++)
							System.out.print(rt[i][j].distance + "," +rt[i][j].phop + "\t");
						System.out.println();
					}	
					break;
			}
		}while(choice!=3);
	}
	
}
		
