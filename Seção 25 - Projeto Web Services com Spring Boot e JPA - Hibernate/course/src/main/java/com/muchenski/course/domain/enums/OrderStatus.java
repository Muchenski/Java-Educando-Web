package com.muchenski.course.domain.enums;

public enum OrderStatus {

	WAITING_PAYMENT(0, "Waiting Payment"), 
	PAID(1, "Paid"), 
	SHIPPED(2, "Shipped"), 
	DELIVERED(3, "Delivered"),
	CANCELED(4, "Canceled");
	
	private Integer code;
	private String description;
	
	private OrderStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static OrderStatus toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(OrderStatus orderStatus : OrderStatus.values()) {
			if(code.equals(orderStatus.getCode())) {
				return orderStatus;
			}
		}

		throw new IllegalArgumentException("Invalid Order Status code -> " + code);
	}
}
