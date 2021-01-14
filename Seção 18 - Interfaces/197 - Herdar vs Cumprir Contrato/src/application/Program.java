package application;

import entities.Circle;
import entities.IShape;
import entities.Rectangle;
import entities.AbstractShape;
import entities.enums.Cor;

public class Program {

	public static void main(String[] args) {

		/*
		 * Quando uma classe abstrata implementa uma interface, ela repassa os métodos
		 * obrigatórios da interface implementada para as classes que herdam dela.
		 * 
		 * Nós podemos instanciar um objeto a partir do tipo da interface que ele
		 * implementa. Porém, ele só terá acesso aos métodos da interface, até que se
		 * faça um downcasting.
		 */

		AbstractShape s1 = new Circle(Cor.BLACK.getCode(), 2.0);
		AbstractShape s2 = new Rectangle(Cor.WHITE.getCode(), 3.0, 4.0);

		IShape s3 = new Circle(Cor.BLACK.getCode(), 2.0);
		// s3.getCor() -> Não pode acessar, pois foi inicializado com o tipo da
		// interface
		// Logo, só poderá acessar os métodos da interface.
		// Podemos resolver isso com um DownCasting:
		Circle s4 = (Circle) s3;
		System.out.println("Circle color: " + s4.getCor());
		System.out.println("Circle area: " + String.format("%.2f", s4.area()));

		System.out.println();
		System.out.println("Circle color: " + s1.getCor());
		System.out.println("Circle area: " + String.format("%.2f", s1.area()));

		System.out.println();
		System.out.println("Rectangle color: " + s2.getCor());
		System.out.println("Rectangle area: " + String.format("%.2f", s2.area()));
	}
}
