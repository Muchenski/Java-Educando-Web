package entities;

public class ComboDevice extends Device implements IPrinter, IScanner {

	public ComboDevice(String serialNumber) {
		super(serialNumber);
	}

	@Override
	public String scan() {
		return "Scanned content (ComboDevice)";
	}

	@Override
	public void print(String doc) {
		System.out.println("Printing: " + doc + " (ComboDevice)");
	}

	@Override
	public void processDoc(String doc) {
		System.out.println(this.getClass().getSimpleName() + " processing " + doc);
	}

}
