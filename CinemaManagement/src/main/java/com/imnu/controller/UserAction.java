package com.imnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qrsx.springmvc.pojo.User;

@Controller
@RequestMapping("/users")
public class UserAction {
	@RequestMapping("/findUser.action")
	public ModelAndView findUserList() {
		ModelAndView mv = new ModelAndView();
		List<User> userList=new ArrayList<User>();
		User u1 = new User();
		u1.setName("tom");
//		u1.setAge(20);
		userList.add(u1);
		
		User u2 = new User();
		u2.setName("tom");
//		u2.setAge(20);
		userList.add(u2);
		
		User u3 = new User();//shift+alt+r
		u3.setName("tom");
//		u3.setAge(20);
		userList.add(u3);
		mv.addObject("userList", userList);
		mv.setViewName("userList");
		return mv;
	}
	@RequestMapping("/findUserById")
	public 	ModelAndView findUserById(int id) {
		ModelAndView mv = new ModelAndView();
		System.out.println(id);
		return mv;
	}

}
