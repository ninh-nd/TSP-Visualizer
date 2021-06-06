package graph;

import javafx.scene.shape.Shape;

public class Edge {
	private Node source, target;
	private double weight;
	private Shape line;
	
	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public Edge(Node u, Node v, Shape line) {
		this.source = u;
		this.target = v;
		this.weight = v.calcWeight(u);
		this.line = line;
	}
	public Edge(Node u, Node v, double weight, Shape line) {
		this.source = u;
		this.target = v;
		this.weight = weight;
		this.line = line;
	}
}
