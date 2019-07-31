package com.fate.util;

import com.fate.Service.DiceService;
import com.fate.Service.UserService;
import com.fate.Service.impl.DiceServiceImpl;

import com.fate.Dao.*;
import com.fate.bean.DataMsg;
import com.fate.bean.Dice;
import com.fate.bean.QQuser;
import com.fate.bean.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;


public class StaticObjectUtils {

    private static ApplicationContext context = ApplicationContextUtil.getApplicationContext();
    public static UserService uSv;
    public static DiceService diceSv;
    public static UnitsDao uDao;
    public static DataDao dDao;
    public static MechaDao mDao;
    public static boolean fateState = true;
    public static ArrayList<QQuser> groupList = null;
    public static ArrayList<QQuser> ARPGList = new ArrayList<>();
    public static ArrayList<Unit> uList = new ArrayList<>();
    public static ArrayList<DataMsg> dList = new ArrayList<>();
    public static HashMap<String,String[]> cardMode;
    public static Dice myDice = null;
    static {
        cardMode = new HashMap<>();
        uDao = new UnitsDao();
        dDao = new DataDao();
        mDao = new MechaDao();
; }
}
