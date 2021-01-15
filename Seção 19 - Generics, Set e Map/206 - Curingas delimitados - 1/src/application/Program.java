package application;

import java.util.ArrayList;
import java.util.List;

import entities.Circle;
import entities.Rectangle;
import entities.Shape;

public class Program {

	public static void main(String[] args) {

		List<Shape> myShapes = new ArrayList<Shape>();

		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));

		System.out.println("Total area: " + totalAreaNotGeneric(myShapes));
		System.out.println();

		List<Circle> myCircles = new ArrayList<Circle>();
		myCircles.add(new Circle(2.0));
		myCircles.add(new Circle(3.0));

		// Não podemos:
		// System.out.println("Total area: " + totalAreaNotGeneric(myCircles));
		// Apesar de Shape ser SuperClasse de Circle
		// Uma Lista<Shape> não é superClasse de uma List<Circle>.

		// Podemos:
		System.out.println("Total area: " + totalAreaGenericForShapes(myCircles));
		System.out.println("Total area: " + totalAreaGenericForShapes(myShapes));
	}

	// Aceita tanto List<Shape> quanto as List's que possuem elementos de Subtipos
	// de Shape.
	public static double totalAreaGenericForShapes(List<? extends Shape> list) {

		// Não podemos adicionar itens em listas passadas como argumentos genéricos,
		// pois o compilador não tem como descobrir qual é o tipo da lista que irá
		// receber como argumento, e nem tem como saber quais são os subtipos do tipo
		// deste argumento.

		// Isso causará erros, pois a list passada poderia ser List<Rectancle>, por
		// exemplo:

		// list.add(new Circle());

		double sum = 0.0;
		for (Shape s : list) {
			sum += s.area();
		}
		return sum;
	}

	// Só aceita List<Shape> como argumento.
	public static double totalAreaNotGeneric(List<Shape> list) {

		// Como este método não recebe argumento genérico, podemos realizar adição na
		// lista que vem como argumento.
		// Pois o método compreende quais tipos(e subtipos) podem ser adicionados nesta
		// lista.
		list.add(new Rectangle());

		double sum = 0.0;
		for (Shape s : list) {
			sum += s.area();
		}
		return sum;
	}

}
