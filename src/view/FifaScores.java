package view;

import java.sql.Date;

import controller.ListaPartidas;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bean.Partida;
import model.property.PartidasProperty;

public class FifaScores extends Application {
	private AnchorPane pane;
	private TextField txPesquisa;
	private TableView<PartidasProperty> tbPartidas;
	private TableColumn<PartidasProperty, Date> columnDataPartida;
	private TableColumn<PartidasProperty, String> columnModoDeJogo;
	private TableColumn<PartidasProperty, String> columnAdversario;
	private TableColumn<PartidasProperty, String> columnMeuTime;
	private TableColumn<PartidasProperty, Integer> columnGolsFeitos;
	private TableColumn<PartidasProperty, Integer> columnGolsConcedidos;
	private TableColumn<PartidasProperty, String> columnTimeAdversario;
	private TableColumn<PartidasProperty, Integer> columnPenaltisGf;
	private TableColumn<PartidasProperty, Integer> columnPenaltisGc;
	private static ObservableList<PartidasProperty> listPartidas = FXCollections.observableArrayList();
	private static Stage stage;

	public void start(Stage stage) throws Exception {
		initComponents();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Fifa Scores");
		stage.show();
		FifaScores.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		pane.setStyle(
				"-fx-background-color: linear-gradient(to bottom right, rgba(61, 210, 255, 1), rgba(61, 255, 216))");

		txPesquisa = new TextField();
		txPesquisa.setPromptText("Pesquisa");

		tbPartidas = new TableView<PartidasProperty>();
		tbPartidas.setPrefHeight(580);

		columnDataPartida = new TableColumn<PartidasProperty, Date>();
		columnModoDeJogo = new TableColumn<PartidasProperty, String>();
		columnAdversario = new TableColumn<PartidasProperty, String>();
		columnMeuTime = new TableColumn<PartidasProperty, String>();
		columnGolsFeitos = new TableColumn<PartidasProperty, Integer>();
		columnGolsConcedidos = new TableColumn<PartidasProperty, Integer>();
		columnTimeAdversario = new TableColumn<PartidasProperty, String>();
		columnPenaltisGf = new TableColumn<PartidasProperty, Integer>();
		columnPenaltisGc = new TableColumn<PartidasProperty, Integer>();
		tbPartidas.getColumns().addAll(columnDataPartida, columnModoDeJogo, columnAdversario, columnMeuTime,
				columnGolsFeitos, columnGolsConcedidos, columnTimeAdversario, columnPenaltisGf, columnPenaltisGc);
		pane.getChildren().addAll(txPesquisa, tbPartidas);

		columnDataPartida.setCellValueFactory(new PropertyValueFactory<PartidasProperty, Date>("dataPartida"));
		columnModoDeJogo.setCellValueFactory(new PropertyValueFactory<PartidasProperty, String>("modoDeJogo"));
		columnAdversario.setCellValueFactory(new PropertyValueFactory<PartidasProperty, String>("adversario"));
		columnMeuTime.setCellValueFactory(new PropertyValueFactory<PartidasProperty, String>("meuTime"));
		columnGolsFeitos.setCellValueFactory(new PropertyValueFactory<PartidasProperty, Integer>("golsFeitos"));
		columnGolsConcedidos.setCellValueFactory(new PropertyValueFactory<PartidasProperty, Integer>("golsConcedidos"));
		columnTimeAdversario.setCellValueFactory(new PropertyValueFactory<PartidasProperty, String>("timeAdversario"));
		columnPenaltisGf.setCellValueFactory(new PropertyValueFactory<PartidasProperty, Integer>("penaltisGf"));
		columnPenaltisGc.setCellValueFactory(new PropertyValueFactory<PartidasProperty, Integer>("penaltisGc"));

		initPartidas();
		tbPartidas.setItems(listPartidas);

	}

	private void initPartidas() {
		ListaPartidas lp = new ListaPartidas();

		for (Partida p : lp.getPartidas())
			listPartidas.add(new PartidasProperty(p.getDataPartida(), p.getModoDeJogo(), p.getAdversario(),
					p.getMeuTime(), p.getGolsFeito(), p.getGolsConcedido(), p.getTimeAdversario(), p.getPenaltisGf(),
					p.getPenaltisGc()));
	}
}
