package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/Fifa confrontos";
	private static final String USER = "postgres";
	private static final String PASS = "BodcoS0";

	/**
	 * Realiza a conex�o com o banco de dados.
	 */
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro de conex�o: ", ex);
		}
	}

	/**
	 * Fecha a conex�o.
	 */
	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Fecha a conex�o.
	 */
	public static void closeConnection(Connection con, PreparedStatement stmt) {

		closeConnection(con);

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Fecha a conex�o
	 * 
	 * @param con uma conex�o com um banco de dados espec�fico.
	 * @param stmt vari�vel ado tipo PreparedStatement que representa uma declara��o
	 *             SQL pr�-compilada.
	 * @param rs vari�vel do tipo ResultSet.
	 */
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

		closeConnection(con, stmt);

		try {
			if (rs != null)
				rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
