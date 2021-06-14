package graph;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

public class Graph implements Display {

	private List<MyNode> nodeList = new ArrayList<MyNode>();
	private List<Edge> edgeList = new ArrayList<Edge>();
	public static void setUpDefaultGraph(Pane root) {
		MyNode node1 = new MyNode(50, 312);
		MyNode node2 = new MyNode(221, 154);
		MyNode node3 = new MyNode(395, 311);
		MyNode node4 = new MyNode(195, 462);
		node1.display(root);
		node2.display(root);
		node3.display(root);
		node4.display(root);
	}
	public void setNodeList(List<MyNode> nodeList) {
		this.nodeList = nodeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public List<MyNode> getNodeList() {
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
	public Graph(ArrayList<MyNode> nodeList) {
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
