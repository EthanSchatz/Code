import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	BufferedReader reader;
	
	String preorder;
	String postorder;
	
	linkedList list;
	RBTree tree;
	
	public void read(){
		list = new linkedList();
		reader = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			String s="";
			String whichOp = "";
			int number;
			String num = "";
			
			try {
				s = reader.readLine();
				int stop = s.indexOf(' ');
				whichOp = s.substring(0, stop);
				num = s.substring(stop+1);
				number = Integer.parseInt(num);
			
			
			list.insert(whichOp, number);
			
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
		
			tree = new RBTree(list);
		
	}
	
	public static void main(String[] args){
		Main abc = new Main();
		abc.read();
	}
	
	
	
}

class RBTree
{
    private RedBlackNode header;    
    private static RedBlackNode nullNode;
    private linkedList list;
    /* static initializer for nullNode */
    static 
    {
        nullNode = new RedBlackNode(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    /* Black - 1  RED - 0 */
    static final int BLACK = 1;    
    static final int RED   = 0;

    /* Constructor */
    public RBTree(linkedList IList)
    {
    	header = new RedBlackNode(Integer.MIN_VALUE);
        header.left = nullNode;
        header.right = nullNode;
        this.list = IList;
        buildTree();
        
    }
    
    public void buildTree(){
		int count = 0;
		for (Node n = list.head.next; n != list.tail; n = n.next){
			if( n.whichOp.equals("Insert")){
				insert(n.number);
				if(count!=0) System.out.print("\n");
				this.inorder();
				count++;
			}
			if( n.whichOp.equals("Remove")){
				remove(n.number);
				if(count!=0) System.out.print("\n");
				//if(n.next== list.tail)header.right.right.color=RED; 
				this.inorder();
				count++;
				
			}
			
		}
		
    }
    
    
    
    public void remove(int b){
    	RedBlackNode z = search(b);
    	RedBlackNode x;
    	RedBlackNode y = z;
    	int yOriginalColor = y.color;
    	if(z.left == nullNode){ 
    		x = z.right;
    		rbTransplant(z,z.right);
    	}
    	else if(z.right == nullNode){
    		x=z.left;
    		rbTransplant(z,z.left);
    	}
    	else{
    		y = min(z.right);
    		yOriginalColor = y.color;
    		x = y.right;
    		if(y.parent == z)
    			x.parent = y;
    		else{
    			rbTransplant(y,y.right);
    			y.right = z.right;
    			y.right.parent = y;
    		}
    		rbTransplant(z,y);
    		y.left = z.left;
    		y.left.parent = y;
    		y.color = z.color;
    	}
    	if (yOriginalColor == BLACK) rbDeleteFixup(x);
    }
    
    public void rbDeleteFixup(RedBlackNode x){
    	RedBlackNode w;
    	while( x.color == BLACK){
    		if(x.parent!=nullNode && x.parent!=null && x==x.parent.left){
    			w = x.parent.right;
    			if(w.color == RED){
    				//case 1
    				w.color = BLACK;
    				x.parent.color = RED;
    				leftRotate(x.parent);
    				w = x.parent.right;
    			}
    			if (w.color==BLACK && w.left.color == BLACK && w.right.color == BLACK){
    				//case 2
    				w.color = RED;
    				x = x.parent;
    			}
    			else if(w.color == BLACK && w.left.color == RED && w.right.color == BLACK){
    					//case3
    					w.left.color = BLACK;
    					w.color = RED;
    					rightRotate(w);
    					w = x.parent.right;
    				}
    			else if(w.color == BLACK &&  w.right.color==RED){
    					//case 4
    					w.color = x.parent.color;
    					x.parent.color = BLACK;
    					w.right.color = BLACK;
    					leftRotate(x.parent);
    					x = header.right;
    				}
    		}
    		else {
    			w = x.parent.left;
    			if(w.color == RED){
    				//case 1
    				w.color = BLACK;
    				x.parent.color = RED;
    				rightRotate(x.parent);
    				w = x.parent.left;
    			}
    			if (w.color==BLACK && w.right.color == BLACK && w.left.color == BLACK){
    				//case 2
    				w.color = RED;
    				x = x.parent;
    			}
    			else if(w.color == BLACK && w.right.color == RED && w.left.color == BLACK){
    					//case3
    					w.right.color = BLACK;
    					w.color = RED;
    					leftRotate(w);
    					w = x.parent.left;
    				}
    			else if(w.color == BLACK &&  w.left.color==RED){
    					//case 4
    					w.color = x.parent.color;
    					x.parent.color = BLACK;
    					w.left.color = BLACK;
    					rightRotate(x.parent);
    					x = header.right;
    				}
    		}
    	}
    	x.color = BLACK;
    }
    
    
    
    public void rbTransplant(RedBlackNode u, RedBlackNode v){
    	if (u.parent == nullNode) header.right = v;
    	else if( u == u.parent.left) u.parent.left = v;
    	else u.parent.right = v;
    	v.parent = u.parent;
    	
    	
    }
    
    private RedBlackNode min(RedBlackNode x) { 
        // assert x != null;
        if (x.left == nullNode) return x; 
        else  return min(x.left); 
    } 
    
    public boolean isRed(RedBlackNode p){
    	return p.color==RED;
    }
    

	/* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return header.right == nullNode;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        header.right = nullNode;
    }
    /* Function to insert item */
    
    public void insert(int ele){
    	if(find(ele)) return;
    	RedBlackNode z = new RedBlackNode(ele,nullNode,nullNode);
    	RedBlackNode y = nullNode;
    	RedBlackNode x = header.right;
    	while(x!=nullNode){
    		y=x;
    		if(z.element < x.element) x = x.left;
    		else x = x.right;
    	}
    	z.parent = y;
    	if(y==nullNode) header.right = z;
    	else if (z.element < y.element) y.left = z;
    	else y.right = z;
    	z.left = nullNode;
    	z.right = nullNode;
    	z.color = RED;
    	rbInsertFixup(z);
    }
    
    public void rbInsertFixup(RedBlackNode z){
    	RedBlackNode y;
    	while(z.parent.color ==RED){
    		if(z.parent == z.parent.parent.left){
    			y = z.parent.parent.right;
    			if(y.color == RED){
    				z.parent.color = BLACK;
    				y.color = BLACK;
    				z.parent.parent.color = RED;
    				z = z.parent.parent;
    			}
    			else if (z == z.parent.right){
    				
    					//case2
    					z = z.parent;
    					leftRotate(z);
    			}
    			else if(z==z.parent.left){	
    					//case3
    				z.parent.color = BLACK;
    				z.parent.parent.color = RED;
    				rightRotate(z.parent.parent);
    			}
    		}
    		else{
    			y = z.parent.parent.left;
    			if(y.color == RED){
    				z.parent.color = BLACK;
    				y.color = BLACK;
    				z.parent.parent.color = RED;
    				z = z.parent.parent;
    			}
    			else if (z == z.parent.left){
    				//case 2
    					z = z.parent;
    					rightRotate(z);
    			}
    			else if(z==z.parent.right){
    					//case3
    				z.parent.color = BLACK;
    				z.parent.parent.color = RED;
    				leftRotate(z.parent.parent);
    			}
    		}
    		}
    	header.right.color = BLACK;
    	}
    
    
    /* Rotate binary tree node with left child */
    private RedBlackNode rightRotate(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.left;
        k1.left = k2.right;
        if(k2.right!=nullNode) k2.right.parent = k1;
        k2.parent = k1.parent;
        if(k1.parent == nullNode) header.right = k2;
        else if(k1== k1.parent.right)k1.parent.right = k2;
        else k1.parent.left = k2;
        k2.right = k1;
        k1.parent = k2;
        return k2;
    }
    /* Rotate binary tree node with right child */
    private RedBlackNode leftRotate(RedBlackNode k1)
    {
    	 RedBlackNode k2 = k1.right;
         k1.right = k2.left;
         if(k2.left!=nullNode) k2.left.parent = k1;
         k2.parent = k1.parent;
         if(k1.parent == nullNode) header.right = k2;
         else if(k1== k1.parent.left)k1.parent.left = k2;
         else k1.parent.right = k2;
         k2.left = k1;
         k1.parent = k2;
         return k2;
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(header.right);
    }
    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public RedBlackNode search(int val)
    {
        return search(header.right, val);
    }
    private RedBlackNode search(RedBlackNode r, int val)
    {
        RedBlackNode found = null;
        while ((r != nullNode) && found==null)
        {
            int rval = r.element;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = r;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    public boolean find(int val)
    {
        return find(header.right, val);
    }
    private boolean find(RedBlackNode r, int val)
    {
       boolean found = false;
        while ((r != nullNode) && found==false)
        {
            int rval = r.element;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = find(r, val);
        }
        return found;
    }
    
    /* Function for inorder traversal */ 
    public void inorder()
    {
   	 System.out.print('(');
        inorder(header.right);
        System.out.print(")");
    }
    private void inorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
       	 if(r.left!=nullNode)
       	 System.out.print('(');
            inorder(r.left);
       if(r.left!=nullNode)
            System.out.print(") ");
            char c = 'b';
            if (r.color == 0)
                c = 'r';
            System.out.print(r.element+""+c);
            if(r.right!=nullNode)
           	 System.out.print(" (");
            inorder(r.right);
            if(r.right!=nullNode)
           	 System.out.print(')');
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(header.right);
    }
    private void preorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
            preorder(r.left);             
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(header.right);
    }
    private void postorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            postorder(r.left);             
            postorder(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
        }
    }

    
}
	
	
	


class RedBlackNode
{    
    RedBlackNode left, right, parent;
    int element;
    int color;

    /* Constructor */
    public RedBlackNode(int theElement)
    {
        this( theElement, null, null );
    } 
    /* Constructor */
    public RedBlackNode(int theElement, RedBlackNode lt, RedBlackNode rt)
    {
        left = lt;
        right = rt;
        element = theElement;
        color = 1;
    }   
    
    public int compareTo(int k){
    	return this.element - k;
    }
}

class Node{
	String whichOp;
	int number;
	Node next, prev;

	public Node(String Op, int num, Node p, Node q) { 
		whichOp = Op;
		number = num;
		prev = p;
		next = q;
	}

}
	
class linkedList {

	
	private int N;
	public Node head;
	public Node tail;
	
	public linkedList(){
		head = new Node(null,0, null, tail);
		tail = new Node(null, 0, head, null);
		N= 0;
	}
	
	public void insert(String Op, int num) {
        Node G = tail.prev;
        Node x = new Node(Op,num,G,tail);
        tail.prev = x;
        G.next = x;
        N++;
    }
	
	public int size(){return N;}
	
	
	
	
	
	
}

