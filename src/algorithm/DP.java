package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import graph.Graph;
import graph.ManageNode;
import graph.MyNode;
import javafx.scene.layout.Pane;
import graph.Edge;

public class DP {
	private final int N;
	private final int start;
	  private static double[][] distance;
	  private List<Integer> tour = new ArrayList<>();
	  private double minTourCost = Double.POSITIVE_INFINITY;
	  private boolean ranSolver = false;	  
	  private static int step=1;
	  
	  public DP	(double[][] distance) {
	    this(0, distance);
	  }

	  public DP(int start, double[][] distance) {
	    N = distance.length;

	    if (N < 2) throw new IllegalStateException("N < 2 not yet supported.");
	    if (N != distance[0].length) throw new IllegalStateException("Matrix must be square (n x n)");
	    if (start < 0 || start >= N) throw new IllegalArgumentException("Invalid startMyNode.");
	    if (N > 32)
	      throw new IllegalArgumentException(
	          "Matrix too large! A matrix that size for the DP TSP problem with a time complexity of"
	              + "O(n^2*2^n) requires way too much computation for any modern home computer to handle");

	    this.start = start;
	    DP.distance = distance;
	  }
	  
	  private static double[][] getMatrix(Graph graph,double[][] distance) {
//		  for (double[] row : distance) java.util.Arrays.fill(row, 10000);
		  for(Edge e:graph.getEdgeList()) {
			  int i=e.getSource().getNodeID()-1,j=e.getTarget().getNodeID()-1;
			  distance[i][j]=e.getWeight();
			  distance[j][i]=e.getWeight();
		  }
		  return distance;
	  }

	  public List<Integer> getTour() {
	    if (!ranSolver) solve();
	    return tour;
	  }

	  public double getTourCost() {
	    if (!ranSolver) solve();
	    return minTourCost;
	  }

	  public void solve() {

	    if (ranSolver) return;

	    final int END_STATE = (1 << N) - 1;
	    Double[][] memo = new Double[N][1 << N];


	    for (int end = 0; end < N; end++) {
	      if (end == start) continue;
	      memo[end][(1 << start) | (1 << end)] = distance[start][end];
	    }

	    for (int r = 3; r <= N; r++) {
	      for (int subset : combinations(r, N)) {
	        if (notIn(start, subset)) continue;
	        for (int next = 0; next < N; next++) {
	          if (next == start || notIn(next, subset)) continue;
	          int subsetWithoutNext = subset ^ (1 << next);
	          double minDist = Double.POSITIVE_INFINITY;
	          for (int end = 0; end < N; end++) {
	            if (end == start || end == next || notIn(end, subset)) continue;
	            double newDistance = memo[end][subsetWithoutNext] + distance[end][next];
	            if (newDistance < minDist) {
	              minDist = newDistance;
	            }
	          }
	          memo[next][subset] = minDist;
	        }
	      }
	    }

	    for (int i = 0; i < N; i++) {
	      if (i == start) continue;
	      double tourCost = memo[i][END_STATE] + distance[i][start];
	      if (tourCost < minTourCost) {
	        minTourCost = tourCost;
	      }
	    }

	    int lastIndex = start;
	    int state = END_STATE;
	    tour.add(start+1);

	    for (int i = 1; i < N; i++) {

	      int bestIndex = -1;
	      double bestDist = Double.POSITIVE_INFINITY;
	      for (int j = 0; j < N; j++) {
	        if (j == start || notIn(j, state)) continue;
	        double newDist = memo[j][state] + distance[j][lastIndex];
	        if (newDist < bestDist) {
	          bestIndex = j;
	          bestDist = newDist;
	        }
	      }

	      tour.add(bestIndex+1);
	      state = state ^ (1 << bestIndex);
	      lastIndex = bestIndex;
	    }

	    tour.add(start+1);
	    Collections.reverse(tour);

	    ranSolver = true;
	  }

	  private static boolean notIn(int elem, int subset) {
	    return ((1 << elem) & subset) == 0;
	  }

	  public static List<Integer> combinations(int r, int n) {
	    List<Integer> subsets = new ArrayList<>();
	    combinations(0, 0, r, n, subsets);
	    return subsets;
	  }
	  private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

		    int elementsLeftToPick = n - at;
		    if (elementsLeftToPick < r) return;

		    if (r == 0) {
		      subsets.add(set);
		    } else {
		      for (int i = at; i < n; i++) {

		        set ^= (1 << i);

		        combinations(set, i + 1, r - 1, n, subsets);
		        set ^= (1 << i);
		      }
		    }
		  }
	  
	  private static void displayLine(Pane root, Graph graph) {
			graph.addLine(graph);
			graph.display(root);
		}
	  
	  public static Graph getSubgraph(int s, Graph graph) {
		  ArrayList<MyNode> subset = new ArrayList<MyNode>();
		  for(int i=0;i<s;i++) subset.add(graph.getNode(i+1));
		  Graph subgraph = new Graph(subset);
		  return subgraph;
	  }
	  
	  public static void run(Pane root) {
		  long startTime = System.currentTimeMillis();
		  Graph graph = new Graph(ManageNode.getInstance().getNodeList());
		  int n = graph.getNodeList().size();
		  graph.getAllEdges();
		  double[][] distanceMatrix = new double[n][n];
		  distanceMatrix = getMatrix(graph, distanceMatrix);
		  ArrayList<MyNode> newNodeList = new ArrayList<MyNode>();
		  int start = 0;
		  DP solver = new DP(start, distanceMatrix);
		  for(int i:solver.getTour()) {
			  for(MyNode node:graph.getNodeList())
				  if(node.getNodeID()==i) newNodeList.add(node);
		  }
		  Graph finalGraph = new Graph(newNodeList);
		  Controller.clearLine(root);
		  displayLine(root, finalGraph);
		  String message = "Shortest tour: "+solver.getTour().toString()+"\nTour cost: "+String.valueOf(solver.getTourCost());
		  long endTime   = System.currentTimeMillis();
		  long totalTime = endTime - startTime;
		  message = message+"\nAlgorithm's run time: "+String.valueOf(totalTime)+"ms";
		  Controller.printDescription(message, root);
	  }
	  
	  public static void runInStep(Pane root) {
		  Graph graph = new Graph(ManageNode.getInstance().getNodeList());
		  int n = graph.getNodeList().size();
		  if(step<n) step++; else step =2;
		  Graph subgraph = getSubgraph(step,graph);
		  subgraph.getAllEdges();
		  double[][] distanceMatrix = new double[step][step];
		  distanceMatrix = getMatrix(subgraph, distanceMatrix);
		  ArrayList<MyNode> newNodeList = new ArrayList<MyNode>();
		  int start = 0;
		  DP solver = new DP(start, distanceMatrix);
		  for(int i:solver.getTour()) {
			  for(MyNode node:subgraph.getNodeList())
				  if(node.getNodeID()==i) newNodeList.add(node);
		  }
		  Graph finalGraph = new Graph(newNodeList);
		  Controller.clearLine(root);
		  displayLine(root, finalGraph);
		  String message = "Shortest tour: "+solver.getTour().toString()+"\nTour cost: "+String.valueOf(solver.getTourCost());
		  Controller.printDescription(message, root);
	  }
}
