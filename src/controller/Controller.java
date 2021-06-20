package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import algorithm.DP;
import algorithm.MST;
import algorithm.Naive;
//import algorithm.runMST;
import graph.Graph;
import graph.ManageNode;
import graph.MyNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	@FXML
	private BorderPane mainRoot;

	@FXML
	private Pane root;

	@FXML
	private AnchorPane subRoot;

	@FXML
	private Button runBut;

	@FXML
	private Button stepBut;

	@FXML
	private Button clearBut;

	@FXML
	private Button undoBut;

	@FXML
	private Button resetBut;

	@FXML
	private ChoiceBox<String> chooseAlgo;

	@FXML

	private String[] algo = { "Naive", "Dynamic", "MST" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		chooseAlgo.getItems().addAll(algo);
	}

	@FXML
	public void getPoint(MouseEvent event) throws IOException {
		MyNode myNode = new MyNode(event.getX(), event.getY());
		myNode.display(root);
	}

	@FXML
	public void removePoint(ActionEvent event) {
		if (ManageNode.getInstance().numberOfNodes() > 0) {
			ManageNode.getInstance().clearOne(ManageNode.getInstance().numberOfNodes() - 1, root);
		}
	}

	@FXML
	public void runAlgorithm(ActionEvent event) {
		if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[0]) {
			if (ManageNode.getInstance().getNodeList().isEmpty()) {
				Graph.setUpDefaultGraph(root);
				new Naive().run(root);
			} else
				new Naive().run(root);
		} else if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[1]) {
			if (ManageNode.getInstance().getNodeList().isEmpty()) {
				Graph.setUpDefaultGraph(root);
				new DP().run(root);
			} else
				new DP().run(root);
		}

		else if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[2]) {
			if (ManageNode.getInstance().getNodeList().isEmpty()) {
				Graph.setUpDefaultGraph(root);
				new MST().run(root);
			} else
				new MST().run(root);
		}
	}

	@FXML
	void reset(ActionEvent event) {
		reset(root);
	}

	@FXML
	void runInStep(ActionEvent event) {
		if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[0]) {
			if (ManageNode.getInstance().getNodeList().isEmpty()) {
				Graph.setUpDefaultGraph(root);
				new Naive().runInStep(root);
			} else
				new Naive().runInStep(root);
		}
		else if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[1]) {
			if (ManageNode.getInstance().getNodeList().isEmpty()) {
				Graph.setUpDefaultGraph(root);
				new DP().runInStep(root);
			} 
			else new DP().runInStep(root);
		}
	}

	public static void clearLine(Pane root) {
		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Line"));
	}

	@FXML
	void clearLine(ActionEvent event) {
		clearLine(root);
	}

	public static void reset(Pane root) {
		MyNode.setCount(0); // Reset nodeID
		clearLine(root);
		ManageNode.getInstance().clearAll();
		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Line")
				|| t.getClass().getSimpleName().equals("Circle") || t.getClass().getSimpleName().equals("Text")
				|| t.getClass().getSimpleName().equals("Circle") || t.getClass().getSimpleName().equals("Label"));
		Naive.setIndex(-1);
	}

	public static void printDescription(String title, Pane root) {
		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Label"));
		Label label = new Label(title);
		root.getChildren().add(label);
	}
}
