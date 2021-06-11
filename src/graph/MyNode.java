package graph;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MyNode implements Display {
	private int nodeID;
	private static int count = 0;

	public static void setCount(int count) {
		MyNode.count = count;
	}

	private final Point2D location;
	private final Circle circle;

	public MyNode(double d, double e) {
		this.nodeID = ++count;
		this.location = new Point2D(d, e);
		this.circle = new Circle(d, e, 10);
		ManageNode.getInstance().addNode(this);
	}

	protected double getDistanceTo(MyNode myNode) {
		return Math.round(this.getLocation().distance(myNode.getLocation()));
	}

	public int getNodeID() {
		return nodeID;
	}

	@Override
	public void display(Pane root) {
		root.getChildren().add(this.circle);
	}

	@Override
	public void clear(Pane root) {

		root.getChildren().remove(this.circle);
	}

	protected Point2D getLocation() {
		return this.location;
	}

}
