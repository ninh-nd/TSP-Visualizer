package graph;

import java.util.ArrayList;

import javafx.scene.layout.Pane;


public class ManageNode {
	private static final ManageNode instance = new ManageNode();
	
	private ArrayList<Node> nodeList;
	
	public static ManageNode getInstance() {
		return instance;
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	private ManageNode() {
		this.nodeList = new ArrayList<>();
	}

	public void addNode(Node node) {
		this.nodeList.add(node);
	}

	public Node getNode(int index) {
		return this.nodeList.get(index);
	}

	public void clearAll() {
		this.nodeList.clear();
	}

	public void clearOne(int p, Pane root) {
		this.nodeList.get(p).clear(root);
		this.nodeList.remove(p);
	}

	public int numberOfNodes() {
		return nodeList.size();
	}
}
