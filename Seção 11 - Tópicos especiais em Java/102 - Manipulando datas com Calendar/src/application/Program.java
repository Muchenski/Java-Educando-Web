package application;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date d1 = Date.from(Instant.parse("2018-06-25T15:42:05Z"));
		System.out.println(sdf.format(d1));

		// Acrescentando horas na data
		Calendar calendar = Calendar.getInstance();
		// Atribuindo o valor da data, dentro do nosso Calendar.
		calendar.setTime(d1);
		// Adicionando 4 horas à nossa data do Calendar.
		calendar.add(Calendar.HOUR_OF_DAY, 4);

		// Retornando uma data com os valores alterados.
		d1 = calendar.getTime();

		System.out.println(sdf.format(d1));

		System.out.println();

		///////////////////////////////////////////////////////////////////////
		// Obtendo uma unidade de tempo a partir de uma data.

		// Obtendo os minutos.
		int minutos = calendar.get(Calendar.MINUTE);
		System.out.println(minutos + " minutos");

		// Obtendo o mes.
		int mes = calendar.get(Calendar.MONTH);
		System.out.println("mes " + mes);
		
		// ATENÇÃO: O Calendar considera que o Mês inicial é 0.
		// Portanto, devemos somar + 1 para deixarmos de acordo com o
		// nosso calendário, de fato.
		
		mes = 1 + calendar.get(Calendar.MONTH);
		System.out.println("mes " + mes);
	}

}
