package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PersonMapper;
import com.project.dao.TicketPurchaseInfoMapper;
import com.project.model.TicketPurchaseInfo;
import com.project.model.TicketShowInfo;
import com.project.service.TicketPurchaseInfoService;

@Service("ticketPurchaseInfoService")
public class TicketPurchaseInfoServiceImpl implements TicketPurchaseInfoService{
	
	@Autowired
	private TicketPurchaseInfoMapper ticketPurchaseInfoMapper;

	@Override
	public void buyTicket(TicketPurchaseInfo ticketPurchaseInfo) {
		// TODO Auto-generated method stub
		ticketPurchaseInfoMapper.buyTicket(ticketPurchaseInfo);
	}

	@Override
	public void delTicket(TicketPurchaseInfo ticketPurchaseInfo) {
		// TODO Auto-generated method stub
		ticketPurchaseInfoMapper.delTicket(ticketPurchaseInfo);
	}

	@Override
	public void checkTicket(TicketPurchaseInfo ticketPurchaseInfo) {
		// TODO Auto-generated method stub
		ticketPurchaseInfoMapper.checkTicket(ticketPurchaseInfo);
	}

	@Override
	public List<TicketPurchaseInfo> queryPosition(
			TicketPurchaseInfo ticketPurchaseInfo) {
		// TODO Auto-generated method stub
		return ticketPurchaseInfoMapper.queryPosition(ticketPurchaseInfo);
	}

	@Override
	public List<TicketShowInfo> allTicketInfo(int userId) {
		// TODO Auto-generated method stub
		return ticketPurchaseInfoMapper.allTicketInfo(userId);
	}

	@Override
	public TicketPurchaseInfo queryByIdAndVer(int movieid, int verificationcode) {
		// TODO Auto-generated method stub
		TicketPurchaseInfo ticketPurchaseInfo=new TicketPurchaseInfo();
		ticketPurchaseInfo.setMovieid(movieid);
		ticketPurchaseInfo.setVerificationcodel(verificationcode);
		if(ticketPurchaseInfoMapper.queryByIdAndVer(ticketPurchaseInfo)==null)
			return null;
		else return ticketPurchaseInfoMapper.queryByIdAndVer(ticketPurchaseInfo);
	}

	@Override
	public List<TicketShowInfo> queryAllTicket() {
		// TODO Auto-generated method stub
		return ticketPurchaseInfoMapper.queryAllTicket();
	}

}
