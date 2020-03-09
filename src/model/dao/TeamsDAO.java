package model.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.connection.ConnectionFactory;
import model.bean.Team;

public class TeamsDAO {

	public static List<Team> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Team> teams = new ArrayList<>();

		try {
			String sql = "SELECT * FROM confrontos.teams ORDER BY confrontos.teams.jogos DESC";

			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Team t = new Team();

				t.setNome(rs.getString("nome"));
				t.setJogos(rs.getInt("jogos"));
				t.setVitorias(rs.getInt("vitorias"));
				t.setEmpates(rs.getInt("empates"));
				t.setDerrotas(rs.getInt("derrotas"));
				t.setGolsFeitos(rs.getInt("gols_feitos"));
				t.setGolsConcedidos(rs.getInt("gols_concedidos"));

				int saldoDeGols = t.getGolsFeitos() - t.getGolsConcedidos();
				t.setSaldoDeGols(saldoDeGols);

				double pontosFeitos = t.getVitorias() * 3 + t.getEmpates();
				double pontosTotais = t.getJogos() * 3;
				double app = (pontosFeitos / pontosTotais) * 100;

				BigDecimal bd = new BigDecimal(app).setScale(2, RoundingMode.HALF_UP);
				t.setAproveitamento(bd.doubleValue());

				teams.add(t);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return teams;
	}

	public static List<Team> readMostPlayedTeams() {
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Team> teams = new ArrayList<Team>();

		try {
			String sql = "SELECT * FROM confrontos.teams ORDER BY teams.jogos DESC LIMIT 10";

			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Team t = new Team();

				t.setNome(rs.getString("nome"));
				t.setJogos(rs.getInt("jogos"));
				t.setVitorias(rs.getInt("vitorias"));
				t.setEmpates(rs.getInt("empates"));
				t.setDerrotas(rs.getInt("derrotas"));
				t.setGolsFeitos(rs.getInt("gols_feitos"));
				t.setGolsConcedidos(rs.getInt("gols_concedidos"));

				int saldoDeGols = t.getGolsFeitos() - t.getGolsConcedidos();
				t.setSaldoDeGols(saldoDeGols);

				double pontosFeitos = t.getVitorias() * 3 + t.getEmpates();
				double pontosTotais = t.getJogos() * 3;
				double app = (pontosFeitos / pontosTotais) * 100;

				BigDecimal bd = new BigDecimal(app).setScale(2, RoundingMode.HALF_UP);
				t.setAproveitamento(bd.doubleValue());

				teams.add(t);
			}

		} catch (SQLException e) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return teams;
	}
}
