package com.imnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.qrsx.springmvc.mapper.AccountMapper;
import com.qrsx.springmvc.pojo.Account;
@Service
@EnableTransactionManagement 
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Transactional
	public void transfer(String from, String to, double money) {
		//from
		Account account=accountMapper.findAccountByName(from);
		
		account.setMoney(account.getMoney()-money);
		accountMapper.updateByPrimaryKey(account);
		
		int i=1/0;
		
		//to
		Account account2 = accountMapper.findAccountByName(to);
		account2.setMoney(account2.getMoney()+money);
		accountMapper.updateByPrimaryKey(account2);
		
	}
	

}
