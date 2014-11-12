import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {

	public static int s, n, m, r, d;
	public static int [] arrayC, arrayB, arrayQ, arrayRC, arrayRB, arrayH, arrayT, pivots;
	public static linkedList pivotList;
	public static int countingSort, binSort, radixCountingSort, radixBinSort, 
					 quickSortP, heapSortN, heapSortS, treeSortN;
	public static long quickSortI, heapSortI, treeSortI, treeSortL;;
	public static long rbsc;
	
	
	public static void main(String []args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		if(sc.hasNext()){
			//s is the seed for a pseudo-random number generator
			s = Integer.parseInt(sc.next());
		}
		if(sc.hasNext()){
			//n is the size of the array to be created
			n = Integer.parseInt(sc.next());
			arrayC = new int[n]; arrayB = new int[n]; arrayQ = new int[n]; arrayRC = new int[n]; arrayRB = new int[n]; arrayH = new int[n]; arrayT = new int[n];
		}
		if(sc.hasNext()){
			//m is the range that all array elements will be generated in the rage [0,m)
			m = Integer.parseInt(sc.next());
		}
		if(sc.hasNext()){
			//r denotes the value of the raix used by the two radix sorts
			r = Integer.parseInt(sc.next());
		}
		
		d = (int) Math.ceil(Math.log(m)/Math.log(r));
		rbsc = 0;
		PseudoRandom p = new PseudoRandom(s);
		for(int k=0; k<n; k++){
			arrayC[k]  = p.nextRandom(m);
			arrayB[k]  = arrayC[k];
			arrayQ[k]  = arrayC[k];
			arrayRC[k] = arrayC[k];
			arrayRB[k] = arrayC[k];
			arrayH[k]  = arrayC[k];
			arrayT[k]  = arrayC[k];
		}
		

		
		//counting sort
//		for(int poop: arrayC) System.out.print(poop+" ");
		CountingSort(arrayC,m);
//		for(int poop: arrayC) System.out.print(poop+" ");
		System.out.println("iterations(CountingSort) = "+countingSort);
		
		System.out.println();
		
		//bin sort
		int[] bin = BinSort(arrayB, m);
		System.out.println("iterations(BinSort) = "+binSort);
		
		
		System.out.println();
		
		int[] rb =RadixBinSort(arrayRB,r, d);
		//Radix Counting
		RadixCountingSort(arrayRC,r,d);
		//for(int g: arrayRC) System.out.println(g);
		System.out.println("checksum(RadixCountingSort,halfway) = "+rbsc);
		System.out.println("iterations(RadixCountingSort) = "+ radixCountingSort);
		
		System.out.println();
		
		//RadixBin
		
		//for(int g: rb) System.out.println(g);
		System.out.println("checksum(RadixBinSort,halfway) = "+rbsc);
		System.out.println("iterations(RadixBinSort) = "+ radixBinSort);
		
		System.out.println();
		
		//quick sort
		linkedList list = new linkedList();
		for(int x: arrayQ){
			//System.out.println(x);
			list.insert(x);
		}
		pivotList = new linkedList();
		linkedList quick = QuickSort(list);
		int[] quicky = array(quick);
		pivots = array(pivotList);
		System.out.println("checksum(QuickSort,pivots) = "+checkSum(pivots));
		System.out.println("pivots(QuickSort) = "+quickSortP);
		System.out.println("iterations(QuickSort) = "+quickSortI);
		
		System.out.println();
		
		//heap sort
		int[] heap = heapSort(arrayH);
		System.out.println("checksum(HeapSort,inorder) = "+ heapSortI);
		System.out.println("nodes(HeapSort) = "+heapSortN);
		System.out.println("swaps(HeapSort) = "+heapSortS);

		System.out.println();
		
		//tree sort
		treeSort(arrayT);
		System.out.println("checksum(TreeSort,inorder) = "+treeSortI);
		System.out.println("checksum(TreeSort,level) = "+treeSortL);
		System.out.println("nodes(TreeSort) = "+treeSortN);
		
		
		
		
	}
	
	public static void RadixCountingSort (int[] input, int r, int d) { // each A[k] in range
		radixCountingSort+= r+2;
		int []B = new int[n];
		int[]C = new int[m];
		for (int j=0; j<m; j++){
			C[j] = 0;
		}
		int[] A = new int[input.length];
		for(int i = 0; i<A.length; i++){ 
			A[i] = input[i];
		}
		int someint = 1;
		
		for (int i=1; i<d; i++){
			//radixCountingSort++;
			for (int k=0; k<n-1; k++){
				radixCountingSort++;
				C[A[k]]++;
			}
			// now each C[j] = number of copies of key j in array A
			for (int j=1; j<m; j++){
				//radixCountingSort++;
				C[j]+= C[j-1];
			}
			// now each C[j] = total number of elements <= j in array A
			for (int k=n-1; k>=0; k--) {
			radixCountingSort++;
				if(C[(A[k]/someint)%r] >=0 && C[(A[k]/someint)%r] <B.length){
				B[C[(A[k]/someint)%r]] = A[k];
				C[A[k]] --;
				}
			}
			for (int k=0; k<n; k++){
				radixCountingSort++;
				A[k] = B[k];
			}
			someint*=r;
			
		}
		
		       
	}
	
	public static int[] RadixBinSort (int[] input, int r, int d) { // each A[k] in range
		radixBinSort++;
		linkedQueue[] queues = new linkedQueue[r];
		int[] A = new int[input.length];
		for(int g = 0; g<A.length; g++){ 
			radixBinSort+=2;
			A[g] = input[g];
		}
		for (int j=0; j<queues.length; j++){
			radixBinSort+=2;
			queues[j] = new linkedQueue();
		}
		int someint = 1;
		for (int i=1; i<d; i++){
			
			radixBinSort++;
			//do any stable sort using the kth rightmost digit as key;
			for (int k=0; k<n; k++){
				radixBinSort++;
				queues[(A[k]/someint)%r].insert (A[k]);
				
			}
			// next copy all the keys from the bins to A
			int k = 0;
			for (int j=0; j<r; j++){
				radixBinSort+=2;
				while (! queues[j].isEmpty( )){
					radixBinSort++;
					A[k] = queues[j].remove( );
					k++;
				}
			}
		if(i==(d/2)){
			rbsc = checkSum(A);
			}
			someint*=r;
		}
		return A;
	}
	
	
	public static int[] heapSort(int[] array) throws Exception{
		int[] newArray = new int[array.length];
		BinaryHeap H = new BinaryHeap(array.length);
		for(int i = 0; i<array.length; i++){
			H.insert(array[i]);
		}
		H.inorder();
		heapSortI = H.inorder;
		
		for(int i = 0; i<array.length; i++){
			
			newArray[i] = H.removeMin();
		}
		heapSortS = H.swaps;
		heapSortN = H.nodes;
		
		
		
		return newArray;
	}
	
	
	
	public static void CountingSort (int[] A,int  m) { // each A[k] in range
		countingSort++;
		int []B = new int[n];
		int[]C = new int[m];
		for (int j=0; j<m; j++){
			countingSort++;
			C[j] = 0;
		}
		for (int k=0; k<n-1; k++){
			countingSort++;
			C[A[k]]++;
		}
		// now each C[j] = number of copies of key j in array A
		for (int j=1; j<m; j++){
			countingSort++;
			C[j]+= C[j-1];
		}
		// now each C[j] = total number of elements <= j in array A
		for (int k=n-1; k>=0; k--) {
			countingSort++;
			B[C[A[k]]] = A[k];
			C[A[k]] --;
		}
		for (int k=0; k<n; k++){
			countingSort++;
			A[k] = B[k];
		}
	}
	
	
	public static int[] BinSort (int[] A, int m) { // each A[k] in range 
		int[] newArray = new int[n];
		linkedQueue[] B = new linkedQueue [m];
		for (int j=0; j<m; j++){
			binSort++;
			B[j] = new linkedQueue();
		}
		// first copy all the keys from A to the bins
		for (int k=0; k<n; k++){
			binSort++;
			B[A[k]].insert (A[k]);
		}
		// next copy all the keys from the bins to A
		int k = 0;
		for (int j=0; j<m; j++){
			binSort++;
			while (! B[j].isEmpty( )){
				binSort++;
				newArray[k] = B[j].remove( );
				k++;
			}
		}
		return newArray;
		}
	
	
	public static void treeSort(int[] arr){
		BinarySearchTree tittyTree = new BinarySearchTree();
		for(int ballsack: arr){
			tittyTree.insert(ballsack);
		}
		tittyTree.inorder();
		treeSortI = tittyTree.inorder;
		treeSortN = tittyTree.nodes;
		tittyTree.levelorder();
		treeSortL = tittyTree.levelorder;
		
	}
	
	public static int[] array(linkedList A){
		int[] a = new int[A.size()];
		//System.out.println(A.size());
		int index = 0;
		for(Node q = A.head.right; q!=A.tail && index<a.length; q=q.right){
			//System.out.println(index);
			a[index] = q.key;
			index++;
		}
		return a;
	}

	
	
	

	public static linkedList QuickSort (linkedList A) {
		if (A.size()<=1){ 
			return A;
		}
		linkedList Less = new linkedList(); linkedList Equal = new linkedList(); linkedList Greater = new linkedList();
		int[] temp = array(A);
		int pivot = median(temp[0], temp[(temp.length-1)/2],temp[temp.length-1]);
		quickSortP++;
		pivotList.insert(pivot);
		for(Node x = A.head.right; x!=A.tail; x=x.right){
			quickSortI++;
			if (x.key<pivot) Less.insert((x.key));
				else if (x.key==pivot) Equal.insert((x.key));
				else /* (x>pivot) */ Greater.insert((x.key));
	}
		Less = QuickSort (Less);
		Greater = QuickSort (Greater);
		A = new linkedList();
		if(!Less.isEmpty()){
		for(Node x = Less.head.right; x!=Less.tail; x=x.right){
			quickSortI++;
			if(x!=null)A.insert(x.key);
		}}
		if(!Equal.isEmpty()){
		for(Node x = Equal.head.right; x!=Equal.tail; x=x.right){
			quickSortI++;
			if(x!=null)A.insert(x.key);
		}}
		if(!Greater.isEmpty()){
		for(Node x = Greater.head.right; x!=Greater.tail; x=x.right){
			quickSortI++;
			if(x!=null)A.insert(x.key);
		}}
//		A = concatenation of Less, Equal, Greater;
		return A;
	}

	public static int median(int a, int b, int c){
		if(a>b){
			if(b>c){
				return b;
			} else if (a>c){
				return c;
			} else{
				return a;
			}
		} else{
			if(a>c){
				return a;
			} else if(b>c){
				return c;
			} else{
				return b;
			}
		}
	}
	
	
	public static long checkSum(int[] arr){
		long checkSum = 0;
		for(int i = 0; i<arr.length; i++){
			checkSum+= (i+1) * arr[i];
			
		}
		return checkSum;
	}
	
	
	
}

