package algorithm;

import java.util.ArrayList;

import graph.Edge;
import graph.Graph;
import graph.ManageNode;
import javafx.scene.layout.Pane;

public class runMST {
	private static Graph graph = new Graph(ManageNode.getInstance().getNodeList());
	
	private static MST ts = new MST(graph.getNodeList());
	
	private static ArrayList<Edge> mst = ts.computeMST();
	
	ArrayList<Edge> mstWalk = ts.walkMST();
	
	public static void run(Pane root) {
		for (Edge e : mst) {
			e.display(root);
		}
	}
}
