import java.util.Map;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {

		// • Principais implementações:

		// • HashMap - mais rápido (operações O(1) em tabela hash) e não ordenado

		// • TreeMap - mais lento (operações O(log(n)) em árvore rubro-negra) e ordenado
		// pelo compareTo do objeto (ou Comparator)

		// • LinkedHashMap - velocidade intermediária e elementos na ordem em que são
		// adicionados

		// • put(key, value), remove(key), containsKey(key), get(key)
		// • Baseado em equals e hashCode
		// • Se equals e hashCode não existir, é usada comparação de ponteiros
		// • clear()
		// • size()
		// • keySet() - retorna um Set<K>
		// • values() - retornaa um Collection<V>

		// Como String implementa Comparable, podemos utilizar o TreeMap<K,V>.
		Map<String, String> cookies = new TreeMap<String, String>();

		// put(chave, valor);
		cookies.put("username", "Maria");
		cookies.put("email", "maria@gmail.com");
		cookies.put("phone", "4199241439");

		// remove(chave);
		cookies.remove("email");

		System.out.println("ALL COOKIES: ");
		for (String key : cookies.keySet()) {
			System.out.println(key + ": " + cookies.get(key));
		}
	}

}
