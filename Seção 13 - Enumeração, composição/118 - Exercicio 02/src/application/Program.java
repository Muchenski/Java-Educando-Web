package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;

public class Program {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter client data: ");

		System.out.print("Name: ");
		String name = sc.nextLine();

		System.out.print("Email: ");
		String email = sc.nextLine();

		System.out.print("Birth date(DD/MM/YYYY): ");
		Date birthDate = sdf.parse(sc.nextLine());

		Client client = new Client(name, email, birthDate);

		System.out.println();
		
		System.out.println("Enter order data: ");
		System.out.print(
				"Status:\n" + "[PENDING_PAYMENT(0)]\n" + "[PROCESSING(1)]\n" + "[SHIPPED(2)]\n" + "[DELIVERED(3)]\n: ");
		int status = sc.nextInt();

		System.out.println();
		
		System.out.print("How many items to this order?: ");
		int numberOfItems = sc.nextInt();

		Product product;
		OrderItem item;
		Order order = new Order(new Date(System.currentTimeMillis()), status, client);

		for (int i = 1; i <= numberOfItems; i++) {
			System.out.println("Enter #" + i + " item data:");
			sc.nextLine();
			System.out.print("Product name: ");
			String productName = sc.nextLine();
			System.out.print("Product price: ");
			double price = sc.nextDouble();
			System.out.print("Quantity: ");
			int quantity = sc.nextInt();

			product = new Product(productName, price);
			item = new OrderItem(quantity, product.getPrice(), product);
			order.addItem(item);
		}
		
		System.out.println();
		System.out.println("Order summary: ");
		System.out.println(order);
		sc.close();
	}
}
