import java.nio.charset.Charset;
import java.util.*; // For HashMap, HashSet


public final class Graph<T> implements Iterable<Vertex> {
    /* A map from nodes in the graph to sets of outgoing edges.  Each
     * set of edges is represented by a map from edges to doubles.
     */
    private final Map<Vertex, Set<Edge>> mGraph = new HashMap<Vertex, Set<Edge>>();
    public static int weight = 0;
    ArrayList<ArrayList<Vertex>> components;
    PriorityQueue<Vertex> Q;
    
    public Graph(){
    	components = new ArrayList<ArrayList<Vertex>>();
    	Q = new PriorityQueue<Vertex>();
    	
    }
    
    public void callMethods(){
    	makeEdges();
    	doBFS();
    	int[][] weightedD = floydWarshall(makeWeightedMatrix());
    	int[][] unWeightedD = floydWarshall(makeUnweightedMatrix());
//    	for(int[] i: weightedD) for(int b: i) {
//    		if(b==2 || b==3)
//    		System.out.println(b);
//    	}
    	printUnweighted(unWeightedD);
    	printWeighted(weightedD);
    	
    }
    
    public void printUnweighted(int[][] unweighted){
    	String s = "Unweighted distances:\t{";

    	int[] numcomp = new int[500000];
    	
    	for(int i = 0; i<unweighted.length; i++){
    			for(int j = 0; j<i; j++){
    				if(unweighted[i][j]<1000000) numcomp[unweighted[i][j]]++;
    			}
    			
    		}
    	
    	
    	int ind = 1;
    	while(numcomp[ind]==0) ind++;
    	s+=ind+"*"+(numcomp[ind]);
    	for(int i = ind+1; i<numcomp.length; i++){
    		if(numcomp[i]!=0){
    			int h = numcomp[i];
    			s+=","+i+"*"+((h));
    		}
    	}
    	s+="}";
    	
    	System.out.println(s);
    }
    public void printWeighted(int[][] weighted){
    	String s = "Weighted distances:\t{";
    	
    	int[] numcomp = new int[500000];
    	
    	for(int i = 0; i<weighted.length; i++){
			for(int j = 0; j<i; j++){
				if(weighted[i][j]<1000000) numcomp[weighted[i][j]]++;
			}
			
		}
    	
    	
    	int ind = 1;
    	while(numcomp[ind]==0) ind++;
    	s+=ind+"*"+(numcomp[ind]);
    	for(int i = ind+1; i<numcomp.length; i++){
    		if(numcomp[i]!=0){
    			int h = numcomp[i];
    			s+=","+i+"*"+(numcomp[i]);
    		}
    	}
    	s+="}";
    	
    	System.out.println(s);
    }
    
    //use floyd wershallldfsdfs
    public int[][] floydWarshall(int[][] d){
    	int n = size();
    	int[][] D = d;
    	for(int k = 0; k<n; k++){
    		for(int i = 0; i<n; i++){
    			for(int j = 0; j<n; j++){
    				D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
    				
    			}
    		}
    	}
    	return D;
    	
    }
    
    public int[][] makeWeightedMatrix(){
    	int[][] matrix = new int[size()][size()];
    	int col = 0;
    	int row = 0;
    	for(Vertex j: this){
    		for(Vertex k: this){
    			if (j==k) matrix[col][row] = 0;
    			else if(edgeExists(j, k)){
    				int g = 0;
    				for(Edge e: edgesFrom(j)){
    					if( e.v == k){
    						g = e.weight;
    					}
    				}
    				matrix[col][row] = g;
    			}
    			else //edge does not exist
    			{
    				matrix[col][row] = 100000000;
    			}
    			
    			row++;
    		}
    		row = 0;
    		col++;
    	}
    	return matrix;
    }
   
    public int[][] makeUnweightedMatrix(){
    	int[][] matrix = new int[size()][size()];
    	int col = 0;
    	int row = 0;
    	for(Vertex j: this){
    		for(Vertex k: this){
    			if (j==k) matrix[col][row] = 0;
    			else if(edgeExists(j, k)){
    				matrix[col][row] = 1;
    			}
    			else //edge does not exist
    			{
    				matrix[col][row] = 1000000;
    			}
    			
    			row++;
    		}
    		row = 0;
    		col++;
    	}
    	return matrix;
    }
    
    public void doBFS(){
    	int count = 0;
    	for(Vertex v: this){
    		if(!v.seen){
    			ArrayList<Vertex> alv = new ArrayList<Vertex>();
    			components.add(alv);
    			BFS(v,count);
    			count++;
    		}
    	}
    	int[] numcomp = new int[500000];
    	for(ArrayList<Vertex> al: components){
    		numcomp[al.size()] +=1;
    	}
    	String s = "";
    	int ind = 1;
    	while(numcomp[ind]==0) ind++;
    	s+=ind+"*"+numcomp[ind];
    	for(int i = ind+1; i<numcomp.length; i++){
    		if(numcomp[i]!=0){
    			s+=","+i+"*"+numcomp[i];
    		}
    	}
    	System.out.println("Component sizes:\t{"+s+"}");
    }
    
