package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TelaResumoController implements Initializable {

	@FXML
	private AnchorPane content;
	@FXML
	private Label headerMelhorTime;
	@FXML
	private ImageView imgTeamBadge;
	@FXML
	private Label lblJogosValue;
	@FXML
	private Label lblVitoriasValue;
	@FXML
	private Label lblEmpatesValue;
	@FXML
	private Label lblDerrotasValue;
	@FXML
	private Label lblGPValue;
	@FXML
	private Label lblGCValue;
	@FXML
	private Label lblAppValue;
    @FXML
    private Label winBar;
    @FXML
    private Label drawBar;
    @FXML
    private Label lossBar;
    @FXML
    private VBox circularProgressBar;

	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaResumo.fxml"));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		RingProgressIndicator indicator = new RingProgressIndicator();
		Label performanceRate = new Label("App%");
		performanceRate.getStyleClass().add("performance-label");
		
		circularProgressBar.getChildren().addAll(indicator, performanceRate);
		indicator.setProgress(Double.valueOf(59.0).intValue());
	}

	@FXML
	void onBtnAction(MouseEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaTimes.fxml"));
		content.getChildren().setAll(pane);
	}

	private void setTeamBadge(String teamName) {
		Image teamBadge = new Image(
				"file:///C:/Users/Raptor/Documents/Faculdade/Java/FifaConfrontos/FifaConfrontos/src/resources/icons/teams-badges/128x128-badges/"
						+ teamName + ".png");
		imgTeamBadge.setImage(teamBadge);
	}

	private void setTeamsPerformanceValues(int jogos, int vitorias, int empates, int derrotas, int gp, int gc,
			double app) {
		lblJogosValue.setText(Integer.toString(jogos));
		lblVitoriasValue.setText(Integer.toString(vitorias));
		lblEmpatesValue.setText(Integer.toString(empates));
		lblDerrotasValue.setText(Integer.toString(derrotas));
		lblGPValue.setText(Integer.toString(gp));
		lblGCValue.setText(Integer.toString(gc));
		lblAppValue.setText(Double.toString(app) + "%");
	}
	
	private void performanceBarChart(double winRate, double drawRate, double lossRate) {
		int winWidth = (int) Math.round(winRate * 434 / 100);
		int drawWidth = (int) Math.round(drawRate * 434 / 100);
		int lossWidth = (int) Math.round(lossRate * 434 / 100);
		
		winBar.setMinWidth(winWidth);;
		drawBar.setMinWidth(drawWidth);;
		lossBar.setMinWidth(lossWidth);
	}

}