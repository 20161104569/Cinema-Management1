package com.imnu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qrsx.day03_spring.config.DatabaseBean;
import com.qrsx.day03_spring.config.ElConfig;
import com.qrsx.day03_spring.pojo.Car;
import com.qrsx.day03_spring.pojo.User;

//加载spring环境
@RunWith(SpringJUnit4ClassRunner.class)
//读取配置文件
/* @ContextConfiguration(classes =AppConfig.class ) */
@ContextConfiguration(locations = "classpath:applictationContext.xml")
public class AppTest {
//	@Autowired
//	private User user;
	@Autowired
	private DatabaseBean databaseBean;
	@Autowired
	private ElConfig elconfig;
//	@Autowired
//	private Car car;
	@Test
	public void testAnnotation() {
//		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applictationContext.xml");
		User user = (User) ac.getBean("user");
		System.out.println(user);
	}	
	@Test
	public void testUser() {
		/* System.out.println(user); */
//		System.out.println(databaseBean.getClassdriver());
		System.out.println(elconfig.getName());
		System.out.println(elconfig.getM());
		
		System.out.println(elconfig.getCar());
		
		System.out.println(elconfig.getUser());
		
		System.out.println(elconfig.getBrand());
		
		System.out.println(elconfig.getPi());
		
		System.out.println(elconfig.getNumber());
		
	}
	

	
	
	
   
}
