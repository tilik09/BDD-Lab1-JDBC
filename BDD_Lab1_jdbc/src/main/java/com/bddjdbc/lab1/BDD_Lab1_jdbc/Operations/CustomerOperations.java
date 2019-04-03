package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Customer;

public class CustomerOperations {

	private DatabaseConnection databaseConnection;

	public CustomerOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}

	public boolean addCustomer(Customer customer) {
		databaseConnection.createConnection();
		String query = "INSERT INTO customer (name, email, phone) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());
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

	public boolean dropCustomer(int id) {
		databaseConnection.createConnection();
		String query = "DELETE FROM customer WHERE idCustomer = ?";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
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

	public boolean updateCustomer(int id, Customer customer) {
		databaseConnection.createConnection();
		String query = "UPDATE Customer set name = ?, email = ?, phone = ? where idCustomer = ?;";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setString(3, customer.getPhone());
			ps.setInt(4, id);
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

	public List<Customer> getAllCustomers() {
		databaseConnection.createConnection();
		String query = "SELECT idCustomer, Name, Email, Phone FROM customer";
		List<Customer> customerList = new ArrayList<Customer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("idCustomer"));
				customer.setName(rs.getString("Name"));
				customer.setEmail(rs.getString("Email"));
				customer.setPhone(rs.getString("Phone"));

				customerList.add(customer);
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

		return customerList;
	}

	public void printListOfCustomers(List<Customer> customerList) {
		for (Customer customer : customerList) {
			System.out.println(customer.toString());
		}
	}
}
