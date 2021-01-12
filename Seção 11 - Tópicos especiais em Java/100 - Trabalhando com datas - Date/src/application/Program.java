package application;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));

		try {
			Date y1 = sdf1.parse("25/06/2020");
			Date y2 = sdf2.parse("25/06/2020 15:42:07");

			// Horário atual.
			Date x1 = new Date();

			// Horário atual do sistema.
			Date x2 = new Date(System.currentTimeMillis());

			// Date() também pode receber valores do tipo Long, em milisegundos.
			Date x3 = new Date(0L); // Wed Dec 31 21:00:00 BRT 1969 (Considerando o UTC - 3 do Brasil)

			
			//////////////////////////////////////////////////////////////////////////////////////////
			// No formato ISO 8601 padrão UTC
			Date x4 = Date.from(Instant.parse("2018-06-25T15:42:07Z"));

			// A saída deste print será 3 horas a menos da data especificada no
			// Instant.parse(), pois estamos há - 3 horas do UTC padrão.
			System.out.println(x4); // Mon Jun 25 12:42:07 BRT 2018

			// Porém, resolvemos isto com um SimpleDateFormat com localidade setada
			// como UTC.
			System.out.println(sdf3.format(x4));
			//////////////////////////////////////////////////////////////////////////////////////////

			
			System.out.println();

			System.out.println(y1);
			System.out.println(y2);

			System.out.println();

			System.out.println(sdf1.format(y1));
			System.out.println(sdf2.format(y2));

			System.out.println();

			System.out.println(x1);
			System.out.println(x2);
			System.out.println(x3);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
