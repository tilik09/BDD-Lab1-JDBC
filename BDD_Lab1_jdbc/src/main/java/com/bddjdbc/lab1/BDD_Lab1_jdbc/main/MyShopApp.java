package com.bddjdbc.lab1.BDD_Lab1_jdbc.main;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.menu.Menu;

/**
 * Hello world!
 *
 */
public class MyShopApp {
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		menu.printHeader();
		menu.showMenu();
	
		// System.out.println("Before adding products...");
		// List<Product> productsList = productOperations.getAllProducts();
		// productOperations.printListOfProducts(productsList);

		// System.out.println("Before adding suppliers...");
		// List<Supplier> suppliersList = supplierOperations.getAllSuppliers();
		// supplierOperations.printListOfSuppliers(suppliersList);

		// System.out.println("After update products...");
		// productsList = productOperations.getAllProducts();
		// productOperations.printListOfProducts(productsList);
		// System.out.println("After update suppliers...");
		// suppliersList = supplierOperations.getAllSuppliers();
		// supplierOperations.printListOfSuppliers(suppliersList);

		// Customer customerToUpdate = new Customer("Gavril Andrei",
		// "gavril.andrei1447@gmail.com", "0742869869");
		// customerOperations.updateCustomer(1, customerToUpdate);

		// System.out.println("After update...");
		// customerList = customerOperations.getAllCustomer();
		// customerOperations.printListOfCustomers(customerList);

		// customerOperations.addCustomer(customerToAdd);

		// System.out.println("After adding...");
		// customerList = customerOperations.getAllCustomer();
		// customerOperations.printListOfCustomers(customerList);

		// System.out.println("After deleting...");
		// customerOperations.dropCustomer(5);
		// customerList = customerOperations.getAllCustomer();
		// customerOperations.printListOfCustomers(customerList);
	}
}
