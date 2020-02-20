package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PersonMapper;
import com.project.model.Person;
import com.project.service.PersonService;


@Service("personService")
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonMapper personMapper;
	 
    public PersonMapper getPersonMapper() {
        return personMapper;
    }
    
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }
 
	@Override
	public Person login(Person person) {
		// TODO Auto-generated method stub
		return personMapper.login(person);
	}
	@Override
	public int register(Person person) {
		// TODO Auto-generated method stub
		return personMapper.register(person);
	}
	@Override
	public List<Person> allPerson() {
		// TODO Auto-generated method stub
		List<Person> personList=personMapper.allPerson();
		return personList;
		
	}
	@Override
	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		personMapper.addPerson(person);
	}
	@Override
	public void delPerson(Person person) {
		// TODO Auto-generated method stub
		personMapper.delPerson(person);
	}
	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		personMapper.updatePerson(person);
	}
	@Override
	public Person queryPersonById(int id) {
		// TODO Auto-generated method stub
		return personMapper.queryPersonById(id);
	}

	@Override
	public List<Person> queryPersonForName(String name) {
		// TODO Auto-generated method stub
		return personMapper.queryPersonForName(name);
	}
}
