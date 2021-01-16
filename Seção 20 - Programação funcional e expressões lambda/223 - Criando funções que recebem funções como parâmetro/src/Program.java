import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		List<Product> list = new ArrayList<>();
		list.add(new Product("Tv", 900.00));
		list.add(new Product("Mouse", 50.00));
		list.add(new Product("Tablet", 350.50));
		list.add(new Product("HD Case", 80.90));

		Double initialSumValue = 0.0;
		Predicate<Product> firstLetterF = x -> x.getName().toLowerCase().charAt(0) == 't';

		System.out.println(String.format("R$%.2f", filteredSum(list, firstLetterF, initialSumValue)));
	}

	public static double filteredSum(List<Product> list, Predicate<Product> criteria, Double initialSumValue) {

		Function<Product, Double> getPriceAttr = x -> x.getPrice();

		return list.stream().filter(criteria).map(getPriceAttr).reduce(initialSumValue, (price, sum) -> price + sum);
	}
}
