package controller;

import java.util.List;

import model.bean.Player;
import model.dao.PlayersDAO;

public class PlayersList {
	private static List<Player> PLAYERS = PlayersDAO.read();
	
	public void addPlayers(Player...players) {
		for (Player p : players)
			PLAYERS.add(p);
	}
	
	public List<Player> getPlayers() {
		return PLAYERS;
	}
}
