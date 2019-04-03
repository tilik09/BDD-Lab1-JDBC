package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Address;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.AddressForCustomer;

public class AddressOperations {
	private DatabaseConnection databaseConnection;

	public AddressOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}

	public boolean addAddress(Address address) {
		databaseConnection.createConnection();
		String query = "INSERT INTO address (Customer_idCustomer, Address, City) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, address.getIdCustomer());
			ps.setString(2, address.getAddress());
			ps.setString(3, address.getCity());
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

	public boolean dropAddress(int id) {
		databaseConnection.createConnection();
		String query = "DELETE FROM address WHERE idAddress = ?";
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

	public boolean updateCustomerAddress(int idCustomer, Address address) {
		databaseConnection.createConnection();
		String query = "UPDATE Address set Address = ?, City = ? where idAddress = ?;";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, address.getAddress());
			ps.setString(2, address.getCity());
			ps.setInt(3, idCustomer);
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

	public List<AddressForCustomer> getAddressesForCustomer(int id) {
		databaseConnection.createConnection();
		String query = "SELECT customer.name, address.Address, address.City, address.idAddress"
				+ " FROM address INNER JOIN  customer ON address.Customer_idCustomer=customer.idCustomer WHERE customer.idCustomer = ?";
		List<AddressForCustomer> customerAddressList = new ArrayList<AddressForCustomer>();
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				AddressForCustomer addressForCustomer = new AddressForCustomer(rs.getString("name"),
						rs.getString("Address"), rs.getString("City"), rs.getInt("idAddress"));
				customerAddressList.add(addressForCustomer);
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

		return customerAddressList;
	}

	public List<Address> getAllCustomersAddress() {
		databaseConnection.createConnection();
		String query = "SELECT idAddress, Customer_idCustomer, Address, City FROM address";
		List<Address> addressList = new ArrayList<Address>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("idAddress"));
				address.setIdCustomer(rs.getInt("Customer_idCustomer"));
				address.setAddress(rs.getString("Address"));
				address.setCity(rs.getString("City"));
				addressList.add(address);
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

		return addressList;
	}

	public void printListOfAddress(List<Address> addressList) {
		for (Address address : addressList) {
			System.out.println(address.toString());
		}
	}

	public void printListOfAddressForCustomers(List<AddressForCustomer> addressList) {
		for (AddressForCustomer address : addressList) {
			System.out.println(address.toString());
		}
	}
	
	public void printListOfAddressForCustomersWithId(List<AddressForCustomer> addressList) {
		for (AddressForCustomer address : addressList) {
			System.out.println("[id=" + address.getIdAddress() + ", " + address.getName() + ", " + address.getAddress() + ", " +  address.getCity() + "]");
		}
	}
}
