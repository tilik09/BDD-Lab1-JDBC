package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class Supplier {
	private int id;
	private String name;
	private String phone;

	public Supplier() {
	}

	public Supplier(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}

}
