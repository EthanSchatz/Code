import java.io.*;


public class Main {
	
	BufferedReader reader;
	BufferedWriter writer;
	
	linkedList nameList;
	linkedList nodeList;
	
	
	public void read(){
	
	reader = new BufferedReader(new InputStreamReader(System.in));
	
	nameList = new linkedList();
	nodeList = new linkedList();
	
	while (true)
	{
		nodeList = new linkedList();
		String s="";
		String whichOp = "samesies";
		try {
			s = reader.readLine();
			if (s == null | s =="" | s==" "){
				break;}
			int i = 0;
			String name="";
			String contents="";
			String tempcont=null;
			while(i < s.length()){
				while(s.charAt(i)!=' '){
					name+=s.charAt(i);
					i++;
					if(i == s.length() | i > s.length()) break;
				}
				if(i == s.length() | i > s.length()) break;
				while(s.charAt(i)==' '){
					i++;
				}
				if(s.charAt(i)=='='){
					
					int g=i+1;
					i+=2;
					while(s.charAt(g)==' '){
						g++;
					}
					if (s.charAt(g)=='{'){
						g++;
						i=g;
						whichOp="samesies";
					}
					g++;
					while(!whichOp.equals("samesies") && s.charAt(g)!=' '){
						g++;
					}
					g++;
					
					if(g<=s.length() && s.charAt(g)=='+'){ 
						whichOp="merge";
					}
					if(g<=s.length() && s.charAt(g)=='|'){
						whichOp="union";
					}
					if(g<=s.length() && s.charAt(g)=='&'){
						whichOp="intersect";
					}
					if(g<=s.length() && s.charAt(g)=='-'){
						whichOp="difference";
					}
					if(g<=s.length() && s.charAt(g)=='^'){
						whichOp="symmetricDifference";
					}
					if(g<=s.length() && s.charAt(g)=='*' && s.charAt(g-1)==' ' && s.charAt(g+1)==' '){
						whichOp="multiply";
					}
			}
				
				for (int index=0; index<s.length();index++){
					if (s.charAt(index)=='+') whichOp= "merge";
					if (s.charAt(index)=='|') whichOp= "union";
					if (s.charAt(index)=='&') whichOp= "intersect";
					if (s.charAt(index)=='-') whichOp= "difference";
					if (s.charAt(index)=='^') whichOp= "symmetricDifference";
					if (s.charAt(index)=='*' && s.charAt(index-1)==' ' && s.charAt(index+1)==' ') whichOp = "multiply";
				}
				
				
				
				if(s.charAt(i)=='+'){
					whichOp="merge";
					i+=3;
				}
				
				
				if(s.charAt(i)=='|'){
					whichOp="union";
					i+=3;
				}
				
				if(s.charAt(i)=='&'){
					whichOp="intersect";
					i+=3;
				}
				if(s.charAt(i)=='-'){
					whichOp="difference";
					i+=3;
				}
				if(s.charAt(i)=='^'){
					whichOp="symmetricDifference";
					i+=3;
				}
				if(s.charAt(i)=='*'){
					whichOp="multiply";
					i+=3;
				}
				
				
				if(i == s.length() | i > s.length()) break;
				while(s.charAt(i)!='}'){
					
					if(s.charAt(i)!='{')
					contents+=s.charAt(i);
					i++;
					if(i == s.length() | i > s.length()) break;
					
				}
				if(s.charAt(i-1)=='{' && s.charAt(i)=='}'){
					contents+=" ";}
				i++;
			
				
				if (!whichOp.equals("samesies")){
					tempcont = contents;
				}
				
				
					
				}
					
				int count = 0;
				
				while(count < contents.length()){
					String set="";
					String tempset="";
					if(count == contents.length() | count > contents.length()) break;
					while(contents.charAt(count)!=','){
						set+= contents.charAt(count);
						count++;
						if(count == contents.length() | count > contents.length()) break;
					}
					count++;
					//System.out.println(set);
					
					if (tempcont!=null){
						int tempcount =0;
						while(tempcount < tempcont.length()){
							tempset="";
							if(tempcount == tempcont.length() | tempcount>tempcont.length()) break;
							while(tempcount < tempcont.length() && tempcont.charAt(tempcount)!=' ' && tempcont.charAt(tempcount)!='+' && tempcont.charAt(tempcount)!='-'
									&& tempcont.charAt(tempcount)!='*' && tempcont.charAt(tempcount)!='|' && tempcont.charAt(tempcount)!='&' 
									&& tempcont.charAt(tempcount)!='^'){
								tempset+= tempcont.charAt(tempcount);
								tempcount++;
								if (tempcount == tempcont.length() | tempcount > tempcont.length());
							}
							tempcount++;
							if (!tempset.equals(""))
								nodeList.insert(""+tempset+"");
					
				}
						}
					
					
					
					
					if (contents==" ")
						nodeList.insert(" ");
					

					if(tempset.equals("")){
					nodeList.insert(""+set+"");
					}
					
				}
				
				if(name!=null){
					nameList.insert(name, nodeList, whichOp);
					}
			
			
		if (s == null){
				break;}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (s == null | s =="" | s==" "){
			break;}
		try {
			if (!reader.ready()){
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	}
	
	
	
	
	
	
	public static void main(String[] args){
		Main abc = new Main();
		abc.read();
		System.out.println(abc.nameList.printList());
	
	}
	
	
	
	
	
}
