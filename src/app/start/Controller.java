package app.start;

import gui.GenericController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller extends GenericController {

	public Controller(Stage stage, Scene scene, Parent root) {
		super(stage, scene, root);
	}

	@FXML private AnchorPane bodypane;
	
	private double windowOffsetX = 0;
	private double windowOffsetY = 0;
	
	@FXML private void buttonClose_onAction(Event event) throws Exception {
		closeThis();
	}
	
	@FXML private void toolbar_onMousePressed(MouseEvent event) {
		Stage s = getStage();
		windowOffsetX = s.getX() - event.getScreenX();
		windowOffsetY = s.getY() - event.getScreenY();
		bodypane.requestFocus();
	}
	
	@FXML private void toolbar_onMouseDragged(MouseEvent event) {
		Stage s = getStage();
		s.setX(event.getScreenX() + windowOffsetX);
		s.setY(event.getScreenY() + windowOffsetY);
	}
	
	@FXML private void bodypane_onMousePressed() {
		bodypane.requestFocus();
	}
	
	@FXML private void buttonLogin_onAction() {
		/* TODO */
	}
	
	@FXML private void buttonOfflineMode_onAction(Event event) throws Exception {
		openAndCloseThis("home");
	}	

	@Override
	public void handle(Event event) {

	}
}
