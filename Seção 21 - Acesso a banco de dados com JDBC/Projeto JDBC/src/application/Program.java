package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		//departmentTest();
		sellerTest();

		sc.close();
	}

	public static void sellerTest() {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department d = DaoFactory.createDepartmentDao().findById(2);
		Seller s = null;

		try {
			s = new Seller(null, "Henrique", "henrique@gmail.com", sdf.parse("12/11/1996"), 6000.0, d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Testando o findById do Department:
		System.out.println(sellerDao.findById(2));
	}

	public static void departmentTest() {

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department d = new Department(null, "Utilitarios");

		// Testando o insert do Department:
		departmentDao.insert(d); // Done! id - 47 - Utilitarios - created!
		System.out.println(d); // Department [id=47, name=Utilitarios] -> Agora o 'd' conhece seu id, em memória.
		// departmentDao.insert(null); -> Argument cannot be null!

		sc.nextLine();

		// Testando o update do Department:
		d.setName("Utils");
		departmentDao.update(d); // Done! id - 47 - Utils - updated!
		d.setName(d.getName()); // Testando o mesmo nome.
		departmentDao.update(d); // Done! id - 47 - Utils - updated!
		// departmentDao.update(null); -> Argument cannot be null!

		sc.nextLine();

		// Testando o findById do Department:
		System.out.println(departmentDao.findById(d.getId())); // Department [id=47, name=Utils]
		System.out.println(departmentDao.findById(Integer.MIN_VALUE)); // null
		// System.out.println(departmentDao.findById(null)); -> Argument cannot be null!

		sc.nextLine();

		// Testando o deleteById do Department:
		departmentDao.deleteById(d.getId()); // Done! id - 47 - deleted!
		departmentDao.deleteById(Integer.MIN_VALUE); // Delete fail!
		// departmentDao.deleteById(1); -> SQLIntegrityConstraintViolationException
		// departmentDao.deleteById(null); -> Argument cannot be null!

		sc.nextLine();

		// Testando o findAll() do Department:
		departmentDao.findAll().forEach(System.out::println);

		System.out.println("\n\nEnd department test!");
	}
}
