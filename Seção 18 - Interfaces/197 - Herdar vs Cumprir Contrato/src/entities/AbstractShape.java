package entities;

import entities.enums.Cor;

public abstract class AbstractShape implements IShape {

	private Integer cor;

	public AbstractShape() {
	}

	public AbstractShape(Integer cor) {
		this.cor = cor;
	}

	public Cor getCor() {
		return Cor.toEnum(cor);
	}

	public void setCor(Cor cor) {
		this.cor = cor.getCode();
	}

}
