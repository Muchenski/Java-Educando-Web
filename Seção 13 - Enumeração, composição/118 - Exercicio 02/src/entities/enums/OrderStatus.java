package entities.enums;

public enum OrderStatus {

	PENDING_PAYMENT(0, "Pending"), PROCESSING(1, "Processing"), SHIPPED(2, "Shipped"), DELIVERED(3, "Delivered");

	private Integer code;
	private String description;

	private OrderStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static OrderStatus toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (OrderStatus status : OrderStatus.values()) {
			if (code.equals(status.getCode())) {
				return status;
			}
		}

		throw new IllegalArgumentException("Invalid id: " + code);
	}

}
