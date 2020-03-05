package view;



import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FifaScoresUtil {
	private static final String IMAGE_LOC = "file:///C:/Users/Raptor/Documents/Faculdade/Java/FifaConfrontos/FifaConfrontos/resources/icons/fifascores-icon.png";
	
	public static void setStageIcon(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(IMAGE_LOC));
	}
	
}
