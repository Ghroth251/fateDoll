package com.ghroth251.util;

import com.ghroth251.Dao.*;
import com.ghroth251.Service.DiceService;
import com.ghroth251.Service.MechaService;
import com.ghroth251.Service.UserService;
import com.ghroth251.bean.DataMsg;
import com.ghroth251.bean.Dice;
import com.ghroth251.bean.QQuser;
import org.springframework.context.ApplicationContext;
import webService.client.JcqWebService;
import webService.client.JcqWebServiceService;

import java.util.ArrayList;
import java.util.HashMap;


public class StaticObjectUtils {


    //    private static ApplicationContext context = ApplicationContextUtil.getApplicationContext();
    public static UserService uSv;
    public static DiceService diceSv;
    public static DataDao dDao;
    public static MechaService mSv;
    public static boolean fateState = true;
    public static ArrayList<QQuser> groupList = null;
    public static ArrayList<QQuser> ARPGList = new ArrayList<>();
    public static ArrayList<DataMsg> dList = new ArrayList<>();
    public static HashMap<Long,String[]> cardMode;
    public static Dice myDice = null;
    static {
        cardMode = new HashMap<>();
        dDao = new DataDao();
    }
}
