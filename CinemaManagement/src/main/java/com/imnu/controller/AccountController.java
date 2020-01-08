package com.imnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qrsx.springmvc.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@RequestMapping("/transfer")
	public ModelAndView transferAccount(String from,String to,double money) {
		ModelAndView mv = new ModelAndView();
		accountService.transfer(from, to, money);
		mv.setViewName("success");
		return mv;
	}
}
