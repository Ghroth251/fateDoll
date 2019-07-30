package com.fate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fate.util.BaseDao;
import com.fate.bean.QQuser;
import com.fate.bean.Unit;

import lemocclient.Lemocclient;

import static lemocclient.Lemocclient.myDice;

public class UnitsDao extends BaseDao{
	static UserDao sqlDao = new UserDao();
	public ArrayList<Unit> loadUnits(){
		ArrayList<Unit> unitList;
		String sql = "select * from fatemsg";
		Object[] parms = {};
		unitList = new ArrayList<Unit>();
		ResultSet rs = this.myexecuteQuery(sql, parms);
		try {
			while(rs.next()) {
				int uId=rs.getInt(1);
				String uName = rs.getString(2);
				String uHp = rs.getString(3);
				String uSp = rs.getString(4);
				int uMp=rs.getInt(5);
				int uMaxmp=rs.getInt(6);
				int uType=rs.getInt(7);
				String uFromUser = rs.getString(8);
				String userID = rs.getString(9);
				String usergroup = rs.getString(10);
				unitList.add(new Unit(uId, uName, uHp, uSp, uMp, uMaxmp, uType, uFromUser, userID, usergroup));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closConn();
		}
		return unitList;
	}
	public void saveUnitsMsg(ArrayList<Unit> A){
		for(Unit U:A){
			if(U.getuType()==0){
				int i = 0;
				if((i = Lemocclient.groupList.indexOf(new QQuser(U.getUserID(),U.getUsergroup()))) != -1){
					QQuser Q = Lemocclient.groupList.get(i);
					System.out.println(Q);
					LinkedHashMap<String, Object> L = Q.getUserDataMap();
					L.put("生理压力", U.getuHp());
					L.put("心理压力", U.getuSp());
					L.put("魔法压力", U.getuMp()+"/"+U.getuMaxmp());
					sqlDao.saveData(L,Q,myDice.getState());
				}
				saveUnit(U);
			}else if(U.getuType()==1){
				saveUnit(U);
			}else{
				saveItem(U);
			}
			
		}
	}
	public int findUnit(Unit U){
		String sql="select * from fatemsg where userID = ? and usergroup = ?";
		Object[] parms = {U.getUserID(),U.getUsergroup()};
		ResultSet rs = this.myexecuteQuery(sql, parms);
		int res = 0;
		try {
			while(rs.next()) {
				res = 1;
			}
		} catch (SQLException e) {
		}
		return res;
	}
	public int saveUnit2(Unit U){
		String sql = "insert into fatemsg(name,hp,sp,mp,maxmp,type,userID,usergroup) values (?,?,?,?,?,?,?,?);";
		Object[] params = {U.getuName(),U.getuSp(),U.getuHp(),U.getuMp(),U.getuMaxmp(),U.getuType(),U.getUserID(),U.getUsergroup()};
		return this.myexecuteUpdate(sql, params);
	}
	public void saveUnit(Unit U){
		String sql="update fatemsg set hp=?,sp=?,mp=? where id=?";
		Object[] params = {U.getuHp(),U.getuSp(),U.getuMp(),U.getuId()};
		this.myexecuteUpdate(sql, params);
	}
	public void saveItem(Unit U){
		String sql="update fatemsg set mp=? where id=?";
		Object[] params = {U.getuMp(),U.getuId()};
		this.myexecuteUpdate(sql, params);
	}
}
