package com.project.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.TicketPurchaseInfo;
import com.project.model.movieInfo;
import com.project.service.TicketPurchaseInfoService;

@RestController
@RequestMapping("/TicketPurchaseInfoController")
public class TicketPurchaseInfoController {

	private TicketPurchaseInfoService ticketPurchaseInfoService;

	public TicketPurchaseInfoService getTicketPurchaseInfoService() {
		return ticketPurchaseInfoService;
	}

	public void setTicketPurchaseInfoService(TicketPurchaseInfoService ticketPurchaseInfoService) {
		this.ticketPurchaseInfoService = ticketPurchaseInfoService;
	}
	
	@RequestMapping("/buyTicket")
    @ResponseBody
    public void buyTicket(int userid,int movieid,String position){
		TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
		ticketPurchaseInfo.setUserid(userid);
		ticketPurchaseInfo.setMovieid(movieid);
		ticketPurchaseInfo.setVerificationcodel((int)((Math.random()*9+1)*10000));
		ticketPurchaseInfo.setPosition(position);
		ticketPurchaseInfo.setState("可使用");
		ticketPurchaseInfoService.buyTicket(ticketPurchaseInfo);
    }
    
    @RequestMapping("/delTicket")
    @ResponseBody
    public void delTicket(int id){
    	TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    	ticketPurchaseInfo.setId(id);
    	ticketPurchaseInfoService.delTicket(ticketPurchaseInfo);
    }
    
    @RequestMapping("/checkTicket")
    @ResponseBody
    public void checkTicket(int id){
    	TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    	ticketPurchaseInfo.setId(id);
    	ticketPurchaseInfo.setState("已使用");
    	ticketPurchaseInfoService.checkTicket(ticketPurchaseInfo);
    }
    
    @RequestMapping("/queryPosition")
    @ResponseBody
    public List<TicketPurchaseInfo> queryPosition(int movieid){
    	TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    	ticketPurchaseInfo.setMovieid(movieid);
    	ticketPurchaseInfo.setState("可使用");
    	return ticketPurchaseInfoService.queryPosition(ticketPurchaseInfo);
    }
	
}
