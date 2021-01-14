package model.services;

import java.util.Calendar;

import model.entities.Contrato;
import model.entities.ParcelaContrato;

public class ContratoServico {

	private PagamentoService service;

	public ContratoServico(PagamentoService service) {
		this.service = service;
	}

	public void processaContrato(Contrato contrato, Integer parcelas) {

		Calendar calendario = Calendar.getInstance();
		Double parcelaInicial = contrato.getTotalValue() / parcelas;

		double totalAtualizada;
		for (int i = 1; i <= parcelas; i++) {

			totalAtualizada = parcelaInicial;

			calendario.setTime(contrato.getData());
			calendario.add(Calendar.MONTH, i);

			totalAtualizada += service.juros(parcelaInicial, i);
			totalAtualizada += service.taxa(totalAtualizada);

			contrato.getParcelas().add(new ParcelaContrato(calendario.getTime(), totalAtualizada));
		}
	}

}
