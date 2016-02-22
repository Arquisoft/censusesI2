package es.uniovi.DBUpdate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {

	private static String DRIVER = "org.sqlite.JDBC";
	private static String URL= "jdbc:sqlite:/home/travis/build/Arquisoft/censusesI2/sqlite/asw.db";
	//private static String URL= "jdbc:sqlite:sqlite\\asw.db";

	static {
		try {
			Class.forName( DRIVER );
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver not found in classpath", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL);
	}

	public static void close(ResultSet rs, Statement st, Connection c) {
		close(rs);
		close(st);
		close(c);
	}

	public static void close(ResultSet rs, Statement st) {
		close(rs);
		close(st);
	}

	protected static void close(ResultSet rs) {
		if (rs != null) try { rs.close(); } catch(SQLException e) {e.printStackTrace();}
	}

	public static void close(Statement st) {
		if (st != null ) try { st.close(); } catch(SQLException e) {e.printStackTrace();}
	}

	public static void close(Connection c) {
		if (c != null) try { c.close(); } catch(SQLException e) {e.printStackTrace();}
	}

	public static Connection createThreadConnection() throws SQLException {
		Connection con = getConnection();
		con.setAutoCommit( false );
		threadConnection.set(con);
		return con;
	}

	private static ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	public static Connection getCurrentConnection() {
		return threadConnection.get();
	}

}
