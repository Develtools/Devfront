package app.start;

import gui.GenericScene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Scene extends GenericScene {
	
	public Scene(Stage stage) {
		super(stage);
		
		/* Configure stage specifically for this scene */
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
	}
}
