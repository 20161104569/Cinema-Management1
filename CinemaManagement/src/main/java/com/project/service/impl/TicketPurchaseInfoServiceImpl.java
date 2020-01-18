package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PersonMapper;
import com.project.dao.TicketPurchaseInfoMapper;
import com.project.model.TicketPurchaseInfo;
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

}
