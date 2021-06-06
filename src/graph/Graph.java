package graph;

import java.util.List;
import java.util.Map;

public class Graph {

	private List<Node> nodeList;
	private List<Edge> edgeList;
	public void addEdge(Edge edge) {
		edgeList.add(edge);
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
	
}