class Node{
	int key;
	Node parent, left, right;
	
	public Node(int x){
		key = x; parent = null; left = null; right = null;
	}
	
	public Node(){
		parent = null; left = null; right = null;
	}
	public Node(Node p, Node l, Node r){
		parent = p;
		left = l;
		right = r;
	}
	
	public Node(int x, Node a, Node b){
		key = x;
		left = a;
		right = b;
		parent = null;
	}
	
	public int compareTo(Node that){
		return this.key - that.key;
	}
	
}

class linkedList {

	
	private int N;
	public Node head;
	public Node tail;
	
	public linkedList(){
		head = new Node(0, null, tail);
		tail = new Node(0, head, null);
		N= 0;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public void insert(int num) {
        Node G = tail.left;
        Node x = new Node(num,G,tail);
        tail.left = x;
        G.right = x;
        N++;
    }
	
	public int size(){return N;}
	
	
	
	
	
	
}


class BinaryHeap{
	Node root;
	int n;
	int swaps;
	int index;
	long inorder;
	int nodes;
	public BinaryHeap(int N){
		n = 0;
		root = null;
		swaps = 0;
		index = 0;
		inorder = 0;
		nodes = 0;
	}
	
	public void insert (int x) {
		n += 1;
		if(root==null){
			Node q = new Node();
			q.key = x;
			root = q;
			return;
		}
		Node q = kth(n);
		q.key = x;
		nodes++;
		while (q.parent != null && q.key < q.parent.key) {
			swaps++;
			int temp = q.key;
			q.key = q.parent.key;
			q.parent.key = temp;
			q = q.parent;
			}
	}
	
