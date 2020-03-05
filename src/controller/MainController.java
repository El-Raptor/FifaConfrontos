package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML
	private AnchorPane mainPane;
	@FXML
	private AnchorPane content;
	@FXML
	private HBox titleBar;
	@FXML
	private Label btnClose;
	@FXML
	private Label btnMin;
	@FXML
	private Label btnMax;
	@FXML
	private Label lblJogadoresPorTimes;
	@FXML
	private HBox btnResumo;
	@FXML
	private HBox btnPartidas;
	@FXML
	private HBox btnJogadores;
	@FXML
	private VBox menuTimes;
	@FXML
	private HBox btnTimes;
	@FXML
	private VBox subMenuTimes;
	@FXML
	private HBox btnSubTimes;
	@FXML
	private SVGPath closeSVG;

	private Stage stage;
	private static double xOffset = 0;
	private static double yOffset = 0;

	Map<VBox, VBox> map = new HashMap<VBox, VBox>();

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		toolsSlider(menuTimes, subMenuTimes);
		addMenusToMap();

		lblJogadoresPorTimes.setText("Times por\nJogadores");

		makeStageDragged();
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaResumo.fxml"));
			content.getChildren().setAll(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleMenuResumo(MouseEvent event) throws IOException {
		removeSubMenus(null);

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaResumo.fxml"));
		content.getChildren().setAll(pane);
	}

	@FXML
	void handleMenuPartidas(MouseEvent event) throws IOException {
		removeSubMenus(null);

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaPartidas.fxml"));
		content.getChildren().setAll(pane);
	}

	@FXML
	void handleMenuJogadores(MouseEvent event) throws IOException {
		removeSubMenus(null);

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaJogadores.fxml"));
		content.getChildren().setAll(pane);
	}

	@FXML
	void handleMenuTimes(MouseEvent event) throws IOException {

		toolsSlider(menuTimes, subMenuTimes);
		removeSubMenus(menuTimes);

		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaTimes.fxml"));
		content.getChildren().setAll(pane);
	}

	@FXML
	void handleSubMenuTimes(MouseEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaTimePorJogadores.fxml"));
		content.getChildren().setAll(pane);
	}

	@FXML
	private void makeStageDragged() {
		titleBar.setOnMousePressed((event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		titleBar.setOnMouseDragged((event) -> {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});
	}

	@FXML
	void closeApp(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void maximizeStage(MouseEvent event) {
		// TODO;
	}

	@FXML
	void minimizeStage(MouseEvent event) {
		Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	private void addMenusToMap() {
		map.put(menuTimes, subMenuTimes);

		// Remove os componentes do VBox ao carregar o Stage.
		for (Map.Entry<VBox, VBox> entry : map.entrySet())
			entry.getKey().getChildren().remove(entry.getValue());
	}

	private void toolsSlider(VBox menu, VBox subMenu) {
		if (menu.getChildren().contains(subMenu)) {
			final FadeTransition transition = new FadeTransition(Duration.millis(500), subMenu);
			transition.setFromValue(0.5);
			transition.setToValue(1);
			transition.setInterpolator(Interpolator.EASE_IN);
			menu.getChildren().remove(subMenu);
			transition.play();
		} else {
			final FadeTransition transition = new FadeTransition(Duration.millis(500), subMenu);
			transition.setFromValue(0.5);
			transition.setToValue(1);
			transition.setInterpolator(Interpolator.EASE_IN);
			menu.getChildren().add(subMenu);
			transition.play();
		}
	}

	private void removeSubMenus(VBox menu) {
		for (Map.Entry<VBox, VBox> entry : map.entrySet()) {
			if (!entry.getKey().equals(menu))
				entry.getKey().getChildren().remove(entry.getValue());
		}
	}

}
