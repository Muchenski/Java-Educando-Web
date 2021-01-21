package com.muchenski.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.muchenski.course.domain.Category;
import com.muchenski.course.domain.Order;
import com.muchenski.course.domain.OrderItem;
import com.muchenski.course.domain.Payment;
import com.muchenski.course.domain.Product;
import com.muchenski.course.domain.User;
import com.muchenski.course.domain.enums.OrderStatus;
import com.muchenski.course.repositories.CategoryRepository;
import com.muchenski.course.repositories.OrderItemRepository;
import com.muchenski.course.repositories.OrderRepository;
import com.muchenski.course.repositories.ProductRepository;
import com.muchenski.course.repositories.UserRepository;

// Classe configurada para testes.
@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		// Tudo que estiver nos métodos com inicio "get", nas classes, serão retornados
		// no JSON.

		// Como o JoinTable entre Product x Caetegory está na classe Product
		// Adicionar a Categoria ao Produto sim irá resultar em inserções
		// no banco de dados.

		// Com o JoinColumn é a mesma coisa.
		// O JPA realiza a associação com as informações recebidas na entidade que
		// possui
		// @JoinColumn/@JoinTable na própria classe.

		// Associações 1 para (0,1), que possui um objeto X, e um objeto Y dependente
		// nós não chamamos um repository do Y para realizar as alterações no banco.
		// Nós realizamos a associação desse Y ao X e atualizamos X.

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
		u1.addPhone("988888888");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "123456");
		u2.addPhone("977777777");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

		OrderItem oi1 = new OrderItem(o1, p1, 2);
		OrderItem oi2 = new OrderItem(o1, p3, 1);
		OrderItem oi3 = new OrderItem(o2, p3, 2);
		OrderItem oi4 = new OrderItem(o3, p5, 2);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), o1);
		o1.setPayment(pay1);
		orderRepository.save(o1);
	}
}
