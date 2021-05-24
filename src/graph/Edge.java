package graph;

import javafx.scene.shape.Shape;

public class Edge {
	public Node source, target;
	public double weight;
	public Shape line;
	
	public Edge(Node u, Node v, double weight, Shape line) {
		this.source = u;
		this.target = v;
		this.weight = weight;
		this.line = line;
	}
}
