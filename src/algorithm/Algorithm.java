package algorithm;

import graph.Graph;
import javafx.scene.layout.Pane;

public interface Algorithm {
	public abstract void run(Pane root);
	public abstract void runInStep(Pane root);
	public static void displayLine(Pane root, Graph graph) {
		graph.addLine(graph);
		graph.display(root);
	}
}
