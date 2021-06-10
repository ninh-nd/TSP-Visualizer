package graph;

import java.util.ArrayList;

import javafx.scene.layout.Pane;


public class ManageNode {
	private static final ManageNode instance = new ManageNode();
	
	private ArrayList<Node> nodes;
	
	public static ManageNode getInstance() {
		return instance;
	}

	private ManageNode() {
		this.nodes = new ArrayList<>();
	}

	protected void addNode(Node node) {
		this.nodes.add(node);
	}

	public Node getNode(int index) {
		return this.nodes.get(index);
	}

	protected ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public int numberOfNodes() {
		return this.nodes.size();
	}

	public void clearAll() {
		this.nodes.clear();
	}

	public void clearOne(int p, Pane root) {
		this.nodes.get(p).clear(root);
		this.nodes.remove(p);
	}

}
