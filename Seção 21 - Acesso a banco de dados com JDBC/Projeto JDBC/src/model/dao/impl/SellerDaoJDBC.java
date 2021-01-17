package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
		try {
			this.conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void insert(Seller seller) {

		try {

			st = conn.prepareStatement("INSERT INTO seller()");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		
		nullCheck(id);
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*, department.name as depName "
					+ "FROM seller "
					+ "INNER JOIN department "
					+ "ON seller.departmentId = department.id "
					+ "WHERE seller.id = ?",
					Statement.RETURN_GENERATED_KEYS
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				return instantiateSeller();
			}
			return null;
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// Como este é apenas um método auxiliar, e o método que irá utilizá-lo
	// já realiza o tratamento de uma possível SQLException,
	// iremos propagar(throws) a possível exceção.
	private Department instantiateDepartment() throws SQLException {
		return new Department(rs.getInt("departmentId"), rs.getString("depName"));
	}

	// Como este é apenas um método auxiliar, e o método que irá utilizá-lo
	// já realiza o tratamento de uma possível SQLException,
	// iremos propagar(throws) a possível exceção.
	private Seller instantiateSeller() throws SQLException {

		try {
			
			return new Seller(rs.getInt(1), 
					rs.getString("name"), 
					rs.getString("email"),
					sdf.parse(rs.getString("birthDate")), 
					rs.getDouble("baseSalary"), 
					instantiateDepartment());
			
		} catch (ParseException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	/*
	private void makeRollBack() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	*/
	private void nullCheck(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Argument cannot be null!");
		}
	}
}
