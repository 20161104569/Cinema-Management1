package com.project.dao;

import java.util.List;

import com.project.model.Person;
import com.project.model.TicketPurchaseInfo;

public interface TicketPurchaseInfoMapper {
	
	/**
     * 购票
     * @return
     */
    void buyTicket(TicketPurchaseInfo ticketPurchaseInfo);
    
    /**
     * 退票
     * @return
     */
    void delTicket(TicketPurchaseInfo ticketPurchaseInfo);
    
    /**
     * 验票
     * @return
     */
    void checkTicket(TicketPurchaseInfo ticketPurchaseInfo);
    
    /**
     * 根据电影id查询已有卖出票的座位
     * @return
     */
    List<TicketPurchaseInfo> queryPosition(TicketPurchaseInfo ticketPurchaseInfo);

}
