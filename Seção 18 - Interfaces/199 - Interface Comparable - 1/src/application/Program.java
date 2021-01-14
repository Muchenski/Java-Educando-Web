package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {

	public static void main(String[] args) {

		/*
		 * Problema motivador Faça um programa para ler um arquivo contendo nomes de
		 * pessoas (um nome por linha), armazenando-os em uma lista. Depois, ordenar os
		 * dados dessa lista e mostra-los ordenadamente na tela.
		 */

		List<String> nomes = new ArrayList<String>();
		String path = System.getProperty("user.dir").concat("\\teste.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();

			while (line != null) {
				nomes.add(line);
				line = br.readLine();
			}

			Collections.sort(nomes);
			for (String nome : nomes) {
				System.out.println(nome);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
