package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import algorithm.Naive;

public class Graph {

	public static List<Node> nodeList = new ArrayList<Node>();
	public static List<Edge> edgeList = new ArrayList<Edge>();
	
	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}
	public List<Node> getNodeList() {
		return nodeList;
	}
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	public void removeEdge(Edge edge) {
		if (edgeList.contains(edge)) {
			edgeList.remove(edge);
		}
	}
	public void addNode(Node node) {
		nodeList.add(node);
	}
	public void removeNode(Node node) {
		if (nodeList.contains(node)) {
			for (Edge edge: edgeList) {
				if (edge.getSource().equals(node) || edge.getTarget().equals(node)) edgeList.remove(edge);
			}
			nodeList.remove(node);
		}
	}
	public static void main(String[] args) {
		Node node1 = new Node(0, 0);
		Node node2 = new Node(1, 0);
		Node node3 = new Node(1, 1);
		Node node4 = new Node(0, 1);
		nodeList.add(node1);
		nodeList.add(node2);
		nodeList.add(node3);
		nodeList.add(node4);
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
	}
}
