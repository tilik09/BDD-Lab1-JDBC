package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Order;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.OrderItem;

public class OrderItemOperations {

	private DatabaseConnection databaseConnection;

	public OrderItemOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}

	public boolean addOrderItem(OrderItem orderItem) {
		databaseConnection.createConnection();
		String query = "INSERT INTO `order item` (Product_idProduct, Order_idOrder, Quantity) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, orderItem.getProductId());
			ps.setInt(2, orderItem.getOrderId());
			ps.setInt(3, orderItem.getQuantity());
			ps.executeUpdate();
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

	public List<OrderItem> getAllOrderItems() {
		databaseConnection.createConnection();
		String query = "SELECT Product_idProduct, Order_idOrder, Quantity FROM orderItem";
		List<OrderItem> orderList = new ArrayList<OrderItem>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(rs.getInt("Product_idProduct"));
				orderItem.setOrderId(rs.getInt("Order_idOrder"));
				orderItem.setQuantity(rs.getInt("Quantity"));

				orderList.add(orderItem);
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

	public void printListOfCustomers(List<Order> orderList) {
		for (Order order : orderList) {
			System.out.println(order.printCustomerOrder());
		}
	}
	
	public void printOrdersForCustomer(List<Order> orderList) {
		for (Order order : orderList) {
			System.out.println(order.toString());
		}
	}

}
