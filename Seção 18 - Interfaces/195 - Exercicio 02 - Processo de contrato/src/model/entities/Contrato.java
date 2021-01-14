package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contrato {

	private Integer numero;
	private Date data;
	private Double totalValue;

	private List<ParcelaContrato> parcelas = new ArrayList<ParcelaContrato>();

	public Contrato() {
	}

	public Contrato(Integer numero, Date data, Double totalValue) {
		this.numero = numero;
		this.data = data;
		this.totalValue = totalValue;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ParcelaContrato> getParcelas() {
		return parcelas;
	}

}
