package com.project.dao;

import java.util.List;

import com.project.model.Person;
import com.project.model.TicketPurchaseInfo;
import com.project.model.TicketShowInfo;

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
    
    /**
     * 查询当前用户的购票信息
     * @param userId
     * @return
     */
    List<TicketShowInfo> allTicketInfo(int userId);
    
    /**
	 * 验证码验证
	 * @param id
	 * @param verificationcode
	 */
	TicketPurchaseInfo queryByIdAndVer(TicketPurchaseInfo ticketPurchaseInfo);
	
	/**
	 * 查询所有购票信息
	 * @return
	 */
	List<TicketShowInfo> queryAllTicket();

}
