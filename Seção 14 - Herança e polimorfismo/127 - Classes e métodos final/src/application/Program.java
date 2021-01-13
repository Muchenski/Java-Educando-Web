package application;

public class Program {

	public static void main(String[] args) {

		// A palavra 'final', aplicada a classes, evita que a classe seja herdada.
		// Quando aplicada a métodos, evita que o método seja sobreposto.

		// Neste exemplo:

		// Queremos evitar que BusinessAccount seja herdada.

		// Também queremos que o método 'withdraw', da SavingsAccount, não possa
		// ser sobrescrito por alguma SubClasse dela.

		// Explicações

		// Geralmente convém acrescentar 'final' em métodos sobrepostos, pois
		// sobreposições múltiplas são uma porta de entrada para inconsistências.

		// Atributos de um tipo de classe 'final' são analisados de maneira mais
		// veloz em tempo de execução.
	}

}
