package desktop;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class SqlInjection2 {

	public static void main(String[] args) throws Exception {
		String email = JOptionPane.showInputDialog("Enter your email");
		String oldPassword = JOptionPane.showInputDialog("Enter your password");
		oldPassword = hashPassword(oldPassword);

		Connection connection = getDbConnection();

		String sql = "SELECT * FROM u0082726.person WHERE email=? and password=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, oldPassword);
			ResultSet result = statement.executeQuery();
			result.next();
			String password = result.getString("password");
			JOptionPane.showMessageDialog(null, "First password in db: " + password);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Not found");
		}

	}

	private static String hashPassword(String password) throws Exception {
		String encrypted = password;
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes());
		byte[] encryptedBytes = new byte[40];
		encryptedBytes = digest.digest();
		encrypted = new BigInteger(1, encryptedBytes).toString(16);
		return encrypted;
	}

	private static Connection getDbConnection() throws SQLException {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51415/webontwerp";
		properties.setProperty("user", "u0082726");
		properties.setProperty("password", "Rulli1Spridi");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory",
				"org.postgresql.ssl.NonValidatingFactory");
		Connection connection;
		connection = DriverManager.getConnection(url, properties);
		return connection;
	}

}
