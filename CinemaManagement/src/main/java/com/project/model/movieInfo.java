package com.project.model;

import java.sql.Date;

public class movieInfo {
	
	private int id;
	private String moviename;
	private String content;
	private Date starttime;
	private Date endtime;
	private String state;
	private int sumticket;//电影票总数
	private int purchasedticket;//已卖票数
	private int surplusticket;//剩余票数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getSumticket() {
		return sumticket;
	}
	public void setSumticket(int sumticket) {
		this.sumticket = sumticket;
	}
	public int getPurchasedticket() {
		return purchasedticket;
	}
	public void setPurchasedticket(int purchasedticket) {
		this.purchasedticket = purchasedticket;
	}
	public int getSurplusticket() {
		return surplusticket;
	}
	public void setSurplusticket(int surplusticket) {
		this.surplusticket = surplusticket;
	}
	
	

}
