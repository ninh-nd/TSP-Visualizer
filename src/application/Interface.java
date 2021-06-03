package application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
public class Interface extends Application implements EventHandler<ActionEvent>{

	public static void main(String args[]) {
		launch(args);
	}
	
	Button button;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("TSP virtuallization");
		
		button = new Button();
		button.setText("Click me");
		button.setOnAction(this);
			
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource()==button) {
			System.out.println("UwwU");
		}

	}
	
	

}
