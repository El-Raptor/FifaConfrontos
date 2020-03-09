package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.connection.ConnectionFactory;
import model.bean.Match;

public class MatchesDAO {

	public static void create(Match partidas) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO partidas VALUES (?,?,?,?,?,?,?,?,?)");

			stmt.setDate(1, partidas.getDataPartida());
			stmt.setString(2, partidas.getModoDeJogo());
			stmt.setString(3, partidas.getAdversario());
			stmt.setString(4, partidas.getMeuTime());
			stmt.setInt(5, partidas.getGolsFeito());
			stmt.setInt(6, partidas.getGolsConcedido());
			stmt.setString(7, partidas.getTimeAdversario());
			stmt.setInt(8, partidas.getPenaltisGf());
			stmt.setInt(9, partidas.getPenaltisGc());

		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public static List<Match> read(String sql) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Match> partidas = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Match partida = new Match();

				partida.setDataPartida(rs.getDate("data_partida"));
				partida.setModoDeJogo(rs.getString("modo_de_jogo"));
				partida.setAdversario(rs.getString("adversario"));
				partida.setMeuTime(rs.getString("meu_time"));
				partida.setGolsFeito(rs.getInt("gols_feito"));
				partida.setGolsConcedido(rs.getInt("gols_concedidos"));
				partida.setTimeAdversario(rs.getString("time_adversario"));
				partida.setPenaltisGf(rs.getInt("penaltis_gf"));
				partida.setPenaltisGc(rs.getInt("penaltis_gc"));

				partidas.add(partida);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return partidas;
	}

	public static List<Match> readAll() {

		String sql = "SELECT * FROM confrontos.partidas ORDER BY (data_partida, match_id) DESC";
		return read(sql);

	}

	public static List<Match> readBiggestWin() {

		String sql = "SELECT * , RANK() OVER (ORDER BY (p.gols_feito - p.gols_concedidos) DESC) FROM confrontos.partidas p LIMIT 1";
		return read(sql);
	}

	public static List<Match> readWorstLoss() {

		String sql = "SELECT * , RANK() OVER (ORDER BY (p.gols_feito - p.gols_concedidos)) FROM confrontos.partidas p LIMIT 1";
		return read(sql);

	}

	public static List<Match> readForm() {

		String sql = "SELECT * FROM confrontos.partidas p ORDER BY (p.data_partida, p.match_id) DESC LIMIT 5";
		return read(sql);
		
	}

	public static void update(Match... p) {
		// TODO;
	}

	public static void remove(Match... p) {
		// TODO;
	}
}
