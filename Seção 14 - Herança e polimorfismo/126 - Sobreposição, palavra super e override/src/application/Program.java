package application;

import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;

public class Program {

	public static void main(String[] args) {

		/*
		 * Suponha que a operação de saque possui uma taxa no valor de 5.0. Entretanto,
		 * se a conta for do tipo poupança, esta taxa não deve ser cobrada. Como
		 * resolver isso? Resposta: sobrescrevendo o método withdraw na subclasse
		 * SavingsAccount.
		 */

		/*
		 * É possível chamar a implementação da superclasse usando a palavra super.
		 * Exemplo: suponha que, na classe BusinessAccount, a regra para saque seja
		 * realizar o saque normalmente da superclasse, e descontar mais 2.0.
		 */

		System.out.println("Account:");
		Account acc = new Account(1001, "Alex", 1000.0);
		acc.withDraw(200.0);
		System.out.println(acc.getBalance());

		System.out.println();

		System.out.println("SavingsAccount:");
		Account accS = new SavingsAccount(1002, "Henrique", 1000.0, 0.01);
		accS.withDraw(200.0);
		System.out.println(accS.getBalance());

		System.out.println();

		System.out.println("BusinessAccount:");
		Account accB = new BusinessAccount(1003, "Carla", 1000.0, 500.0);
		accB.withDraw(200.0);
		System.out.println(accB.getBalance());

		/*
		 * Mesmo que nós não consigamos acessar métodos específicos da SubClasse, quando
		 * inicializamos uma variável pela SuperClasse, os métodos sobrepostos utilizados
		 * serão os da SubClasse.
		 * 
		 * Ex:
		 * 
		 * public class Account{
		 * 		public void printar(){
		 * 			System.out.println("Estou na SuperClasse");
		 * 		}
		 * }
		 * 
		 * public class SavingsAccount{
		 * 		public void printar(){
		 * 			System.out.println("Estou na SubClasse");
		 * 		}
		 * }
		 * 
		 * Account a = new SavingsAccount();
		 * a.printar() // Estou na SubClasse.
		 * 
		 */
	}

}
