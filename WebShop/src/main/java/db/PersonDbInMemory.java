package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;

public class PersonDbInMemory implements PersonDb {
	private Map<String, Person> persons = new HashMap<String, Person>();
	
	public PersonDbInMemory () {
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		add(administrator);
	}
	
	@Override
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	
	@Override
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	@Override
	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	@Override
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	@Override
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}
}
