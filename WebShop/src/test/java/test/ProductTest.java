package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import db.DbException;
import db.ProductDb;
import domain.Product;

public class ProductTest {
	String url = "http://localhost:8080/WebShop/Controller";
	WebDriver driver;
	Properties properties;
	String url2;
	Connection connection;
	Statement statement; 
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anissa Faik\\Documents\\webontwikkeling2\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		properties = new Properties();
		url2 = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/1TX34?currentSchema=r0671584";
		properties.setProperty("user", "r0671584");
		properties.setProperty("password", "Hadida98");
		properties.setProperty("ssl","true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		try{
			connection = DriverManager.getConnection(url2,properties);
			statement = connection.createStatement();
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	@After
	public void breakdown(){
		driver.close();
		try {
			statement.executeUpdate("DELETE FROM product WHERE name = 'Testproduct'; ");
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		
	}
	
	@Test
	public void registerProduct(){
		driver.get(url + "?action=addProduct");
		
		WebElement nameField = driver.findElement(By.id("name"));
		nameField.sendKeys("Testproduct");
		
		WebElement descriptionField = driver.findElement(By.id("description"));
		descriptionField.sendKeys("Testproduct beschrijving");
		
		WebElement priceField = driver.findElement(By.id("price"));
		priceField.sendKeys("12");
		
		WebElement saveButton = driver.findElement(By.id("save"));
		saveButton.click();
		
		assertEquals("Products", driver.findElement(By.cssSelector("h2")).getText());	
	}
	
	
}
