package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();

		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();

		System.out.print("Level:\n[Junior(0)]\n[Mid-Level(1)]\n[Senior(2)]\n: ");
		int level = sc.nextInt();

		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();

		System.out.print("How many contracts to this worker?: ");
		int numberOfContracts = sc.nextInt();

		Worker worker = new Worker(name, level, baseSalary, new Department(departmentName));

		if (numberOfContracts > 0) {

			for (int i = 1; i <= numberOfContracts; i++) {

				System.out.println("Enter contract #" + i + " data:");

				sc.nextLine();
				System.out.print("Date(DD/MM/YYYY): ");
				Date date = sdf.parse(sc.nextLine());

				System.out.print("Value per hour: ");
				double valuePerHour = sc.nextDouble();

				System.out.print("Hours: ");
				int hours = sc.nextInt();

				worker.addContract(new HourContract(date, valuePerHour, hours, worker));
			}
		}
		sc.nextLine();
		System.out.print("Enter month and year to calculate income(MM/YYYY): ");
		String[] monthAndYear = sc.nextLine().split("/");

		double income = worker.income(Integer.parseInt(monthAndYear[0]), Integer.parseInt(monthAndYear[1]));
		
		System.out.println();
		System.out.println("Name: " + worker.getName());
		System.out.println("Level: " + worker.getlevel());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println(
				"Income for: " + monthAndYear[0] + "/" + monthAndYear[1] + ": " + String.format("%.2f", income));

		sc.close();
	}
}
