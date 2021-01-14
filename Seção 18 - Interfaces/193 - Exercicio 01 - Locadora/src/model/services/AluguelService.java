package model.services;

import model.entities.Aluguel;
import model.entities.Fatura;

public class AluguelService {

	private Double pricePerDay;
	private Double pricePerHouer;

	private TaxaService taxService;

	public AluguelService(Double pricePerDay, Double pricePerHouer, TaxaService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHouer = pricePerHouer;
		this.taxService = taxService;
	}

	public void processInvoice(Aluguel carRental) {

		Long start = carRental.getStart().getTime();
		Long finish = carRental.getFinish().getTime();
		double hours = ((double) finish - start) / 1000 / 60 / 60;

		double basicPayment;
		if (hours <= 12) {
			basicPayment = Math.ceil(hours) * pricePerHouer;
		} else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}

		double tax = taxService.tax(basicPayment);

		carRental.setInvoice(new Fatura(basicPayment, tax));
	}

}
