package com.ghroth251.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Objects;

import static com.ghroth251.util.MapUtils.*;

@Entity
@Table(name = "QQuser") //建立实体类和表的映射关系
public class QQuser implements Serializable{

	private static final long serialVersionUID = -677496801593907189L;


	@Id//声明当前私有属性为主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
//	@Column(name = "id") //指定和表中cust_id字段的映射关系
	private long id;
	private long userID;
	private String userName;
	private String userOldName;
	private long usergroup;
	private String userAttribute;
	private String userData;
	private String userItem;
	private String userEquip;
	private String ujoinstate;
	public QQuser() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public QQuser(long userID, long usergroup) {
		super();
		this.userID = userID;
		this.usergroup = usergroup;
	}
	public QQuser(long userID, String userName, long usergroup) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.usergroup = usergroup;
	}

	public QQuser(long userID, String userName, long usergroup, String userOldName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userOldName = userOldName;
		this.usergroup = usergroup;
	}

	public QQuser(long userID, String userName, String userOldName, long usergroup, String ujoinstate) {
		this.userID = userID;
		this.userName = userName;
		this.userOldName = userOldName;
		this.usergroup = usergroup;
		this.ujoinstate = ujoinstate;
	}

	public QQuser(long userID, String userName, String userOldName, long usergroup,
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
    public QQuser(long id, long userID, String userName, String userOldName, long usergroup,
                  String userAttribute, String userData,
                  String userItem, String userEquip, String ujoinstate) {
        super();
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.userOldName = userOldName;
        this.usergroup = usergroup;
        this.userData = userData;
        this.userAttribute = userAttribute;
        this.userItem = userItem;
        this.userEquip = userEquip;
        this.ujoinstate = ujoinstate;
    }
	public String getUjoinstate() {
		return ujoinstate;
	}
	public void setUjoinstate(String ujoinstate) {
		this.ujoinstate = ujoinstate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(long usergroup) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QQuser qQuser = (QQuser) o;
		return id == qQuser.id ||(
				userID == qQuser.userID &&
				usergroup == qQuser.usergroup);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, userID, usergroup);
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