package com.project.model;

public class TicketPurchaseInfo {
    private int id;
    private int userid;
    private int movieid;
    private int verificationcodel;
    private String position;
    private String state;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public int getVerificationcodel() {
		return verificationcodel;
	}
	public void setVerificationcodel(int verificationcodel) {
		this.verificationcodel = verificationcodel;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
