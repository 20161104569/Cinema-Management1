package com.project.service;

import java.util.List;

import com.project.model.TicketPurchaseInfo;
import com.project.model.movieInfo;

public interface TicketPurchaseInfoService {
	
	/**
     * 购票
     * @param TicketPurchaseInfo
     * @return
     */
	void buyTicket(TicketPurchaseInfo ticketPurchaseInfo);
    
    /**
     * 退票
     * @param TicketPurchaseInfo
     * @return
     */
	void delTicket(TicketPurchaseInfo ticketPurchaseInfo);
    
    /**
     * 验票
     * @param TicketPurchaseInfo
     * @return
     */
	void checkTicket(TicketPurchaseInfo ticketPurchaseInfo);
	
	/**
     * 根据电影id查询已有卖出票的座位
     * @param TicketPurchaseInfo
     * @return
     */
	List<TicketPurchaseInfo> queryPosition(TicketPurchaseInfo ticketPurchaseInfo);

}
