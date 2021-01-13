import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Program {

	public static void main(String[] args) {

		// new FileWriter(path) -> cria/recria o arquivo especificado.
		// new FileWriter(path, true) -> Acrescenta ao arquivo existente.

		String[] lines = new String[] { "Java", "JPA", "Hibernate", "SpringBoot" };

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\test.txt"))) {

			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
