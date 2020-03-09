package model.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.connection.ConnectionFactory;
import model.bean.Player;

public class PlayersDAO {
	 
	public static List<Player> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Player> players = new ArrayList<>();
		
		
		try {
			String sql = "SELECT * "
					+ "FROM confrontos.players ORDER BY confrontos.players.jogos DESC";
			
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Player pl = new Player();
				
				pl.setJogador(rs.getString("jogador"));
				pl.setJogos(rs.getInt("jogos"));
				pl.setVitorias(rs.getInt("vitorias"));
				pl.setEmpate(rs.getInt("empates"));
				pl.setDerrotas(rs.getInt("derrotas"));
				pl.setGolsFeitos(rs.getInt("gols_feitos"));
				pl.setGolsConcedidos(rs.getInt("gols_concedidos"));
				
				int saldoGols = pl.getGolsFeitos() - pl.getGolsConcedidos();
				pl.setSaldoDeGols(saldoGols);
				
				double pontosFeitos = pl.getVitorias() * 3 + pl.getEmpate();
				double pontosTotais = pl.getJogos() * 3;
				double app = (pontosFeitos / pontosTotais) * 100;
				
				BigDecimal bd = new BigDecimal(app).setScale(2, RoundingMode.HALF_UP);
				double input = bd.doubleValue();
				pl.setAproveitamento(input);
				
				players.add(pl);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return players;
	}
}
