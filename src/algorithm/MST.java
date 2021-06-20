package algorithm;

import java.util.ArrayList;

import controller.Controller;
import graph.Edge;
import graph.Graph;
import graph.ManageNode;
import graph.MyNode;
import javafx.scene.layout.Pane;
public class MST {
	private final int N;
	private final int start;
	private static double[][] distance;
	
	private  ArrayList<Integer> final_ans = new ArrayList<Integer>();	
	
	
	private static double[][] getMatrix(Graph graph,double[][] distance) {
//		for (double[] row : distance) java.util.Arrays.fill(row, 10000);
		for(Edge e:graph.getEdgeList()) {
			int i=e.getSource().getNodeID()-1,j=e.getTarget().getNodeID()-1;
			distance[i][j]=e.getWeight();
			distance[j][i]=e.getWeight();
		}
		return distance;
	}
	
	public MST(int start, double[][] distance) {
		  N = distance.length;

		  this.start = start;
		  MST.distance = distance;
		}
	
	private int minimum_key(double[] key, boolean[] mstSet, int n)
	{
		double min = Double.MAX_VALUE;
		int min_index = 0;

		for (int v = 0; v <n; v++)
		{
			if (mstSet[v] == false && key[v] < min)
			{
				min = key[v];
				min_index = v;
			}
		}

		return min_index;
	}
	
	private ArrayList<ArrayList<Integer>> makeMST(int[] parent, double[][] graph, int V)
	{
		ArrayList<ArrayList<Integer>> v = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i < V; i++)
		{
			ArrayList<Integer> p = new ArrayList<Integer>();
			p.add(parent[i]);
			p.add(i);
			v.add(p);
		}
		return v;
	}
	
	private ArrayList< ArrayList<Integer>> primMST(double[][] graph, int V)
	{
		int[] parent = new int[V];
		double[] key = new double[V];

		// to keep track of vertices already in MST 
		boolean[] mstSet = new boolean[V];

		// initializing key value to INFINITE & false for all mstSet
		for (int i = 0; i < V; i++)
		{
			key[i] = Double.MAX_VALUE;
			mstSet[i] = false;
		}

		// picking up the first vertex and assigning it to 0
		key[0] = 0;
		parent[0] = -1;

		// The Loop
		for (int count = 0; count < V - 1; count++)
		{
			// checking and updating values wrt minimum key
			int u = minimum_key(key, mstSet, V);
			mstSet[u] = true;
			for (int v = 0; v < V; v++)
			{
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v])
				{
					parent[v] = u;
					key[v] = graph[u][v];
				}
			}
		}
		ArrayList< ArrayList<Integer>> v = new ArrayList< ArrayList<Integer>>();
		v = makeMST(parent, graph, V);	
		return v;
	}

	private  void DFS(double[][] edges_list, int num_nodes, int starting_vertex, boolean[] visited_nodes)
	{
		// adding the node to final answer
		final_ans.add(starting_vertex);

		// checking the visited status 
		visited_nodes[starting_vertex] = true;

		// using a recursive call
		for (int i = 0;i < num_nodes;i++)
		{
			if (i == starting_vertex)
			{
				continue;
			}
			if (edges_list [starting_vertex][i] == 1)
			{
				if (visited_nodes[i])
				{
					continue;
				}
				DFS(edges_list, num_nodes, i, visited_nodes);
			}
		}
	}
	
	  private static void displayLine(Pane root, Graph graph) {
			graph.addLine(graph);
			graph.display(root);
		}
	
	public static void runMST(Pane root) {
		long startTime = System.currentTimeMillis();
		Graph graph = new Graph(ManageNode.getInstance().getNodeList());
		int n = graph.getNodeList().size();
		graph.getAllEdges();
		double[][] distanceMatrix = new double[n][n];
		distanceMatrix = getMatrix(graph, distanceMatrix);
		ArrayList<MyNode> newNodeList = new ArrayList<MyNode>();
		
		ArrayList<ArrayList<Integer>> v = new ArrayList<ArrayList<Integer>>();
		int start = 0;
		MST solver = new MST(start, distanceMatrix);
		v = solver.primMST(distanceMatrix,n);
		
		for(int i=0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
		
		double[][] edges_list = new double[n][n];
		for(int i = 0; i < n; i++) {
			edges_list[i] = new double[n];
			for(int j = 0; j < n; j ++) {
				edges_list[i][j] = 0;
			}
		}
		for (int i = 0;i < v.size();i++)
		{
			int first_node = v.get(i).get(0);
			int second_node = v.get(i).get(1);
			edges_list[first_node][second_node] = 1;
			edges_list[second_node][first_node] = 1;
		}
		
		boolean[] visited_nodes = new boolean[n];
		for (int i = 0;i < n;i++)
		{
			boolean visited_node;
			visited_nodes[i] = false;
		}
		
		solver.DFS(edges_list, n, start, visited_nodes);	
		
		solver.final_ans.add(solver.final_ans.get(0));
		
		for(int i = 0; i < solver.final_ans.size(); i++) {
			for(MyNode node:graph.getNodeList()) {
				if(node.getNodeID() == i) newNodeList.add(node);
			}
		}
		
		for(int i = 0 ; i < solver.final_ans.size(); i++) {
			String a =+ solver.final_ans.get(i) + "-";
			System.out.print(solver.final_ans.get(i) + "-");
		}
		Graph finalGraph = new Graph(newNodeList);
		Controller.clearLine(root);
		displayLine(root, finalGraph);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		String message = "Shortest path";
		message = message+"\nAlgorithm's run time: "+String.valueOf(totalTime)+"ms";
		Controller.printDescription(message, root);
		
	}
}
