package entities;

public class Employee implements Comparable<Employee> {

	private String name;
	private Double salary;

	public Employee() {
	}

	public Employee(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return name + ", " + salary;
	}

	@Override
	public int compareTo(Employee o) {
		/*
		 * System.out.println("maria".compareTo("alex"));
		 * System.out.println("alex".compareTo("maria"));
		 * System.out.println("maria".compareTo("maria")); 
		 * 
		 * Output: 
		 *  12 
		 *  -12 
		 *  0
		 */
		return name.toUpperCase().compareTo(o.getName().toUpperCase());
	}
}
