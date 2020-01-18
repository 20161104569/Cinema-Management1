package com.project.dao;

import java.util.List;

import com.project.model.Person;

public interface PersonMapper {
	/**
     * 插入一条记录
     * @param person
     */
    void insert(Person person);
    
    /**
     * 查询所有
     * @return
     */
    List<Person> queryAll();
    
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
     * @return
     */
    void addPerson(Person person);
    
    /**
     * 删除工作人员
     * @return
     */
    void delPerson(Person person);
    
    /**
     * 修改工作人员
     * @return
     */
    void updatePerson(Person person);
    
    /**
     * 根据id查询人员信息
     * @return
     */
    Person queryPersonById(int id);
}
