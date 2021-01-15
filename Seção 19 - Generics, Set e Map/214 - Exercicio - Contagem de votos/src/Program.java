import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {
		// Na contagem de votos de uma eleição, são gerados vários registros
		// de votação contendo o nome do candidato e a quantidade de votos
		// (formato .csv) que ele obteve em uma urna de votação. Você deve
		// fazer um programa para ler os registros de votação a partir de um
		// arquivo, e daí gerar um relatório consolidado com os totais de cada
		// candidato.

		String path = System.getProperty("user.dir") + "\\in.txt";
		File file = new File(path);

		if (!file.exists()) {
			createFile(file);
		}

		writeFile(file);

		try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {

			Map<String, Integer> candidatos = new TreeMap<String, Integer>();
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				if (!candidatos.containsKey(fields[0])) {
					candidatos.put(fields[0], Integer.parseInt(fields[1]));
				} else {
					int votosAnteriores = candidatos.get(fields[0]);
					candidatos.put(fields[0], votosAnteriores + Integer.parseInt(fields[1]));
				}
				line = br.readLine();
			}

			for (String key : candidatos.keySet()) {
				System.out.println(key + "," + candidatos.get(key));
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		file.deleteOnExit();
	}

	public static void createFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void writeFile(File file) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
			bw.write("Alex Blue,15");
			bw.newLine();
			bw.write("Maria Green,22");
			bw.newLine();
			bw.write("Bob Brown,21");
			bw.newLine();
			bw.write("Alex Blue,30");
			bw.newLine();
			bw.write("Bob Brown,15");
			bw.newLine();
			bw.write("Maria Green,27");
			bw.newLine();
			bw.write("Maria Green,22");
			bw.newLine();
			bw.write("Bob Brown,25");
			bw.newLine();
			bw.write("Alex Blue,31");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
