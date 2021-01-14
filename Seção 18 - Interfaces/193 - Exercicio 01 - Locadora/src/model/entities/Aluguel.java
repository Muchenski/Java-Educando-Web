package model.entities;

import java.util.Date;

public class Aluguel {

	private Date start;
	private Date finish;

	private Veiculo vehicle;
	private Fatura invoice;

	public Aluguel(Date start, Date finish, Veiculo vehicle) {
		this.start = start;
		this.finish = finish;
		this.vehicle = vehicle;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public Veiculo getVehicle() {
		return vehicle;
	}

	public void setVehicle(Veiculo vehicle) {
		this.vehicle = vehicle;
	}

	public Fatura getInvoice() {
		return invoice;
	}

	public void setInvoice(Fatura invoice) {
		this.invoice = invoice;
	}

}
