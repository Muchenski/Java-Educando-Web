package model.services;

public class PayPalService implements PagamentoService {

	private static final double TAXA = 0.02;
	private static final double JUROS = 0.01;

	@Override
	public Double taxa(Double amount) {
		return amount * TAXA;
	}

	@Override
	public Double juros(Double amount, Integer parcela) {
		return (amount * JUROS) * parcela;
	}

}
