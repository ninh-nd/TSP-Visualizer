package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import graph.Node;
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
    
    private ArrayList<Node> nodeList = new ArrayList<Node>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		chooseAlgo.getItems().addAll(algo);
	}
	
    @FXML
    public void getPoint(MouseEvent event) throws IOException {
    	Node node = new Node(event.getX(), event.getY());
    	nodeList.add(node);
    	node.display(root);	
    }


}
