package com.project.service;

import java.util.List;

import com.project.model.TicketPurchaseInfo;
import com.project.model.TicketShowInfo;
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
	
	/**
	 * 查询当前用户的购票信息
	 * @return
	 */
	List<TicketShowInfo> allTicketInfo(int userId);
	
	/**
	 * 验证码验证
	 * @param id
	 * @param verificationcode
	 */
	TicketPurchaseInfo queryByIdAndVer(int id,int verificationcode);
	
	/**
	 * 查询所有购票信息
	 * @return
	 */
	List<TicketShowInfo> queryAllTicket();

}
