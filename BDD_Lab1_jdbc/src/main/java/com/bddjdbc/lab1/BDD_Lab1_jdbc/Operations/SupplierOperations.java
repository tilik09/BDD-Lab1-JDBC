package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Supplier;

public class SupplierOperations {

	private DatabaseConnection databaseConnection;

	public SupplierOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}
	
	public boolean addSupplier(Supplier supplier) {
		databaseConnection.createConnection();
		String query = "INSERT INTO supplier (name, phone) VALUES (?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, supplier.getName());
			ps.setString(2, supplier.getPhone());
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

	public boolean dropSupplier(int id) {
		databaseConnection.createConnection();
		String query = "DELETE FROM supplier WHERE idSupplier = ?";
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

	public boolean updateSupplier(int id, Supplier supplier) {
		databaseConnection.createConnection();
		String query = "UPDATE Supplier set name = ?, phone = ? where idSupplier = ?;";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, supplier.getName());
			ps.setString(2, supplier.getPhone());
			ps.setInt(3, id);
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

	public List<Supplier> getAllSuppliers() {
		databaseConnection.createConnection();
		String query = "SELECT idSupplier, Name, Phone FROM supplier";
		List<Supplier> supplierList = new ArrayList<Supplier>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("idSupplier"));
				supplier.setName(rs.getString("Name"));
				supplier.setPhone(rs.getString("Phone"));

				supplierList.add(supplier);
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

		return supplierList;
	}

	public void printListOfSuppliers(List<Supplier> supplierList) {
		for (Supplier supplier : supplierList) {
			System.out.println(supplier.toString());
		}
	}
}
