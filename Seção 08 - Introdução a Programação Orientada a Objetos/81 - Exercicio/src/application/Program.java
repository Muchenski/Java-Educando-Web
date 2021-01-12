package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Account;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter account number: ");
		int number = sc.nextInt();
		
		System.out.print("Enter account holder name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		System.out.print("Is there an initial deposit? (y/n): ");
		char yesOrNot = sc.nextLine().charAt(0);
		
		Account acc;
		
		if(yesOrNot == 'y') {
			
			System.out.print("Enter initial deposit value: ");
			double balance = sc.nextDouble();
			
			acc = new Account(number, name, balance);
		
		}else {
			acc = new Account(number, name);
		}
		
		System.out.println();
		System.out.print("Enter a deposit value: ");
		double deposit = sc.nextDouble();
		acc.makeDeposit(deposit);
		
		System.out.println();
		System.out.println("Updated account data:");
		System.out.println(acc);
		
		System.out.println();
		System.out.print("Enter a withdraw value: ");
		double withdraw = sc.nextDouble();
		acc.makeWithdraw(withdraw);
		
		System.out.println();
		System.out.println("Updated account data:");
		System.out.println(acc);
		
		sc.close();
	}

}
