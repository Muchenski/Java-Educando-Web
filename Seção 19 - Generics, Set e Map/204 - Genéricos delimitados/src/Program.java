import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import entities.Product;
import services.CalculationService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		String path = System.getProperty("user.dir") + "\\in.txt";
		File file = new File(path);

		if (!file.exists()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {

				bw.write("Computer,890.50");
				bw.newLine();
				bw.write("IPhone X,910.00");
				bw.newLine();
				bw.write("Tablet,550.00");

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else {

			try (BufferedReader br = new BufferedReader(new FileReader(path))) {

				String line = br.readLine();
				while (line != null) {
					String[] fields = line.split(",");
					list.add(new Product(fields[0], Double.parseDouble(fields[1])));
					line = br.readLine();
				}

				Product x = CalculationService.max(list);
				System.out.println("Most expensive Product:");
				System.out.println(x);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println();
		Integer x = CalculationService.max(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("Max number: " + x);

	}
}
