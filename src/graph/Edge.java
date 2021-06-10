package graph;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Edge implements Display {
	private Node source, target;
	private double weight;
	private Line line;
	
	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public Edge(Node u, Node v) {
		this.source = u;
		this.target = v;
		this.weight = v.getDistanceTo(u);
		this.line = newLine();
	}
	private Line newLine() {
		Line newLine = new Line();
		Color defaultColor = Color.CYAN;
		int defaultWidth = 3;
		newLine.setStartX(this.source.getLocation().getX());
		newLine.setStartY(this.source.getLocation().getY());
		newLine.setEndX(this.target.getLocation().getX());
		newLine.setEndY(this.target.getLocation().getY());
		newLine.setStroke(defaultColor); // set line color
		newLine.setStrokeWidth(defaultWidth); // set line width
		return newLine;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public void display(Pane root) {
		root.getChildren().add(line);
	}

	@Override
	public void clear(Pane root) {
		root.getChildren().remove(this.line);
	}
	//Chot class, khong phai sua them
}
