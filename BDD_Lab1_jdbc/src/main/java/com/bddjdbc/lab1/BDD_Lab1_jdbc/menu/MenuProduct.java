package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.ProductOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Product;

public class MenuProduct extends Menu implements menuOptions {

	public Menu rootMenu;
	private ProductOperations productOperations = new ProductOperations(databaseConnection);
	private List<Product> productList = new ArrayList<Product>();

	public MenuProduct() {
	}

	public MenuProduct(Menu rootMenu) {
		this.rootMenu = rootMenu;
	}

	public void showMenu() {
		System.out.println("+---------------------------------------+");
		System.out.println("Product Menu");
		System.out.println("+---------------------------------------+");
		while (!rootMenu.isExit()) {
			System.out.println("1)Add Product");
			System.out.println("2)Delete Product");
			System.out.println("3)Update Product");
			System.out.println("4)Product List");
			System.out.println("5)Back");
			System.out.println("0)Quit");
			int choice = getInput(5);
			performeAction(choice);
		}
	}

	void performeAction(int choice) {
		switch (choice) {
		case 0: // Quit
			rootMenu.setExit(true);
			System.out.println("Session ending");
			break;
		case 1:// Add Product
			System.out.println("+---------------------------------------+");
			System.out.println("Add Product");
			System.out.println("+---------------------------------------+");
			Product product = new Product();
			System.out.print("Name : ");
			product.setName(scanner.nextLine());
			System.out.print("Price : ");
			product.setPrice(scanner.nextDouble());
			scanner.nextLine();
			System.out.print("Quantity : ");
			product.setQuantity(scanner.nextInt());
			System.out.print("idSupplier : ");
			product.setIdSuplier(scanner.nextInt());
			scanner.nextLine();
			productOperations.addProduct(product);
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 2:// Delete Product
			System.out.println("+---------------------------------------+");
			System.out.println("List of Products");
			System.out.println("+---------------------------------------+");
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("+---------------------------------------+");
			System.out.print("Select id to delete product:");
			int id = scanner.nextInt();
			scanner.nextLine();
			productOperations.dropProductr(id);
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 3:// Update Product
			System.out.println("+---------------------------------------+");
			System.out.println("List of products");
			System.out.println("+---------------------------------------+");
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("+---------------------------------------+");
			System.out.println("Select id update product:");
			id = scanner.nextInt();
			scanner.nextLine();
			product = new Product();
			System.out.print("Name : ");
			product.setName(scanner.nextLine());
			System.out.print("Price : ");
			product.setPrice(scanner.nextDouble());
			scanner.nextLine();
			System.out.print("Quantity : ");
			product.setQuantity(scanner.nextInt());
			System.out.print("idSupplier : ");
			product.setIdSuplier(scanner.nextInt());
			scanner.nextLine();
			productOperations.updateProduct(id, product);
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 4:// Product List
			System.out.println("+---------------------------------------+");
			System.out.println("List of products");
			System.out.println("+---------------------------------------+");
			productList = productOperations.getAllProducts();
			productOperations.printListOfProducts(productList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 5:// back
			rootMenu.showMenu();
			break;
		}
	}
}
