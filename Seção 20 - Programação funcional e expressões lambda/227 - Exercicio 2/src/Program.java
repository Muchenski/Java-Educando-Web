import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		String path = System.getProperty("user.dir") + "\\in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> employees = new ArrayList<Employee>();
			String line = br.readLine();
			String[] fields;

			while (line != null) {
				fields = line.split(",");
				employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			
			System.out.print("Enter salary: ");
			double filterSalary = sc.nextDouble();
			Stream<String> emails = employees.stream()
					.filter(e -> e.getSalary() > filterSalary)
					.map(p -> p.getEmail())
					.sorted();
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", filterSalary) + ":");
			emails.forEach(System.out::println);
			
			double sum = employees.stream()
					.filter(e -> e.getName().toLowerCase().charAt(0) == 'm')
					.map(e -> e.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}
}
