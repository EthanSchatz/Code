


class Node{
	Node next, prev;
	public boolean samesies;
	public boolean merge;
	public boolean union;
	public boolean intersect;
	public boolean difference;
	public boolean symmetricDifference;
	public boolean multiply;
	public String value;
	public String multiplication;
	public String data;
	public int size;
	public linkedList list;
	public Node(String x, Node p, Node q) { 
		String data;
		size =0;
		linkedList list;
		value = "";
		multiplication="";
		prev = p; 
		next = q;
		samesies = false;
		merge = false;
		union = false;
		intersect = false;
		difference = false;
		symmetricDifference = false;
		multiply = false;
		
	}
	
	
	public String value(){
		String s ="";
		int i=0;
			while(data.charAt(i)!='*' && i<data.length()){
				if(i==data.length()) break;
				s+=data.charAt(i);
				i++;
				if(i==data.length()) break;
			}
		
		return s;
	}
	
	
	public String mult(){
		String s="";
		int i=0;
		while(data.charAt(i)!='*' && i<=data.length()){
				if(i==data.length()) break;
				i++;
				if(i==data.length()) break;
			}
			i++;
		while(i<=data.length()){

			if(i==data.length()) break;
			s+=data.charAt(i);
			i++;

			if(i==data.length()) break;
		}
		
		
		if(s.equals("null")) s= null;
		return s;
	}
	
	
	
	public int Size(){
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			if(q.data !="" && q.data!= null){
				if(q.mult().equals(""))
					size++;
				else 
					size+= Integer.parseInt(q.mult());
		}}
		return size;
	}
	public String write(linkedList list){
		String s="";
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			if(q == list.tail.prev)
			s+= q.data;
			else if (list.size()==1) s+= q.data;
			else if (q ==list.head) s+= q.data+",";
			else s+= q.data+",";
			s.trim();
			s= s.replace(",null,",",");
			s= s.replace(",null","");
			s= s.replace("null","");
		}
		return s;
	}
	
}

public class linkedList {
	
	public boolean ready;
	private int N;
	public Node head;
	public Node tail;
	
	public linkedList(){
		head = new Node(null, null, tail);
		tail = new Node(null, head, null);
		N= 0;
		ready = true;
	}
	
	public void insert(String item) {
		//System.out.println(item);
        Node G = tail.prev;
        Node x = new Node(item,G,tail);
        x.data = item;
        tail.prev = x;
        G.next = x;
        N++;
    }
	
	public void insert(String item, linkedList nodeList, String whichOp) {
        Node G = tail.prev;
        Node x = new Node(item,G,tail);
        x.data = ""+item+"";
        x.list = nodeList;
        tail.prev = x;
        G.next = x;
        N++;
        if( whichOp == "samesies")
        	x.samesies = true;
        if( whichOp == "merge")
        	x.merge = true;
        if( whichOp == "union")
        	x.union = true;
        if( whichOp == "intersect")
        	x.intersect = true;
        if( whichOp == "difference")
        	x.difference = true;
        if( whichOp == "symmetricDifference")
        	x.symmetricDifference = true;
        if( whichOp == "multiply")
        	x.multiply = true;
        
    }
	
	public Node before (Node p) { return p.prev; }
	public Node after (Node p) { return p.next; } 
	
	public boolean isEmpty(){return N==0;}
	
	public void insertFirst(String x){insertAfter(head,x);}
	
	public void insertLast(String x){insertBefore(tail,x);}
	
	public void insertBefore(Node p, String x){
		Node q = new Node (x, p.prev, p); 
		p.prev.next = q; 
		p.prev = q;
		
	}
	
	public void insertAfter(Node p, String x){
		Node q = new Node(x,p,p.next);
		p.next.prev=q;
		p.next = q;
		
	}
	
	

//	public void insertAtRank (int rank, String x) 
//	 { insertBefore (toPosition(rank), x); }
	
