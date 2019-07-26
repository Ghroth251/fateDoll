package com.fate.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class QQuser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -677496801593907189L;
	
	private String userID;
	private String userName;
	private String userOldName;
	private String usergroup;
	private LinkedHashMap<String,Object> userAttribute;
	private LinkedHashMap<String,Object> userData;
	private LinkedHashMap<Item,Integer> userItem;
	private LinkedHashMap<String,Object> userEquip;
	public QQuser() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public QQuser(String userID, String usergroup) {
		super();
		this.userID = userID;
		this.usergroup = usergroup;
	}
	public QQuser(String userID, String userName, String usergroup) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.usergroup = usergroup;
	}
	
	public QQuser(String userID, String userName, String usergroup, String userOldName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userOldName = userOldName;
		this.usergroup = usergroup;
	}

	public QQuser(String userID, String userName, String userOldName, String usergroup,
			LinkedHashMap<String, Object> userAttribute, LinkedHashMap<String, Object> userData,
			LinkedHashMap<Item, Integer> userItem, LinkedHashMap<String, Object> userEquip) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userOldName = userOldName;
		this.usergroup = usergroup;
		this.userData = userData;
		this.userAttribute = userAttribute;
		this.userItem = userItem;
		this.userEquip = userEquip;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}
	public String getUserOldName() {
		return userOldName;
	}
	public void setUserOldName(String userOldName) {
		this.userOldName = userOldName;
	}

	public LinkedHashMap<String, Object> getUserData() {
		return userData;
	}
	public void setUserData(LinkedHashMap<String, Object> h) {
		this.userData = h;
	}
	public void setUserData(String s,Object o) {
		this.userData.put(s, o);
	}
	
	public LinkedHashMap<Item, Integer> getUserItem() {
		return userItem;
	}
	public void setUserItem(LinkedHashMap<Item, Integer> userItem) {
		this.userItem = userItem;
	}
	
	public LinkedHashMap<String, Object> getUserEquip() {
		return userEquip;
	}
	public void setUserEquip(LinkedHashMap<String, Object> userEquip) {
		this.userEquip = userEquip;
	}
	
	public LinkedHashMap<String, Object> getUserAttribute() {
		return userAttribute;
	}
	public void setUserAttribute(LinkedHashMap<String, Object> userAttribute) {
		this.userAttribute = userAttribute;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((usergroup == null) ? 0 : usergroup.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QQuser other = (QQuser) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (usergroup == null) {
			if (other.usergroup != null)
				return false;
		} else if (!usergroup.equals(other.usergroup))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QQuser [userID=" + userID + ", userName=" + userName + ", userOldName=" + userOldName + ", usergroup="
				+ usergroup + ", userData=" + userData + ", userAttribute=" + userAttribute + ", userItem=" + userItem
				+ ", userEquip=" + userEquip + "]";
	}

	
}
