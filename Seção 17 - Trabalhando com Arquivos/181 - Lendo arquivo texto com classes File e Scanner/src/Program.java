import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		File file = new File(System.getProperty("user.dir") + "\\test.txt");

		Scanner sc = null;

		try {

			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());

		} finally {
			// Queremos fechar o Scanner no final da execução
			// mas devemos verificar se ele é nulo, pois pode ter ocorrido
			// algum erro que fez com que ele não chegasse a ser instanciado.
			if (sc != null) {
				sc.close();
			}
		}
	}

}
