package graph;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

public class Graph implements Display {

	public static List<Node> nodeList = new ArrayList<Node>();
	public static List<Edge> edgeList = new ArrayList<Edge>();
	
	public static void setNodeList(List<Node> nodeList) {
		Graph.nodeList = nodeList;
	}

	public static void setEdgeList(List<Edge> edgeList) {
		Graph.edgeList = edgeList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public static void addLine() {
		for (int i = 0; i < nodeList.size(); i++) {
			edgeList.add(new Edge(nodeList.get(i), nodeList.get((i + 1) % nodeList.size())));
		}
	}

	public Graph() {
		for (int i = 0; i < ManageNode.getInstance().numberOfNodes(); i++) {
			nodeList.set(i, ManageNode.getInstance().getNode(i));
		}
	}

	@Override
	public void display(Pane root) {
		for (int i = 0; i < edgeList.size(); i++)
			edgeList.get(i).display(root);
	}

	@Override
	public void clear(Pane root) {
		for (int i = 0; i < edgeList.size(); i++)
			edgeList.get(i).clear(root);
	}
	
	@Override
	public String toString() {
		String tmp = "";
		for (int i = 0; i < nodeList.size(); i++) {
			tmp += nodeList.get(i).toString();
		}
		return tmp;
	}
	//Con method printTourLine
}
