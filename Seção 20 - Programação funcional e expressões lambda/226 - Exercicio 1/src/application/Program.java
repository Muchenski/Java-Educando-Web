package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		// Fazer um programa para ler um conjunto de produtos a partir de um
		// arquivo em formato .csv (suponha que exista pelo menos um produto).
		// Em seguida mostrar o preço médio dos produtos. Depois, mostrar os
		// nomes, em ordem decrescente, dos produtos que possuem preço
		// inferior ao preço médio.

		Locale.setDefault(Locale.US);
		String path = System.getProperty("user.dir") + "\\in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Product> products = new ArrayList<Product>();

			String line = br.readLine();
			String[] fields;

			while (line != null) {
				fields = line.split(",");
				products.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}

			double average = products.stream()
					.map(x -> x.getPrice())
					.reduce(0.0, (x, y) -> x + y) / products.size();
			
			System.out.println("Average: " + String.format("R$%.2f", average));

			Comparator<String> comp = (x, y) -> x.toUpperCase().compareTo(y.toUpperCase());
			
			List<String> names = products.stream()
					.filter(x -> x.getPrice() < average)
					.map(x -> x.getName())
					.sorted(comp.reversed()) 
					.collect(Collectors.toList());
			
			names.forEach(System.out::println);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
