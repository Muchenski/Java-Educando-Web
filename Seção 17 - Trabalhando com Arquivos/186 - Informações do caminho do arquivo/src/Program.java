import java.io.File;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("enter a file path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		// Acessando o nome do arquivo:
		System.out.println(path.getName());

		// Acessando o o caminho do arquivo, desprezando o nome:
		System.out.println(path.getParent());

		// Acessando todo o caminho, incluindo o nome do arquivo:
		System.out.println(path.getPath());

		sc.close();
	}
}
