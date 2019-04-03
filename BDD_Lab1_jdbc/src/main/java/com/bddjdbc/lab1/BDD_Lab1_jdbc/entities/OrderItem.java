package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class OrderItem {

	private int idOrderItem;
	private int productId;
	private int orderId;
	private int Quantity;

	public OrderItem() {

	}

	public OrderItem(int productId, int orderId, int quantity) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		Quantity = quantity;
	}

	public int getIdOrder() {
		return idOrderItem;
	}

	public void setIdOrder(int idOrder) {
		this.idOrderItem = idOrder;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + idOrderItem + ", productId=" + productId + ", orderId=" + orderId + ", Quantity=" + Quantity + "]";
	}
}
