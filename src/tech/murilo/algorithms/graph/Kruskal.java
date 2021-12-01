package tech.murilo.algorithms.graph;

public class Kruskal {
	//TODO: Refactor to improve variable and methods names, try OO aproach
	
	private static final int MAX_INT = Integer.MAX_VALUE;
	private static final int VERTICE_COUNT = 7;
	private static final int EDGE_COUNT = 9; 
	
	 
	private static void kruskalsMCST(int a[][]){
	    int[][] t = new int[2][VERTICE_COUNT - 1];  // Solution array
	    int[] track = new int[EDGE_COUNT];  // Track edges that are included in solution
	    int set[] = {-1, -1, -1, -1, -1, -1, -1, -1};  // Array for finding cycle
	 
	    int i = 0;
	    while (i < VERTICE_COUNT - 1){
	        int min = MAX_INT;
	        int u = 0;
	        int v = 0;
	        int k = 0;
	 
	        // Find a minimum cost edge
	        for (int j = 0; j < EDGE_COUNT; j++){
	            if (track[j] == 0 && a[2][j] < min){
	                min = a[2][j];
	                u = a[0][j];
	                v = a[1][j];
	                k = j;
	            }
	        }
	 
	        // Check if the selected min cost edge (u, v) forming a cycle or not
	        if (find(u, set) != find(v, set)){
	            t[0][i] = u;
	            t[1][i] = v;
	 
	            // Perform union
	            union(find(u, set), find(v, set), set);
	            i++;
	        }
	        
	        track[k] = 1;
	    }
	 
	    print(t);
	}
	
	private static int find(int u, int s[]){
	    int x = u;
	    int v = 0;
	 
	    while (s[x] > 0){
	        x = s[x];
	    }
	 
	    while (u != x){
	        v = s[u];
	        s[u] = x;
	        u = v;
	    }
	    return x;
	}
	
	private static void union(int u, int v, int s[]){
	    if (s[u] < s[v]){
	        s[u] += s[v];
	        s[v] = u;
	    } else {
	        s[v] += s[u];
	        s[u] = v;
	    }
	}
	
	private static void print(int t[][]){
		System.out.println("Minimum Cost Spanning Tree Edges");
	    
	    for (int i = 0; i < VERTICE_COUNT - 1; i++){
	    	System.out.println(String.format("[%s]--[%s]", t[0][i], t[1][i]));
	        
	    }
	    
	    System.out.println();
	}
	
	public static void main(String[] args) {
		int[][] edges =  
		{
		    { 1, 1,  2,  2, 3,  4,  4,  5,  5},
            { 2, 6,  3,  7, 4,  5,  7,  6,  7},
            {25, 5, 12, 10, 8, 16, 14, 20, 18}
        };

		kruskalsMCST(edges);
	}
}
