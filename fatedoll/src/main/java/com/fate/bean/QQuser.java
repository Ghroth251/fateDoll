package com.fate.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;

import static com.fate.util.MapUtils.*;

@Entity
@Table(name = "QQuser") //建立实体类和表的映射关系
public class QQuser implements Serializable{

	private static final long serialVersionUID = -677496801593907189L;


	@Id//声明当前私有属性为主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
	@Column(name = "key") //指定和表中cust_id字段的映射关系
	private int id;
	private String userID;
	private String userName;
	private String userOldName;
	private String usergroup;
	private String userAttribute;
	private String userData;
	private String userItem;
	private String userEquip;
	private String ujoinstate;
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
			String userAttribute, String userData,
			String userItem, String userEquip) {
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
	public String getUjoinstate() {
		return ujoinstate;
	}
	public void setUjoinstate(String ujoinstate) {
		this.ujoinstate = ujoinstate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUserData() {
		return userData;
	}
	public void setUserData(String h) {
		this.userData = h;
	}
	public void setUserData(String s,Object o) {
		LinkedHashMap<String,Object> L1 = new LinkedHashMap<>();
		L1.put(s, o);
		this.userData = mapSave(L1);
	}
	public String getUserItem() {
		return userItem;
	}
	public void setUserItem(String userItem) {
		this.userItem = userItem;
	}
	public String getUserEquip() {
		return userEquip;
	}
	public void setUserEquip(String userEquip) {
		this.userEquip = userEquip;
	}
	public String getUserAttribute() {
		return userAttribute;
	}
	public void setUserAttribute(String userAttribute) {
		this.userAttribute = userAttribute;
	}

	public LinkedHashMap<String,Object> getUserDataMap() {
		return mapLoad(userData);
	}
	public void setUserData(LinkedHashMap<String,Object> h) {
		this.userData = mapSave(h);
	}
	public LinkedHashMap<Item,Integer> getUserItemMap() {
		return itemLoad(userItem);
	}
	public void setUserItem( LinkedHashMap<Item,Integer> userItem) {
		this.userItem = itemSave(userItem);
	}
	public LinkedHashMap<String,Object> getUserEquipMap() {
		return mapLoad(userEquip);
	}
	public void setUserEquip(LinkedHashMap<String,Object> userEquip) {
		this.userEquip = mapSave(userEquip);
	}
	public LinkedHashMap<String,Object> getUserAttributeMap() {
		return mapLoad(userAttribute);
	}
	public void setUserAttribute(LinkedHashMap<String,Object> userAttribute) {
		this.userAttribute = mapSave(userAttribute);
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
		return "QQuser{" +
				"id=" + id +
				", userID='" + userID + '\'' +
				", userName='" + userName + '\'' +
				", userOldName='" + userOldName + '\'' +
				", usergroup='" + usergroup + '\'' +
				", userAttribute='" + userAttribute + '\'' +
				", userData='" + userData + '\'' +
				", userItem='" + userItem + '\'' +
				", userEquip='" + userEquip + '\'' +
				", ujoinstate='" + ujoinstate + '\'' +
				'}';
	}
}
