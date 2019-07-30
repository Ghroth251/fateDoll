package com.fate.Service.impl;

import com.fate.Service.UserService;
import com.fate.bean.Item;
import com.fate.bean.QQuser;
import com.fate.Dao.UserDao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.fate.util.MapUtils.*;
import static lemocclient.Lemocclient.myDice;

public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDao();
    @Override
    public QQuser getUserByUser(QQuser u) {
        return userDao.getUserByUser(u);
    }
    @Override
    public ArrayList<QQuser> getUserList(String table) {
        return userDao.getUserList(table);
    }
    @Override
    public void addQQuser(QQuser A, String table) {
        userDao.cuUser(new QQuser(A.getUserID(),
                A.getUserName(),A.getUserOldName(),
                A.getUsergroup(),table));
    }

    @Override
    public void saveName(String name, QQuser u, String table) {
        userDao.cuUser(new QQuser(u.getId(),u.getUserID(),name,u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),u.getUserData(),
                u.getUserItem(), u.getUserEquip(),table));
    }

    @Override
    public void saveData(LinkedHashMap<String, Object> L, QQuser u, String table) {
        userDao.cuUser(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),mapSave(L),
                u.getUserItem(), u.getUserEquip(),table));
    }

    @Override
    public void saveAtt(LinkedHashMap<String, Object> L, QQuser u) {
        userDao.cuUser(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), mapSave(L),u.getUserData(),
                u.getUserItem(), u.getUserEquip(),myDice.getState())) ;
    }

    @Override
    public void saveItem(LinkedHashMap<Item, Integer> L, QQuser u) {
        userDao.cuUser(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),u.getUserData(),
                itemSave(L), u.getUserEquip(),myDice.getState()));
    }

}
