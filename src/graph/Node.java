package graph;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Node implements Display {
	private int nodeID;
	private static int count = 0;

	
    private final Point2D location;
    private final Circle circle;
    
//	private Circle circle;
	private boolean visited;
	public Node(int x, int y) {
		this.nodeID = ++count;
		this.location = new Point2D(x, y);
        this.circle = new Circle(x, y, 5);
	}
	
    protected double getDistanceTo(Node node) {
        return this.getLocation().distance(node.getLocation());
    }
	public int getNodeID() {
		return nodeID;
	}

	@Override
	public void display(Pane root) {
		// TODO Auto-generated method stub
		 root.getChildren().add(this.circle);
	}
	@Override
	public void clear(Pane root) {
		// TODO Auto-generated method stub
		  root.getChildren().remove(this.circle);
	}
	
    protected Point2D getLocation() {
        return this.location;
    }
}
