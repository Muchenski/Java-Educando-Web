package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Arrays;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();

			// Definindo que as alterações no banco só ocorrerão quando o método
			// conn.commit() for chamado.
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 9090 WHERE DepartmentId = 1");

			// Forçando uma exceção.
			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 1313 WHERE DepartmentId = 2");

			// Somente se o programa chegar a executar esta linha abaixo, que as alterações
			// realizadas ocorrerão no banco.
			conn.commit();
			System.out.println("Rows affected: " + rows1 + rows2);

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new DbIntegrityException(e.getMessage());

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException(e.getMessage());

			} catch (SQLException e1) {
				throw new DbException(e1.getMessage());
			}
		} finally {
			DB.closeSettings(Arrays.asList(conn, st));
		}

	}

}
