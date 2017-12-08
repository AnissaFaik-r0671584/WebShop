package desktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import domain.Person;

public class Desktop {
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/lector?currentSchema=u0082726";
		properties.setProperty("user", "u0082726");
		properties.setProperty("password", "Rulli1Spridi");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		Connection connection = DriverManager.getConnection(url,properties);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery( "SELECT * FROM person" );
		
		while(result.next()){
			String firstName = result.getString("firstname");
			String lastName = result.getString("lastname");
			String email = result.getString("email");
			String password = result.getString("password");
			String userid = result.getString("userid");

			Person person = new Person(userid, email, password, firstName, lastName);
			System.out.println(person);
		}
		
		statement.close();
		connection.close();

	}
}