	public void remove (Node p) { 
		 p.prev.next = p.next; 
		 p.next.prev = p.prev; 
		 p.prev = p.next = null; 
		 p.data = "";
		} 
	
//	public void removeAtRank (int rank) 
//	 { remove (toPosition(rank)); } 
	
	public String element (Node p) 
	 { return p.data; } 
	
//	public String elementAtRank (int rank) 
//	 { return toPosition(rank).data; }
	
	//done
	public int size(){return N;}
	
	
	public boolean contains(String data){
		
		for (Node g = head.next; g!=tail; g=g.next){
			if (g.data!=null && g.value().equals(data)){
				return true;
			}
			
		}
		
		
		
		
		return false;
	}
	
	
	
	
	//done
	public Node find(String x, Node q,String s){
		if(q!=head.next)
		for (Node g = q.prev; g!=head; g=g.prev){
			
			   if ( q.list.head.next.data!=null && g.data.equals(q.list.head.next.data)){
				   return g;
				   }
			   
			   }
		
		Node g = q;
		g.list=q.list;
		g.list.head.next.data="5*0";
		g.list.head.next.next.data="2*0";
			   return g;
		
			}
	public Node find(String x, Node q,int s){
		for (Node g = q.prev; g!=head; g=g.prev){
			if(q.data!=null){
			   if (q.data!=null && q.data.equals(g.data)){
				   return g;
				   }
			   
			   }}
			   return q;
		
			}
	public Node find(String x,String s, Node q){
		for (Node g = q.prev; g!=head; g=g.prev){
			if(s!=null){
			   if (s!=null && s.equals(g.data)){
				  
				   return g;
				   }
			   
			   }}
			   return q;
		
			}
	   
	
	
