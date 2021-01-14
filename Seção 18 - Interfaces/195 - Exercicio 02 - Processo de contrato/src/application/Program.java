package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contrato;
import model.entities.ParcelaContrato;
import model.services.ContratoServico;
import model.services.PayPalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Contrato contrato = new Contrato();

		System.out.println("Entre com os dados do contrato: ");

		System.out.print("Numero: ");
		contrato.setNumero(sc.nextInt());

		sc.nextLine();
		System.out.print("Data: ");
		contrato.setData(sdf.parse(sc.nextLine()));

		System.out.print("Valor: ");
		contrato.setTotalValue(sc.nextDouble());

		System.out.print("Numero de parcelas: ");
		int parcelas = sc.nextInt();

		ContratoServico servico = new ContratoServico(new PayPalService());
		servico.processaContrato(contrato, parcelas);

		System.out.println("Parcelas: ");
		for (ParcelaContrato parcela : contrato.getParcelas()) {
			System.out.println(sdf.format(parcela.getDate()) + " - " + parcela.getAmount());
		}

		sc.close();
	}

}
