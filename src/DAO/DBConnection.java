package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static String DEFAULT_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static Connection open() {
		Connection cnn = null;
		try {
			String driverString = DEFAULT_DRIVER;
		    String DB_URL = "jdbc:sqlserver://localhost:1433;"
		            + "databaseName=QLBONGDA;";
		    String user = "sa";
			String pass = "123456";
			Class.forName(driverString);
			cnn = (Connection) DriverManager.getConnection(DB_URL,user,pass);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cnn;
	}
	public static void main(String[] args) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		pStatement = (PreparedStatement) cnn.prepareStatement("SELECT * FROM TINH");
		rSet = pStatement.executeQuery();
		while (rSet.next()) {
            System.out.println(rSet.getString(1)) ;
        }
	}

}
