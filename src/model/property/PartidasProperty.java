package model.property;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartidasProperty {
	private ObjectProperty<Date> dataPartida;
	private StringProperty modoDeJogo;
	private StringProperty adversario;
	private StringProperty meuTime;
	private IntegerProperty golsFeitos;
	private IntegerProperty golsConcedidos;
	private StringProperty timeAdversario;
	private IntegerProperty penaltisGf;
	private IntegerProperty penaltisGc;

	public PartidasProperty(Date dataPartida, String modoDeJogo, String adversario, String meuTime, Integer golsFeitos,
			Integer golsConcedidos, String timeAdversario, Integer penaltisGf, Integer penaltisGc) {
		this.dataPartida = new SimpleObjectProperty<Date>(dataPartida);
		this.modoDeJogo = new SimpleStringProperty(modoDeJogo);
		this.adversario = new SimpleStringProperty(adversario);
		this.meuTime = new SimpleStringProperty(meuTime);
		this.golsFeitos = new SimpleIntegerProperty(golsFeitos);
		this.golsConcedidos = new SimpleIntegerProperty(golsConcedidos);
		this.timeAdversario = new SimpleStringProperty(timeAdversario);
		this.penaltisGf = new SimpleIntegerProperty(penaltisGf);
		this.penaltisGc = new SimpleIntegerProperty(penaltisGc);
	}

	public  Date getDataPartida() {
		return dataPartida.get();
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida.set(dataPartida);;
	}

	public String getModoDeJogo() {
		return modoDeJogo.get();
	}

	public void setModoDeJogo(String modoDeJogo) {
		this.modoDeJogo.set(modoDeJogo);
	}

	public String getAdversario() {
		return adversario.get();
	}

	public void setAdversario(String adversario) {
		this.adversario.set(adversario);
	}

	public String getMeuTime() {
		return meuTime.get();
	}

	public void setMeuTime(String meuTime) {
		this.meuTime.set(meuTime);
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

	public String getTimeAdversario() {
		return timeAdversario.get();
	}

	public void setTimeAdversario(String timeAdversario) {
		this.timeAdversario.set(timeAdversario);
	}

	public Integer getPenaltisGf() {
		return penaltisGf.get();
	}

	public void setPenaltisGf(Integer penaltisGf) {
		this.penaltisGf.set(penaltisGf);
	}

	public Integer getPenaltisGc() {
		return penaltisGc.get();
	}

	public void setPenaltisGc(Integer penaltisGc) {
		this.penaltisGc.set(penaltisGc);;
	}

}
