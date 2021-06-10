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
	public Node(double d, double e) {
		this.nodeID = ++count;
		this.location = new Point2D(d, e);
        this.circle = new Circle(d, e, 5);
        ManageNode.getInstance().addNode(this);
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
