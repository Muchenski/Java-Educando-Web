package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			conn = DB.getConnection();
			if (!conn.isClosed()) {
				System.out.println("Connection ok");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(conn));
			try {
				System.out.println(conn.isClosed() ? "Closed" : "Active");
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

}
