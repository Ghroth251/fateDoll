package com.fate.Service.impl;

import com.fate.Dao.UserDao;
import com.fate.Service.UserService;
import com.fate.bean.Item;
import com.fate.bean.QQuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import static com.fate.util.MapUtils.*;
import static com.fate.util.StaticObjectUtils.*;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserDao uDo;

    @Override
    public QQuser getUserByUser(QQuser u) {
        return uDo.findByUserIDLikeAndUsergroupLike(u.getUserID(),u.getUsergroup());
    }
    @Override
    public ArrayList<QQuser> getUserList(String table) {
        return uDo.findByUjoinstateLike(table);
    }
    @Override
    public void addQQuser(QQuser A, String table) {
        uDo.save(new QQuser(A.getUserID(),
                A.getUserName(),A.getUserOldName(),
                A.getUsergroup(),table));
    }

    @Override
    public void saveName(String name, QQuser u, String table) {
        System.out.println(new QQuser(u.getId(),u.getUserID(),name,u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),u.getUserData(),
                u.getUserItem(), u.getUserEquip(),table));
        uDo.save(new QQuser(u.getId(),u.getUserID(),name,u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),u.getUserData(),
                u.getUserItem(), u.getUserEquip(),table));
    }

    @Override
    public void saveData(LinkedHashMap<String, Object> L, QQuser u, String table) {
        uDo.save(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),mapSave(L),
                u.getUserItem(), u.getUserEquip(),table));
    }

    @Override
    public void saveAtt(LinkedHashMap<String, Object> L, QQuser u) {
        uDo.save(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), mapSave(L),u.getUserData(),
                u.getUserItem(), u.getUserEquip(),myDice.getState())) ;
    }

    @Override
    public void saveItem(LinkedHashMap<Item, Integer> L, QQuser u) {
        uDo.save(new QQuser(u.getId(),u.getUserID(),u.getUserName(),u.getUserOldName(),
                u.getUsergroup(), u.getUserAttribute(),u.getUserData(),
                itemSave(L), u.getUserEquip(),myDice.getState()));
    }

}
