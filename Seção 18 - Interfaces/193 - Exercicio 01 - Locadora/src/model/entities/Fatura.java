package model.entities;

public class Fatura {

	private Double basicPayment;
	private Double tax;

	public Fatura() {
	}

	public Fatura(Double basicPayment, Double tax) {
		this.basicPayment = basicPayment;
		this.tax = tax;
	}

	public Double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(Double basicPayment) {
		this.basicPayment = basicPayment;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalPayment() {
		return getBasicPayment() + getTax();
	}
}
