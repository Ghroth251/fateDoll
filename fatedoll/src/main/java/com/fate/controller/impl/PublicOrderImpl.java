package com.fate.controller.impl;

import com.fate.bean.QQuser;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import static com.fate.util.FateUtils.*;
import static lemocclient.Lemocclient.*;
import static com.fate.util.StaticObjectUtils.*;

public class PublicOrderImpl {


    public static String fnn(QQuser u, String value) {
        QQuser a = getARPGUserByUser(u);
        if(value!=null){
            u.setUserName(value);
            uSv.saveName(value,u,"QQ");
            if(a!=null){
                a.setUserName(value);
                uSv.saveName(value,u,myDice.getState());
            }
            return groupMsg(u.getUsergroup(),u.getUserOldName()+"更改名字为："+u.getUserName());
        }else{
            u.setUserName(u.getUserOldName());
            uSv.saveName(u.getUserOldName(),u,"QQ");
            if(a!=null){
                a.setUserName(u.getUserOldName());
                uSv.saveName(u.getUserOldName(),u,myDice.getState());
            }
            return groupMsg(u.getUsergroup(),u.getUserOldName()+"已恢复原名");
        }
    }
    public static String fate(QQuser u, String value,String skill) {
        int num;
        String sent;
        if (value != null) {
            LinkedHashMap<String,String> map = getSent(value);
            value = map.get("value");
            sent = map.get("sent");
            num = getFateNum(value);
            return groupMsg(u.getUsergroup(),FateRoll(u.getUserName(),num,sent,skill));
        }else{
            return groupMsg(u.getUsergroup(),FateRoll(u.getUserName(),0, null,skill));
        }
    }
    public static String fon(QQuser u, String value) {
        if(value!=null&&u.getUserID().equals("553859318")&&value.equals(myDice.getCode())){
            fateState = true;
            return groupMsg(u.getUsergroup(),myDice.getName()+"已开启");
        }else{
            return groupMsg(u.getUsergroup(),"你没有权限这么做");
        }
    }
    public static String foff(QQuser u, String value) {
        if(value!=null&&u.getUserID().equals("553859318")&&value.equals(myDice.getCode())){
            fateState = false;
            return groupMsg(u.getUsergroup(),myDice.getName()+"已关闭");
        }else{
            return groupMsg(u.getUsergroup(),"你没有权限这么做");
        }
    }
    public static String addPC(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(value!=null&&u.getUserID().equals("553859318")) {
            value = reAllSpace(value);
            QQuser u2 = uSv.getUserByUser(new QQuser(value, u.getUsergroup()));
            if (u2 != null&&ARPGList.indexOf(u2)==-1) {
                ARPGList.add(u2);
                sbd.append("添加参团角色完成");
                System.out.println("u2"+u2);
                uSv.addQQuser(u2,myDice.getState());
            } else {
                sbd.append("出错了！");
            }
        }else{
            sbd.append("你没有权限这么做");
        }
        return groupMsg(u.getUsergroup(), sbd.toString());
    }
    public static String fatedbLink(QQuser u) {
        if(u.getUserID().equals("553859318")){
            groupList = new ArrayList<>();
            groupList = uSv.getUserList("QQ");
            ARPGList = new ArrayList<>();
            ARPGList = uSv.getUserList(myDice.getState());
            uList = new ArrayList<>();
            uList = uDao.loadUnits();
            return groupMsg(u.getUsergroup(),"与数据库同步成功");
        }else{
            return groupMsg(u.getUsergroup(),"你没有权限这么做");
        }
    }
    public static String cset(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder("");
        if(u.getUserID().equals("553859318")){
            int level = value!=null&&!value.equals("") ?Integer.parseInt(value):4;
            String[] card;
            String cardNum;
            switch(level){
                case 1:{
                    card = new String[]{"大成功","成功","成功","成功","成功","成功","成功","成功","失败"};
                    cardNum = "1张大成功，7张成功，1张失败";
                    break;
                }
                case 2:{
                    card = new String[]{"大成功","成功","成功","成功","成功","成功","成功","失败","失败"};
                    cardNum = "1张大成功，6张成功，2张失败";
                    break;
                }
                case 3:{
                    card = new String[]{"大成功","成功","成功","成功","成功","成功","失败","失败","失败"};
                    cardNum = "1张大成功，5张成功，3张失败";
                    break;
                }
                case 4:{
                    card = new String[]{"大成功","成功","成功","成功","成功","失败","失败","失败","大失败"};
                    cardNum = "1张大成功，4张成功，3张失败，1张大失败";
                    break;
                }
                case 5:{
                    card = new String[]{"大成功","成功","成功","成功","失败","失败","失败","失败","大失败"};
                    cardNum = "1张大成功，3张成功，4张失败，1张大失败";
                    break;
                }
                case 6:{
                    card = new String[]{"成功","成功","成功","失败","失败","失败","失败","失败","大失败"};
                    cardNum = "3张成功，5张失败，1张大失败";
                    break;
                }
                case 7:{
                    card = new String[]{"成功","成功","失败","失败","失败","失败","失败","失败","大失败"};
                    cardNum = "2张成功，6张失败，1张大失败";
                    break;
                }
                case 8:{
                    card = new String[]{"成功","失败","失败","失败","失败","失败","失败","失败","大失败"};
                    cardNum = "1张成功，7张失败，1张大失败";
                    break;
                }
                default:{
                    card = new String[]{"大成功","成功","成功","成功","成功","失败","失败","失败","大失败"};
                    cardNum = "1张大成功，4张成功，3张失败，1张大失败";
                    break;
                }
            }
            Random ra = new Random();
            String[] cardDeck = new String[9];
            int[] num1 = new int[]{0,0,0,0,0,0,0,0,0};
            int raNum;
            for(int i=0;i<9;i++){
                do{
                    raNum = ra.nextInt(9)+1;
                }
                while(arrayRE(num1,raNum));
                num1[i] = raNum;
                cardDeck[num1[i]-1]=card[i];
            }
            cardMode.put(u.getUsergroup(),cardDeck);
            sbd.append("抽卡已经设定完成,已洗入");
            sbd.append(cardNum);
            sbd.append("，请抽取");
            for (String aCardDeck : cardDeck) {
                System.out.println(aCardDeck);
            }
        }else{
            sbd.append("只有GM才能开始洗牌！");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String diceQH(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder("");
        if(value!=null&&u.getUserID().equals("553859318")){
            switch (value) {
                case "mf":
                    sbd.append("成功切换到魔法模式");
                    break;
                case "jj":
                    sbd.append("成功切换到机甲模式");
                    break;
                default:
                    return groupMsg(u.getUsergroup(), "模式错误！");
            }
            diceSv.saveDiceState(value);
            myDice = diceSv.diceLoad();
            ARPGList = uSv.getUserList(myDice.getState());
        }else{
            sbd.append("您没有权限这么做！");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String jrrp(QQuser u) {
        StringBuilder sbd = new StringBuilder();
        LinkedHashMap<String,Object> map = u.getUserDataMap()==null?
                new LinkedHashMap<>():u.getUserDataMap();
        if(map.get("Date")==null||!map.get("Date").equals(getTodayDate())){
            int[] doll = fateArray(100);
            int[] dollNum = new int[3];
            for (int aDoll : doll) {
                switch (aDoll) {
                    case -1:
                        dollNum[0]++;
                        break;
                    case 0:
                        dollNum[1]++;
                        break;
                    default:
                        dollNum[2]++;
                        break;
                }
            }
            map.put("Date",getTodayDate());
            u.setUserData(map);
            uSv.saveData(map,u,"QQ");
            sbd.append("在一百次的命运中，").append(u.getUserName()).
                    append("得到了").append(dollNum[2]).append("次的‘1’").
                    append(dollNum[1]).append("次的‘0’与").append(dollNum[0]).
                    append("次的‘-1’。\\n").append(u.getUserName()).
                    append("的命运合计为【").append(dollNum[2] - dollNum[0]).append("】。");
        }else{
            sbd.append("今日已经测过了。");
        }
        return groupMsg(u.getUsergroup(), sbd.toString());
    }

}
