import java.io.File;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a folder path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		System.out.println("FOLDERS: ");
		// Capturando todas as subpastas do caminho passado em strPath:
		File[] folders = path.listFiles(File::isDirectory);
		for (File folder : folders) {
			System.out.println(folder);
		}

		System.out.println();
		
		System.out.println("FILES: ");
		// Capturando todos os arquivos do caminho passado em strPath:
		File[] files = path.listFiles(File::isFile);
		for (File file : files) {
			System.out.println(file);
		}
		
		// Criando uma subpasta no diretório passado em srtPath.
		boolean success = new File(System.getProperty("user.dir") + "\\subdir").mkdir();
		if(success) {
			System.out.println("Subdir created!");
		}

		sc.close();
	}

}
