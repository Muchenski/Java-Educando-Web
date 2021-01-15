import entities.Client;

public class Program {

	public static void main(String[] args) {

		Client c1 = new Client("Maria", "maria@gmail.com");
		Client c2 = new Client("Alex", "alex@gmail.com");
		Client c3 = new Client("Maria", "maria@hotmail.com");

		System.out.println(c1.hashCode()); // 74113781
		System.out.println(c2.hashCode()); // 2043485
		System.out.println(c3.hashCode()); // 74113781

		System.out.println(c1.equals(c2)); // false

		// Verdadeiro, pois nosso critério de avaliação está apenas no nome.
		System.out.println(c1.equals(c3)); // true

		// '==' compara referência de memória, em tipos sem tratamento especial.
		System.out.println(c1 == c3); // false

		// Para comparações de igualdade de valores, damos preferência ao equals().
		// Para compararmos a igualdade de referência, damos preferência ao '=='.

		// O tipo String, quando não há instanciação, é tratado como tipo especial
		// então podemos utilizar o == para comparar a igualdade de valores.
		String a = "teste";
		String b = "teste";
		System.out.println(a == b); // true

		// Porém, se forçarmos instanciação, deixará de ser tratada como tipo especial:
		String c = new String("teste");
		String d = new String("teste");
		System.out.println(c == d); // false

	}

}
