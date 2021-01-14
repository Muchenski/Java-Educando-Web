package model.services;

public interface PagamentoService {

	public Double taxa(Double amount);

	public Double juros(Double amount, Integer parcela);
}
