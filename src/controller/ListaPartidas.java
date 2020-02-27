package controller;

import java.util.List;

import model.bean.Partida;
import model.dao.PartidasDAO;

public class ListaPartidas {
	private static List<Partida> partidas = PartidasDAO.read();
	
	public void addPartidas(Partida... ps) {
		for (Partida p : ps)
			partidas.add(p);
	}
	
	public List<Partida> getPartidas() {
		return partidas;
	}
}
