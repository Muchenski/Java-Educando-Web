package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DB {

	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(loadProperties().getProperty("dburl"), loadProperties());
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static <T extends AutoCloseable> void closeSettings(List<T> ac) {
		for (T t : ac) {
			if (t != null) {
				try {
					t.close();
				} catch (Exception e) {
					throw new DbException(e.getMessage());
				}
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}