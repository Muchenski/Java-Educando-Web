package entities;

public class Circle extends AbstractShape {

	private Double radius;

	public Circle() {
	}

	public Circle(Integer cor, Double radius) {
		super(cor);
		this.radius = radius;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	@Override
	public Double area() {
		return Math.PI * (radius * radius);
	}

}