	public int removeMin( ) throws Exception {
		nodes++;
		if (n==0) throw new Exception();
		int x = root.key;
		Node q = kth(n);
		root.key = q.key;
		if(q.parent!= null && q.parent.left!=null && q.parent.left == q)q.parent.left = null;
		if(q.parent!=null && q.parent.right!=null && q.parent.right == q) q.parent.right = null;
		q.parent = null;
		q = null;
		n -= 1;
		Node p = root;
		Node c;
		while ((p.left != null && p.left.compareTo(p) < 0)
				|| (p.right != null && p.right.compareTo(p) < 0)) {
			c = p.left;
			if (p.right != null && p.right.compareTo(p.left) < 0)
				c = p.right;
			int temp = p.key;
			p.key = c.key;
			c.key = temp;
			swaps++;
			p = c;
		}
		return x;
		}
	
	
	
	
	
	public Node kth(int k){
		Node q = root;
		String code = Integer.toBinaryString(k);
		if(code.equals("1")){ 
			nodes++;
			return root;
		}
		for(int i = 1; i<code.length(); i++){
			nodes++;
			if(code.charAt(i)=='0'){
				if(q.left!=null){
					q=q.left;
				}
				else if(q.left==null){
					Node h = new Node();
					h.parent = q;
					q.left = h;
					return h;
				}
			}
			else if(code.charAt(i)=='1'){
				if(q.right!=null){
					q=q.right;
				}
				else if(q.right==null){
					Node h = new Node();
					h.parent = q;
					q.right = h;
					return h;
				}
			}
		}
		return q;
	}
	
