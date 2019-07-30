package com.fate.Service;

import com.fate.bean.Item;
import com.fate.bean.QQuser;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface UserService {

    QQuser getUserByUser(QQuser u);
    ArrayList<QQuser> getUserList(String table);
    void addQQuser(QQuser A, String table);
    void saveName(String name, QQuser u, String table);
    void saveData(LinkedHashMap<String, Object> L, QQuser u, String table);
    void saveAtt(LinkedHashMap<String,Object> L, QQuser u);
    void saveItem(LinkedHashMap<Item, Integer> L, QQuser u);

}
