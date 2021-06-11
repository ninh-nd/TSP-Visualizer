package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.ManageNode;
import graph.Node;
import javafx.scene.layout.Pane;

public class Naive {
	private static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Double> weightList = new ArrayList<Double>();
	private static ArrayList<Integer> numberList = new ArrayList<Integer>();
	private Graph shortestPath = null;
	private double distance;
	public Naive() {
		
	}

	private static void getPermutation(ArrayList<Integer> numberList, int start) {
		if (start >= numberList.size()) {
			ArrayList<Integer> a = (ArrayList<Integer>) numberList.clone();
			result.add(a);
		} else {
			for (int i = start; i < numberList.size(); i++) {
				swap(numberList, start, i);
				getPermutation(numberList, start + 1);
				swap(numberList, start, i);
			}
		}
	}

	private static void getAllPath(ArrayList<Integer> numberList) {
		// Get all permutation
		getPermutation(numberList, 0);
		// Adding starting node to every permutation
		for (int i = 0; i < result.size(); i++) {
			result.get(i).add(result.get(i).get(0));
		}
	}

	private static void swap(ArrayList<Integer> numberList, int from, int to) {
		int tmp = numberList.get(from);
		numberList.set(from, numberList.get(to));
		numberList.set(to, tmp);
	}

	private static void fillWeightList(ArrayList<ArrayList<Integer>> result, Graph graph) {
		for (int i = 0; i < result.size(); i++) {
			double sum = 0;
			ArrayList<Integer> currentPerm = result.get(i);
			for (int j = 0; j < currentPerm.size() - 1; j++) {
				for (Edge edge : graph.getEdgeList()) {
					if ((edge.getSource().getNodeID() == currentPerm.get(j)
							&& edge.getTarget().getNodeID() == currentPerm.get(j + 1))
							|| (edge.getSource().getNodeID() == currentPerm.get(j + 1)
									&& edge.getTarget().getNodeID() == currentPerm.get(j)))
						sum += edge.getWeight();
				}
			}
			weightList.add(sum);
			sum = 0;
		}
	}

	private static double smallestWeight() {
		return Collections.min(weightList);
	}

	private static ArrayList<Integer> shortestPathInArray() {
		int index = 0;
		for (int i = 0; i < weightList.size(); i++) {
			if (weightList.get(i) == smallestWeight()) {
				index = i;
				break;
			}
		}
		return result.get(index);
	}
	
	private static ArrayList<Node> shortestPath(ArrayList<Integer> shortestPathArray, Graph graph) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (int i=0; i< shortestPathArray.size(); i++) {
			for (int j=0; j< graph.getNodeList().size(); j++) {
				if (graph.getNodeList().get(j).getNodeID() == shortestPathArray.get(i)) nodeList.add(graph.getNodeList().get(j));
			}
		}
		return nodeList;
	}

	private static void fillNumberList(List<Node> nodeList) {
		for (int i = 0; i < nodeList.size(); i++) {
			numberList.add(nodeList.get(i).getNodeID());
		}
	}

//	public void run(Pane root) {
//		long start = System.nanoTime();
//		this.clearLine(root);
//		this.distance = smallestWeight();
//		this.timeElapsed = System.nanoTime() - start;
//		this.displayLine(root, this.shortestPath);
//	}
	
	public static void run(Pane root) {
		Graph graph = new Graph(ManageNode.getInstance().getNodeList());
		graph.addLine(graph);
		fillNumberList(graph.getNodeList());
		getAllPath(numberList);
		System.out.println("All permutation: " + result);
		fillWeightList(result, graph);
		System.out.println("According weight: " + weightList);
		System.out.println("Shortest weight: " + smallestWeight() + " - " + "Shortest path: " + shortestPathInArray());
		ArrayList<Node> optimizedNodeList = shortestPath(shortestPathInArray(), graph);
		Graph optimizedGraph = new Graph(optimizedNodeList);
		displayLine(root, optimizedGraph);
	}

	private static void displayLine(Pane root, Graph shortestPath) {
		shortestPath.addLine(shortestPath);
		shortestPath.display(root);
	}

//	public void clearLine(Pane root) {
//		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Line"));
//	}
}
