package com.bddjdbc.lab1.BDD_Lab1_jdbc.entities;

public class AddressForCustomer {

	private String name;
	private String address;
	private String city;
	private int idAddress;

	public AddressForCustomer() {
	
	}
	public AddressForCustomer(String name, String address, String city, int idAddress) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.idAddress = idAddress;
	}

	public int getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Override
	public String toString() {
		return "Address [" + name + ", " + address + ", " + city + "]";
	}


}
