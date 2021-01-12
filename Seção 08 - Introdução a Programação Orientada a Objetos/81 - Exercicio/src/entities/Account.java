package entities;

public class Account {

	private int number;
	private String name;
	private double balance;

	public Account() {
	}

	public Account(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public Account(int number, String name, double balance) {
		this.number = number;
		this.name = name;
		makeDeposit(balance);
	}

	public void makeDeposit(double value) {
		balance += value;
	}

	public void makeWithdraw(double value) {
		balance -= value + 5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public double getbalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account " + number + ", Holder: " + name + ", Balance: $" + String.format("%.2f", balance);
	}
}
