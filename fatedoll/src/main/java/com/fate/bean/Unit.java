package com.fate.bean;

public class Unit {
	private int uId;
	private String uName;	
	private String uHp;	
	private String uSp;	
	private int uMp;
	private int uMaxmp;
	private int uType;
	private String uFromUser;
	private String userID;
	private String usergroup;
	public Unit() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public Unit(String uName, String uHp, String uSp, int uMp, int uMaxmp, int uType, String userID, String usergroup) {
		super();
		this.uName = uName;
		this.uHp = uHp;
		this.uSp = uSp;
		this.uMp = uMp;
		this.uMaxmp = uMaxmp;
		this.uType = uType;
		this.userID = userID;
		this.usergroup = usergroup;
	}

	public Unit(int uId, String uName, String uHp, String uSp, int uMp, int uMaxmp, int uType, String uFromUser,
			String userID, String usergroup) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uHp = uHp;
		this.uSp = uSp;
		this.uMp = uMp;
		this.uMaxmp = uMaxmp;
		this.uType = uType;
		this.uFromUser = uFromUser;
		this.userID = userID;
		this.usergroup = usergroup;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuHp() {
		return uHp;
	}
	public void setuHp(String uHp) {
		this.uHp = uHp;
	}
	public String getuSp() {
		return uSp;
	}
	public void setuSp(String uSp) {
		this.uSp = uSp;
	}
	public int getuMp() {
		return uMp;
	}
	public void setuMp(int uMp) {
		this.uMp = uMp;
	}
	public int getuMaxmp() {
		return uMaxmp;
	}
	public void setuMaxmp(int uMaxmp) {
		this.uMaxmp = uMaxmp;
	}
	public int getuType() {
		return uType;
	}
	public void setuType(int uType) {
		this.uType = uType;
	}
	public String getuFromUser() {
		return uFromUser;
	}
	public void setuFromUser(String uFromUser) {
		this.uFromUser = uFromUser;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}
	@Override
	public String toString() {
		return "Unit [uId=" + uId + ", uName=" + uName + ", uHp=" + uHp + ", uSp=" + uSp + ", uMp=" + uMp + ", uMaxmp="
				+ uMaxmp + ", uType=" + uType + ", uFromUser=" + uFromUser + ", userID=" + userID + ", usergroup="
				+ usergroup + "]";
	}
	



}
