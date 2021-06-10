package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	public static List<Node> nodeList = new ArrayList<Node>();
	public static List<Edge> edgeList = new ArrayList<Edge>();

	public static void setNodeList(List<Node> nodeList) {
		Graph.nodeList = nodeList;
	}

	public static void setEdgeList(List<Edge> edgeList) {
		Graph.edgeList = edgeList;
	}
	
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
			for (Edge edge : edgeList) {
				if (edge.getSource().equals(node) || edge.getTarget().equals(node))
					edgeList.remove(edge);
			}
			nodeList.remove(node);
		}
	}

	public static void main(String[] args) {

	}
}
