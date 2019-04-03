package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class Address {

	private int id;
	private String address;
	private String city;
	private int idCustomer;

	public Address() {

	}

	public Address(String address, String city, int idCustomer) {
		super();
		this.address = address;
		this.city = city;
		this.idCustomer = idCustomer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", idCustomer=" + idCustomer + ", address=" + address + ", city=" + city + "]";
	}

}
