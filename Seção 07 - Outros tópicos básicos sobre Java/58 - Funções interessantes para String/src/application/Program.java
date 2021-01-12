package application;

public class Program {

	public static void main(String[] args) {
		
		String name = "Henrique Muchenski";
		
		// IndexOf() e lastIndexOf() é um método case-sensitive
		// Caso estes não encontrem o que tentamos buscar, retornarão -1.
		System.out.println("Primeira ocorrência de 'Mu': " + name.indexOf("Mu"));
		System.out.println("Retorno de busca sem respostas: " + name.indexOf("x"));
		
		System.out.println("Primeira ocorrência de 'Mu': " + name.lastIndexOf("i"));
		System.out.println("Retorno de busca sem respostas: " + name.lastIndexOf("x"));
	}

}
