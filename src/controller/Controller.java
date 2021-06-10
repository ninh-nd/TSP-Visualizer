package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import algorithm.Naive;
import graph.ManageNode;
import graph.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;


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
    
    private String[] algo = {"Naive", "Dynamic", "MST"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		chooseAlgo.getItems().addAll(algo);
	}
	
    @FXML
    public void getPoint(MouseEvent event) throws IOException {
    	Node node = new Node(event.getX(), event.getY());
    	node.display(root);	
    }

    @FXML
    public void removePoint(ActionEvent event) {
    	if (ManageNode.getInstance().numberOfNodes() > 0) {
    		ManageNode.getInstance().clearOne(ManageNode.getInstance().numberOfNodes()-1, root);
    	}
    }
    
    @FXML
    public void runAlgorithm(ActionEvent event) {
    	if (chooseAlgo.getSelectionModel().getSelectedItem() == algo[0]) {
    		Naive.run(root);
    	}
    }
}
