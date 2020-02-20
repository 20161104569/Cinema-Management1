package com.project.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Person;
import com.project.model.TicketPurchaseInfo;
import com.project.model.TicketShowInfo;
import com.project.model.movieInfo;
import com.project.service.PersonService;
import com.project.service.TicketPurchaseInfoService;
import com.project.service.movieInfoService;

@RestController
@RequestMapping("/TicketPurchaseInfoController")
public class TicketPurchaseInfoController {

	@Autowired
	private TicketPurchaseInfoService ticketPurchaseInfoService;
	@Autowired
	private movieInfoService movieInfoService;
	@Autowired
	private PersonService personService;

	public movieInfoService getMovieInfoService() {
		return movieInfoService;
	}

	public void setMovieInfoService(movieInfoService movieInfoService) {
		this.movieInfoService = movieInfoService;
	}

	public TicketPurchaseInfoService getTicketPurchaseInfoService() {
		return ticketPurchaseInfoService;
	}

	public void setTicketPurchaseInfoService(TicketPurchaseInfoService ticketPurchaseInfoService) {
		this.ticketPurchaseInfoService = ticketPurchaseInfoService;
	}
	
	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	@RequestMapping("/buyTicket")
    @ResponseBody
    public int buyTicket(int userId,int id,String buyticket){
		TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
		ticketPurchaseInfo.setUserid(userId);
		ticketPurchaseInfo.setMovieid(id);
		int code=(int)((Math.random()*9+1)*10000);
		ticketPurchaseInfo.setVerificationcodel(code);
		ticketPurchaseInfo.setPosition(buyticket);
		ticketPurchaseInfo.setState("1");
		ticketPurchaseInfoService.buyTicket(ticketPurchaseInfo);
		movieInfo movieInfo=movieInfoService.queryMovieInfoById(id);
		movieInfo.setPurchasedticket(movieInfo.getPurchasedticket()+1);
		movieInfo.setSurplusticket(movieInfo.getSurplusticket()-1);
		movieInfoService.updateMovieInfoSum(movieInfo);
		return code;
    }
    
    @RequestMapping("/delTicket")
    @ResponseBody
    public Boolean delTicket(int id,int movieid){
    	Boolean result;
    	TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    	ticketPurchaseInfo.setId(id);
    	ticketPurchaseInfo.setMovieid(movieid);
    	if(ticketPurchaseInfoService.queryPosition(ticketPurchaseInfo).size()!=0){
    		TicketPurchaseInfo ticketPurchaseInfoResult=ticketPurchaseInfoService.queryPosition(ticketPurchaseInfo).get(0);
    		ticketPurchaseInfoResult.setState("2");
        	ticketPurchaseInfoService.checkTicket(ticketPurchaseInfoResult);
        	movieInfo movieInfo=movieInfoService.queryMovieInfoById(movieid);
        	movieInfo.setPurchasedticket(movieInfo.getPurchasedticket()-1);
        	movieInfo.setSurplusticket(movieInfo.getSurplusticket()+1);
        	movieInfoService.updateMovieInfoSum(movieInfo);
        	result=true;
    	}else{
    		result=false;
    	}
    	return result;
    	
    }
    
    @RequestMapping("/checkTicket")
    @ResponseBody
    public Boolean checkTicket(int id,int verificationcode){
    	Boolean result;
    	TicketPurchaseInfo ticketPurchaseInfo =ticketPurchaseInfoService.queryByIdAndVer(id,verificationcode);
    	if(ticketPurchaseInfo==null||ticketPurchaseInfo.equals("")){
    		result=false;
    	}else{
    		ticketPurchaseInfo.setState("3");
    		ticketPurchaseInfoService.checkTicket(ticketPurchaseInfo);
    		result=true;
    	}
    	return result;
    }
    
    @RequestMapping("/queryPosition")
    @ResponseBody
    public List<TicketPurchaseInfo> queryPosition(int movieid){
    	TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    	ticketPurchaseInfo.setMovieid(movieid);
    	ticketPurchaseInfo.setState("可使用");
    	return ticketPurchaseInfoService.queryPosition(ticketPurchaseInfo);
    }
    
    @RequestMapping("/allTicketInfo")
    @ResponseBody
    public List<TicketShowInfo> allTicketInfo(int userId){
    	List<TicketShowInfo> ticketList=ticketPurchaseInfoService.allTicketInfo(userId);
        return ticketList;
    }
    
    @RequestMapping("/managerBuyTicket")
    @ResponseBody
    public int managerBuyTicket(String userName,int id,String buyticket){
    	if(personService.queryPersonForName(userName)==null||personService.queryPersonForName(userName).isEmpty()){
    		return 0;
    	}else{
    		Person person=personService.queryPersonForName(userName).get(0);
    		TicketPurchaseInfo ticketPurchaseInfo =new TicketPurchaseInfo();
    		ticketPurchaseInfo.setUserid(person.getId());
    		ticketPurchaseInfo.setMovieid(id);
    		int code=(int)((Math.random()*9+1)*10000);
    		ticketPurchaseInfo.setVerificationcodel(code);
    		ticketPurchaseInfo.setPosition(buyticket);
    		ticketPurchaseInfo.setState("1");
    		ticketPurchaseInfoService.buyTicket(ticketPurchaseInfo);
    		movieInfo movieInfo=movieInfoService.queryMovieInfoById(id);
    		movieInfo.setPurchasedticket(movieInfo.getPurchasedticket()+1);
    		movieInfo.setSurplusticket(movieInfo.getSurplusticket()-1);
    		movieInfoService.updateMovieInfoSum(movieInfo);
    		return code;
    	}
    }
    
    @RequestMapping("/queryAllTicket")
    @ResponseBody
    public List<TicketShowInfo> queryAllTicket(){
    	List<TicketShowInfo> ticketList=ticketPurchaseInfoService.queryAllTicket();
        return ticketList;
    }

	
	
}
