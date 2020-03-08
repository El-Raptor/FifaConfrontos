package controller;

import java.util.List;

import model.bean.Team;
import model.dao.TeamsDAO;

public class TeamsList {
	private static List<Team> TEAMS = TeamsDAO.read();
	
	public void addTeams(Team...teams) {
		for (Team t : teams)
			TEAMS.add(t);
	}
	
	public List<Team> getTeams() {
		return TEAMS;
	}
}
