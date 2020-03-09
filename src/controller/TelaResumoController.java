package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import model.bean.Match;
import model.bean.MyStats;
import model.bean.Player;
import model.bean.Team;
import model.dao.MatchesDAO;
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
	@FXML
	private Label totalGoals;
	@FXML
	private Label goalsDifference;
	@FXML
	private Label goalsPerGame;
	@FXML
	private Label lbWinBar;
	@FXML
	private Label lbDrawBar;
	@FXML
	private Label lbLossBar;
	@FXML
	private Label lbBiggestWin;
	@FXML
	private Label lbWorstLoss;
	@FXML
	private Label formBox1;
	@FXML
	private Label formBox2;
	@FXML
	private Label formBox3;
	@FXML
	private Label formBox4;
	@FXML
	private Label formBox5;

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
	private MyStats ms = new MyStats();

	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaResumo.fxml"));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		RingProgressIndicator indicator = new RingProgressIndicator();
		Label performanceRate = new Label("App");
		performanceRate.getStyleClass().add("performance-label");

		initTblRivals();
		initTblLayout();
		updateBestTeamStats();
		setGoalsStats();
		injectPerformanceCard();

		circularProgressBar.getChildren().addAll(indicator, performanceRate);
		indicator.setProgress(Double.valueOf(ms.getPerfRate()).intValue());
	}

	@FXML
	void melhorTimeOnBtnAction(MouseEvent event) throws IOException {
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

	private void setGoalsStats() {

		totalGoals.setText(Integer.toString(ms.getGoalsFor()));
		goalsDifference.setText(Integer.toString(ms.getGoalsDifference()));
		goalsPerGame.setText(Double.toString(ms.getGoalsPerGame()));
	}
	
	private void injectPerformanceCard() {
		performanceBarChart();
		setRecords();
		setForm();
	}
	
	private void performanceBarChart() {

		int winWidth = (int) Math.round(ms.getWinRate() * 434 / 100);
		int drawWidth = (int) Math.round(ms.getDrawRate() * 434 / 100);
		int lossWidth = (int) Math.round(ms.getLossRate() * 434 / 100);

		winBar.setMinWidth(winWidth);
		drawBar.setMinWidth(drawWidth);
		lossBar.setMinWidth(lossWidth);

		lbWinBar.setText(Integer.toString(ms.getWins()));
		lbLossBar.setText(Integer.toString(ms.getLosses()));
		lbDrawBar.setText(Integer.toString(ms.getDraws()));
	}

	private void setRecords() {
		List<Match> bw = MatchesDAO.readBiggestWin();

		String golsFeito = Integer.toString(bw.get(0).getGolsFeito());
		String golsConcedidos = Integer.toString(bw.get(0).getGolsConcedido());

		lbBiggestWin.setText(golsFeito + " - " + golsConcedidos);

		List<Match> bl = MatchesDAO.readWorstLoss();

		golsFeito = Integer.toString(bl.get(0).getGolsFeito());
		golsConcedidos = Integer.toString(bl.get(0).getGolsConcedido());

		lbWorstLoss.setText(golsFeito + " - " + golsConcedidos);

	}

	private void setForm() {
		List<Match> matches = MatchesDAO.readForm();

		List<Label> forms = new ArrayList<Label>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(formBox1);
				add(formBox2);
				add(formBox3);
				add(formBox4);
				add(formBox5);
			}
		};

		int index = 0;
		for (Match m : matches) {
			if (m.getGolsFeito() > m.getGolsConcedido()) {
				forms.get(index).setText("V");
				forms.get(index).setStyle("-fx-background-color: #06cf4d");
			} else if (m.getGolsFeito() < m.getGolsConcedido()) {
				forms.get(index).setText("D");
				forms.get(index).setStyle("-fx-background-color: #ff2e1e");
			} else {
				forms.get(index).setText("E");
				forms.get(index).setStyle("-fx-background-color: gray");
			}

			index++;
		}
	}

	private void initPlayers() {
		PlayersList pl = new PlayersList();

		for (Player p : pl.getPlayers())
			LIST_PLAYERS.add(new PlayersProperty(p.getJogador(), p.getJogos(), p.getVitorias(), p.getEmpate(),
					p.getDerrotas(), p.getGolsFeitos(), p.getGolsConcedidos(), p.getAproveitamento()));

	}

	@SuppressWarnings("unchecked")
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

		rivalColumn.setText("Adversário");
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