package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Product;

public class ProductDbSql implements ProductDb {
	private Properties properties = new Properties();
	private String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/DBNAAM?currentSchema=SCHEMA";

	public ProductDbSql() {
		properties.setProperty("user", "USER");
		properties.setProperty("password", "PASSWORD");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public Product get(int productId) {
		Product product = null;
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();) {
			ResultSet result = statement.executeQuery("SELECT * FROM product WHERE productId = " + productId);

			if (result.next()) {
				product = createProductObject(result);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return product;
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();

		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();)
		{
			ResultSet result = statement.executeQuery("SELECT * FROM product");

			while (result.next()) {
				Product product = createProductObject(result);
				products.add(product);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return products;
	}

	@Override
	public void add(Product product) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();) {
			statement.execute("INSERT INTO product (name, description, price) VALUES ('" 
				+ product.getName() + "','" 
					+ product.getDescription() + "'," 
				+ product.getPrice() + ")");
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Product product) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();) {
			String sql = "UPDATE product SET description='" 
						+ product.getDescription() + "', price=" 
							+ product.getPrice() + ", name='" 
						+ product.getName() + "' WHERE productid=" + product.getProductId();
			statement.execute(sql);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(int productId) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();) {
			statement.execute("DELETE FROM product WHERE productid=" + productId);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	private Product createProductObject(ResultSet result) throws SQLException {
		Product product = new Product();
		int productId = result.getInt("productid");
		String name = result.getString("name");
		String description = result.getString("description");
		double price = result.getDouble("price");
		product.setProductId(productId);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		return product;
	}

}
