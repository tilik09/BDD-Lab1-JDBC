package com.bddjdbc.lab1.BDD_Lab1_jdbc.Operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bddjdbc.lab1.BDD_Lab1_jdbc.connections.DatabaseConnection;
import com.bddjdbc.lab1.BDD_Lab1_jdbc.entities.Product;

public class ProductOperations {

	private DatabaseConnection databaseConnection;

	public ProductOperations(DatabaseConnection databaseConnection) {
		super();
		this.databaseConnection = databaseConnection;
	}

	public boolean addProduct(Product product) {
		databaseConnection.createConnection();
		String query = "INSERT INTO product (name, Price, Quantity, Supplier_idSupplier) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setInt(4, product.getIdSuplier());
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

	public boolean dropProductr(int id) {
		databaseConnection.createConnection();
		String query = "DELETE FROM product WHERE idProduct = ?";
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

	public boolean updateProduct(int id, Product product) {
		databaseConnection.createConnection();
		String query = "UPDATE Product set name = ?, price = ?, Quantity = ?, Supplier_idSupplier =? where idProduct = ?;";
		PreparedStatement ps = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setInt(4, product.getIdSuplier());
			ps.setInt(5, id);
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

	public List<Product> getAllProducts() {
		databaseConnection.createConnection();
		String query = "SELECT idProduct, Name, Price, Quantity, Supplier_idSupplier FROM product";
		List<Product> ProductList = new ArrayList<Product>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("idProduct"));
				product.setName(rs.getString("Name"));
				product.setPrice(rs.getDouble("Price"));
				product.setQuantity(rs.getInt("Quantity"));
				product.setIdSuplier(rs.getInt("Supplier_idSupplier"));
				ProductList.add(product);
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

		return ProductList;
	}

	public void printListOfProducts(List<Product> productList) {
		for (Product product : productList) {
			System.out.println(product.toString());
		}
	}
	
	public Product selectProduct(int idSelect) {
		Product product = new Product();
		databaseConnection.createConnection();
		String query = "SELECT idProduct, Name, Price, Quantity, Supplier_idSupplier FROM product WHERE idProduct = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = databaseConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				product.setId(rs.getInt("idProduct"));
				product.setName(rs.getString("Name"));
				product.setPrice(rs.getDouble("Price"));
				product.setQuantity(rs.getInt("Quantity"));
				product.setIdSuplier(rs.getInt("Supplier_idSupplier"));
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
		return product;
	}

}
