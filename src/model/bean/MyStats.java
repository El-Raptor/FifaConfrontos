package model.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import controller.PlayersList;

public class MyStats {

	private int wins;
	private int draws;
	private int losses;
	private int winRate;
	private int drawRate;
	private int lossRate;
	private int goalsFor;
	private int goalsAgainst;
	private int goalsDifference;
	private int games;
	private double goalsPerGame;
	private int perfRate;


	public MyStats() {
		this.setStats();
	}
	

	public void setStats() {
		PlayersList pl = new PlayersList();

		List<Player> players = pl.getPlayers();

		for (Player p : players) {
			goalsFor += p.getGolsConcedidos();
			goalsAgainst += p.getGolsFeitos();
			games += p.getJogos();
			wins += p.getDerrotas();
			draws += p.getEmpate();
			losses += p.getVitorias();
		}

		goalsDifference = goalsFor - goalsAgainst;

		double goalsPerGame = (double) goalsFor / games;
		BigDecimal bd = new BigDecimal(goalsPerGame).setScale(1, RoundingMode.HALF_UP);

		this.goalsPerGame = bd.doubleValue();
		
		double dWins = wins;
		double dGames = games;
		double dDraws = draws;
		double dLosses = losses;
		long winsRate = Math.round(dWins * 100 / dGames);
		long drawsRate = Math.round(dDraws * 100 / dGames);
		long lossesRate = Math.round(dLosses * 100 / dGames);
		
		winRate = (int) winsRate;
		drawRate = (int) drawsRate;
		lossRate = (int) lossesRate;
		
		double pointsWon = wins * 3 + draws;
		double totalPoints = games * 3;
		long lPerfRate = Math.round(pointsWon * 100 / totalPoints);
		
		perfRate = (int) lPerfRate;
	}


	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}


	public int getDraws() {
		return draws;
	}


	public void setDraws(int draws) {
		this.draws = draws;
	}


	public int getLosses() {
		return losses;
	}


	public void setLosses(int losses) {
		this.losses = losses;
	}


	public int getWinRate() {
		return winRate;
	}


	public void setWinRate(int winRate) {
		this.winRate = winRate;
	}


	public int getDrawRate() {
		return drawRate;
	}


	public void setDrawRate(int drawRate) {
		this.drawRate = drawRate;
	}


	public int getLossRate() {
		return lossRate;
	}


	public void setLossRate(int lossRate) {
		this.lossRate = lossRate;
	}


	public int getGoalsFor() {
		return goalsFor;
	}


	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}


	public int getGoalsAgainst() {
		return goalsAgainst;
	}


	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}


	public int getGoalsDifference() {
		return goalsDifference;
	}


	public void setGoalsDifference(int goalsDifference) {
		this.goalsDifference = goalsDifference;
	}


	public int getGames() {
		return games;
	}


	public void setGames(int games) {
		this.games = games;
	}


	public double getGoalsPerGame() {
		return goalsPerGame;
	}


	public void setGoalsPerGame(double goalsPerGame) {
		this.goalsPerGame = goalsPerGame;
	}


	public int getPerfRate() {
		return perfRate;
	}


	public void setPerfRate(int perfRate) {
		this.perfRate = perfRate;
	}

	
}