import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {

	public static void main(String[] args) {

		// Como o Bufferedreader e o FileReader foram declarados dentro do escopo do try
		// não necessitamos realizar a função .close() em cima deles, uma vez que não
		// existirão fora do escopo do try.
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\test.txt"))) {

			String line = br.readLine();

			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
