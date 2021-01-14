package entities;

public class Scanner extends Device implements IScanner {

	public Scanner(String serialNumber) {
		super(serialNumber);
	}

	@Override
	public String scan() {
		return "Scanned content";
	}

	@Override
	public void processDoc(String doc) {
		System.out.println(this.getClass().getSimpleName() + " processing " + doc);
	}

}
