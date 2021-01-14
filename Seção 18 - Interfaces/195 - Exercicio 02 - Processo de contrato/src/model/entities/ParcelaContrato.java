package model.entities;

import java.util.Date;

public class ParcelaContrato {

	private Date date;
	private Double amount;

	public ParcelaContrato() {
	}

	public ParcelaContrato(Date date, Double amount) {
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