	public void inorder(){
		inorder(root);
	}
	public void inorder(Node q){
		if(q == null) return;
		inorder(q.left);
		inorder+= (index+1) * q.key;
		index++;
		//System.out.println(q.key);
		inorder(q.right);
	}
	
	
	
}

class BinarySearchTree{
	Node root;
	int index, nodes;
	long inorder, levelorder;
	AlinkedQueue Q;
	
	BinarySearchTree(){
		root=null;
		index = 0; inorder=0; levelorder=0; nodes = 0;
		Q = new AlinkedQueue();
		
	}
	
	public boolean find(int x){
		return find(x,root);
	}
	public boolean find(int x, Node p){
		if(p==null) return false;
		if(x==p.key) return true;
		if(x<p.key) return find(x,p.left);
		/* if(x>p.data) */ return find(x,p.right);
	}
	
	public void insert(int x){
		root = insert(x,root);
	}
	
	public Node insert(int x, Node p){
		nodes++;
		if(p==null){ 
			return new Node(x);
		}
		if(x<p.key) p.left = insert(x,p.left);
		else if(x>p.key) p.right = insert(x,p.right);
		else if(x==p.key){p.right = insert(x,p.right);}
		return p;
	}
	
	
	public void remove(int x){
		root = remove(x,root);
	}
	
	public Node remove(int x, Node p){
		if (p==null) {
			}
		if (x<p.key)
			p.left = remove (x, p.left);
		else if (x>p.key)
			p.right = remove (x, p.right);
		else if (p.right==null)
			p = p.left;
		else if (p.left==null)
			p = p.right;
		else { // node p has two children
			p.key = findMin (p.right); // successor of x
			p.right = remove (p.key, p.right);
			}
			return p;
			}
	public int findMin (Node p) {
			if (p.left==null) return p.key;
			return findMin (p.left);
	}
	
	
	public void inorder(){
		inorder(root);
	}
	
	public void inorder(Node q){
		
		if(q==null) return;
		
		inorder(q.left);
		
		inorder += (index+1) * q.key;
		index++;
		
		inorder(q.right);
		
	}
	
	public void levelorder(){
		index=0;
		levelorder(root);
	}
	public void levelorder(Node q){
		
		if (q==null) return;
		
		Q.insert(q);
		
		while(!Q.isEmpty()){
			Node r = Q.remove();
			levelorder += (index+1) * r.key;
			index++;
			if(r.left!=null){
				Q.insert(r.left);
			}
			if(r.right !=null){
				Q.insert(r.right);
			}
			
		}
		
		
	}
	
	
	
}


/*  Class linkedQueue  */
class AlinkedQueue
{
    protected QNode front, rear;
    public int size;
 
