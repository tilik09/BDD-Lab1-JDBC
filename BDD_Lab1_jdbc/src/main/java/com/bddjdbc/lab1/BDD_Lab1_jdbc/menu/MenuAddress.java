package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.AddressOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.CustomerOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Address;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.AddressForCustomer;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Customer;

public class MenuAddress extends MenuCustomer implements menuOptions {

	public MenuCustomer rootMenuCustomer;
	public Menu rootMenu;
	private CustomerOperations customerOperations = new CustomerOperations(databaseConnection);
	private AddressOperations addressOperations = new AddressOperations(databaseConnection);
	private List<Customer> customerList = new ArrayList<Customer>();
	private List<AddressForCustomer> addressCustomerList = new ArrayList<AddressForCustomer>();

	public MenuAddress() {
	}

	public MenuAddress(MenuCustomer rootMenuCustomer) {
		this.rootMenuCustomer = rootMenuCustomer;
		this.rootMenu = rootMenuCustomer.rootMenu;
	}

	public void showMenu() {
		System.out.println("+---------------------------------------+");
		System.out.println("Address Menu");
		System.out.println("+---------------------------------------+");
		while (!rootMenuCustomer.isExit()) {
			System.out.println("1)Add Customer Address");
			System.out.println("2)Delete Customer Address");
			System.out.println("3)Update Customer Address");
			System.out.println("4)List Customer Address");
			System.out.println("5)Back");
			System.out.println("0)Quit");
			choice = getInput(5);
			performeAction(choice);
		}
	}

	private void performeAction(int choice) {
		switch (choice) {
		case 0: // Quit
			rootMenuCustomer.setExit(true);
			rootMenu.setExit(true);
			System.out.println("Session ending Customer");
			break;
		case 1:// Add Address
			System.out.println("+---------------------------------------+");
			System.out.println("Add customer address");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer to add address:");
			Address customerAddress = new Address();
			int idCustomer = scanner.nextInt();
			scanner.nextLine();
			customerAddress.setIdCustomer(idCustomer);
			System.out.println("Address : ");
			customerAddress.setAddress(scanner.nextLine());
			System.out.print("city : ");
			customerAddress.setCity(scanner.nextLine());
			addressOperations.addAddress(customerAddress);
			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomers(addressCustomerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 2:// Delete Customer Address
			System.out.println("+---------------------------------------+");
			System.out.println("Delete customer address");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomersWithId(addressCustomerList);
			System.out.print("Select address to delete:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			addressOperations.dropAddress(idCustomer);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 3:// Update Customer Address
			System.out.println("+---------------------------------------+");
			System.out.println("Update customer address");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomersWithId(addressCustomerList);
			System.out.print("Select address to update:");
			int idAddress = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Address : ");
			Address customerUpdateAddress = new Address();
			customerUpdateAddress.setAddress(scanner.nextLine());
			System.out.print("city : ");
			customerUpdateAddress.setCity(scanner.nextLine());
			addressOperations.updateCustomerAddress(idAddress, customerUpdateAddress);

			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomers(addressCustomerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 4:// List Customer Address
			System.out.println("+---------------------------------------+");
			System.out.println("List customer address");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer to list addresses:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomers(addressCustomerList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 5:// Back
			rootMenuCustomer.showMenu();
			break;
		}
	}

}
