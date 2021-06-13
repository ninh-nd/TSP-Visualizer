package graph;

import java.util.ArrayList;

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

	public double getDistanceTo(MyNode myNode) {
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
	
	
	//for MST
	private boolean visited = false;
    private Edge shortestEdge;
    private ArrayList<MyNode> children = new ArrayList<MyNode>();
    
    public void addChild(MyNode child)
    {
        this.children.add(child);
    }

    public void setShortestEdge(MyNode c)
    {
        this.shortestEdge = new Edge(this, c);
    }

    public void setShortestEdge(MyNode c, double len)
    {
        this.shortestEdge = new Edge(this, c, len);
    }

    public void setVisited(boolean v)
    {
        this.visited = v;
    }

    public ArrayList<MyNode> getChildren()
    {
        return this.children;
    }

    public double getPriority()
    {
        return this.shortestEdge.getWeight();
    }

    public Edge getShortestEdge()
    {
        return this.shortestEdge;
    }

    public boolean visited()
    {
        return this.visited;
    }
}
