package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"UPDATE seller " 
					+ "SET BaseSalary = BaseSalary + ? " 
					+ "WHERE " + "DepartmentId = ?;");

			st.setDouble(1, 10000.0);
			st.setInt(2, 2);

			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeSettings(Arrays.asList(conn, st));
		}

	}

}