	//done
	public void combine(linkedList list){
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			
			//if the string is 1 number
			if(q.data!=null && !q.value().equals("null") && q.mult().equals("") && !q.data.equals(" ")){
				q.data=q.data+"*1";
				}
			}
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			for (Node g = list.head.next; g!=list.tail; g=g.next){
				if(g.data != null && !g.mult().equals("") && g!=q && q.data!= null && !q.value().equals("null") && !g.value().equals("null") && !q.data.equals(" ")){
					if (q.value().equals(g.value())){
						int a=Integer.parseInt(""+q.mult()+"");
						int b=Integer.parseInt(""+g.mult()+"");
						int c= a+b;
						q.data = (q.value()+"*"+c);
						g.data="DONE";
					}
				}
			}
		}
	} //end merge method
		
	//done
	public void doDone(linkedList list){
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			if(q.data !=null && q.value().equals("0") | q.mult().equals("0"))
				q.data = "DONE";
			if(q.data!=null && q.data.equals("DONE")){
				q.data=null;
				
				
			}
		}
	}
		
	//done	
	public void samesies(Node q){
		for (Node g = head.next; g!=tail; g=g.next){
			   if (q.list.head.next.data!=null && q.list.head.next.data.equals(g.data)){
				   for(Node h = g.next; h!=q; h=h.next){
					   if (q.list.head.next.data!=null && q.list.head.next.data.equals(h.data)){
						   q.list = new linkedList();
						   for(Node dat = h.list.head.next; dat!=h.list.tail; dat = dat.next){
							  q.list.insert(dat.data);
						  }
						   q.size = 0;
						   combine(q.list);
					 	   doDone(q.list);
					 	   sort(q.list);
					 	   q.Size();
						   return;
					   }
				   }
				   String qname = q.list.head.next.data;
				  q.list = new linkedList();
				   for(Node dat = find(g.data,qname,q).list.head.next; dat!=find(g.data,qname,q).list.tail; dat = dat.next){
					  q.list.insert(dat.data);
				  }
				   q.size = 0;
				   q.Size();
			}
	   }
		combine(q.list);
 	   	doDone(q.list);
 	   	sort(q.list);
	}
	
	//done
	public void merge(Node q){
		
		if(q.list.size() == 1){
			
			//for (Node g = head.next; g!=tail; g=g.next){
				  // if (q.data.equals(g.data) && q!=g){
					   linkedList newQ = new linkedList();
					   String qname = q.list.head.next.data;
					   q.list.head.next.data=null;
					   Node g = head.next;
					   for(Node dat = find(g.data,q,1).list.head.next; dat!=find(g.data,q,1).list.tail; dat = dat.next){
							  newQ.insert(dat.data);
						   
					   }
					   //now add in the data from the list that was the old q.list.head.next.data
					   for (Node f =q.prev; f!=head; f=f.prev){
						   if (qname.equals(f.data)){
							   q.list = new linkedList();
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								   if(j.data!=null){
									   q.list.insert(j.data);
								   }
								   q.size = find(f.data,qname,q).size;
								   
							   }
							   
						   }
					   }
					   
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null){
						   q.list.insert(p.data);
						   q.size=0;
						   q.Size();
				    	   combine(q.list);
				    	   doDone(q.list);
				    	   sort(q.list);
					   }
					   }
			    	   
			    	   
				//   }
		//}
			
			return;
		}
		
		else if (q.list.size() != 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				   //if (q.list.head.next.data.equals(g.data) && q!=g){
					   Node g = head.next;
					   String qname = q.list.tail.prev.data;
					   linkedList newQ = new linkedList();
					   for(Node dat = find(g.data,q,"").list.head.next; dat!=find(g.data,q,"").list.tail; dat = dat.next){	
							  newQ.insert(dat.data);
					   }
					   
					   //now add in the data from the list that was the old q.list.head.next.data
					   for (Node f = head.next; f!=tail; f=f.next){
						  // System.out.println(q.list.tail.prev.data);
						   if (qname.equals(f.data) && q!=f){
							   String qq = qname;
							   q.list = new linkedList();
							   for(Node j = find(f.data,qq,q).list.head.next; j!=find(f.data,qq,q).list.tail; j=j.next){
								   if(j.data!=null){
									   q.list.insert(j.data);
								   }
								   
							   }
						   }
					   }
					   
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
					   }
					   
					   q.size=0;
			    	   q.Size();
					   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				 //  }
		//}
			return;
		}
		
		
		
	}
	
	//not done
	
	//not done
	public void union(Node q){
		
		if(q.list.size() == 1){
		//	for (Node g = head.next; g!=tail; g=g.next){
				//   if (q.data.equals(g.data) && q!=g){
					  Node g = head.next;
					   linkedList newQ = new linkedList();
					   linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   for(Node dat = find(g.data,q,1).list.head.next; dat!=find(g.data,q,1).list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					  
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									 
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											  k.data =j.data;
									   }
										   if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   k.data=k.data;
									   }
										  
								   }
									   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
										   updatedJ.insert(j.data);
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				//   }
		//}
			
			return;
		}
		
		else if (q.list.size() != 1){
			//for (Node g = head.next; g!=tail; g=g.next){
			//	if (q.list.head.next.data.equals(g.data) && q!=g){
					Node g = head.next;
					linkedList newQ = new linkedList();
					linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   for(Node dat = find(g.data,q,"").list.head.next; dat!=find(g.data,q,"").list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					  
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   String qq = q.list.tail.prev.data;
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qq,q).list.head.next; j!=find(f.data,qq,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											  k.data = j.data;
									   }
										   if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   k.data = k.data;
									   }
									}
									   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
										   updatedJ.insert(j.data);
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
			//	   }
		//}
			
			return;
		}
		
		

		
	}

	//not done
	public void intersect(Node q){
	
		if(q.list.size() == 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				 //  if (q.data.equals(g.data) && q!=g){
					   linkedList newQ = new linkedList();
					   Node g = head.next;
					   for(Node dat = find(g.data,q,1).list.head.next; dat!=find(g.data,q,1).list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									 
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   
									   }
										   else if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   k.data=j.data;
									   }
										   
								   }
									   
									   else if(k.data!=null){
											  if(!find(f.data,qname,q).list.contains(k.value())){
												  k.data="DONE";
												  doDone(newQ);
											  }
										   }
									   
							   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				//   }
		//}
			
			return;
		}
		
		else if (q.list.size() != 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				//System.out.println(g.data+"  "+g.list.head.next.data);
				//if (q.list.head.next.data.equals(g.data) && q!=g){
					Node g = head.next;
					linkedList newQ = new linkedList();
					
					   for(Node dat = find(g.data,q,"").list.head.next; dat!=find(g.data,q,"").list.tail; dat = dat.next){
						   
							  newQ.insert(dat.data);
					   }
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname) && q!=f){
							   q.list = new linkedList(); 
							  // combine(f.list);
							  // combine(newQ);
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									  
									  
									  //!find(j.data,qname,q).list.contains(k.value())  &&
									   if( j.data!=null && k.data!=null && !j.data.equals("null") && !k.data.equals("null") && j.value().equals(k.value())){
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   
									   }
										   else if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   k.data=j.data;
											   
											   
									   }
										  
										   
								   }
									   
									  
									   else if(k.data!=null){
										  if(!find(f.data,qname,q).list.contains(k.value())){
											  k.data="DONE";
											  doDone(newQ);
										  }
									   }
										   
									   
									
									   
							   }
							   }
							   
						   }
					   }
					  
					   
					   
					   
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   q.size=0;
				    	   q.Size();
				    	   combine(q.list);
				    	   doDone(q.list);
				    	   sort(q.list);
						   
					   }
			    	  
			    	   
				 //  }
		//}
			
			return;
		}
		
		
		
		
		
	}
	
	//not done
	public void difference(Node q){	
	
		if(q.list.size() == 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				 //  if (q.data.equals(g.data) && q!=g){
					   linkedList newQ = new linkedList();
					   linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   Node g = head.next;
					   for(Node dat = find(g.data,q,1).list.head.next; dat!=find(g.data,q,1).list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									 
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   k.data="DONE";
											   doDone(newQ);
									   }
										   else if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+k.mult()+"")-Integer.parseInt(""+j.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
									   }
										   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
											   updatedJ.insert(j.data);
										   
								   }
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				//   }
		//}
			
			return;
		}
		
		else if (q.list.size() != 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				//System.out.println(g.data+"  "+g.list.head.next.data);
				//if (q.list.head.next.data.equals(g.data) && q!=g){
					Node g = head.next;
					linkedList newQ = new linkedList();
					linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   for(Node dat = find(g.data,q,"").list.head.next; dat!=find(g.data,q,"").list.tail; dat = dat.next){
						   
							  newQ.insert(dat.data);
					   }
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   
						   String fname = f.data;
						  
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname) && q!=f){
							   q.list = new linkedList(); 
							  // combine(f.list);
							  // combine(newQ);
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									   if(j.data!=null && k.data!=null && !j.data.equals("null") && !k.data.equals("null") && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   k.data="DONE";
											   k.value="";
											   k.multiplication="";
											   doDone(newQ);
									   }
										   else if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+k.mult()+"")-Integer.parseInt(""+j.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
											   
									   }
										   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
											   updatedJ.insert(j.data);
										   
								   }
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   q.size=0;
				    	   q.Size();
				    	   combine(q.list);
				    	   doDone(q.list);
				    	   sort(q.list);
						   
					   }
			    	  
			    	   
				 //  }
		//}
			
			return;
		}
		
	}

	//not done
	public void symmetricDifference(Node q){
	
		if(q.list.size() == 1){
			//for (Node g = head.next; g!=tail; g=g.next){
				  // if (q.data.equals(g.data) && q!=g){
					  Node g = head.next;
					   linkedList newQ = new linkedList();
					   linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   for(Node dat = find(g.data,q,1).list.head.next; dat!=find(g.data,q,1).list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					  
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									 
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+j.mult()+"")-Integer.parseInt(""+k.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
									   }
										   if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+k.mult()+"")-Integer.parseInt(""+j.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
									   }
										   
										   
								   }
									   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
										   updatedJ.insert(j.data);
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				  // }
		//}
			
			return;
		}
		
		else if (q.list.size() != 1){
			//for (Node g = head.next; g!=tail; g=g.next){
			//	if (q.list.head.next.data.equals(g.data) && q!=g){
			Node g = head.next;
					   linkedList newQ = new linkedList();
					   linkedList updatedJ = new linkedList();
					   updatedJ.insert(null);
					   updatedJ.insert(null);
					   updatedJ.insert("DONE");
					   for(Node dat = find(g.data,q,"").list.head.next; dat!=find(g.data,q,"").list.tail; dat = dat.next){
							  newQ.insert(dat.data);
					   }
					   for (Node f = head.next; f!=tail; f=f.next){
						   String qname = "";
						   if(q.list.tail.prev.data!=null){
						   qname = q.list.tail.prev.data;}
						   String fname = f.data;
						   if (f.data!=null  && !qname.equals("") && qname.equals(fname)){
							   q.list = new linkedList(); 
							   for(Node j = find(f.data,qname,q).list.head.next; j!=find(f.data,qname,q).list.tail; j=j.next){
								  for(Node k =newQ.head.next; k!=newQ.tail; k = k.next){
									   if(j.data!=null && k.data!=null && j.value().equals(k.value())){
										   
										   if(Integer.parseInt(j.mult())>Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+j.mult()+"")-Integer.parseInt(""+k.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
											   //updatedJ.insert(j.data);
									   }
										   else if(Integer.parseInt(j.mult())<Integer.parseInt(k.mult())){
											   int newnum = Integer.parseInt(""+k.mult()+"")-Integer.parseInt(""+j.mult()+"");
											   k.data =""+k.value()+"*"+(""+newnum+"");
											   //updatedJ.insert(j.data);
									   }
										   
										   
								   }
									   else if (!newQ.contains(j.value()) && !updatedJ.contains(j.value())) 
										   updatedJ.insert(j.data);
									   
									   
							   }
							   }
							   for(Node j = updatedJ.head.next; j!=updatedJ.tail; j=j.next){
								   if (j.data!=null && j.data!="" && j.data!="DONE"){
									   newQ.insert(j.data);
								   }
							   }
						   }
					   }
					   for (Node p=newQ.head.next; p!=newQ.tail; p=p.next){
						   if(p.data != null)
						   q.list.insert(p.data);
						   
//						for (Node h = q.list.head.next; h!=q.list.tail; h=h.next){
//							System.out.println(h.data);
//						}
						   
						   
					   }
			    	   q.size=0;
			    	   q.Size();
			    	   combine(q.list);
			    	   doDone(q.list);
			    	   sort(q.list);
			    	   
				//   }
		//}
			
			return;
		}
		
		
		
	}

	//not done
	public void multiply(Node q){
		if(q.list.size() == 1){
			//for (Node g = head.next; g!=q; g=g.next){
				//   if (q.data.equals(g.data) && q!=g && q.list.head.next.data!=null){
					   Node g = head.next;
					   int mult = Integer.parseInt(q.list.head.next.data);
					   q.list = new linkedList();
					   if(find(g.data,q,1)!=null){
						   Node find = find(g.data,q,1);
					   for(Node dat = find.list.head.next; dat!=find.list.tail; dat = dat.next){
							if(dat.data!=null){  
						   q.list.insert(dat.data);
							}
						  }
					   }
					   q.size = g.size*mult;
					   
			    	   for(Node p = q.list.head.next; p!= q.list.tail; p = p.next){
						   if(p.data!=null && !p.data.equals("null")){
						   int old = Integer.parseInt(""+p.mult()+"");
						   p.data = p.value()+""+"*"+""+(""+mult*old+"");
						   q.size=0;
						   q.Size();
						   combine(q.list);
					       doDone(q.list);
					       sort(q.list);
						   }
					   }
			    	   
			//	   }
				   
	//	}
			
			return;
		}
		
		else if (q.list.size() != 1){
		//for (Node g = head.next; g!=tail; g=g.next){
			//   if (q.list.head.next.data!=null && q.list.head.next.data.equals(g.data)){
				   Node g = head.next;
				   if (find(g.data,q,"")!=null){
					   Node d = find(g.data,q,"");
				   int mult =Integer.parseInt(q.list.head.next.next.data);
				   q.list = new linkedList();
				   
				   for(Node dat = d.list.head.next; dat!=d.list.tail; dat = dat.next){
						  q.list.insert(dat.data);
					  }
				   q.size = g.size*mult;
				   
				   for(Node p = q.list.head.next; p!= q.list.tail; p = p.next){
					   if(p.data!=null && !p.data.equals("null")){
					   int old = Integer.parseInt(""+p.mult()+"");
					   p.data = p.value()+""+"*"+""+(mult*old);
					   q.size=0;
					   q.Size();
					   combine(q.list);
				       doDone(q.list);
				       sort(q.list);
					   }
				   }
					}
				   else if (find(g.data,q,"")==null){
					   int mult =Integer.parseInt(q.list.head.next.data);
					   String qname = q.list.head.next.next.data;
					   q.list = new linkedList();
					   for(Node dat = find(g.data,qname,q).list.head.next; dat!=find(g.data,qname,q).list.tail; dat = dat.next){
							  q.list.insert(dat.data);
						  }
					   q.size = g.size*mult;
					   
					   for(Node p = q.list.head.next; p!= q.list.tail; p = p.next){
						   if(p.data!=null && !p.data.equals("null")){
							   int old = Integer.parseInt(""+p.mult()+"");
							   p.data = p.value()+""+"*"+""+(mult*old);
							   q.size=0;
							   q.Size();
							   combine(q.list);
						       doDone(q.list);
						       sort(q.list);
							   }
						   
						   
					   }
					   
					   
				   }
				   
				}
			   
	}
	
	//done
	public void sort(linkedList list){
		for (Node q = list.head.next; q!= list.tail; q= q.next){
			for (Node g = list.head.next; g!= list.tail; g= g.next){
				if(q.data!=null && g.data!= null && list.ready==true){
					
				
				if(!g.data.equals("null") && !q.data.equals("null") && !g.value().equals("DONE") && !q.value().equals("DONE") && q.data!=null && g.data!=null && g!=q && !q.data.equals("") && !q.data.equals(" ") && !g.data.equals("") && !g.data.equals(" ")){
					
					if ( Integer.parseInt(""+g.value()+"") > Integer.parseInt(""+q.value()+"")){
						String gNew = g.data;
						String qNew = q.data;
						g.data = qNew;
						q.data = gNew;
				}
				}
				
			}
		}
		}
	}
	
	//done
	public String printList() {
		String s = "";
		for (Node q = head.next; q!= tail; q= q.next){
			
    	   if(!q.list.isEmpty()){
    		   if (q.write(q.list).equals(" "))
    			   q.list.ready=false;
    	   }
    	   
    	   if(!q.list.isEmpty()){
    		   if (!q.write(q.list).equals(" ")){
    			   q.Size();
    		   }
    			   
    	   }
    	   
    	   
    	   if(q.samesies){
    		   samesies(q);
    	   }
    	  else if(q.merge){
    		   merge(q);
    	   }
    	   else if(q.union){
    		   union(q);
    	   }
    	   else if(q.intersect){
    		   intersect(q);
    	   }
    	   else if(q.difference){
    		   difference(q);
    	   }
    	   else if(q.symmetricDifference){
    		   symmetricDifference(q);
    	   }
    	    if(q.multiply){
    		   multiply(q);
    	   }
    	   
    	   if(!q.list.isEmpty()){
    		   if (q.write(q.list).equals(" "))
    			   s+=q.data+" 0 {}";
    		   if (!q.write(q.list).equals(" "))
    			   s+=q.data+" "+q.size+" {"+q.write(q.list)+"}";
    		   	   s= s.replace("{,","{");
    	    }
    	   else s+=q.data;
    	   if(q!=tail.prev)
    	   s+='\n';
    	   }
		
       
       s.trim();
       return s;
       
       
    }
	
}
