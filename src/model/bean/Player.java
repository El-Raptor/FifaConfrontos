package model.bean;

public class Player {
	private String jogador;
	private int jogos;
	private int vitorias;
	private int empate;
	private int derrotas;
	private int golsFeitos;
	private int golsConcedidos;
	private int saldoDeGols;
	private double aproveitamento;
	
	public String getJogador() {
		return jogador;
	}
	public void setJogador(String jogador) {
		this.jogador = jogador;
	}
	public int getJogos() {
		return jogos;
	}
	public void setJogos(int jogos) {
		this.jogos = jogos;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public int getEmpate() {
		return empate;
	}
	public void setEmpate(int empate) {
		this.empate = empate;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getGolsFeitos() {
		return golsFeitos;
	}
	public void setGolsFeitos(int golsFeitos) {
		this.golsFeitos = golsFeitos;
	}
	public int getGolsConcedidos() {
		return golsConcedidos;
	}
	public void setGolsConcedidos(int golsConcedidos) {
		this.golsConcedidos = golsConcedidos;
	}
	public int getSaldoDeGols() {
		return saldoDeGols;
	}
	public void setSaldoDeGols(int saldoDeGols) {
		this.saldoDeGols = saldoDeGols;
	}
	public double getAproveitamento() {
		return aproveitamento;
	}
	public void setAproveitamento(double aproveitamento) {
		this.aproveitamento = aproveitamento;
	}
	
	
}
