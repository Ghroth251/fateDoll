package com.fate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import com.fate.util.BaseDao;
import com.fate.bean.Dice;
import com.fate.bean.Item;
import com.fate.bean.QQuser;

import static com.fate.util.MapUtils.mapLoad;
import static com.fate.util.MapUtils.mapSave;
import static lemocclient.Lemocclient.myDice;

public class UserDao extends BaseDao {
    public ArrayList<QQuser> allQQuser(String table) {
        ArrayList<QQuser> custList;
        String sql = "select * from " + table +
                "user";
        Object[] parms = {};
        custList = new ArrayList<>();
        ResultSet rs = this.myexecuteQuery(sql, parms);
        try {
            while(rs.next()) {
                String userID=rs.getString(2);
                String userName=rs.getString(3);
                String userOldName=rs.getString(4);
                String usergroup=rs.getString(5);
                LinkedHashMap<String,Object> userAttribute=mapLoad(rs.getString(6));
                LinkedHashMap<String,Object> userDate=mapLoad(rs.getString(7));
                LinkedHashMap<Item,Integer> userItem=itemLoad(rs.getString(8));
                LinkedHashMap<String,Object> userEquid=mapLoad(rs.getString(9));
                custList.add(new QQuser(userID,userName,userOldName,usergroup,userAttribute,userDate,userItem,userEquid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closConn();
        }
        return custList;
    }
    public QQuser getUserByUser(QQuser u) {
        QQuser u2=null;
        String sql = "select * from QQuser where userID like ? and usergroup=?";
        Object[] parms = {u.getUserID(), u.getUsergroup()};
        ResultSet rs = this.myexecuteQuery(sql, parms);
        try {
            while (rs.next()) {
                String userID = rs.getString(2);
                String userName = rs.getString(3);
                String userOldName = rs.getString(4);
                String usergroup = rs.getString(5);
                u2 = new QQuser(userID,userName,userOldName,usergroup,null,null,null,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closConn();
        }
        return u2;
    }
    public Dice diceLoad() {
        String sql = "select * from dice where id = 1";
        Object[] parms = {};
        Dice d = null;
        ResultSet rs = this.myexecuteQuery(sql, parms);
        try {
            while(rs.next()) {
                String name=rs.getString(2);
                String shortName=rs.getString(3);
                String code=rs.getString(4);
                String State=rs.getString(5);
                d = new Dice(name, shortName, code, State);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closConn();
        }
        return d;
    }
    private static LinkedHashMap<Item,Integer> itemLoad(String value) {
        LinkedHashMap<Item,Integer>  h = new LinkedHashMap<>();
        if(value!=null){
            String values = value;
            while(values.contains("|")){
                String key = values.substring(0,values.indexOf(":"));
                int val = Integer.valueOf(values.substring(values.indexOf(":")+1,values.indexOf("|")));
                String name = key.substring(0,key.indexOf("-"));
                key = key.substring(key.indexOf("-")+1);
                int weight = Integer.valueOf(key.substring(0,key.indexOf("-")));
                key = key.substring(key.indexOf("-")+1);
                int price = Integer.valueOf(key);
                h.put(new Item(name,weight,price),val);
                values = values.substring(values.indexOf("|")+1);
            }
            return h;
        }
        return null;
    }
    private static String itemSave(LinkedHashMap<Item, Integer> L) {
        StringBuilder sbd = new StringBuilder();
        if(L!=null){
            Set<Item> keySet = L.keySet();
            for (Item key : keySet) {
                int val = L.get(key);
                sbd.append(key.getItemName()).
                        append("-").append(key.getItemWeight()).
                        append("-").append(key.getItemprice()).
                        append(":").append(val).append("|");
            }
            return sbd.toString();
        }
        return null;
    }
    public void addQQuser(QQuser A,String table) {
        String sql = "insert into " + table +
                "user(userID,userName,userOldName,usergroup,userData) values (?,?,?,?,?);";
        String userData = mapSave(A.getUserData());
        Object[] params = {A.getUserID(),A.getUserName(),A.getUserOldName(),A.getUsergroup(),userData};
        this.myexecuteUpdate(sql, params);
    }
    public void saveName(String name, QQuser u,String table){
        String sql = "update " + table +
                "user set userName=? where userID=? and usergroup=?";
        Object[] params = { name,u.getUserID(), u.getUsergroup() };
        this.myexecuteUpdate(sql, params);
    }
    public void saveData(LinkedHashMap<String,Object> L, QQuser u,String table){
        String sql = "update " + table +
                "user set userData=? where userID=? and usergroup=?";
        Object[] params = { mapSave(L),u.getUserID(),u.getUsergroup() };
//		u.getUserID();
        this.myexecuteUpdate(sql, params);
    }
    public void saveAtt(LinkedHashMap<String,Object> L, QQuser u){
        String sql = "update " + myDice.getState() +
                "user set userAttribute=? where userID=? and usergroup=?";
        Object[] params = { mapSave(L),u.getUserID(),u.getUsergroup() };
        this.myexecuteUpdate(sql, params);
    }
    public void saveItem(LinkedHashMap<Item,Integer> L, QQuser u){
        String sql = "update " + myDice.getState() +
                "user set userItem=? where userID=? and usergroup=?";
        Object[] params = { itemSave(L),u.getUserID(),u.getUsergroup() };
        this.myexecuteUpdate(sql, params);
    }
    //	public int saveSame(String val,String item,QQuser u){
//		StringBuilder sql;
//        sql = new StringBuilder("update ");
//        sql.append(myDice.getState());
//		sql.append("user set ");
//		sql.append(item);
//        sql.append("=? ");
//        sql.append("where userID=? and usergroup=?");
//		Object[] params = { val,u.getUserID(),u.getUsergroup() };
//		return this.myexecuteUpdate(sql.toString(), params);
//	}
    public void saveDiceState(String State){
        Object[] params = { State};
        this.myexecuteUpdate("update dice set state=?", params);
    }

}
