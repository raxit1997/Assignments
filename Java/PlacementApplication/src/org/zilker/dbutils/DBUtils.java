package org.zilker.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBUtils {

	private static final Logger logger = Logger.getLogger(DBUtils.class.getName());
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/placement";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "zilkeradmin";
	private static Connection conn;

	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
		} catch (SQLException e) {
			logger.warning("Error connecting with SQL Driver");
		}

		return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.warning("Error closing the connection variables");
		}
	}

}
