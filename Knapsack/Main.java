import java.util.Scanner;


public class Main {
	
	public static int N,M,P,W, iSize, cSize;
	public static int[][] kint;
	public static double[] kcont, uppersC, uppersI;
	public static int[] kints;

	
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		
		//Knapsack ks = new Knapsack();
		N = sc.nextInt();
		M = sc.nextInt();
		
		item[] ints = new item[N + 1];
		item[] conts = new item[N + 1];
		item[] all = new item[N+1];
        
		iSize = 0;
		cSize = 0;
		int pp =0;
		int g=1;
        while(g<=N){
        	int val = sc.nextInt();
        	int wt = sc.nextInt();
        	int up = sc.nextInt();
        	String type = sc.next();
        	all[pp] = new item(val,wt,up,type);
        	pp++;
        	if(type.equals("I")){
        		ints[iSize] = new item(val, wt, up, type);
        		iSize++;
        	}
        	else if(type.equals("C")){
        		conts[cSize] = new item(val, wt, up, type);
        		cSize++;
        	}
        	g++;
        }
        kint = new int[iSize+1][M+1];
        kcont = new double[M+1];
        
        uppersI = new double[ints.length];
        for(int i = 0; i<uppersI.length; i++){
        	if(ints[i]!=null)
        	uppersI[i] = ints[i].upper;
        }
        
        for(item item: ints){
        	if(item!=null){
        	item.amount = new double[M+1];
        	for(int i = 0; i<item.amount.length; i++){
        		item.amount[i] = 0;
        	}
        	}
        }
        
       // System.out.println(dp(ints));
        dp(ints);
        //System.out.println();
        
        for(item item: conts){
        	if(item!=null){
        	item.amount = new double[M+1];
        	for(int i = 0; i<item.amount.length; i++){
        		item.amount[i] = 0;
        	}
        	}
        }
        
        
        sort(conts);
        uppersC = new double[conts.length];
        for(int i = 0; i<uppersC.length; i++){
        	if(conts[i]!=null)
        	uppersC[i] = conts[i].upper;
        }
        
        
      // System.out.println(greedy(conts));
       greedy(conts); 
       
      kints = new int[M+1];
    	   for(int j=0; j<=M;j++){
    		   kints[j] = kint[iSize][j];
    	   }
    	  
       
       
