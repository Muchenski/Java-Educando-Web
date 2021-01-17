package application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			departmentTest();
			sc.nextLine();
			sellerTest();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			undoTestData();
			sc.close();
		}
	}

	public static void sellerTest() throws ParseException {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department d = DaoFactory.createDepartmentDao().findById(1);
		Seller s = null;

		s = new Seller(null, "Henrique", "henrique@gmail.com", sdf.parse("12/11/1996"), 6000.0, d);

		System.out.println("Insert Seller Test");
		sellerDao.insert(s); // -> CREATED
		sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, d)); // -> CREATED
		sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", new Date(), 6000.0, d)); // -> CREATED
		// sellerDao.insert(new Seller(5, null, null, null, null, null)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, null, null, null, null, null)); -> Invalid Seller!
		// sellerDao.insert(new Seller()); -> Invalid Seller!
		// sellerDao.insert(null); -> Argument cannot be null!
		// sellerDao.insert(new Seller(null, null, "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, d)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", null, sdf.parse("12/11/1996"), 6000.0, d)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", null, 6000.0, d)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), null, d)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, null)); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, new Department())); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, new Department(null, "Teste"))); -> Invalid Seller!
		// sellerDao.insert(new Seller(null, "Teste", "teste@gmail.com", sdf.parse("12/11/1996"), 6000.0, new Department(Integer.MIN_VALUE, "Teste"))); -> Fail
		
		sc.nextLine();
		
		System.out.println("Update Seller Test");
		s.setName("muxinhakkkkkkk");
		sellerDao.update(s);
		
		sc.nextLine();
		
		System.out.println("Find Seller Test");
		System.out.println(sellerDao.findById(s.getId()));
		
		sc.nextLine();
		
		System.out.println("Delete Seller Test");
		sellerDao.deleteById(s.getId());
		
		sc.nextLine();
		
		System.out.println("FindByDepartment Seller Test");
		sellerDao.findByDepartment(d).forEach(System.out::println);
		// sellerDao.findByDepartment(new Department()); -> Invalid Department!
		sellerDao.findByDepartment(new Department(Integer.MIN_VALUE, "")).forEach(System.out::println); // []
		// sellerDao.findByDepartment(null); -> Argument cannot be null!
		
		sc.nextLine();
		
		System.out.println("FindAll Seller Test");
		sellerDao.findAll().forEach(System.out::println);
		
		System.out.println("\n\nEnd Seller test!\n\n");
	}

	public static void departmentTest() {

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department d = new Department(null, "Utilitarios");

		System.out.println("Insert Department Test:");
		departmentDao.insert(d); // -> CREATED
		departmentDao.insert(new Department(null, "Teste")); // -> CREATED
		// departmentDao.insert(new Department(5, null)); -> Invalid Department!
		// departmentDao.insert(new Department(null, null)); -> Invalid Department!
		// departmentDao.insert(new Department()); -> Invalid Department!
		// departmentDao.insert(null); -> Argument cannot be null!

		sc.nextLine();

		System.out.println("Update Department Test:");
		d.setName("Utils");
		departmentDao.update(d); // -> UPDATED
		d.setName(d.getName());
		departmentDao.update(d); // -> UPDATED
		// departmentDao.update(new Department(5, null)); -> Invalid Department!
		// departmentDao.update(new Department(null, null)); -> Invalid Department!
		// departmentDao.update(new Department()); -> Invalid Department!
		// departmentDao.update(null); -> Argument cannot be null!

		sc.nextLine();

		System.out.println("Find Department Test:");
		System.out.println(departmentDao.findById(d.getId())); // -> OK
		System.out.println(departmentDao.findById(Integer.MIN_VALUE)); // -> null
		// System.out.println(departmentDao.findById(null)); -> Argument cannot be null!

		sc.nextLine();

		System.out.println("Delete Department test:");
		departmentDao.deleteById(d.getId()); // -> DELETED
		departmentDao.deleteById(Integer.MIN_VALUE); // -> Delete fail!
		// departmentDao.deleteById(null); -> Argument cannot be null!

		sc.nextLine();

		System.out.println("FindAll Department test:");
		departmentDao.findAll().forEach(System.out::println); // OK

		System.out.println("\n\nEnd department test!\n\n");
	}

	public static void undoTestData() {
		try {
			DB.getConnection().createStatement().executeUpdate("delete from department where id > 4;");
			DB.getConnection().createStatement().executeUpdate("delete from seller where id > 6;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

// delete from department where id > 4;
// delete from seller where id > 6;