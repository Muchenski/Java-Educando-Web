package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		/*
		 * Faça um programa para ler um arquivo contendo funcionários (nome e salário)
		 * no formato .csv, armazenando-os em uma lista. Depois, ordenar a lista por
		 * nome e mostrar o resultado na tela. Nota: o caminho do arquivo pode ser
		 * informado "hardcode".
		 * 
		 */

		List<Employee> employees = new ArrayList<Employee>();

		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\in.csv"))) {

			String line = br.readLine();
			String[] employeesData;
			while (line != null) {

				employeesData = line.split(",");
				employees.add(new Employee(employeesData[0], Double.parseDouble(employeesData[1])));

				line = br.readLine();
			}
			
			// Para nós ordenarmos os Employees da maneira que desejamos
			// devemos implementar a interface Comparable na classe do tipo.
			Collections.sort(employees);

			for (Employee employee : employees) {
				System.out.println(employee);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
