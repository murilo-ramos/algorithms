package tech.murilo.algorithms.graph;

public class Prim {
	//TODO: Refactor to improve variable and methods names, try OO aproach
	
	private static final int VERTICE_COUNT = 8;
	private static final int MAX_INT = Integer.MAX_VALUE;
	
	private static void primsMST(int g[][]){
	    int u = 0;
	    int v = 0;
	    int min = MAX_INT;
	    int[] track = new int[VERTICE_COUNT];
	    int[][] t = new int[2][VERTICE_COUNT - 2];
	 
	    // Initial: Find min cost edge
	    for (int i = 1; i < VERTICE_COUNT; i++){
	        track[i] = MAX_INT;  // Initialize track array with INFINITY
	        
	        
	        for (int j = i; j < VERTICE_COUNT; j++){
	            if (g[i][j] < min){
	                min = g[i][j];
	                u = i;
	                v = j;
	            }
	        }
	    }
	    
	    t[0][0] = u;
	    t[1][0] = v;
	    track[u] = track[v] = 0;
	 
	    // Initialize track array to track min cost edges
	    for (int i = 1; i < VERTICE_COUNT; i++){
	        if (track[i] != 0){
	            if (g[i][u] < g[i][v]){
	                track[i] = u;
	            } else {
	                track[i] = v;
	            }
	        }
	    }
	 
	    // Repeat
	    for (int i = 1; i < g.length - 2; i++){
	        int k = 0;
	        min = MAX_INT;
	        
	        for (int j = 1; j < VERTICE_COUNT; j++){
	            if (track[j] != 0 && g[j][track[j]] < min){
	                k = j;
	                min = g[j][track[j]];
	            }
	        }
	        
	        t[0][i] = k;
	        t[1][i] = track[k];
	        track[k] = 0;
	 
	        // Update track array to track min cost edges
	        for (int j = 1; j < VERTICE_COUNT; j++){
	            if (track[j] != 0 && g[j][k] < g[j][track[j]]){
	                track[j] = k;
	            }
	        }
	    }
	    
	    print(t, g);
	}
	
	private static void print(int t[][], int g[][]){
	    System.out.println("Minimum Spanning Tree Edges (w/ cost)");
	    
	    int sum = 0;
	    
	    for (int i = 0; i < VERTICE_COUNT - 2; i++){
	        int c = g[t[0][i]][t[1][i]];
	        System.out.println(String.format("[%s]--[%s] cost: %s ", t[0][i], t[1][i], c));
	        sum += c;
	    }
	    
	    System.out.println();
	    System.out.print(String.format("Total cost of MST: %s ", sum));
	}
	
	public static void main(String[] args) {
		int[][] cost = {
            {MAX_INT, MAX_INT, MAX_INT, MAX_INT, MAX_INT, MAX_INT, MAX_INT, MAX_INT},
            {MAX_INT, MAX_INT, 25,      MAX_INT, MAX_INT, MAX_INT, 5,       MAX_INT},
            {MAX_INT, 25,      MAX_INT, 12,      MAX_INT, MAX_INT, MAX_INT, 10},
            {MAX_INT, MAX_INT, 12,      MAX_INT, 8,       MAX_INT, MAX_INT, MAX_INT},
            {MAX_INT, MAX_INT, MAX_INT, 8,       MAX_INT, 16,      MAX_INT, 14},
            {MAX_INT, MAX_INT, MAX_INT, MAX_INT, 16,      MAX_INT, 20,      18},
            {MAX_INT, 5,       MAX_INT, MAX_INT, MAX_INT, 20,      MAX_INT, MAX_INT},
            {MAX_INT, MAX_INT, 10,      MAX_INT, 14,      18,      MAX_INT, MAX_INT},
       };      
 
       primsMST(cost);
	}

}
