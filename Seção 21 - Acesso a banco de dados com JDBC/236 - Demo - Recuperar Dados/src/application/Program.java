package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM department;");

			while (rs.next()) {
				// Ao invés de rs.getInt(1), poderíamos rs.getInt("id");
				System.out.println(rs.getString(1) + " - " + rs.getString("name"));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(conn, st, rs));
		}

	}

}
