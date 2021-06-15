package graph;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class MyNode implements Display {
	private int nodeID;
	private static int count = 0;
	public static void setCount(int count) {
		MyNode.count = count;
	}

	public static int getCount() {
		return count;
	}

	private final Point2D location;
	private final Circle circle;
	private final Text text;

	public MyNode(double d, double e) {
		this.nodeID = ++count;
		this.location = new Point2D(d, e);
		this.circle = new Circle(d, e, 10);
		circle.setStroke(Color.FORESTGREEN);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.AZURE);
		ManageNode.getInstance().addNode(this);
		this.text = new Text(d + 15, e + 15, Integer.toString(nodeID));
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
		root.getChildren().add(this.text);
	}

	@Override
	public void clear(Pane root) {
		root.getChildren().remove(this.circle);
		root.getChildren().remove(this.text);
	}

	public Point2D getLocation() {
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
