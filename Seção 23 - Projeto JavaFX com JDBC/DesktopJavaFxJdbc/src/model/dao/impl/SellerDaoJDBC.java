package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
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
	}

	@Override
	public void insert(Seller seller) {

		nullCheck(seller);

		try {
			st = conn.prepareStatement("INSERT INTO seller" + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES" + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());

			int rowsAffected = st.executeUpdate();

			conn.commit();

			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					// Em memória, o objeto ainda não sabe o próprio id
					// por isso devemos setar ao inserir.
					seller.setId(id);
					System.out.println("CREATED - " + seller);
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

		} catch (SQLException e) {
			makeRollBack();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}

	}

	@Override
	public void update(Seller seller) {
		nullCheck(seller);

		try {

			st = conn.prepareStatement("UPDATE seller SET " + "name = ?," + "email = ?," + "birthDate = ?,"
					+ "baseSalary = ?," + "departmentId = ? " + "WHERE id = ?;");

			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			st.setInt(6, seller.getId());

			int rowsAffected = st.executeUpdate();

			conn.commit();

			if (rowsAffected > 0) {
				System.out.println("UPDATED - " + seller);
			} else {
				System.out.println("Update fail!");
			}

		} catch (SQLException e) {
			makeRollBack();
			throw new DbException(e.getMessage());

		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
	}

	@Override
	public void deleteById(Integer id) {
		nullCheck(id);

		try {

			st = conn.prepareStatement("DELETE FROM seller WHERE id = ?;");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();

			conn.commit();

			if (rowsAffected > 0) {
				System.out.println("DELETED - " + id);
			} else {
				System.out.println("Delete fail!");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			makeRollBack();
			throw new DbIntegrityException(e.getMessage());

		} catch (SQLException e) {
			makeRollBack();
			throw new DbException(e.getMessage());

		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
	}

	@Override
	public Seller findById(Integer id) {

		nullCheck(id);

		try {

			st = conn.prepareStatement(
					"SELECT seller.*, department.name as depName " + "FROM seller " + "INNER JOIN department "
							+ "ON seller.departmentId = department.id " + "WHERE seller.id = ?",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				return instantiateSeller(instantiateDepartment());
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
	}

	@Override
	public List<Seller> findAll() {

		try {

			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			st = conn.prepareStatement(
					"SELECT seller.*, department.name as depName " + "FROM seller INNER JOIN department "
							+ "ON seller.departmentId = department.Id " + "ORDER BY seller.name;");

			rs = st.executeQuery();

			Department dep;
			while (rs.next()) {

				dep = map.get(rs.getInt("departmentId"));

				if (dep == null) {
					dep = instantiateDepartment();
					map.put(rs.getInt("departmentId"), dep);
				}

				sellers.add(instantiateSeller(dep));
			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		nullCheck(department);

		try {

			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			st = conn.prepareStatement("SELECT seller.*, department.name as depName "
					+ "FROM seller INNER JOIN department " + "ON seller.departmentId = department.Id "
					+ "WHERE departmentId = ? " + "ORDER BY seller.name;");

			st.setInt(1, department.getId());
			rs = st.executeQuery();

			Department dep;
			while (rs.next()) {

				dep = map.get(rs.getInt("departmentId"));

				if (dep == null) {
					dep = instantiateDepartment();
					map.put(rs.getInt("departmentId"), dep);
				}

				sellers.add(instantiateSeller(dep));
			}
			return sellers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeSettings(Arrays.asList(st, rs));
		}
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
	private Seller instantiateSeller(Department department) throws SQLException {

		try {

			return new Seller(rs.getInt(1), rs.getString("name"), rs.getString("email"),
					sdf.parse(rs.getString("birthDate")), rs.getDouble("baseSalary"), department);

		} catch (ParseException e) {
			throw new DbException(e.getMessage());
		}
	}

	private void makeRollBack() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private void nullCheck(Object object) {
		if (object != null) {
			if (object instanceof Seller) {
				if (((Seller) object).getName() == null || ((Seller) object).getEmail() == null
						|| ((Seller) object).getBirthDate() == null || ((Seller) object).getBaseSalary() == null
						|| ((Seller) object).getDepartment() == null
						|| ((Seller) object).getDepartment().getId() == null) {
					throw new DbException("Invalid Seller!");
				}
			} else if (object instanceof Department) {
				if (((Department) object).getId() == null) {
					throw new DbException("Invalid Department!");
				}
			}
		} else {
			throw new DbException("Argument cannot be null!");
		}
	}
}