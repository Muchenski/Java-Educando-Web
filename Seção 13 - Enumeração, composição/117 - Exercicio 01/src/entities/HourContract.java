package entities;

import java.util.Date;

public class HourContract {

	private Date date;
	private Double valuePerHour;
	private Integer hours;

	private Worker worker;

	public HourContract() {
	}

	public HourContract(Date date, Double valuePerHour, Integer hours, Worker worker) {
		this.date = date;
		this.valuePerHour = valuePerHour;
		this.hours = hours;
		this.worker = worker;
	}

	public Double totalValue() {
		return valuePerHour * hours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getValuePerHour() {
		return valuePerHour;
	}

	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

}
