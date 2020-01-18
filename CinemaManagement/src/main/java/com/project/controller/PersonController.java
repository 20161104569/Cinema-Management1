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
    public String login(String username,String password){
    	Person person =new Person();
    	person.setName(username);
    	person.setPassword(password);
        System.out.println(person.getName());
        Person userInfo = personService.login(person);
        String result;
        if(userInfo==null||userInfo.equals("")){
        	result="false";
        }else{
        	result="true";
        }
        return result;
    }
    
    @RequestMapping("/register")
    @ResponseBody
    public int register(String username,String password,String type){
    	Person person =new Person();
    	person.setName(username);
    	person.setPassword(password);
    	person.setType(type);
        System.out.println(person.getName());
        int id=personService.register(person);
        return id;
    }
    
    @RequestMapping("/allPerson")
    @ResponseBody
    public JSONArray allPerson(){
    	List<Person> personList=personService.allPerson();
    	
    	JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < personList.size(); i++) {
        	JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", personList.get(i).getId());
            jsonObject.put("name", personList.get(i).getName());
            jsonObject.put("password", personList.get(i).getPassword());
            jsonObject.put("type", personList.get(i).getType());
            jsonObject.put("sex", personList.get(i).getSex());
            jsonObject.put("tel", personList.get(i).getTel());
            jsonArray.put(i, jsonObject);
	    }

        return jsonArray;
    }
    
    @RequestMapping("/addPerson")
    @ResponseBody
    public void addPerson(String username,String password,String type,String sex,String tel){
    	Person person =new Person();
    	person.setName(username);
    	person.setPassword(password);
    	person.setType(type);
    	person.setSex(sex);
    	person.setTel(tel);
    	personService.addPerson(person);
    }
    
    @RequestMapping("/delPerson")
    @ResponseBody
    public void delPerson(int id){
    	Person person =new Person();
    	person.setId(id);
    	personService.delPerson(person);
    }
    
    @RequestMapping("/updatePerson")
    @ResponseBody
    public void updatePerson(int id,String username,String password,String type,String sex,String tel){
    	Person person =new Person();
    	person.setId(id);
    	person.setName(username);
    	person.setPassword(password);
    	person.setType(type);
    	person.setSex(sex);
    	person.setTel(tel);
    	personService.updatePerson(person);
    }
    
    @RequestMapping("/queryPersonById")
    @ResponseBody
    public Person queryPersonById(int id){
    	Person person=personService.queryPersonById(id);
        return person;
    }
    
}
