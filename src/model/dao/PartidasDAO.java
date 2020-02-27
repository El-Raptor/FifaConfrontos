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
import model.bean.Partida;

public class PartidasDAO {
	
	public static void create(Partida partidas) {
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
			
		} catch(SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static List<Partida> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Partida> partidas = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM confrontos.partidas ORDER BY data_partida DESC");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Partida partida = new Partida();
				
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
	
	public static void update(Partida... p) {
		//TODO;
	}
	
	public static void remove(Partida... p) {
		//TODO;
	}
}
