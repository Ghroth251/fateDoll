package com.fate.util;

import com.fate.dao.*;


import java.util.HashMap;

public class StaticObjectUtils {

    public static HashMap<String,String[]> cardMode;
    public static UserDao sqlDao;
    public static UnitsDao uDao;
    public static DataDao dDao;
    public static MechaDao mDao;
    public static boolean fateState = true;
    static {
        cardMode = new HashMap<>();
        sqlDao = new UserDao();
        uDao = new UnitsDao();
        dDao = new DataDao();
        mDao = new MechaDao();
    }
}
