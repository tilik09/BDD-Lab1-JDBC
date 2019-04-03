package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class Order {

	private int idOrder;
	private int idCustomer;
	private int idAddress;
	private String Date;
	private int idStatus;

	private int orderItemId;
	private OrderItem orderItem;
	private String customerId;
	private String customerName;
	private String productName;
	private double productPrice;

	public Order() {

	}

	public Order(int idCustomer, int idAddress, String date, int idStatus) {
		super();
		this.idCustomer = idCustomer;
		this.idAddress = idAddress;
		Date = date;
		this.idStatus = idStatus;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	private int orderItemQuantity;
	private String orderStatus;

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + idOrder + ", Date=" + Date
				+ ", " + orderStatus + "]";
	}

	public String printCustomerOrder() {
		return "CustomerOrder [id=" + idOrder + ", " + orderItemId + ", " + customerName + ", " + productName + ", "
				+ productPrice + ", " + orderItemQuantity + ", " + Date + ", " + orderStatus + "]";
	}

	public String printOrderProducts() {
		return "OrderProducts [id=" + idOrder + ", " + orderItemId +  ", " + productName + ", "	+ productPrice + ", " + orderItemQuantity + ", " + Date + "]";
	}

}
