package com.fate.controller.impl;

import com.fate.bean.DataMsg;
import com.fate.bean.Item;
import com.fate.bean.QQuser;
import com.fate.bean.Unit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import static com.fate.controller.impl.PublicOrderImpl.fate;
import static com.fate.util.FateUtils.*;
import static com.fate.util.MapUtils.mapLoad;
import static com.fate.util.StaticObjectUtils.*;
import static com.fate.util.StaticObjectUtils.dDao;
import static com.fate.util.StaticObjectUtils.sqlDao;
import static com.fate.util.StaticObjectUtils.uDao;
import static lemocclient.Lemocclient.groupList;
import static lemocclient.Lemocclient.myDice;
import static lemocclient.Lemocclient.uList;

public class AUserOlderImpl {
    public static String dc(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder("");
        if(value==null){
            sbd.append("请输入抽牌的序号");
        }else{
            int num = Integer.parseInt(value);
            if(value.equals("")){
                sbd.append("请输入抽牌的序号");
            }else if(cardMode.get(u.getUsergroup())==null){
                sbd.append("没有洗入命运卡牌");
            }else if(num<1||num>9){
                sbd.append("请输入1到9之间的序号");
            }else{
                sbd.append("你抽到的卡牌是： ");
                sbd.append(cardMode.get(u.getUsergroup())[num]);
            }
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String fz(QQuser u) {
        Random ra = new Random();
        int[] doll = new int[2];
        for(int i=0;i<doll.length;i++){
            doll[i] = ra.nextInt(3)-1;
        }
        String fzmsg =u.getUserName() + "掷出的命运骰子为（" + doll[0] + "，" + doll[1] + "），" +
                "合计为:" + (doll[0] + doll[1]);
        return groupMsg(u.getUsergroup(), fzmsg);
    }
    public static String fr(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(value!=null){
            String sent = null;
            String val;
            int num = 0;
            if(value.contains(" ")){
                if(value.contains("+")){
                    val = value.substring(0,value.indexOf("+"));
                    num = Integer.parseInt(value.substring(value.indexOf("+")+1,value.indexOf(" ")));
                    sent = value.substring(value.indexOf(" ")+1);
                }else if(value.contains("-")){
                    val = value.substring(0,value.indexOf("-"));
                    num = -Integer.parseInt(value.substring(value.indexOf("-")+1,value.indexOf(" ")));
                    sent = value.substring(value.indexOf(" ")+1);
                }else{
                    val = value.substring(0,value.indexOf(" "));
                    sent = value.substring(value.indexOf(" ")+1);
                }
                sent = reflSpace(sent);
            }else{
                if(value.contains("+")){
                    val = value.substring(0,value.indexOf("+"));
                    num = Integer.parseInt(value.substring(value.indexOf("+")+1));
                }else if(value.contains("-")){
                    val = value.substring(0,value.indexOf("-"));
                    num = -Integer.parseInt(value.substring(value.indexOf("-")+1));
                }else{
                    val = value;
                }
            }
            int sVal;
            if(u.getUserAttribute().get(val)!=null){
                sVal = Integer.parseInt(u.getUserAttribute().get(val).toString())+(u.getUserAttribute().get(val+"X")==null?0:Integer.parseInt(u.getUserAttribute().get(val+"X").toString()));
                sVal += num;
                sbd.append(sVal);
                if(sent!=null){
                    sbd.append(" ");
                    sbd.append(sent);
                }
                return fate(u,sbd.toString(),val);
            }else{
                sbd.append("技能名错误");
                return groupMsg(u.getUsergroup(),sbd.toString());
            }
        }else{
            sbd.append("请输入要检定的技能名");
            return groupMsg(u.getUsergroup(),sbd.toString());
        }
    }
    public static String setu(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        String 	defineAtt = "默认值:0|";
        if(myDice.getState().equals("mf")){
            defineAtt = "魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|" +
                    "欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|" +
                    "亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|";
        }else if(myDice.getState().equals("jj")){
            defineAtt = "感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|" +
                    "欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|" +
                    "电子:0|驾驶:0|机师等级:1|";
        }
        if(value!=null){
            String att = value.substring(0,value.indexOf("-"));
            String fdata = value.substring(value.indexOf("-")+1);
            LinkedHashMap<String, Object> L1 = mapLoad(att);
            LinkedHashMap<String, Object> L2 = mapLoad(defineAtt);
            LinkedHashMap<String, Object> L3 = mapLoad(fdata);
            Set<String> keySet = L1.keySet();
            for (String k : keySet) {
                Object v = L1.get(k);
                L2.put(k, v);
            }
            if(myDice.getState().equals("mf")){
                int qh = Integer.parseInt(L2.get("魔力亲和").toString());
                if(qh==1){
                    L2.put("体魄X",1);
                    L2.put("打斗X",1);
                    L2.put("运动X",1);
                }else if(qh==2){
                    L2.put("体魄X",1);
                    L2.put("运动X",1);
                }else if(qh==3){
                    L2.put("体魄X",1);
                }else if(qh==5){
                    L2.put("体魄X",-1);
                }else if(qh==6){
                    L2.put("体魄X",-2);
                    L2.put("魔法X",1);
                }else if(qh==7){
                    L2.put("体魄X",-2);
                    L2.put("运动X",-1);
                    L2.put("魔法X",1);
                    L2.put("察觉X",1);
                }
                int sl = Integer.parseInt(L2.get("体魄").toString())+(L2.get("体魄X")==null?0:Integer.parseInt(L2.get("体魄X").toString()));
                stress(sl, L3,"生理压力");
                int xl = Integer.parseInt(L2.get("意志").toString())+(L2.get("意志X")==null?0:Integer.parseInt(L2.get("意志X").toString()));
                stress(xl, L3,"心理压力");
                Unit u1 = new Unit(u.getUserName(),L3.get("生理压力").toString(),L3.get("心理压力").toString(),Integer.parseInt(L3.get("魔力压力").toString()),Integer.parseInt(L3.get("魔力压力").toString()),0,u.getUserID(),u.getUsergroup());
                if(uDao.findUnit(u1)!=0){
                    uDao.saveUnit(u1);
                }else{
                    uDao.saveUnit2(u1);
                }
            }else if(myDice.getState().equals("jj")){
                int sl = Integer.parseInt(L2.get("体魄").toString());
                stress(sl, L3,"生理压力");
                int xl = Integer.parseInt(L2.get("意志").toString());
                stress(xl, L3,"心理压力");
            }
            u.setUserAttribute(L2);
            u.setUserData(L3);
            sqlDao.saveData(u.getUserData(), u,myDice.getState());
            sqlDao.saveAtt(u.getUserAttribute(), u);
            sbd.append("导卡成功！");
        }else{
            sbd.append("导卡指令错误！");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String hf(QQuser u, String value) {
        if(u.getUserID().equals("553859318")){
            StringBuffer sbf = new StringBuffer("自然恢复列表：\\n");
            switch (value) {
                case "mp":
                    for (Unit A : uList) {
                        if (A.getuType() == 2) {
                            if (A.getuMp() < A.getuMaxmp()) {
                                A.setuMp(A.getuMp() + 1);
                                sbf.append(A.getuFromUser()).append("的").append(A.getuName()).append("充能了1魔力，当前").append(A.getuMp()).append("点\\n");
                            }
                        } else {
                            if (A.getuMp() < A.getuMaxmp()) {
                                A.setuMp(A.getuMaxmp());
                                sbf.append(A.getuName()).append("的魔力恢复完毕\\n");
                            }
                        }
                    }
                    break;
                case "hp":
                    for (Unit A : uList) {
                        if (A.getuType() != 2 && (A.getuHp().contains("√") || A.getuSp().contains("√"))) {
                            A.setuHp(A.getuHp().replace("√", "□"));
                            A.setuSp(A.getuSp().replace("√", "□"));
                            sbf.append(A.getuName()).append("的生理压力与精神压力恢复完毕\\n");
                        }
                    }
                    break;
                default:
                    sbf.append("输入错误\\n");
                    break;
            }
            uDao.saveUnitsMsg(uList);
            sbf = new StringBuffer(sbf.substring(0,sbf.length()-2));
            return groupMsg(u.getUsergroup(),sbf.toString());
        }else{
            return groupMsg(u.getUsergroup(),"你没有权限这么做");
        }
    }
    public static String fd(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(value!=null){
            String type;
            if(value.contains(".")){
                type =value.substring(0,value.indexOf("."));
                value = value.substring(value.indexOf(".")+1);
                value = replaceQuotes(value);
                if(!value.equals("err")){
                    ArrayList<DataMsg> dList = dDao.datafind(value,type);
                    DataMsg dataMsg = new DataMsg();
                    int index = 0;
                    for(DataMsg d:dList){
                        index++;
                        if(index == 1){
                            dataMsg = d;
                        }
                    }
                    if(index == 1){
                        sbd.append("条目名:").append(dataMsg.getName()).append("\\n");
                        sbd.append("类型:").append(dataMsg.getType()).append("\\n");
                        sbd.append(dDao.huan(dataMsg.getMsg()));
                    }else if(index > 1){
                        sbd.append("已找到相关条目").append(index).append("条：\\n");
                        for(DataMsg d:dList){
                            sbd.append("  ").append(d.getName()).append("[").append(d.getType()).append("]\\n");
                        }
                        sbd = new StringBuilder(sbd.substring(0,sbd.length()-2));
                    }else{
                        sbd.append("未找到相关条目");
                    }
                }else{
                    sbd.append("错误，请不要输入单个英文双引号");
                }
            }
            if(value.equals("")||value.equals(" ")){
                sbd.append("请输入要查询的关键字。");
            }
            return groupMsg(u.getUsergroup(),sbd.toString());
        }else{
            return groupMsg(u.getUsergroup(),"请输入要查询的关键字。");
        }
    }
    public static String uList(QQuser u, String value) {
        ArrayList<Unit> userUnit = new ArrayList<>();
        ArrayList<Unit> npcUnit = new ArrayList<>();
        ArrayList<Unit> itemUnit = new ArrayList<>();
        StringBuilder sbd = new StringBuilder();
        for(Unit A:uList){
            switch(A.getuType()){
                case 0:
                    userUnit.add(A);
                    break;
                case 1:
                    npcUnit.add(A);
                    break;
                case 2:
                    itemUnit.add(A);
                    break;
            }
        }
        if(value!=null&&value.equals("item")){
            sbd.append("物品状态："+"\\n");
            sbd.append("物品名"+ "\\t\\t" + "魔力压力"+ "\\t" + "所属"+"\\n");
            for(Unit A:itemUnit){
                sbd.append(A.getuName()).append((A.getuName().length() > 2) ? "\\t" : "\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\t\\t").append(A.getuFromUser()).append("\\n");
            }
        }else{
            sbd.append("单位状态："+"\\n");
            sbd.append("单位名"+ "\\t\\t" + "生理压力"+ "\\t" + "心理压力"+ "\\t" + "魔力压力"+"\\n");
            sbd.append("—————————————PC单位——————————————————"+"\\n");
            for(Unit A:userUnit){
                sbd.append(A.getuName()).append((A.getuName().length() > 3) ? "\\t" : "\\t\\t").append(A.getuHp()).append((A.getuHp().length() > 4) ? "\\t" : "\\t\\t").append(A.getuSp()).append("\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\n");
            }
            sbd.append("—————————————NPC单位——————————————————"+"\\n");
            for(Unit A:npcUnit){
                sbd.append(A.getuName()).append((A.getuName().length() > 3) ? "\\t" : "\\t\\t").append(A.getuHp()).append((A.getuHp().length() > 4) ? "\\t" : "\\t\\t").append(A.getuSp()).append("\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\n");
            }
        }
        uDao.saveUnitsMsg(uList);
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String addItem(QQuser u, String value) {
        if(value!=null){
            LinkedHashMap<Item,Integer>  h = (u.getUserItem()==null)? new LinkedHashMap<>():u.getUserItem();
            if(value.contains("-")){
                String key = value.substring(0,value.indexOf(","));
                int val = Integer.valueOf(value.substring(value.indexOf(",")+1));
                String name = key.substring(0,key.indexOf("-"));
                key = key.substring(key.indexOf("-")+1);
                int weight = Integer.valueOf(key.substring(0,key.indexOf("-")));
                key = key.substring(key.indexOf("-")+1);
                int price = Integer.valueOf(key);
                name = reAllSpace(name);
                h.put(new Item(name,weight,price),(val==0)?1:val);
                u.setUserItem(h);
                sqlDao.saveItem(u.getUserItem(), u);
                return groupMsg(u.getUsergroup(),"新增物品-"+name+"成功！");
            }else{
                String key = value.substring(0,value.indexOf(","));
                int val = Integer.valueOf(value.substring(value.indexOf(",")+1));
                key = reAllSpace(key);
                h.put(new Item(key,0,0),(val==0)?1:val);
                u.setUserItem(h);
                sqlDao.saveItem(u.getUserItem(), u);
                return groupMsg(u.getUsergroup(),"新增物品-"+key+"成功！");
            }
        }else{
            return groupMsg(u.getUsergroup(),"请输入要新增的物品信息！！");
        }
    }
    public static String set(QQuser u, String value) {
        if(value!=null){
            if((value.indexOf(":"))!=-1){
                String key = value.substring(0,value.indexOf(":"));
                String val = value.substring(value.indexOf(":")+1);
                return set(u,key,val);
            }else{
                return groupMsg(u.getUsergroup(),"格式不正确，格式set.xxx:vvv");
            }
        }else{
            return groupMsg(u.getUsergroup(),"请输入要设置的属性哦，格式set.xxx:vvv");
        }
    }
    public static String set(QQuser u, String key,String val) {
        key = reflSpace(key);
        val = reflSpace(val);
        if(u.getUserData()!=null&&u.getUserData().get(key)!=null){
            u.setUserData(key,val);
            sqlDao.saveData(u.getUserData(), u,myDice.getState());
            return groupMsg(u.getUsergroup(),"修改成功");
        }else{
            return groupMsg(u.getUsergroup(),"未找到修改项属性");
        }


    }
    public static String tra(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(value!=null){
            int index;
            if((index = groupList.indexOf(new QQuser(value,u.getUsergroup()))) != -1){
                Set<String> keySet = groupList.get(index).getUserData().keySet();
                for (String key : keySet) {
                    Object val = groupList.get(index).getUserData().get(key);
                    sbd.append(key).append(":\\t").append(val).append("\\n");//+"\n"
                }
                return groupMsg(u.getUsergroup(),groupList.get(index).getUserName()+"的属性为：\\n"+sbd.toString());
            }else{
                return groupMsg(u.getUsergroup(),"该QQ在本群的人物不存在哦！");
            }
        }else{
            if(u.getUserData()!=null){
                Set<String> keySet = u.getUserData().keySet();
                for (String key : keySet) {
                    Object val = u.getUserData().get(key);
                    sbd.append(key).append(":\\t").append(val).append("\\n");
                }
                sbd.append("装备为："+"\\n");
                Set<String> keySet3 = u.getUserEquip().keySet();
                if(u.getUserEquip()!=null){
                    for (String key : keySet3) {
                        Object val = u.getUserEquip().get(key);
                        sbd.append(key).append(":\\t").append(val).append("\\n");
                    }
                }
                if(u.getUserItem()!=null){
                    Set<Item> keySet2 = u.getUserItem().keySet();
                    sbd.append("持有的物品为："+"\\n");
                    sbd.append("物品名"+ "\\t" + "重量"+ "\\t" + "价格"+ "\\t" + "数量"+"\\n");
                    int sumW = 0;
                    int sumP = 0;
                    for (Item key : keySet2) {
                        int val = u.getUserItem().get(key);
                        sbd.append(key.getItemName()).append("\\t").append(key.getItemWeight()).append("kg\\t").append(key.getItemprice()).append("G\\t").append(val).append("\\n");
                        sumW+=key.getItemWeight()*val;
                        sumP+=key.getItemprice()*val;
                    }
                    sbd.append("总重量为").append(sumW).append("kg   总价格为").append(sumP).append("G");
                }
                return groupMsg(u.getUsergroup(),u.getUserName()+"的属性为：\\n"+sbd.toString());
            }else{
                return groupMsg(u.getUsergroup(),u.getUserName()+"没有数据！");
            }

        }
    }
    public static String setnew(QQuser u, String value) {
        LinkedHashMap<String,Object> h = new LinkedHashMap<>();
        if(value!=null){
            h.put("姓名",u.getUserName());
            String values = value;
            while(values.contains("|")){
                String key = values.substring(0,values.indexOf(":"));
                String val = values.substring(values.indexOf(":")+1,values.indexOf("|"));
                //System.out.println("key为："+key);
                key = reflSpace(key);
                val = reflSpace(val);
                h.put(key,val);
                values = values.substring(values.indexOf("|")+1);
            }
            u.setUserData(h);
            sqlDao.saveData(u.getUserData(), u,myDice.getState());
            return groupMsg(u.getUsergroup(),u.getUserName()+"的属性保存成功！");
        }else{
            return groupMsg(u.getUsergroup(),"请输入要设置的属性哦，格式setnew.xxx:vvv|xxx:vvv...");
        }
    }
}
