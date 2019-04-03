package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations.SupplierOperations;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Supplier;

public class MenuSupplier extends Menu implements menuOptions {

	public Menu rootMenu;
	private SupplierOperations supplierOperations = new SupplierOperations(databaseConnection);
	private List<Supplier> supplierList = new ArrayList<Supplier>();
	
	public MenuSupplier() {
	}

	public MenuSupplier(Menu rootMenu) {
		this.rootMenu = rootMenu;
	}

	public void showMenu() {
		System.out.println("+---------------------------------------+");
		System.out.println("supplier Menu");
		System.out.println("+---------------------------------------+");
		while (!rootMenu.isExit()) {
			System.out.println("1)Add Supplier");
			System.out.println("2)Delete Supplier");
			System.out.println("3)Update Supplier");
			System.out.println("4)Supplier List");
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
		case 1:// Add Supplier
			System.out.println("+---------------------------------------+");
			System.out.println("Add Supplier");
			System.out.println("+---------------------------------------+");
			Supplier supplier = new Supplier();
			System.out.print("Name : ");
			supplier.setName(scanner.nextLine());
			System.out.print("Price : ");
			supplier.setPhone(scanner.nextLine());
			supplierOperations.addSupplier(supplier);
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 2:// Delete Supplier
			System.out.println("+---------------------------------------+");
			System.out.println("List of suppliers");
			System.out.println("+---------------------------------------+");
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("+---------------------------------------+");
			System.out.print("Select id to delete supplier:");
			int id = scanner.nextInt();
			scanner.nextLine();
			supplierOperations.dropSupplier(id);
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 3:// Update Supplier
			System.out.println("+---------------------------------------+");
			System.out.println("List of suppliers");
			System.out.println("+---------------------------------------+");
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("+---------------------------------------+");
			System.out.println("Select id update supplier:");
			id = scanner.nextInt();
			scanner.nextLine();
			supplier = new Supplier();
			System.out.print("Name : ");
			supplier.setName(scanner.nextLine());
			System.out.print("Phone : ");
			supplier.setPhone(scanner.nextLine());
			supplierOperations.updateSupplier(id, supplier);
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 4:// Customer Supplier
			System.out.println("+---------------------------------------+");
			System.out.println("List of suppliers");
			System.out.println("+---------------------------------------+");
			supplierList = supplierOperations.getAllSuppliers();
			supplierOperations.printListOfSuppliers(supplierList);
			System.out.println("Press any key to continue...");
			scanner.nextLine();
			break;
		case 5:// back
			rootMenu.showMenu();
			break;
		}
	}
}
