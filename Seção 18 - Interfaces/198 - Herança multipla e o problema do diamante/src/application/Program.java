package application;

import entities.ComboDevice;
import entities.Printer;
import entities.Scanner;

public class Program {

	public static void main(String[] args) {

		/*
		 * Uma classe não pode extender de mais de uma outra classe. Porém, uma classe
		 * pode implementar diversas interfaces.
		 */

		Printer p = new Printer("1080");
		p.processDoc("My letter");
		p.print("My letter");

		System.out.println();

		Scanner s = new Scanner("2000");
		s.processDoc("My email");
		System.out.println(s.scan());

		System.out.println();

		ComboDevice c = new ComboDevice("2081");
		c.processDoc("My dissertation");
		c.print("My dissertation");
		System.out.println(c.scan());
	}

}
