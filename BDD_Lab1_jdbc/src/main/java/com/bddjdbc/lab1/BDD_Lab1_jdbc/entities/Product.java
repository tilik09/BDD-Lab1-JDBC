package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class Product {

	private int id;
	private String name;
	private double price;
	private int quantity;
	private int idSuplier;

	public Product() {

	}

	public Product(String name, double price, int quantity, int idSuplier) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.idSuplier = idSuplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdSuplier() {
		return idSuplier;
	}

	public void setIdSuplier(int idSuplier) {
		this.idSuplier = idSuplier;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", idSuplier="
				+ idSuplier + "]";
	}
}
