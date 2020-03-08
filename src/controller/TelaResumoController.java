package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.bean.Player;
import model.bean.Team;
import model.dao.TeamsDAO;
import model.property.PlayersProperty;

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
	@FXML
	private VBox tbVBox;

	private TableView<PlayersProperty> tbRivals;
	private TableColumn<PlayersProperty, String> rivalColumn;
	private TableColumn<PlayersProperty, Integer> matchColumn;
	private TableColumn<PlayersProperty, Integer> winColumn;
	private TableColumn<PlayersProperty, Integer> drawColumn;
	private TableColumn<PlayersProperty, Integer> lossComlumn;
	private TableColumn<PlayersProperty, Integer> goalsForColumn;
	private TableColumn<PlayersProperty, Integer> goalsAgainstColumn;
	private TableColumn<PlayersProperty, Double> winRateColumn;
	private static ObservableList<PlayersProperty> LIST_PLAYERS = FXCollections.observableArrayList();

	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaResumo.fxml"));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		RingProgressIndicator indicator = new RingProgressIndicator();
		Label performanceRate = new Label("App%");
		performanceRate.getStyleClass().add("performance-label");

		initTblRivals();
		initTblLayout();

		updateBestTeamStats();

		circularProgressBar.getChildren().addAll(indicator, performanceRate);
		indicator.setProgress(Double.valueOf(59.0).intValue());
	}

	@FXML
	void onBtnAction(MouseEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/TelaTimes.fxml"));
		content.getChildren().setAll(pane);
	}

	private void updateBestTeamStats() {

		List<Team> mostPlayedTeams = TeamsDAO.readMostPlayedTeams();

		Team bestTeam = Collections.max(mostPlayedTeams, Comparator.comparing(bt -> bt.getAproveitamento()));

		setTeamBadge(bestTeam);
		setTeamsPerformanceValues(bestTeam);
	}

	private void setTeamBadge(Team bestTeam) {
		Image teamBadge = new Image(
				"file:///C:/Users/Raptor/Documents/Faculdade/Java/FifaConfrontos/FifaConfrontos/src/resources/icons/teams-badges/128x128-badges/"
						+ bestTeam.getNome() + ".png");
		imgTeamBadge.setImage(teamBadge);
	}

	private void setTeamsPerformanceValues(Team bestTeam) {
		lblJogosValue.setText(Integer.toString(bestTeam.getJogos()));
		lblVitoriasValue.setText(Integer.toString(bestTeam.getVitorias()));
		lblEmpatesValue.setText(Integer.toString(bestTeam.getEmpates()));
		lblDerrotasValue.setText(Integer.toString(bestTeam.getDerrotas()));
		lblGPValue.setText(Integer.toString(bestTeam.getGolsFeitos()));
		lblGCValue.setText(Integer.toString(bestTeam.getGolsConcedidos()));
		lblAppValue.setText(Double.toString(bestTeam.getAproveitamento()) + "%");
	}

	private void performanceBarChart(double winRate, double drawRate, double lossRate) {
		int winWidth = (int) Math.round(winRate * 434 / 100);
		int drawWidth = (int) Math.round(drawRate * 434 / 100);
		int lossWidth = (int) Math.round(lossRate * 434 / 100);

		winBar.setMinWidth(winWidth);
		drawBar.setMinWidth(drawWidth);
		lossBar.setMinWidth(lossWidth);
	}

	private void initPlayers() {
		PlayersList pl = new PlayersList();

		for (Player p : pl.getPlayers())
			LIST_PLAYERS.add(new PlayersProperty(p.getJogador(), p.getJogos(), p.getVitorias(), p.getEmpate(),
					p.getDerrotas(), p.getGolsFeitos(), p.getGolsConcedidos(), p.getAproveitamento()));

	}

	private void initTblRivals() {
		tbRivals = new TableView<PlayersProperty>();

		rivalColumn = new TableColumn<PlayersProperty, String>();
		matchColumn = new TableColumn<PlayersProperty, Integer>();
		winColumn = new TableColumn<PlayersProperty, Integer>();
		drawColumn = new TableColumn<PlayersProperty, Integer>();
		lossComlumn = new TableColumn<PlayersProperty, Integer>();
		goalsForColumn = new TableColumn<PlayersProperty, Integer>();
		goalsAgainstColumn = new TableColumn<PlayersProperty, Integer>();
		winRateColumn = new TableColumn<PlayersProperty, Double>();

		tbRivals.getColumns().addAll(rivalColumn, matchColumn, winColumn, drawColumn, lossComlumn, goalsForColumn,
				goalsAgainstColumn, winRateColumn);

		tbVBox.getChildren().addAll(tbRivals);

		rivalColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, String>("jogador"));
		matchColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("jogos"));
		winColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("vitorias"));
		drawColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("empates"));
		lossComlumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("derrotas"));
		goalsForColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("golsFeitos"));
		goalsAgainstColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Integer>("golsConcedidos"));
		winRateColumn.setCellValueFactory(new PropertyValueFactory<PlayersProperty, Double>("aproveitamento"));

		initPlayers();
		tbRivals.setItems(LIST_PLAYERS);
	}

	private void initTblLayout() {

		tbRivals.getStylesheets()
				.add(TelaResumoController.class.getResource("/view/table-stylesheet.css").toExternalForm());

		rivalColumn.setText("Jogador");
		rivalColumn.setEditable(false);
		rivalColumn.setResizable(false);
		rivalColumn.setSortable(false);
		rivalColumn.setPrefWidth(120);
		rivalColumn.setStyle("-fx-alignment: center-left; -fx-background-radius: 4 4 0 0");

		matchColumn.setText("J");
		matchColumn.setResizable(false);
		matchColumn.setSortable(false);
		matchColumn.setEditable(false);
		matchColumn.setPrefWidth(40);
		matchColumn.setStyle("-fx-background-radius: 4 0 0 0");

		winColumn.setText("V");
		winColumn.setResizable(false);
		winColumn.setSortable(false);
		winColumn.setEditable(false);
		winColumn.setPrefWidth(40);

		drawColumn.setText("E");
		drawColumn.setResizable(false);
		drawColumn.setSortable(false);
		drawColumn.setEditable(false);
		drawColumn.setPrefWidth(40);

		lossComlumn.setText("D");
		lossComlumn.setResizable(false);
		lossComlumn.setSortable(false);
		lossComlumn.setEditable(false);
		lossComlumn.setPrefWidth(40);

		goalsForColumn.setText("GF");
		goalsForColumn.setResizable(false);
		goalsForColumn.setSortable(false);
		goalsForColumn.setEditable(false);
		goalsForColumn.setPrefWidth(40);

		goalsAgainstColumn.setText("GC");
		goalsAgainstColumn.setResizable(false);
		goalsAgainstColumn.setSortable(false);
		goalsAgainstColumn.setEditable(false);
		goalsAgainstColumn.setPrefWidth(40);

		winRateColumn.setText("App%");
		winRateColumn.setResizable(false);
		winRateColumn.setSortable(false);
		winRateColumn.setEditable(false);
		winRateColumn.setPrefWidth(40);
		winRateColumn.setStyle("-fx-background-radius: 0 4 0 0");

	}

}