       //find best combination
       combine(ints,conts,kints,kcont, all);
       
       
      
	}
	
	public static void combine(item[] ints, item[]conts, int[]kint, double[] kcont, item[] all){
		
		double Z = 0;
		int iIndex = 0;
		int cIndex=M;
		
		for(int i = 0, c = M; i <=M; i++, c--){
			//System.out.println(kint[i]+"  vs  "+kcont[c]+" = "+(kint[i]+kcont[c]));
			if(kint[i]+kcont[c]>Z){
				Z = kint[i] + kcont[c];
				iIndex = i;
				cIndex=c;
			}
			
		}
		
		
		display(Z);
		for(item item: all){
			for(int i = 0; i<ints.length; i++){
				if(ints[i]!=null && item!=null){
					if(ints[i].profit == item.profit && ints[i].weight== item.weight
						&& ints[i].type==item.type && ints[i].upper == item.upper){
					display(ints[i].amount[iIndex]);
				}
				}
			}
			for(int i = 0; i<conts.length;i++){
				if(conts[i]!=null && item!=null){
					if(conts[i].profit==item.profit && conts[i].weight == item.weight
						&& conts[i].type==item.type && conts[i].upper == item.upper){
					display(conts[i].amount[cIndex]);
				}
			}
			}
		
		}
		
		
		
	}
	
	public static void display (double d) {
		int t=(int)d;
		if (t==d) System.out.println (t);
		else System.out.format ("%.4f%n", d);
		}
	
	public static void sort(item[] arr){
		for(int i = 0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				if(arr[i]!=null && arr[j]!=null)
					if(arr[i].ratio>arr[j].ratio){
						item temp = arr[j];
						arr[j] = arr[i];
						arr[i] = temp;
				}
				
			}
		}
	}
	
	
	public static double greedy(item[] conts){
		int index=0;
		
		if(conts[0]==null){
			for(double g: kcont){
				g=0;
				return 0;
			}
		}
		
			for(int cap=0; cap<=M; cap++){
				index=0;
				

				if(cap==0) kcont[cap] = 0;
				else if(conts[index].weight>=cap){
					kcont[cap] = (conts[index].ratio*cap);
					conts[index].amount[cap] = (double)cap/(double)conts[index].weight;
				}
				else if(conts[index].weight<cap){
					double count = 0;
					index=-1;
					double weight = cap;
					while(weight>0){
						count = 0;
						index++;
						
						if(conts[index]==null) break;
						while(conts[index].upper!=0){
							if(weight==0) break;
							if(conts[index].weight<=weight){
								count+=1;
								conts[index].upper--;
								weight-=conts[index].weight;
								
								
							}
						    else if(conts[index].weight>weight){
								count+= (weight/conts[index].weight);
								//if(cap==7) System.out.println(count);
								conts[index].upper-= weight/conts[index].weight;
								weight-=weight;
							}
							
						}
						
						conts[index].amount[cap] = count;
						kcont[cap]+=(conts[index].profit*count);
					}
					for(int i = 0; i<conts.length; i++){
						if(conts[i]!=null)
						conts[i].upper = uppersC[i];
					}
				
			}
			
			
		}
		
//			for(int k = 0; k<=M;k++){
//				if(kcont[k]>=10)
//					System.out.print(kcont[k]+" ");
//				else
//					System.out.print(" "+kcont[k]+" ");
//			}
//			System.out.println();
		
		
		return kcont[M];
	}
	
	public static int dp(item[] ints){
		for(int j=0;j<=iSize; j++){
			for(int k=0;k<=M;k++){
				if(k==0||j==0) kint[j][k]=0;
				else{
					item item = ints[j-1];
					if(item.upper==-1){
						int amI = 0;
						
						while(((amI+1)*item.weight)<=k){
							amI++;
							//item.amount[k]++;
						}
						int tmp = (k-((int)amI*item.weight));
						if(tmp<0){
							tmp=0;
						}
						//if(amI>item.amount[k]) item.amount[k] = amI;
						item.amount[k] = amI;
						kint[j][k]= (((int)(item.amount[k]*item.profit)+(kint[j-1][tmp])));
						
						if(kint[j-1][k]>kint[j][k]){
							item.amount[k] = 0;
							kint[j][k] = kint[j-1][k];
							}
						
						}
					
			
					else{
						int amC=0;
							while(((amC+1)*item.weight)<=k && amC+1<=item.upper){
								amC++;
							}
							int tmp = (int) ((int)k-(amC*item.weight));
							if(tmp<0){
								tmp=0;
							}
							if(amC>item.amount[k]) item.amount[k]=amC;
							kint[j][k]= (((int)(item.amount[k]*item.profit)+(kint[j-1][tmp])));
							if(kint[j-1][k]>kint[j][k]) {
								item.amount[k]=0;
								kint[j][k] = kint[j-1][k];
							}
							else {
								//System.out.println("J: "+j);
								if(j>=1){
									for(int i=j; i>=1; i--){
										if((item.amount[k]*item.profit)>(ints[i-1].amount[k]*ints[i-1].profit)){
											ints[i-1].amount[k]=0;
									}
									
									}
								}
							}
							
						}
					
					}
				}
			
				
			}
		
		
		
		
		
		
		
//		int []K = new int[M+1];
//		K[0] = 0;
//		int i = 0; int j = 0; int tmp = 0; int pos = 0;
//		for(i=1; i<=M;i++){
//			K[i] = K[i-1];
//			pos = i-1;
//			for(item g: ints) if(g!=null) g.amount[i] = g.amount[i-1];
//			for(j = 0; j<iSize; j++){
//				if(i>=ints[j].weight && (ints[j].amount[j]<ints[j].upper|| ints[j].upper==-1)){
//					tmp = K[i-ints[j].weight]+ints[j].profit;
//				}
//				if(tmp>K[i]){
//					K[i] = tmp;
//					//ints[j].upper--;
//					ints[j].amount[j]+=1;
//					pos = i-ints[j].weight;
//				}
//				
//				
//			}
//		}
//		
//		
//		
//		for(int k=0; k<K.length; k++){
//			System.out.println(" "+K[k]+" ");
//		}
//		
//		return K[K.length-1];
		
//		for(int j=0;j<=iSize;j++){
//			for(int k=0; k<=M; k++){
//				System.out.print(" "+kint[j][k]+" ");
//			}
//			System.out.println();
//		}
		
		return kint[iSize][M];
		
	}
	
	public static int max(int a, int b){ 
        return (a > b)? a : b; 
  }	
	
	
	
}

class item{
	
	public int weight, profit;
	public double[] amount;
	public double upper;
	public double ratio;
	public String type;
	
	public item(int p, int w, int up, String t){
		weight = w;
		profit = p;
		upper = (double) up;
		type = t;
		ratio = (double)p/(double)w;
		
	}
	
}
