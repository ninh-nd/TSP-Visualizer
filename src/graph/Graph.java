package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.scene.layout.Pane;

public class Graph implements Display {

	private List<Node> nodeList = new ArrayList<Node>();
	private List<Edge> edgeList = new ArrayList<Edge>();
	
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void addLine(Graph graph) { 
		for (int i = 0; i < graph.nodeList.size(); i++) {
			graph.edgeList.add(new Edge(graph.nodeList.get(i), graph.nodeList.get((i + 1) % graph.nodeList.size())));
		}
	}

	public Graph() {
		for (int i = 0; i < ManageNode.getInstance().numberOfNodes(); i++) {
			nodeList.set(i, ManageNode.getInstance().getNode(i));
		}
	}
	public Graph(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
	@Override
	public void display(Pane root) {
		for (int i = 0; i < edgeList.size(); i++) {
			edgeList.get(i).display(root);
	}
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
