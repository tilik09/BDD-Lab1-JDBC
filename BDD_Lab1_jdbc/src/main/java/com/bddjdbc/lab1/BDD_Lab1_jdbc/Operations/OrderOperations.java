package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Order;

public class OrderOperations {
	private DatabaseConnection databaseConnection;

	public OrderOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}

	public boolean addOrder(Order order) {
		databaseConnection.createConnection();
		String query = "INSERT INTO `order` (Customer_idCustomer, Address_idAddress, Date, Status_idStatus) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getIdCustomer());
			ps.setInt(2, order.getIdAddress());
			ps.setString(3, order.getDate());
			ps.setInt(4, 5);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order.setIdOrder(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
			return false;
		} finally {
			try {
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}
		return true;
	}

	public List<Order> getCustomerOrdersReport(int id) {
		databaseConnection.createConnection();
		String query = "SELECT `order`.idOrder, `order item`.idOrderItem, `customer`.name, `product`.Name , `product`.Price, `order item`.quantity , `order`.Date, `status`.`Type` \r\n"
				+ "FROM customer\r\n" + "INNER JOIN `order` ON `order`.Customer_idCustomer = customer.idCustomer\r\n"
				+ "INNER JOIN `order item` ON `order item`.Order_idOrder = `order`.idOrder\r\n"
				+ "INNER JOIN `status` ON `status`.idStatus = `order`.Status_idStatus\r\n"
				+ "INNER JOIN `product` ON `order item`.Product_idProduct = `product`.idProduct WHERE customer.idCustomer = ?";
		List<Order> orderList = new ArrayList<Order>();
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setIdOrder(rs.getInt("order.idOrder"));
				order.setOrderItemId(rs.getInt("order item.idOrderItem"));
				order.setCustomerName(rs.getString("customer.name"));
				order.setProductPrice(rs.getDouble("product.price"));
				order.setProductName(rs.getString("product.name"));
				order.setOrderItemQuantity(rs.getInt("order item.quantity"));
				order.setDate(rs.getString("order.Date"));
				order.setOrderStatus(rs.getString("status.Type"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return orderList;
	}

	public List<Order> getOrdersForCustomer(int id) {
		databaseConnection.createConnection();
		String query = "SELECT `order`.idOrder, `order`.Date, `status`.`Type` FROM `order` \r\n" + 
				"INNER JOIN `status` ON `status`.idStatus = `order`.Status_idStatus\r\n" + 
				"WHERE Customer_idCustomer = ?";
		List<Order> orderList = new ArrayList<Order>();
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setIdOrder(rs.getInt("order.idOrder"));
				order.setDate(rs.getString("order.Date"));
				order.setOrderStatus(rs.getString("status.Type"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return orderList;
	}
	
	public List<Order> getOrderProducts(int id) {
		databaseConnection.createConnection();
		String query = "SELECT `order`.idOrder, `order item`.idOrderItem, `product`.Name , `product`.Price, `order item`.quantity , `order`.Date\r\n" + 
				"FROM `order`\r\n" + 
				"INNER JOIN `order item` ON `order item`.Order_idOrder = `order`.idOrder\r\n" + 
				"INNER JOIN `product` ON `order item`.Product_idProduct = `product`.idProduct\r\n" + 
				"WHERE `order`.idOrder=?";
		List<Order> orderList = new ArrayList<Order>();
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setIdOrder(rs.getInt("order.idOrder"));
				order.setOrderItemId(rs.getInt("order item.idOrderItem"));
				order.setProductName(rs.getString("product.name"));
				order.setProductPrice(rs.getDouble("product.price"));
				order.setOrderItemQuantity(rs.getInt("order item.quantity"));
				order.setDate(rs.getString("order.Date"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			System.err.println("Error when creating query: " + e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				databaseConnection.getConnection().close();
			} catch (SQLException e) {
				System.err.println("Failed closing streams: " + e.getMessage());
			}
		}

		return orderList;
	}

	public void printListOfOrders(List<Order> orderList) {
		for (Order order : orderList) {
			System.out.println(order.toString());
		}
	}

	public void printCustomerOrder(List<Order> orderList) {
		for (Order order : orderList) {
			System.out.println(order.printCustomerOrder());
		}
	}
	
	public void printOrderProducts(List<Order> orderList) {
		for (Order order : orderList) {
			System.out.println(order.printOrderProducts());
		}
	}
}
