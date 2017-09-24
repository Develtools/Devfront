package app.home;

import gui.GenericController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class Controller extends GenericController {
	
	@FXML private TreeView<String> treeviewProjectDirectory;

	public Controller(Stage stage, Scene scene, Parent root) {
		super(stage, scene, root);
		// TODO Auto-generated constructor stub
	}
	
	@FXML private void buttonLoginOnAction() throws Exception {
		openAndCloseThis("start");
	}
	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub		
	}
	
}
