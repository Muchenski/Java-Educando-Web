package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO seller" + "(name, email, birthDate, baseSalary, departmentId) "
							+ "VALUES(?, ?, ?, ?, ?);",

					// Comando para que seja retornado o id do registro, após a inserção.
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Henrique");
			st.setString(2, "henrique@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/11/1996").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {

				// getGeneratedKeys() -> Recupera os id's dos novos registros, retornados por
				// conta do 'Statement.RETURN_GENERATED_KEYS'.
				rs = st.getGeneratedKeys();

				while (rs.next()) {

					// Como getGeneratedKeys() retorna um ResultSet de apenas uma coluna
					// contendo os id's dos novos registros, e também tendo em vista
					// de que nós não sabemos o nome desta mesma coluna, utilizamos o getInt(1).
					System.out.println("Done! Id - " + rs.getInt(1));
				}
			} else {
				System.out.println("No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeSettings(Arrays.asList(conn, st, rs));
		}

	}
}

// Por que o getGeneratedKeys() retorna um ResultSet?
// Porque podemos realizar uma inserção múltipla:

// INSERT INTO department(name) VALUES('D1'),('D2');

// Esta inserção retornaria 2 id's.
