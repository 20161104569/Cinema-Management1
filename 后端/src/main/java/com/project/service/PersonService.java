package com.project.service;


import java.util.List;

import com.project.model.Person;
public interface PersonService {
    
    /**
     * 登录
     * @return
     */
    Person login(Person person);
    
    /**
     * 注册
     * @return
     */
    int register(Person person);
    
    /**
     * 查询所有工作人员信息
     * @return
     */
    List<Person> allPerson();
    
    /**
     * 添加工作人员
     * @param person
     * @return
     */
    void addPerson(Person person);
    
    /**
     * 删除工作人员
     * @param person
     * @return
     */
    void delPerson(Person person);
    
    /**
     * 修改工作人员
     * @param person
     * @return
     */
    void updatePerson(Person person);
    
    /**
     * 根据id查询工作人员信息
     * @return
     */
    Person queryPersonById(int id);
    
    /**
     * 根据name查询工作人员信息
     * @return
     */
    List<Person> queryPersonForName(String name);
}
