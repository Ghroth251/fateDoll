package com.fate.Dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fate.util.BaseDao;
import com.fate.bean.Dice;
import com.fate.bean.Item;
import com.fate.bean.QQuser;
import com.fate.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static com.fate.util.MapUtils.*;
import static lemocclient.Lemocclient.myDice;

public class UserDao extends BaseDao {
    private static EntityManager em = null;
    private static EntityTransaction tx = null;

    @SuppressWarnings("unchecked")
    public ArrayList<QQuser> getUserList(String table) {
        ArrayList<QQuser> custList= new ArrayList<>();
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            //创建query对象
            String jpql = "from QQuser where ujoinstate like ?1 ";
            Query query = em.createQuery(jpql);
            //对占位符赋值，从1开始
            query.setParameter(1,table);
            //查询并得到返回结果
            custList = (ArrayList<QQuser>) query.getResultList(); //得到唯一的结果集对象
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }
        return custList;
    }
    public QQuser getUserByUser(QQuser u) {
        QQuser u2=null;
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            //创建query对象
            String jpql = "from QQuser where userID like ?1 ";
            Query query = em.createQuery(jpql);
            //对占位符赋值，从1开始
            query.setParameter(1,u.getUserID());
            Object obj = query.getSingleResult();
            u2 = (QQuser)obj;
            tx.commit();
        }catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }
        return u2;
    }
    public void cuUser(QQuser A) {
        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(A);
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }
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

}
