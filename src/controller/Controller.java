package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import algorithm.Naive;
import graph.ManageNode;
import graph.MyNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import algorithm.MST;
import algorithm.runMST;

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
			Naive.run(root);
		}
		
		else if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[2]) {
			runMST.run(root);
		}
	}

	@FXML
	void reset(ActionEvent event) {
		reset(root);
	}

	@FXML
	void runInStep(ActionEvent event) {
		if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[0]) {
			Naive.runInStep(root);
		}
	}

    @FXML
    public static Text text;

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
				|| t.getClass().getSimpleName().equals("Circle"));
		Naive.setIndex(-1);
	}
}
