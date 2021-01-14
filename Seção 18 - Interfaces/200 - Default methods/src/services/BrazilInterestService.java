package services;

public class BrazilInterestService implements InterestService {

	private Double tax;

	public BrazilInterestService(Double tax) {
		this.tax = tax / 100;
	}

	@Override
	public double getInterestRate() {
		return tax;
	}

}
