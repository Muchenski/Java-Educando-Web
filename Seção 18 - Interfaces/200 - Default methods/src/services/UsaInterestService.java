package services;

public class UsaInterestService implements InterestService {

	private Double tax;

	public UsaInterestService(Double tax) {
		this.tax = tax / 100;
	}

	@Override
	public double getInterestRate() {
		return tax;
	}

}