    /* Constructor */
    public AlinkedQueue()
    {
        front = null;
        rear = null;
        size = 0;
    }    
    /*  Function to check if queue is empty */
    public boolean isEmpty()
    {
        return front == null;
    }    
    /*  Function to get the size of the queue */
    public int getSize()
    {
        return size;
    }    
    /*  Function to insert an element to the queue */
    public void insert(Node data)
    {
        QNode nptr = new QNode(data, null);
        if (rear == null)
        {
            front = nptr;
            rear = nptr;
        }
        else
        {
            rear.setLink(nptr);
            rear = rear.getLink();
        }
        size++ ;
    }    
    /*  Function to remove front element from the queue */
    public Node remove()
    {
        if (isEmpty() )
            {}
        QNode ptr = front;
        front = ptr.getLink();        
        if (front == null)
            rear = null;
        size-- ;        
        return ptr.getNode();
    }    
    /*  Function to check the front element of the queue */
    public Node peek()
    {
        if (isEmpty() )
            {}
        return front.getNode();
    }    
    /*  Function to display the status of the queue */
    public void display()
    {
        System.out.print("\nQueue = ");
        if (size == 0)
        {
            System.out.print("Empty\n");
            return ;
        }
        QNode ptr = front;
        while (ptr != rear.getLink() )
        {
            System.out.print(ptr.getData()+" ");
            ptr = ptr.getLink();
        }
        System.out.println();        
    }
}





/*  Class Node  */
class QNode
{
    protected int data;
    protected QNode link;
    public Node key;
 
    /*  Constructor  */
    public QNode()
    {
    	key = null;
        link = null;
        data = 0;
    }    
    /*  Constructor  */
    public QNode(int d,QNode n)
    {
        data = d;
        link = n;
    }
    public QNode(Node p, QNode n){
    	key = p;
    	link = n;
    }
    /*  Function to set link to next Node  */
    public void setLink(QNode n)
    {
        link = n;
    }    
    /*  Function to set data to current Node  */
    public void setData(int d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public QNode getLink()
    {
        return link;
    }    
    /*  Function to get data from current Node  */
    public int getData()
    {
        return data;
    }
    public Node getNode(){
    	return key;
    }
}
 
/*  Class linkedQueue  */
class linkedQueue
{
    protected QNode front, rear;
    public int size;
 
    /* Constructor */
    public linkedQueue()
    {
        front = null;
        rear = null;
        size = 0;
    }    
    /*  Function to check if queue is empty */
    public boolean isEmpty()
    {
        return front == null;
    }    
    /*  Function to get the size of the queue */
    public int getSize()
    {
        return size;
    }    
    /*  Function to insert an element to the queue */
    public void insert(int data)
    {
        QNode nptr = new QNode(data, null);
        if (rear == null)
        {
            front = nptr;
            rear = nptr;
        }
        else
        {
            rear.setLink(nptr);
            rear = rear.getLink();
        }
        size++ ;
    }    
    /*  Function to remove front element from the queue */
    public int remove()
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        QNode ptr = front;
        front = ptr.getLink();        
        if (front == null)
            rear = null;
        size-- ;        
        return ptr.getData();
    }    
    /*  Function to check the front element of the queue */
    public int peek()
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        return front.getData();
    }    
    /*  Function to display the status of the queue */
    public void display()
    {
        System.out.print("\nQueue = ");
        if (size == 0)
        {
            System.out.print("Empty\n");
            return ;
        }
        QNode ptr = front;
        while (ptr != rear.getLink() )
        {
            System.out.print(ptr.getData()+" ");
            ptr = ptr.getLink();
        }
        System.out.println();        
    }
}




class PseudoRandom{
	private long current;
	public PseudoRandom(long seed){
		current = (seed<<32) + 0x330e;
	}
	public int nextRandom(int m){
		current = (current*0x5deece66dL + 11) & 0xffffffffffffL;
		return (int)((current>>16)%m);
	}
}








