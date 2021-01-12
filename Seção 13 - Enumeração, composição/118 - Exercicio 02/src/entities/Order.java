package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Date moment;
	private Integer status;
	private Client client;

	private List<OrderItem> items = new ArrayList<OrderItem>();

	public Order() {
	}

	public Order(Date moment, Integer status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Double getTotal() {
		double total = 0;
		for (OrderItem orderItem : items) {
			total += orderItem.subTotal();
		}
		return total;
	}

	public void setStatus(OrderStatus status) {
		this.status = status.getCode();
	}

	public OrderStatus getStatus() {
		return OrderStatus.toEnum(status);
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Order moment: " + sdf.format(moment));
		sb.append("\nOrder status: " + getStatus());
		sb.append("\nClient: " + client);
		sb.append("\nOrder items: \n");
		for (OrderItem orderItem : items) {
			sb.append(orderItem + "\n");
		}
		sb.append("Total price: " + String.format("$%.2f", getTotal()));
		return sb.toString();
	}
}
