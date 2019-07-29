package com.fate.util;

import com.fate.bean.DataMsg;
import com.fate.bean.QQuser;
import com.fate.dao.DataDao;
import com.fate.dao.UserDao;


import java.text.SimpleDateFormat;
import java.util.*;

import static com.fate.util.MapUtils.*;
import static lemocclient.Lemocclient.ARPGList;

public class FateUtils {
    static DataDao dDao =new DataDao();


    public static HashMap<String,String> splitMsg(String msg){
        HashMap<String,String> map = new HashMap<>();
        String comder;
        String value = null;
        String ordinal =null;
        //&#91;='['
        //&#93;=']'
        if(msg.contains("&#91;")) {
            ordinal=msg.substring(msg.indexOf("&#91;")+5,msg.indexOf("&#93;"));
            String fMsg = msg.substring(0,msg.indexOf("&#91;"));
            String lMsg = msg.substring(msg.indexOf("&#93;")+5);
            msg = fMsg+lMsg;
        }
        if (msg.contains(".")) {
            comder = msg.substring(0, msg.indexOf("."));
            value = msg.substring(msg.indexOf(".") + 1);
        } else if (msg.contains("。")) {
            if (msg.toCharArray()[msg.length() - 1] != '。') {
                comder = msg.substring(0, msg.indexOf("。"));
                value = msg.substring(msg.indexOf("。") + 1);
            } else {
                comder = msg.substring(0, msg.indexOf("。"));
                value = null;
            }
        } else {
            comder = msg;
        }
        comder = reAllSpace(comder);
        if(value != null){
            value = reSpace(value);
        }
        map.put("value",value);
        map.put("comder",comder);
        map.put("ordinal",ordinal);
        return map;
    }
    public static String groupMsg(String groupid,String msg){
        return "{\"act\": \"101\", \"groupid\": \"" + groupid + "\", \"msg\":\"" + msg + "\"}";
    }
    public static String reAllSpace(String key){
        return key.replaceAll(" ","");
    }
    public static String reSpace(String value){
        while(value.toCharArray()[0] == ' '){
            value = value.substring(value.indexOf(' ')+1);
        }
        return value;
    }
    private static String relastSpace(String value){
        while(value.toCharArray()[value.toCharArray().length-1] == ' '){
            value = value.substring(0,value.toCharArray().length-1);
        }
        return value;
    }
    public static String reflSpace(String value){
        String s = reSpace(value);
        s = relastSpace(s);
        return s;
    }
    public static String FateRoll(String name,int num,String sent,String skill){
        int[] doll = fateArray(4);
        String msg = "";
        if(sent != null){
            msg = "由于"+sent+",";
        }
        StringBuilder sbd = new StringBuilder(msg);
        sbd.append(name).append("掷出的命运骰子为（").append(doll[0]).append("，").append(doll[1]).append("，").append(doll[2]).append("，").append(doll[3]).append("），");
        if(skill==null){
            if(num != 0){
                sbd.append("与").append((num < 0) ? "" : "+").append(num).append("技能值合计为:").append(doll[0] + doll[1] + doll[2] + doll[3] + num);
            }else{
                sbd.append("合计为:").append(doll[0] + doll[1] + doll[2] + doll[3]);
            }
        }else{
            sbd.append("与").append(skill).append("(").append((num < 0) ? "" : "+").append(num).append(")技能合计为:").append(doll[0] + doll[1] + doll[2] + doll[3] + num);
        }
        System.out.println(sbd.toString());
        return sbd.toString();
    }
    public static boolean arrayRE(int[] num,int val){
        for (int aNum : num) {
            if (aNum == val) {
                return true;
            }
        }
        return false;
    }
    public static void stress(int num, LinkedHashMap<String, Object> lmap, String valName) {
        if(num<=0){
            lmap.put(valName, "□1□2");
        }else if(num<=2){
            lmap.put(valName, "□1□2□3");
        }else{
            lmap.put(valName, "□1□2□3□4");
        }
    }
    public static LinkedHashMap<String,String> getSent(String value) {
        String sent;
        LinkedHashMap<String,String> map;
        if (value.contains(" ") && (value.contains("+") || value.contains("-")
                || (value.toCharArray()[0] >= '0' || value.toCharArray()[0] <= '9'))) {
            sent = value.substring(value.indexOf(" ") + 1);
            value = value.substring(0, value.indexOf(" "));
            sent = reflSpace(sent);
        } else if (!value.contains(" ") && !value.contains("+") && !value.contains("-")
                && (value.toCharArray()[0] < '0' || value.toCharArray()[0] > '9')) {
            sent = value;
            value = "0";
            sent = reflSpace(sent);
        } else {
            sent = null;
        }
        map = new LinkedHashMap<>();
        map.put("sent",sent);
        map.put("value",value);
        return map;
    }
    public static int getFateNum(String value){
        int num;
        if(value.contains("+")){
            num = Integer.valueOf(value.substring(value.indexOf("+")+1));
        }else if(value.contains("-")){
            num = -(Integer.valueOf(value.substring(value.indexOf("-")+1)));
        }else{
            num = Integer.valueOf(value);
        }
        return num;
    }
    public static int[] fateArray(int num){
        Random ra = new Random();
        int[] doll = new int[num];
        for(int i=0;i<doll.length;i++){
            doll[i] = ra.nextInt(3)-1;
        }
        return doll;
    }
    public static LinkedHashMap<String,Object>getDataByName(String name,String type){
        DataMsg dataMsg = getDataObjectByName(name,type);
        if(dataMsg!=null){
            return dataMsg.getDdata();
        }else{
            return null;
        }
    }
    public static DataMsg getDataObjectByName(String name,String type){
        ArrayList<DataMsg> dList = dDao.datafind(name,type);
        DataMsg dataMsg;
        for(DataMsg d:dList){
            if(d.getDdata()!=null){
                dataMsg = d;
                return dataMsg;
            }
        }
        return null;
    }
    public static String replaceQuotes(String value){
        if(value.indexOf('"')!=-1){
            String s1 = value.substring(0,value.indexOf('"'));
            String s2 = value.substring(value.indexOf('"')+1);
            if(s2.indexOf('"')!=-1){
                String s3 = s2.substring(s2.indexOf('"')+1);
                s2 = s2.substring(0,s2.indexOf('"'));
                value = s1+'“'+s2+'”'+s3;
                replaceQuotes(value);
            }else{
                return "err";
            }
        }
        return value;
    }
    public static QQuser getARPGUserByUser(QQuser u){
        int aIndex = ARPGList.indexOf(u);
        QQuser a = null;
        if(aIndex!=-1){
            a = ARPGList.get(aIndex);
        }
        return a;
    }
    public static String getTodayDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return(df.format(new Date()));
    }
//    public static int[] getJrrp(QQuser u,LinkedHashMap<String,Object> map){
//        int[] doll = fateArray(100);
//        int[] dollNum = new int[3];
//        for(int i=0;i<doll.length;i++){
//            switch (doll[i]) {
//                case -1:
//                    dollNum[0]++;
//                    break;
//                case 0:
//                    dollNum[1]++;
//                    break;
//                default:
//                    dollNum[2]++;
//                    break;
//            }
//        }
//        map.put("Date",getTodayDate());
//        u.setUserData(map);
//        sqlDao.saveData(map,u,"QQ");
//        return dollNum;
//    }
}
