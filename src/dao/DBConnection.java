package dao;

import java.sql.*;

public class DBConnection {
	private static Connection connection;
	private static String url = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=QLCHITIEU;";
	private static String user = "sa";
	private static String password = "";

	public static Connection open() throws SQLException {
		try {
			connection = DriverManager.getConnection(url, user, password);
			//System.out.println("Database successfully connected!");
		}catch(SQLException e){
			System.out.println("Connect to database failed, some errors occurred!");
			e.printStackTrace();
		}
		return connection;
	}
	public static ResultSet getData(String stringSQL, Connection conn) throws SQLException {
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(stringSQL);
		return rs;
	}
}
