package entities;

public class Printer extends Device implements IPrinter {

	public Printer(String serialNumber) {
		super(serialNumber);
	}

	@Override
	public void print(String doc) {
		System.out.println("Printing: " + doc);
	}

	@Override
	public void processDoc(String doc) {
		System.out.println(this.getClass().getSimpleName() + " processing " + doc);
	}
}
