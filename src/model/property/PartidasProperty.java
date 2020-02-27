package model.property;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartidasProperty {
	private ObjectProperty<Date> dataPartida;
	private SimpleStringProperty modoDeJogo;
	private SimpleStringProperty adversario;
	private SimpleStringProperty meuTime;
	private SimpleIntegerProperty golsFeitos;
	private SimpleIntegerProperty golsConcedidos;
	private SimpleStringProperty timeAdversario;
	private SimpleIntegerProperty penaltisGf;
	private SimpleIntegerProperty penaltisGc;
	
	public ObjectProperty<Date> getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(ObjectProperty<Date> dataPartida) {
		this.dataPartida = dataPartida;
	}
	public SimpleStringProperty getModoDeJogo() {
		return modoDeJogo;
	}
	public void setModoDeJogo(SimpleStringProperty modoDeJogo) {
		this.modoDeJogo = modoDeJogo;
	}
	public SimpleStringProperty getAdversario() {
		return adversario;
	}
	public void setAdversario(SimpleStringProperty adversario) {
		this.adversario = adversario;
	}
	public SimpleStringProperty getMeuTime() {
		return meuTime;
	}
	public void setMeuTime(SimpleStringProperty meuTime) {
		this.meuTime = meuTime;
	}
	public SimpleIntegerProperty getGolsFeitos() {
		return golsFeitos;
	}
	public void setGolsFeitos(SimpleIntegerProperty golsFeitos) {
		this.golsFeitos = golsFeitos;
	}
	public SimpleIntegerProperty getGolsConcedidos() {
		return golsConcedidos;
	}
	public void setGolsConcedidos(SimpleIntegerProperty golsConcedidos) {
		this.golsConcedidos = golsConcedidos;
	}
	public SimpleStringProperty getTimeAdversario() {
		return timeAdversario;
	}
	public void setTimeAdversario(SimpleStringProperty timeAdversario) {
		this.timeAdversario = timeAdversario;
	}
	public SimpleIntegerProperty getPenaltisGf() {
		return penaltisGf;
	}
	public void setPenaltisGf(SimpleIntegerProperty penaltisGf) {
		this.penaltisGf = penaltisGf;
	}
	public SimpleIntegerProperty getPenaltisGc() {
		return penaltisGc;
	}
	public void setPenaltisGc(SimpleIntegerProperty penaltisGc) {
		this.penaltisGc = penaltisGc;
	}
	
	
}
