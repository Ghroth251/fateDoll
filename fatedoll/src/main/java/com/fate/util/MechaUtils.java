package com.fate.util;

import com.fate.bean.Mecha;
import com.fate.bean.QQuser;
import com.fate.Dao.MechaDao;

import java.util.LinkedHashMap;
import java.util.Set;

import static com.fate.util.FateUtils.*;
import static com.fate.util.MapUtils.*;

public class MechaUtils {

    public static Mecha getMechaByUser(QQuser u){
        MechaDao mDao = new MechaDao();
        LinkedHashMap<String, Object> uid = new LinkedHashMap<>();
        uid.put("qq",u.getUserID());
        uid.put("group",u.getUsergroup());
        return mDao.getMechaById(mapSave(uid));
    }
    public static int getUserHit(LinkedHashMap<String,Object> att,String weaponAtt){
        int userHit;
        int userSkill;
        if(weaponAtt.contains("近战")){
            userSkill = Integer.parseInt(att.get("打斗")==null?"0":(String)att.get("打斗"));
        }else{
            userSkill = Integer.parseInt(att.get("射击")==null?"0":(String)att.get("射击"));
        }
        userHit = userSkill/2+userSkill%2+
                Integer.parseInt((String)att.get("机师等级"));
        return userHit;
    }

    public static int[] hitOperation(LinkedHashMap<String,Object> att, int mechaHit,LinkedHashMap<String,Object> w){
        int[] hit = new int[3];
        String weaponHit = (String)w.get("命中");
        hit[0]=Integer.parseInt(weaponHit==null? "0":weaponHit);
        hit[1]=mechaHit;
        String weaponAtt = w.get("武器类型")==null?
                "近战":(String)w.get("武器类型");
        hit[2] = getUserHit(att,weaponAtt);
        return hit;
    }
    public static Mecha generateMecha(String value){
        String att;
        String val = value;
        String weapon1=null;
        String weapon2=null;
        String weapon3=null;
        String weapon4=null;
        Mecha mecha = new Mecha();
        String 	defineAtt = "制造商:0|类型:0|机体名字:0|装配值:0|能量:0|结构:0|" +
                "机动:0|感应:0|装配值:0|特殊装备1:0|特殊装备2:0|特殊装备3:0|" +
                "特技1:0|特技2:0|特技3:0|特技4:0|最大装甲压力:0|最大能量压力:0|";
        if(value.contains("-Weapon1-")){
            att = val.substring(0,val.indexOf("-Weapon1-"));
            val = value.substring(value.indexOf("-Weapon1-")+9);
            if(value.contains("-Weapon2-")){
                weapon1 = val.substring(0,val.indexOf("-Weapon2-"));
                val = value.substring(value.indexOf("-Weapon2-")+9);
                if(value.contains("-Weapon3-")){
                    weapon2 = val.substring(0,val.indexOf("-Weapon3-"));
                    val = value.substring(value.indexOf("-Weapon3-")+9);
                    if(value.contains("-Weapon4-")){
                        weapon3 = val.substring(0,val.indexOf("-Weapon4-"));
                        weapon4 = value.substring(value.indexOf("-Weapon4-")+9);
                    }else{
                        weapon3 = val;
                    }
                }else{
                    weapon2 = val;
                }
            }else{
                weapon1 = val;
            }
        }else{
            att=val;
        }
        LinkedHashMap<String, Object> dAttMap = mapLoad(defineAtt);
        LinkedHashMap<String, Object> attMap = mapLoad(att);
        Set<String> keySet = attMap.keySet();
        for (String k : keySet) {
            Object v = attMap.get(k);
            dAttMap.put(k, v);
        }
        mecha.setMname((String)dAttMap.get("机体名字"));
        mecha.setmEn(Integer.parseInt((String)dAttMap.get("能量")));
        mecha.setmSt(Integer.parseInt((String)dAttMap.get("结构")));
        mecha.setmMo(Integer.parseInt((String)dAttMap.get("机动")));
        mecha.setmSe(Integer.parseInt((String)dAttMap.get("感应")));
        dAttMap.remove("机体名字");
        dAttMap.remove("能量");
        dAttMap.remove("结构");
        dAttMap.remove("机动");
        dAttMap.remove("感应");
        mecha.setAttData(dAttMap);
        mecha.setWeapon1Data(weapon1==null?null:mapLoad(weapon1));
        mecha.setWeapon2Data(weapon2==null?null:mapLoad(weapon2));
        mecha.setWeapon3Data(weapon3==null?null:mapLoad(weapon3));
        mecha.setWeapon4Data(weapon4==null?null:mapLoad(weapon4));
        return mecha;
    }
    public static LinkedHashMap<String,Object> getUidByUser(QQuser u){
        LinkedHashMap<String, Object> uid = new LinkedHashMap<>();
        uid.put("qq",u.getUserID());
        uid.put("group",u.getUsergroup());
        return uid;
    }
    public static int getUserAvo(LinkedHashMap<String,Object> att){
        int userPerception = Integer.parseInt(
                att.get("感知")==null?"0":(String)att.get("感知"));
        int userDriverLever = Integer.parseInt(
                att.get("机师等级")==null?"0":(String)att.get("机师等级"));
        return ((userPerception-1)/2+(userPerception-1)%2+userDriverLever);
    }
    public static int getMechaAvo(int mechaMo,int mechaSe){
        return mechaMo+(mechaSe-1)/2+(mechaSe-1)%2;
    }
    public static int getDataMechaAvo(String val){
        int mechaAvo;
        String mechaName = val.substring(val.indexOf(",")+1);
        if(mechaName.toCharArray()[0]=='-'||mechaName.toCharArray()[0]=='+'||
                (mechaName.toCharArray()[0]<'9'&&mechaName.toCharArray()[0]>'0')){
            mechaAvo = getFateNum(mechaName);
        }else{
            int mechaSe =Integer.parseInt((String)
                            getDataByName(mechaName, "机甲").get("感应"));
            int mechaMo =Integer.parseInt((String)
                    getDataByName(mechaName, "机甲").get("机动"));
            mechaAvo = mechaName==""?0:getMechaAvo(mechaMo,mechaSe);
        }
        return mechaAvo;
    }


}
