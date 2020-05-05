package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.model.Person;
import com.project.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/personController")

public class PersonController {
   
	private PersonService personService;
    
    public PersonService getPersonService() {
        return personService;
    }
 
    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    @RequestMapping("/login")
    @ResponseBody
    public Person login(String username,String password){
    	Person person =new Person();
    	person.setName(username);
    	person.setPassword(password);
        System.out.println(person.getName());
        Person userInfo = personService.login(person);
        Person result;
        if(userInfo==null||userInfo.equals("")){
        	result=null;
        }else{
        	result=userInfo;
        }
        return result;
    }
    
    @RequestMapping("/register")
    @ResponseBody
    public int register(String username,String password){
    	Person person =new Person();
    	person.setName(username);
    	person.setPassword(password);
    	person.setType("3");
        System.out.println(person.getName());
        int id = personService.register(person);
        return id;
    }
    
    @RequestMapping("/allPerson")
    @ResponseBody
    public List<Person> allPerson(){
    	List<Person> personList=personService.allPerson();
        return personList;
    }
    
    @RequestMapping("/addPerson")
    @ResponseBody
    public Boolean addPerson(String name,String password,String type,String sex,String tel){
    	Person person =new Person();
    	person.setName(name);
    	person.setPassword(password);
    	person.setType(type);
    	person.setSex(sex);
    	person.setTel(tel);
    	personService.addPerson(person);
    	return true;
    }
    
    @RequestMapping("/delPerson")
    @ResponseBody
    public Boolean delPerson(int id){
    	Person person =new Person();
    	person.setId(id);
    	personService.delPerson(person);
    	return true;
    }
    
    @RequestMapping("/updatePerson")
    @ResponseBody
    public Boolean updatePerson(int id,String name,String password,String type,String sex,String tel){
    	Person person =new Person();
    	person.setId(id);
    	person.setName(name);
    	person.setPassword(password);
    	person.setType(type);
    	person.setSex(sex);
    	person.setTel(tel);
    	personService.updatePerson(person);
    	return true;
    }
    
    @RequestMapping("/queryPersonById")
    @ResponseBody
    public Person queryPersonById(int id){
    	Person person=personService.queryPersonById(id);
        return person;
    }
    
    @RequestMapping("/queryPersonForName")
    @ResponseBody
    public List<Person> queryPersonForName(String name){
    	List<Person> person=personService.queryPersonForName(name);
        return person;
    }
    
}
