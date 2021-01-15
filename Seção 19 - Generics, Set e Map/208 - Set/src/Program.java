import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Program {

	public static void main(String[] args) {

		// Set<T> é uma interface.

		// Set<T> não admite repetições.
		// Não possuem posições(podem possuir ordenamento).
		// Acesso, inserção e remoção de elementos são rápidos

		// Principais implementações:

		// • HashSet - mais rápido (operações O(1) em tabela hash) e não ordenado

		// • TreeSet - mais lento (operações O(log(n)) em árvore rubro-negra) e ordenado
		// pelo compareTo do objeto (ou Comparator)

		// • LinkedHashSet - velocidade intermediária e elementos na ordem em que são
		// adicionados

		/*
		 * Alguns métodos importantes
		 * 
		 * • add(obj), remove(obj), contains(obj) • Baseado em equals e hashCode • Se
		 * equals e hashCode não existir, é usada comparação de ponteiros.
		 * 
		 * • clear() • size() • removeIf(predicate)
		 * 
		 * • addAll(other) - união: adiciona no conjunto os elementos do outro conjunto,
		 * sem repetição • retainAll(other) - interseção: remove do conjunto os
		 * elementos não contitos em other • removeAll(other) - diferença: remove do
		 * conjunto os elementos contidos em other
		 */

		// HashSet - Desordenado, mais rápido.
		Set<String> set = new HashSet<String>();
		set.add("TV");
		set.add("Notebook");
		set.add("Tablet");
		System.out.println(set.contains("Notebook"));
		for (String p : set) {
			System.out.println(p);
		}

		// TreeSet - Ordenado, mais lento.
		Set<Integer> a = new TreeSet<Integer>(Arrays.asList(0, 2, 4, 5, 6, 8, 10));
		Set<Integer> b = new TreeSet<Integer>(Arrays.asList(5, 6, 7, 8, 9, 10));
		// union
		Set<Integer> c = new TreeSet<Integer>(a);
		c.addAll(b);
		System.out.println(c);
		// intersection
		Set<Integer> d = new TreeSet<Integer>(a);
		d.retainAll(b);
		System.out.println(d);
		// difference
		Set<Integer> e = new TreeSet<Integer>(a);
		e.removeAll(b);
		System.out.println(e);

		// OBS:
		// Para utilizarmos o TreeSet<T>, T deve implementar a interface Comparable<T>.
	}
}
