package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private Integer level;
	private Double baseSalary;

	private Department department;

	private List<HourContract> contracts = new ArrayList<HourContract>();

	public Worker() {
	}

	public Worker(String name, Integer level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public void addContract(HourContract contract) {
		contracts.add(contract);
	}

	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}

	public Double income(int month, int year) {
		double total = baseSalary;
		Calendar calendar = Calendar.getInstance();

		for (HourContract contract : contracts) {
			calendar.setTime(contract.getDate());
			if ((1 + calendar.get(Calendar.MONTH) == month) && (calendar.get(Calendar.YEAR) == year)) {
				total += contract.totalValue();
			}
		}
		return total;
	}

	public WorkerLevel getlevel() {
		return WorkerLevel.getEnum(level);
	}

	public void setlevel(WorkerLevel level) {
		this.level = level.getCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
