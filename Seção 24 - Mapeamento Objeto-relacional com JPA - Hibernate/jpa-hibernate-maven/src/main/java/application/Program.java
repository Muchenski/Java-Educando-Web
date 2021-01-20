package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import dominio.Pessoa;

public class Program {

	// Para utilizar do JPA, primeiro devemos adicionar como dependência no pom.xml
	// Depois precisamos criar a pasta META-INF no src/main/resources e incluir um
	// arquivo
	// chamado persistence.xml.
	// Neste arquivo, iremos definir a <persistence-unit> e suas propriedades.
	public static void main(String[] args) {

		EntityManager entityManager1 = Persistence.createEntityManagerFactory("exemplo-jpa").createEntityManager();

		Pessoa p1 = entityManager1.find(Pessoa.class, 72);
		Pessoa p2 = entityManager1.find(Pessoa.class, Integer.MIN_VALUE);
		System.out.println(p1); // Pessoa [id=72, nome=Ana Maria, email=ana@gmail.com]
		System.out.println(p2); // null

		System.out.println();

		// Monitoramento de entidades

		// Supondo que queremos apagar um registro do banco de dados:

		try {
			entityManager1.getTransaction().begin();
			entityManager1.remove(new Pessoa(9999, null, null));
			entityManager1.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			// [OUT]

			// Exception Removing a detached instance dominio.Pessoa#72

			// detached : "destacado" em relação ao contexto em que o entityManager está
			// monitorando.
		}

		// Para nós removermos do banco, com o JPA, o objeto deve estar monitorado.

		// Objeto monitorado -> Objeto que acabamos de inserir, ou que acabamos de
		// buscar do banco, onde o entityManager responsável pela inserção/busca ainda
		// não foi fechado.

		// P3 está sendo monitorado pelo entityManager1
		Pessoa p3 = entityManager1.find(Pessoa.class, 71);

		// A partir do momento que fechamos o entityManager1, P3 deixa de ser monitorado
		// e se torna uma instancia destacada.
		entityManager1.close();

		// Iremos tentar remover P3 com um outro entityManager.
		EntityManager entityManager2 = Persistence.createEntityManagerFactory("exemplo-jpa").createEntityManager();
		try {
			entityManager2.getTransaction().begin();
			entityManager2.remove(p3);
			entityManager2.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		entityManager2.close();
		// Irá ocorrer uma exceção novamente, informando que P3 não está monitorado.
		// pois quem monitorava P3 era o entityManager1.
		// Então, o entityManager2 nem conhece P3.
	}

	public static void seedPersons() {

		// Persistence.createEntityManagerFactory(<apelido da persistence-unit, da pasta
		// META-INF>);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
		Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

		// [OUT]

//		Pessoa [id=null, nome=Carlos da Silva, email=carlos@gmail.com]
//		Pessoa [id=null, nome=Joaquim Torres, email=joaquim@gmail.com]
//		Pessoa [id=null, nome=Ana Maria, email=ana@gmail.com]

		try {
			entityManager.getTransaction().begin();

			entityManager.persist(p1);
			entityManager.persist(p2);
			entityManager.persist(p3);

			// Nem foi preciso realizar o commit para que o id fosse retornado do banco.
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);

			// [OUT]

//			Pessoa [id=58, nome=Carlos da Silva, email=carlos@gmail.com]
//			Pessoa [id=59, nome=Joaquim Torres, email=joaquim@gmail.com]
//			Pessoa [id=60, nome=Ana Maria, email=ana@gmail.com]

			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}
