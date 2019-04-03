package com.bddjdbc.lab1.BDD_Lab1_jdbc.menu;

import java.util.Scanner;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.Database;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;

public class Menu implements menuOptions {

	private Database database = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/bdd__lab1jdbc",
			"root", "root");
	protected DatabaseConnection databaseConnection = new DatabaseConnection(database);
	protected boolean exit = false;

	protected Scanner scanner = new Scanner(System.in);
	protected int choice;

	public Menu() {

	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public void printHeader() {
		System.out.println("+---------------------------------------+");
		System.out.println("|		Welcome			|");
		System.out.println("+---------------------------------------+");
	}

	protected int getInput(int maxInput) {
		int choice = -1;
		while (choice < 0 || choice > maxInput) {
			try {
				System.out.println("\nPlease enter your choice:");
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 0 || choice > maxInput) {
					System.out.println("Invalid selection. Please try again");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection. Please try again");
			}
		}
		return choice;
	}

	public void showMenu() {
		while (!exit) {
			System.out.println("+---------------------------------------+");
			System.out.println("First Menu");
			System.out.println("+---------------------------------------+");
			System.out.println("1)Customer");
			System.out.println("2)Product");
			System.out.println("3)Supplier");
			System.out.println("4)Order");
			System.out.println("0)Quit");
			choice = getInput(4);
			performeAction();
		}

	}

	public void performeAction() {
		switch (choice) {
		case 0:
			exit = true;
			System.out.println("Session ending Menu");
			break;
		case 1:
			MenuCustomer menuCustomer = new MenuCustomer(this);
			menuCustomer.showMenu();
			break;
		case 2:
			MenuProduct menuProduc = new MenuProduct(this);
			menuProduc.showMenu();
			break;
		case 3:
			MenuSupplier menuSupplier = new MenuSupplier(this);
			menuSupplier.showMenu();
			break;
		case 4:
			MenuOrder menuOrder = new MenuOrder(this);
			menuOrder.showMenu();
			break;
		}
	}

}
