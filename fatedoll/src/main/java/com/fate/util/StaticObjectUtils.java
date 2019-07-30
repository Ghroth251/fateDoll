package com.fate.util;

import com.fate.Service.DiceService;
import com.fate.Service.UserService;
import com.fate.Service.impl.DiceServiceImpl;
import com.fate.Service.impl.UserServiceImpl;
import com.fate.Dao.*;


import java.util.HashMap;

public class StaticObjectUtils {

    public static HashMap<String,String[]> cardMode;
    public static UserService uSv;
    public static DiceService diceSV;
    public static UnitsDao uDao;
    public static DataDao dDao;
    public static MechaDao mDao;
    public static boolean fateState = true;
    static {
        cardMode = new HashMap<>();
        uSv = new UserServiceImpl();
        uDao = new UnitsDao();
        dDao = new DataDao();
        mDao = new MechaDao();
        diceSV = new DiceServiceImpl();
    }
}
