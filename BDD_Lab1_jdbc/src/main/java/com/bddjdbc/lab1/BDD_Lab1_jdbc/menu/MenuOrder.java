package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.AddressOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.CustomerOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.OrderItemOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Order;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.OrderItem;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Product;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.OrderOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.ProductOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.AddressForCustomer;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Customer;

public class MenuOrder extends Menu implements menuOptions {

	protected Menu rootMenu;
	private OrderOperations orderOperations = new OrderOperations(databaseConnection);
	private CustomerOperations customerOperations = new CustomerOperations(databaseConnection);
	private AddressOperations addressOperations = new AddressOperations(databaseConnection);
	private ProductOperations productOperations = new ProductOperations(databaseConnection);
	private OrderItemOperations orderItemOperations = new OrderItemOperations(databaseConnection);

	private List<Customer> customerList = new ArrayList<Customer>();
	private List<AddressForCustomer> addressCustomerList = new ArrayList<AddressForCustomer>();
	private List<Product> productList = new ArrayList<Product>();
	private List<Order> orderList = new ArrayList<Order>();

	public MenuOrder() {
	}

	public MenuOrder(Menu rootMenu) {
		this.rootMenu = rootMenu;
	}

	public void showMenu() {
		System.out.println("+---------------------------------------+");
		System.out.println("Order Menu");
		System.out.println("+---------------------------------------+");
		while (!rootMenu.isExit()) {
			System.out.println("1)Add Order");
			System.out.println("2)List order for customer");
			System.out.println("3)Customer report");
			System.out.println("4)Back");
			System.out.println("0)Quit");
			choice = getInput(4);
			performeAction(choice);
		}
	}

	private void performeAction(int choice) {
		switch (choice) {
		case 0: // Quit
			rootMenu.setExit(true);
			System.out.println("Session ending Customer");
			break;
		case 1:// Add Order
			System.out.println("+---------------------------------------+");
			System.out.println("Add order");
			System.out.println("+---------------------------------------+");
			// select customer
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer:");
			int idCustomer = scanner.nextInt();
			scanner.nextLine();
			// select Address
			addressCustomerList = addressOperations.getAddressesForCustomer(idCustomer);
			addressOperations.printListOfAddressForCustomersWithId(addressCustomerList);
			System.out.print("Select delivery address:");
			int idAddress = scanner.nextInt();
			scanner.nextLine();
			// create Order
			int idOrderStatus = 5;
			LocalDate localDate = LocalDate.now();
			String currentDate = DateTimeFormatter.ofPattern("dd.MM.YYYY").format(localDate);
			Order newOrder = new Order(idCustomer, idAddress, currentDate, idOrderStatus);
			orderOperations.addOrder(newOrder);
			int idORder = newOrder.getIdOrder();
			// select Product
			boolean exitProductList = false;
			do {
				productList = productOperations.getAllProducts();
				productOperations.printListOfProducts(productList);
				System.out.println("Select product:");
				int idProduct = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Insert quantity:");
				int quantity = scanner.nextInt();
				scanner.nextLine();
				OrderItem orderItem = new OrderItem(idProduct, idORder, quantity);
				orderItemOperations.addOrderItem(orderItem);

				System.out.println("+---------------------------------------+");
				System.out.println("Add another product");
				System.out.println("+---------------------------------------+");
				System.out.println("1)Yes");
				System.out.println("2)No");
				int addProductChoice = getInput(2);
				if (addProductChoice == 2) {
					exitProductList = true;
				}
			} while (!exitProductList);

			break;
		case 2:// List order for customer
			System.out.println("+---------------------------------------+");
			System.out.println("List order for customer");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer to list order:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			orderList = orderOperations.getOrdersForCustomer(idCustomer);
			orderOperations.printListOfOrders(orderList);

			System.out.print("Select Order to see products:");
			int idOrder = scanner.nextInt();
			scanner.nextLine();

			orderList = orderOperations.getOrderProducts(idOrder);
			orderOperations.printOrderProducts(orderList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();

			break;
		case 3:// Customer report
			System.out.println("+---------------------------------------+");
			System.out.println("Customer report");
			System.out.println("+---------------------------------------+");
			customerList = customerOperations.getAllCustomers();
			customerOperations.printListOfCustomers(customerList);
			System.out.print("Select customer to list order:");
			idCustomer = scanner.nextInt();
			scanner.nextLine();
			orderList = orderOperations.getCustomerOrdersReport(idCustomer);
			orderOperations.printCustomerOrder(orderList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 4: // back
			rootMenu.showMenu();
			break;
		}
	}

}
