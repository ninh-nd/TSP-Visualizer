package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import graph.Edge;
import graph.Graph;
import graph.ManageNode;
import graph.MyNode;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
public class Naive {
	private static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Graph> allGraph = new ArrayList<Graph>();
	private static ArrayList<Double> weightList = new ArrayList<Double>();
	private static ArrayList<Integer> numberList = new ArrayList<Integer>();
	private static int index = -1;
	private static int shortestGraphIndex;
	public Naive() {
		
	}

	private static void getPermutation(ArrayList<Integer> numberList, int start) { //Sub-function of all permutation
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

	private static void getAllPath(ArrayList<Integer> numberList) { //All permutation
		// Get all permutation
		getPermutation(numberList, 0);
		// Adding starting node to every permutation
		for (int i = 0; i < result.size(); i++) {
			result.get(i).add(result.get(i).get(0));
		}
	}

	private static void swap(ArrayList<Integer> numberList, int from, int to) { //Utility function for all permutation gen
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
				shortestGraphIndex = i;
				break;
			}
		}
		return result.get(index);
	}
	
	private static ArrayList<MyNode> shortestPath(ArrayList<Integer> shortestPathArray, Graph graph) {
		ArrayList<MyNode> nodeList = new ArrayList<MyNode>();
		for (int i=0; i< shortestPathArray.size(); i++) {
			for (int j=0; j< graph.getNodeList().size(); j++) {
				if (graph.getNodeList().get(j).getNodeID() == shortestPathArray.get(i)) nodeList.add(graph.getNodeList().get(j));
			}
		}
		return nodeList;
	}
	private static void getAllGraph(ArrayList<ArrayList<Integer>> result, Graph graph) {
		for (int i=0; i< result.size(); i++) { //Consider all permutations
			ArrayList<MyNode> nodeList = new ArrayList<MyNode>();
			for (int j=0; j< result.get(i).size(); j++) { //Consider each element in each permutation
				for (MyNode myNode: graph.getNodeList()) {
					if (myNode.getNodeID() == result.get(i).get(j)) nodeList.add(myNode);
				}
			}
			Graph newGraph = new Graph(nodeList);
			allGraph.add(newGraph);
		}
	}
	private static void fillNumberList(List<MyNode> nodeList) {
		for (int i = 0; i < nodeList.size(); i++) {
			numberList.add(nodeList.get(i).getNodeID());
		}
	}
	
	public static void run(Pane root) {
		result.clear();
		allGraph.clear();
		weightList.clear();
		numberList.clear();
		Graph completeGraph = new Graph(ManageNode.getInstance().getNodeList());
		completeGraph.addLine(completeGraph);
		fillNumberList(completeGraph.getNodeList());
		getAllPath(numberList);
		System.out.println("All permutation: " + result);
		fillWeightList(result, completeGraph);
		System.out.println("According weight: " + weightList);
		System.out.println();
		getAllGraph(result, completeGraph);
		for (Graph graph: allGraph) {
			Controller.clearLine(root);
			displayLine(root, graph);
		}
		System.out.println("Shortest weight: " + smallestWeight() + " - " + "Shortest path: " + shortestPathInArray());
	}
	
	public static void runInStep(Pane root) {
		index++;
		result.clear();
		allGraph.clear();
		weightList.clear();
		numberList.clear();
		Graph completeGraph = new Graph(ManageNode.getInstance().getNodeList());
		completeGraph.addLine(completeGraph);
		fillNumberList(completeGraph.getNodeList());
		getAllPath(numberList);
		System.out.println("Current permutation: " + result.get(index));
		fillWeightList(result, completeGraph);
		System.out.println("According weight: " + weightList.get(index));
		getAllGraph(result, completeGraph);
		if (index < allGraph.size()-1) {
			Controller.clearLine(root);
			displayLine(root, allGraph.get(index));
		}
		else {
			Controller.clearLine(root);
			displayLine(root, allGraph.get(shortestGraphIndex));
			System.out.println("Shortest weight: " + smallestWeight() + " - " + "Shortest path: " + shortestPathInArray());
		}
	}

	public static void setIndex(int index) {
		Naive.index = index;
	}

	private static void displayLine(Pane root, Graph graph) {
		graph.addLine(graph);
		graph.display(root);
	}
}
