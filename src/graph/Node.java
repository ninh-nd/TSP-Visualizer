package graph;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Node {
	private Point2D name;
	private int x;
	private int y;
	private Circle circle;
	private boolean visited;
	public Node(double x, double y) {
		this.name = new Point2D(x, y);
		this.circle = new Circle(x, y, 10);
	}
	public double calcWeight(Node start) {
		double distance = Math.sqrt(Math.pow(this.x - start.getX(),2) + Math.pow(this.y - start.getY(),2));
        return (int)Math.round(distance);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
