package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		// Problema: Queremos ordenar uma lista de produto.

		// Utilizando a interface Comparable<T> nossa classe não fica fechada para
		// alteração:
		// Se o critério de comparação mudar, precisaremos alterar a classe Product.
		// Desta forma, estamos tendo um ponto de manutenção a mais.

		// Solução:
		// • Podemos então usar o default method "sort" da interface List:
		// default void sort(Comparator<? super E> c)

		// Comparator é uma interface funcional(só possui um método).

		List<Product> products = new ArrayList<Product>();

		products.addAll(Arrays.asList(new Product("Tv", 900.00), new Product("Notebook", 1200.00),
				new Product("Tablet", 450.00)));

		// Função anonima:
		Comparator<Product> comp = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());

		products.sort(comp);

		for (Product product : products) {
			System.out.println(product.getName() + "," + product.getPrice());
		}
	}
}

// Outras maneiras de utilizar o Comparator:

//• Comparator objeto de classe separada
//• Comparator objeto de classe anônima
//• Comparator objeto de expressão lambda com chaves
//• Comparator objeto de expressão lambda sem chaves
//• Comparator expressão lambda "direto no argumento"

