package services;

import java.security.InvalidParameterException;

public interface InterestService {

	public double getInterestRate();

	default public double payment(int months, double amount) {

		// Fórmula dos Juros compostos:

		// M = c.(1 + i)**n

		// M = montante
		// c = capital aplicado
		// i = taxa de juros composto
		// n = tempo de aplicação

		if (months < 1) {
			throw new InvalidParameterException("Months must be greater than zero!");
		}
		return amount * Math.pow(1 + getInterestRate(), months);
	}
}
