package services;

import java.util.List;

public class CalculationService {

	// <T extends Comparable<? super T>> representa o tipo de dado que
	// será trabalhado neste método.

	// Neste caso, todos T que implementem uma interface Comparable do tipo T ou de
	// qualquer SuperClasse de T.

	public static <T extends Comparable<? super T>> T max(List<T> list) {

		if (list.isEmpty()) {
			throw new IllegalStateException("List can't be empty!");
		}

		T max = list.get(0);
		for (T item : list) {
			if (item.compareTo(max) > 0) {
				max = item;
			}
		}

		return max;
	}

}
