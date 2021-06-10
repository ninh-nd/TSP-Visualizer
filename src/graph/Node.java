package graph;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Node {
	private int nodeID;
	private static int count = 0;
	private int x;
	private int y;
//	private Circle circle;
	private boolean visited;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
//		this.circle = new Circle(x, y, 10);
		this.nodeID = ++count;
	}
	public double calcWeight(Node start) {
		double distance = Math.sqrt(Math.pow(this.x - start.getX(),2) + Math.pow(this.y - start.getY(),2));
        return distance;
	}
	public int getNodeID() {
		return nodeID;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
