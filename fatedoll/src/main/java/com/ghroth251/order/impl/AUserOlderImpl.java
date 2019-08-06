package com.ghroth251.order.impl;

import com.ghroth251.bean.DataMsg;
import com.ghroth251.bean.Item;
import com.ghroth251.bean.QQuser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import static com.ghroth251.order.impl.PublicOrderImpl.fate;
import static com.ghroth251.util.FateUtils.*;
import static com.ghroth251.util.MapUtils.mapLoad;
import static com.ghroth251.util.StaticObjectUtils.*;


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
        return (sbd.toString());
    }
    public static String fz(QQuser u) {
        Random ra = new Random();
        int[] doll = new int[2];
        for(int i=0;i<doll.length;i++){
            doll[i] = ra.nextInt(3)-1;
        }
        String fzmsg =u.getUserName() + "掷出的命运骰子为（" + doll[0] + "，" + doll[1] + "），" +
                "合计为:" + (doll[0] + doll[1]);
        return ( fzmsg);
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
            if(u.getUserAttributeMap().get(val)!=null){
                sVal = Integer.parseInt(u.getUserAttributeMap().get(val).toString())+(u.getUserAttributeMap().get(val+"X")==null?0:Integer.parseInt(u.getUserAttributeMap().get(val+"X").toString()));
                sVal += num;
                sbd.append(sVal);
                if(sent!=null){
                    sbd.append(" ");
                    sbd.append(sent);
                }
                return fate(u,sbd.toString(),val);
            }else{
                sbd.append("技能名错误");
                return (sbd.toString());
            }
        }else{
            sbd.append("请输入要检定的技能名");
            return (sbd.toString());
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
            }else if(myDice.getState().equals("jj")){
                int sl = Integer.parseInt(L2.get("体魄").toString());
                stress(sl, L3,"生理压力");
                int xl = Integer.parseInt(L2.get("意志").toString());
                stress(xl, L3,"心理压力");
            }
            u.setUserAttribute(L2);
            u.setUserData(L3);
            uSv.saveData(u.getUserDataMap(), u,myDice.getState());
            uSv.saveAtt(u.getUserAttributeMap(), u);
            sbd.append("导卡成功！");
        }else{
            sbd.append("导卡指令错误！");
        }
        return (sbd.toString());
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
            return (sbd.toString());
        }else{
            return ("请输入要查询的关键字。");
        }
    }
    public static String addItem(QQuser u, String value) {
        if(value!=null){
            LinkedHashMap<Item,Integer>  h = (u.getUserItemMap()==null)? new LinkedHashMap<>():u.getUserItemMap();
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
                uSv.saveItem(u.getUserItemMap(), u);
                return ("新增物品-"+name+"成功！");
            }else{
                String key = value.substring(0,value.indexOf(","));
                int val = Integer.valueOf(value.substring(value.indexOf(",")+1));
                key = reAllSpace(key);
                h.put(new Item(key,0,0),(val==0)?1:val);
                u.setUserItem(h);
                uSv.saveItem(u.getUserItemMap(), u);
                return ("新增物品-"+key+"成功！");
            }
        }else{
            return ("请输入要新增的物品信息！！");
        }
    }
    public static String set(QQuser u, String value) {
        if(value!=null){
            if((value.indexOf(":"))!=-1){
                String key = value.substring(0,value.indexOf(":"));
                String val = value.substring(value.indexOf(":")+1);
                return set(u,key,val);
            }else{
                return ("格式不正确，格式set.xxx:vvv");
            }
        }else{
            return ("请输入要设置的属性哦，格式set.xxx:vvv");
        }
    }
    public static String set(QQuser u, String key, String val) {
        key = reflSpace(key);
        val = reflSpace(val);
        if(u.getUserDataMap()!=null&&u.getUserDataMap().get(key)!=null){
            u.setUserData(key,val);
            uSv.saveData(u.getUserDataMap(), u,myDice.getState());
            return ("修改成功");
        }else{
            return ("未找到修改项属性");
        }


    }
    public static String tra(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(value!=null){
            int index;
            if((index = groupList.indexOf(new QQuser(Integer.parseInt(value),u.getUsergroup()))) != -1){
                Set<String> keySet = groupList.get(index).getUserDataMap().keySet();
                for (String key : keySet) {
                    Object val = groupList.get(index).getUserDataMap().get(key);
                    sbd.append(key).append(":\\t").append(val).append("\\n");//+"\n"
                }
                return (groupList.get(index).getUserName()+"的属性为：\\n"+sbd.toString());
            }else{
                return ("该QQ在本群的人物不存在哦！");
            }
        }else{
            if(u.getUserDataMap()!=null){
                Set<String> keySet = u.getUserDataMap().keySet();
                for (String key : keySet) {
                    Object val = u.getUserDataMap().get(key);
                    sbd.append(key).append(":\\t").append(val).append("\\n");
                }
                sbd.append("装备为："+"\\n");
                Set<String> keySet3 = u.getUserEquipMap().keySet();
                if(u.getUserEquipMap()!=null){
                    for (String key : keySet3) {
                        Object val = u.getUserEquipMap().get(key);
                        sbd.append(key).append(":\\t").append(val).append("\\n");
                    }
                }
                if(u.getUserItemMap()!=null){
                    Set<Item> keySet2 = u.getUserItemMap().keySet();
                    sbd.append("持有的物品为："+"\\n");
                    sbd.append("物品名"+ "\\t" + "重量"+ "\\t" + "价格"+ "\\t" + "数量"+"\\n");
                    int sumW = 0;
                    int sumP = 0;
                    for (Item key : keySet2) {
                        int val = u.getUserItemMap().get(key);
                        sbd.append(key.getItemName()).append("\\t").append(key.getItemWeight()).append("kg\\t").append(key.getItemprice()).append("G\\t").append(val).append("\\n");
                        sumW+=key.getItemWeight()*val;
                        sumP+=key.getItemprice()*val;
                    }
                    sbd.append("总重量为").append(sumW).append("kg   总价格为").append(sumP).append("G");
                }
                return (u.getUserName()+"的属性为：\\n"+sbd.toString());
            }else{
                return (u.getUserName()+"没有数据！");
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
            uSv.saveData(u.getUserDataMap(), u,myDice.getState());
            return (u.getUserName()+"的属性保存成功！");
        }else{
            return ("请输入要设置的属性哦，格式setnew.xxx:vvv|xxx:vvv...");
        }
    }
}