    public boolean addNode(String node) {
        /* If the node already exists, don't do anything. */
        if (mGraph.containsKey(node))
            return false;

        /* Otherwise, add the node with an empty set of outgoing edges. */
        mGraph.put(new Vertex(node), new HashSet<Edge>());
        return true;
    }

    
    public boolean nodeExists(T node) {
        return mGraph.containsKey(node);
    }

   
    public void addEdge(Vertex one, Vertex two, int g) {
        /* Confirm both endpoints exist. */
        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
            throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Add the edge in both directions. */
        mGraph.get(one).add(new Edge(two, g));
       // mGraph.get(two).add(new Edge(one, g));
    }

   
    public void removeEdge(T one, T two) {
        /* Confirm both endpoints exist. */
        if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
            throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Remove the edges from both adjacency lists. */
        mGraph.get(one).remove(two);
        mGraph.get(two).remove(one);
    }

   
    public boolean edgeExists(Vertex one, Vertex two) {
        /* Confirm both endpoints exist. */
        if (!mGraph.containsKey(one))
            throw new NoSuchElementException("Both nodes must be in the graph.");     
        
        /* Graph is symmetric, so we can just check either endpoint. */
        for(Edge e: edgesFrom(one)){
        	if (e.v == two) return true;
        }
        return false;
       // return mGraph.get(one).contains(two);
    }

    
    public Set<Edge> edgesFrom(Vertex node) {
        /* Check that the node exists. */
        Set<Edge> arcs = mGraph.get(node);
        if (arcs == null)
            throw new NoSuchElementException("Source node does not exist.");
        return Collections.unmodifiableSet(arcs);
    }
    
    
    public void BFS(Vertex start, int count){
    	start.seen = true;
    	components.get(count).add(start);
    	
    	Q.add(start);
		while(!Q.isEmpty()){
			Vertex x = (Vertex) Q.remove();
			for(Edge y: edgesFrom(x)){
				if(!y.v.seen){
					BFS(y.v,count);
					Q.add(y.v);
				}
			}
		}
    	
    	
    }
    
    public void makeEdges(){
    	
    	for (Vertex j : this){
    		for(Vertex k : this){
    			if(!j.equals(k)){
    				if( compareTo(j.toString().length(), k.toString().length()) <= 1){
    					int[] g = diff(j.toString(),k.toString());
    					if (  Math.abs(g[0]- Math.max(j.toString().length(), k.toString().length())) <=1  ){
    						
    							addEdge(j, k, g[1]);
    						
    				}
    			}
    			}
    		}
    	}
    	
    }
    
    public int[] diff(String a, String b){
    	int count = 0;
    	int weight = 0;
    	ArrayList<Character> aray = new ArrayList<Character>();
    	ArrayList<Character> bray = new ArrayList<Character>();
    	char[] aa = a.toCharArray();
    	char[] bb = b.toCharArray();
    	for(int i = 0; i<aa.length; i++){
    		for(int k = 0; k<bb.length; k++){
    			if(aa[i]==bb[k] && aa[i]!='_' && bb[k]!='_'){
    				aa[i] = '_'; bb[k] = '_';
    				count++;
    			}
    		}
    	}
    	int ax = 0;
    	int bx = 0;
    	for(char j: aa){
    		if(j!='_') aray.add(j);
    	}
    	for(char j: bb){
    		if(j!='_') bray.add(j);
    		}
    	if(aray.size()==1 && bray.size()==1){
    		weight = LevenshteinDistance(a ,b);
    	}
    	else if(aray.size()==0 && bray.size()==1){
    		weight = LevenshteinDistance(a,b);
    	}
    	else if(aray.size()==1 && bray.size()==0){
    		weight = LevenshteinDistance(a,b);
    	}
    	int[] g = new int[]{count, weight};
    	return g;
    }
    
    public static int LevenshteinDistance(String word1, String word2) {
    	int len1 = word1.length();
    	int len2 = word2.length();
     
    	// len1+1, len2+1, because finally return dp[len1][len2]
    	int[][] dp = new int[len1 + 1][len2 + 1];
     
    	for (int i = 0; i <= len1; i++) {
    		dp[i][0] = i;
    	}
     
    	for (int j = 0; j <= len2; j++) {
    		dp[0][j] = j;
    	}
     
    	//iterate though, and check last char
    	for (int i = 0; i < len1; i++) {
    		char c1 = word1.charAt(i);
    		for (int j = 0; j < len2; j++) {
    			char c2 = word2.charAt(j);
     
    			//if last two chars equal
    			if (c1 == c2) {
    				//update dp value for +1 length
    				dp[i + 1][j + 1] = dp[i][j];
    			} else {
    				int replace = dp[i][j] + 1;
    				int insert = dp[i][j + 1] + 1;
    				int delete = dp[i + 1][j] + 1;
     
    				int min = replace > insert ? insert : replace;
    				min = delete > min ? min : delete;
    				dp[i + 1][j + 1] = min;
    			}
    		}
    	}
     
    	return dp[len1][len2];
    }
    
    
    public int compareTo(int a, int b){
    	int x = Math.abs(a-b);
    	return x;
    }

    public boolean containsNode(T node) {
        return mGraph.containsKey(node);
    }

    
    public Iterator<Vertex> iterator() {
        return mGraph.keySet().iterator();
    }

   
    public int size() {
        return mGraph.size();
    }

    
    public boolean isEmpty() {
        return mGraph.isEmpty();
    }

    
    public String toString() {
        return mGraph.toString();
    }
}


class Vertex implements Comparable<Vertex>{

	public boolean seen;
	public String s;
	
	public String toString(){
		return s;
	}
	
	public Vertex(String g){
		s = g;
		seen = false;
	}

	@Override
	public int compareTo(Vertex arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

class Edge{
	
	Vertex v;
	int weight;
	
	public Edge(Vertex a, int g){
		v = a;
		weight = g;
	}
	
	public String toString (){
		return v.s+": "+weight;
	}
	
}

