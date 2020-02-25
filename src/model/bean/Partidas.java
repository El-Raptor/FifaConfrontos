package model.bean;

import java.sql.Date;

public class Partidas {
	private Date dataPartida;
	private String modoDeJogo;
	private String adversario;
	private String meuTime;
	private int golsFeitos;
	private int golsConcedidos;
	private String timeAdversario;
	private Integer penaltisGf;
	private Integer penaltisGc;

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public String getModoDeJogo() {
		return modoDeJogo;
	}

	public void setModoDeJogo(String modoDeJogo) {
		this.modoDeJogo = modoDeJogo;
	}

	public String getAdversario() {
		return adversario;
	}

	public void setAdversario(String adversario) {
		this.adversario = adversario;
	}

	public String getMeuTime() {
		return meuTime;
	}

	public void setMeuTime(String meuTime) {
		this.meuTime = meuTime;
	}

	public int getGolsFeito() {
		return golsFeitos;
	}

	public void setGolsFeito(int golsFeito) {
		this.golsFeitos = golsFeito;
	}

	public int getGolsConcedido() {
		return golsConcedidos;
	}

	public void setGolsConcedido(int golsConcedido) {
		this.golsConcedidos = golsConcedido;
	}

	public String getTimeAdversario() {
		return timeAdversario;
	}

	public void setTimeAdversario(String timeAdversario) {
		this.timeAdversario = timeAdversario;
	}

	public int getPenaltisGf() {
		return penaltisGf;
	}

	public void setPenaltisGf(int penaltisGf) {
		this.penaltisGf = penaltisGf;
	}

	public int getPenaltisGc() {
		return penaltisGc;
	}

	public void setPenaltisGc(int penaltisGc) {
		this.penaltisGc = penaltisGc;
	}

	public String toString() {
		return dataPartida + "\t|\t" + modoDeJogo + "\t|\t" + adversario + "\t|\t" + meuTime + "\t\t| " + golsFeitos
				+ " X " + golsConcedidos + " | " + timeAdversario + "\t|\t" + penaltisGf + "\t|\t" + penaltisGc + "\n";
	}
}
