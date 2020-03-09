package model.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayersProperty {
	private StringProperty jogador;
	private IntegerProperty jogos;
	private IntegerProperty vitorias;
	private IntegerProperty empates;
	private IntegerProperty derrotas;
	private IntegerProperty golsFeitos;
	private IntegerProperty golsConcedidos;
	private IntegerProperty saldoDeGols;
	private DoubleProperty aproveitamento;

	public PlayersProperty(String jogador, Integer jogos, Integer vitorias, Integer empates, Integer derrotas,
			Integer golsFeitos, Integer golsConcedidos, Integer saldoDeGols, Double aproveitamento) {
		
		this.aproveitamento = new SimpleDoubleProperty(aproveitamento);
		this.derrotas = new SimpleIntegerProperty(derrotas);
		this.empates = new SimpleIntegerProperty(empates);
		this.golsConcedidos = new SimpleIntegerProperty(golsConcedidos);
		this.golsFeitos = new SimpleIntegerProperty(golsFeitos);
		this.jogador = new SimpleStringProperty(jogador);
		this.jogos = new SimpleIntegerProperty(jogos);
		this.saldoDeGols = new SimpleIntegerProperty(saldoDeGols);
		this.vitorias = new SimpleIntegerProperty(vitorias);
	
	}
	
	public PlayersProperty(String jogador, Integer jogos, Integer vitorias, Integer empates, Integer derrotas,
			Integer golsFeitos, Integer golsConcedidos, Double aproveitamento) {
		
		this.aproveitamento = new SimpleDoubleProperty(aproveitamento);
		this.derrotas = new SimpleIntegerProperty(derrotas);
		this.empates = new SimpleIntegerProperty(empates);
		this.golsConcedidos = new SimpleIntegerProperty(golsConcedidos);
		this.golsFeitos = new SimpleIntegerProperty(golsFeitos);
		this.jogador = new SimpleStringProperty(jogador);
		this.jogos = new SimpleIntegerProperty(jogos);
		this.vitorias = new SimpleIntegerProperty(vitorias);
	
	}
	
	public String getJogador() {
		return jogador.get();
	}
	public void setJogador(String jogador) {
		this.jogador.set(jogador);
	}
	public Integer getJogos() {
		return jogos.get();
	}
	public void setJogos(Integer jogos) {
		this.jogos.set(jogos);
	}
	public Integer getVitorias() {
		return vitorias.get();
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias.set(vitorias);
	}
	public Integer getEmpates() {
		return empates.get();
	}
	public void setEmpates(Integer empates) {
		this.empates.set(empates);
	}
	public Integer getDerrotas() {
		return derrotas.get();
	}
	public void setDerrotas(Integer derrotas) {
		this.derrotas.set(derrotas);
	}
	public Integer getGolsFeitos() {
		return golsFeitos.get();
	}
	public void setGolsFeitos(Integer golsFeitos) {
		this.golsFeitos.set(golsFeitos);
	}
	public Integer getGolsConcedidos() {
		return golsConcedidos.get();
	}
	public void setGolsConcedidos(Integer golsConcedidos) {
		this.golsConcedidos.set(golsConcedidos);
	}
	public Integer getSaldoDeGols() {
		return saldoDeGols.get();
	}
	public void setSaldoDeGols(Integer saldoDeGols) {
		this.saldoDeGols.set(saldoDeGols);
	}
	public Double getAproveitamento() {
		return aproveitamento.get();
	}
	public void setAproveitamento(Double aproveitamento) {
		this.aproveitamento.set(aproveitamento);
	}
}
