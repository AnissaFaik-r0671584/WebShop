package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Person;

public class PersonDbSql implements PersonDb {
	private Properties properties = new Properties();
	private String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/DBNAAM?currentSchema=SCHEMA";

	public PersonDbSql() {
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
	public Person get(String personId) {
		Person person = null;
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery("SELECT * FROM person WHERE userid = " + personId);

			if (result.next()) {
				person = createPersonObject(result);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return person;
	}

	@Override
	public List<Person> getAll() {
		List<Person> persons = new ArrayList<Person>();

		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement())
		{
			ResultSet result = statement.executeQuery("SELECT * FROM person");

			while (result.next()) {
				Person person = createPersonObject(result);
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return persons;
	}

	@Override
	public void add(Person person) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()) {
			statement.execute("INSERT INTO person (firstname, lastname, email, password, userid) VALUES ('" 
				+ person.getFirstName() + "','" 
					+ person.getLastName() + "','" 
				+ person.getEmail() + "','" 
					+ person.getPassword() + "','" 
				+ person.getUserid() + "')");
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Person person) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()) {
			statement.execute("UPDATE person SET password=" 
				+ person.getPassword() + ", lastname=" 
					+ person.getLastName() + ", firstname=" 
				+ person.getFirstName() + ", role=?, email=" + person.getEmail() + "WHERE userid=" + person.getUserid());
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(String personId) {
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()) {
			statement.execute("DELETE FROM person WHERE userid=" + personId);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	private Person createPersonObject(ResultSet result) throws SQLException {
		String lastname = result.getString("lastname");
		String firstname = result.getString("firstname");
		String email = result.getString("email");
		String password = result.getString("password");
		String userid = result.getString("userid");
		return new Person(userid, email, password, firstname, lastname);
	}

}
