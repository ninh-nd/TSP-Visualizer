package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class Naive {
	private static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Double> weightList = new ArrayList<Double>();
	private static ArrayList<Integer> numberList = new ArrayList<Integer>();
	public Naive() {
		// TODO Auto-generated constructor stub
	}
	private static void getPermutation(ArrayList<Integer> numberList, int start) {
		if (start >= numberList.size()) {
			ArrayList<Integer> a = (ArrayList<Integer>) numberList.clone();
			result.add(a);
		}
		else {
			for (int i= start; i< numberList.size(); i++) {
				swap(numberList, start, i);
				getPermutation(numberList, start+1);
				swap(numberList, start, i);
			}
		}
	}
	private static void getAllPath(ArrayList<Integer> numberList) {
		//Get all permutation
		getPermutation(numberList, 0); 
		//Adding starting node to every permutation
		for (int i=0; i< result.size(); i++) {
			result.get(i).add(result.get(i).get(0));
		}
	}
	private static void swap(ArrayList<Integer> numberList, int from, int to) {
		int tmp = numberList.get(from);
		numberList.set(from, numberList.get(to));
		numberList.set(to, tmp);
	}
	private static void fillWeightList(ArrayList<ArrayList<Integer>> result) {
		for (int i=0; i< result.size(); i++) {
			double sum = 0;
			ArrayList<Integer> currentPerm = result.get(i);
			for (int j=0; j< currentPerm.size()-1; j++) {
				for (Edge edge: Graph.edgeList) {
					if ((edge.getSource().getNodeID() == currentPerm.get(j) && edge.getTarget().getNodeID() == currentPerm.get(j+1)) || (edge.getSource().getNodeID() == currentPerm.get(j+1) && edge.getTarget().getNodeID() == currentPerm.get(j)))
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
	private static ArrayList<Integer> shortestPath() {
		int index = 0;
		for (int i=0; i< weightList.size(); i++) {
			if (weightList.get(i) == smallestWeight()) {
				index = i;
				break;
			}
		}
		return result.get(index);
	}
	private static void fillNumberList(List<Node> nodeList) {
		for (int i=0; i< nodeList.size(); i++) {
			numberList.add(nodeList.get(i).getNodeID());
		}
	}
	public static void main(String[] args) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		Node node1 = new Node(0, 0);
		Node node2 = new Node(1, 0);
		Node node3 = new Node(1, 1);
		Node node4 = new Node(0, 1);
		nodeList.add(node1);
		nodeList.add(node2);
		nodeList.add(node3);
		nodeList.add(node4);
		Graph.setNodeList(nodeList);
		Edge edge1 = new Edge(node1, node2);
		Edge edge2 = new Edge(node1, node3);
		Edge edge3 = new Edge(node1, node4);
		Edge edge4 = new Edge(node2, node3);
		Edge edge5 = new Edge(node2, node4);
		Edge edge6 = new Edge(node3, node4);
		edgeList.add(edge1);
		edgeList.add(edge2);
		edgeList.add(edge3);
		edgeList.add(edge4);
		edgeList.add(edge5);
		edgeList.add(edge6);
		Graph.setEdgeList(edgeList);
		fillNumberList(Graph.nodeList);
		getAllPath(numberList);
		System.out.println("All permutation: " + result);
		fillWeightList(result);
		System.out.println("According weight: " + weightList);
		System.out.println("Shortest weight: " + smallestWeight() + " - " + "Shortest path: " + shortestPath());
	}
}
