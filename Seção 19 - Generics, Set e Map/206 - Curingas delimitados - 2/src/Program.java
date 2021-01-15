import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

	public static void main(String[] args) {

		// PRINCÍPIO GET/PUT

		/*
		 * Vamos fazer um método que copia os elementos de uma lista para uma outra
		 * lista que pode ser mais genérica que a primeira.
		 */

		List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
		List<Double> myDoubles = Arrays.asList(3.14, 6.28);
		List<Object> myObjs = new ArrayList<Object>();

		copyTo(myInts, myObjs);
		myObjs.forEach(x -> System.out.print(x + " "));
		System.out.println();

		myObjs.clear();

		copyTo(myDoubles, myObjs);
		myObjs.forEach(x -> System.out.print(x + " "));
	}

	public static void copyTo(List<? extends Number> subClass, List<? super Number> superClass) {
		for (Number number : subClass) {
			superClass.add(number);
		}
	}

	public static void covariancia() {
		// get - OK
		// put - ERROR

		List<Integer> intList = new ArrayList<Integer>();
		intList.add(10);
		intList.add(5);
		List<? extends Number> list = intList;
		Number x = list.get(0);

		// erro de compilacao:
		// list.add(20);
	}

	public static void contravariancia() {
		// get - ERROR
		// put - OK

		List<Object> myObjs = new ArrayList<Object>();
		myObjs.add("Maria");
		myObjs.add("Alex");
		List<? super Number> myNums = myObjs;
		myNums.add(10);
		myNums.add(3.14);

		// erro de compilacao
		// Number x = myNums.get(0);
	}
}
