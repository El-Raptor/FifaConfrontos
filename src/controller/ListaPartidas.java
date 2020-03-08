package controller;

import java.util.List;

import model.bean.Match;
import model.dao.MatchesDAO;

public class ListaPartidas {
	private static List<Match> partidas = MatchesDAO.read();
	
	public void addPartidas(Match... matches) {
		for (Match m : matches)
			partidas.add(m);
	}
	
	public List<Match> getPartidas() {
		return partidas;
	}
}
