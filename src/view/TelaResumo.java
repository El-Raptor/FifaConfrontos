package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaResumo extends Application{
	
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		
		FifaScoresUtil.setStageIcon(stage);
		stage.setScene(scene);
		stage.setTitle("FifaScores");
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
