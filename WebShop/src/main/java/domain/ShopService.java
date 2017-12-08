package domain;

import java.util.List;

import db.PersonDb;
import db.PersonDbSql;
import db.ProductDb;
import db.ProductDbSql;

public class ShopService {
	private PersonDb personDb = new PersonDbSql();
	private ProductDb productDb = new ProductDbSql();

	public ShopService(){
	}
	
	public Person getPerson(String personId) {
		return getPersonDb().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonDb().getAll();
	}

	public void addPerson(Person person) {
		getPersonDb().add(person);
	}

	public void updatePersons(Person person) {
		getPersonDb().update(person);
	}

	public void deletePerson(String id) {
		getPersonDb().delete(id);
	}

	private PersonDb getPersonDb() {
		return personDb;
	}

	private ProductDb getProductDb() {
		return productDb;
	}

	public void updateProduct(Product product) {
		getProductDb().update(product);
	}

	public void deleteProduct(int id) {
		getProductDb().delete(id);
	}

	public Product getProduct(int id) {
		return getProductDb().get(id);
	}

	public void addProduct(Product product) {
		getProductDb().add(product);
	}

	public List<Product> getProducts() {
		return getProductDb().getAll();
	}
}
