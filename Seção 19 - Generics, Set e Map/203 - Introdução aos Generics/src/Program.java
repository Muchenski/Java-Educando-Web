import java.util.Scanner;

import services.PrintService;

public class Program {
	public static void main(String[] args) {

		// ATENÇÃO:

		// Não utilizar a classe 'Object' para tentar simular Generics<T>.
		// Pois, deste modo não temos o type-safety, podendo aumentar
		// muito a ocorrência de erros, e causando prejuízos a performance
		// do código(já que iremos necessitar de vários castings.

		Scanner sc = new Scanner(System.in);

		PrintService<Integer> psI = new PrintService<Integer>();
		PrintService<String> psS = new PrintService<String>();

		System.out.print("How many values? ");
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			int value = sc.nextInt();
			psI.addValue(value);
		}

		psI.print();
		System.out.println("First: " + psI.first());

		////////////////////////////////////////////////////////////

		System.out.print("How many values? ");
		n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			String value = sc.next();
			psS.addValue(value);
		}

		psS.print();
		System.out.println("First: " + psS.first());

		sc.close();
	}
}
