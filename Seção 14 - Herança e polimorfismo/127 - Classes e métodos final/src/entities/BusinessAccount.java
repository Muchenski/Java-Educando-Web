package entities;

public final class BusinessAccount extends Account {

	private Double loanLimit;

	public BusinessAccount() {
	}

	public BusinessAccount(Integer number, String holder, Double balance, Double loanLimit) {
		super(number, holder, balance);
		this.loanLimit = loanLimit;
	}

	@Override
	public void withDraw(Double amount) {
		super.withDraw(amount);
		balance -= 2;
	}

	public void loan(Double amount) {
		if (amount <= loanLimit) {
			balance += amount - 10;
		}
	}

	public Double getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(Double loanLimit) {
		this.loanLimit = loanLimit;
	}

}
