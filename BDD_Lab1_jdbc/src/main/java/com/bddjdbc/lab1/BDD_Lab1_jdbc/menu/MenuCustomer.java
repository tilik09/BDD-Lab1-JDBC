package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.CustomerOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Customer;

public class MenuCustomer extends Menu implements menuOptions {

	protected Menu rootMenu;
	private CustomerOperations customerOperations = new CustomerOperations(databaseConnection);
	private List<Customer> customerList = new ArrayList<Customer>();

	public MenuCustomer() {
	}

	public MenuCustomer(Menu rootMenu) {
		this.rootMenu = rootMenu;
	}

	public void showMenu() {
		System.out.println("+---------------------------------------+");
		System.out.println("Customer Menu");
		System.out.println("+---------------------------------------+");
		while (!rootMenu.isExit()) {
			System.out.println("1)Add Customer");
			System.out.println("2)Delete Customer");
			System.out.println("3)Update Customer");
			System.out.println("4)Customer List");
			System.out.println("5)Customer Address");
			System.out.println("6)Back");
			System.out.println("0)Quit");
			choice = getInput(6);
			performeAction(choice);
		}
	}

	private void performeAction(int choice) {
		switch (choice) {
		case 0: // Quit
			rootMenu.setExit(true);
			System.out.println("Session ending Customer");
			break;
		case 1:// Add Customer
			System.out.println("+---------------------------------------+");
			System.out.println("Add Customer");
			System.out.println("+---------------------------------------+");
			Customer customer = new Customer();
			System.out.print("Name : ");
			customer.setName(scanner.nextLine());
			System.out.print("Email : ");
			customer.setEmail(scanner.nextLine());
			System.out.print("Phone : ");
			customer.setPhone(scanner.nextLine());
			customerOperations.addCustomer(customer);
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 2:// Delete Customer
			System.out.println("+---------------------------------------+");
			System.out.println("List of customers");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("+---------------------------------------+");
			System.out.print("Select id to delete customer:");
			int id = scanner.nextInt();
			scanner.nextLine();
			customerOperations.dropCustomer(id);
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();

			break;
		case 3:// Update Customer
			System.out.println("+---------------------------------------+");
			System.out.println("List of customers");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("+---------------------------------------+");
			System.out.println("Select id update customer:");
			id = scanner.nextInt();
			scanner.nextLine();
			customer = new Customer();
			System.out.println("Name : ");
			customer.setName(scanner.nextLine());
			System.out.print("Email : ");
			customer.setEmail(scanner.nextLine());
			System.out.print("Phone : ");
			customer.setPhone(scanner.nextLine());
			customerOperations.updateCustomer(id, customer);
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 4:// Customer List
			System.out.println("+---------------------------------------+");
			System.out.println("List of customers");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 5:// Customer Address
			MenuAddress menuAddress = new MenuAddress(this);
			menuAddress.showMenu();
			break;
		case 6:// back
			rootMenu.showMenu();
			break;
		}
	}

}
