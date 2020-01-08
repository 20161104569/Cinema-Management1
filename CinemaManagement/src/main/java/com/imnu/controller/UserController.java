package com.imnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qrsx.springmvc.pojo.User;
import com.qrsx.springmvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUserById")
	public ModelAndView findUserById(int id) {
		ModelAndView mv = new ModelAndView();
		User user = userService.findUserById(id);
		mv.addObject("user", user);
		mv.setViewName("user");
		return mv;
	}

}
