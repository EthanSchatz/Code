import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;


public class Main {

	static
	Graph<String> G;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		Main abc = new Main();
		if(flag){
		G.callMethods();
		}
		else{
			System.out.println("Component sizes:	{}\nUnweighted distances:	{}\nWeighted distances:	{}");
		}
	}
	@SuppressWarnings("resource")
	public Main() throws IOException{
		Scanner sc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = new Graph<>();
		flag = false;
		while(true){
			String s="";
			s = br.readLine();
			sc = new Scanner(s);
			while(sc.hasNext()){
				String b = sc.next();
				G.addNode(b);
				flag = true;
			}
		
		if (s == null | s =="" | s==" "){
			break;}
		
		if (!br.ready()){
				break;
			}
		
		}
	}
}
