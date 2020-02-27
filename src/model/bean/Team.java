package model.bean;

public class Team {
	private String nome;
	private int jogos;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsFeitos;
	private int golsConcedidos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public int getEmpates() {
		return empates;
	}
	public void setEmpates(int empates) {
		this.empates = empates;
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
	
	
}
