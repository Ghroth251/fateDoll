package com.fate.controller.impl;

import com.fate.bean.Mecha;
import com.fate.bean.QQuser;

import java.util.LinkedHashMap;
import java.util.Objects;

import static com.fate.util.FateUtils.*;
import static com.fate.util.MapUtils.mapSave;
import static com.fate.util.MechaUtils.*;
import static com.fate.util.StaticObjectUtils.mDao;

public class JjOrderImpl {
    public static String avo(String value, String ordinal, QQuser a) {
        StringBuilder sbd = new StringBuilder();
        String avoerName,sent = null;
        int userAvo;
        int avoCorrection = 0;
        int mechaAvo = 0;
        if(value!=null){
            if(value.contains(" ")){
                avoCorrection = getFateNum(value.substring(0, value.indexOf(" ")));
                LinkedHashMap<String, String> map = getSent(value);
                sent = map.get("sent");
            }else{
                avoCorrection = getFateNum(value);
            }
        }
        if(ordinal!=null){
            String mechaData;
            if(ordinal.contains(",")){
                avoerName = ordinal.substring(0,ordinal.indexOf(","));
                mechaData = ordinal.substring(ordinal.indexOf(",")+1);
                mechaAvo = getDataMechaAvo(mechaData);
            }else{
                avoerName = ordinal;
            }
            if ( getDataObjectByName(avoerName,"人物")== null) {
                sbd.append("查无此人");
                return groupMsg(a.getUsergroup(), sbd.toString());
            }else{
                LinkedHashMap<String,Object> userAtt = Objects.requireNonNull(getDataObjectByName(avoerName, "人物")).getDdata();
                userAvo = getUserAvo(userAtt);
            }
        }else{
            Mecha mecha = getMechaByUser(a);
            if (mecha == null) {
                sbd.append("你尚未绑定机甲");
                return groupMsg(a.getUsergroup(), sbd.toString());
            }else{
                userAvo = getUserAvo(a.getUserAttribute());
                mechaAvo = getMechaAvo(mecha.getmMo(),mecha.getmSe());
                avoerName = a.getUserName();
            }
        }
        int avoSum =avoCorrection+userAvo+mechaAvo;
        sbd.append(avoerName);
        if (sent != null) {
            sbd.append("高喊着:“").append(sent).append("”,");
        }
        sbd.append("进行回避：\\n")
                .append("机甲回避为:")
                .append(mechaAvo).append(",人物回避修正为:").append(userAvo);
        if (avoCorrection != 0) {
            sbd.append(",额外回避修正为").append(avoCorrection);
        }
        sbd.append("\\n");
        int[] doll = fateArray(4);
        int dollSum = doll[0] + doll[1] + doll[2] + doll[3];
        sbd.append("掷出的命运骰子为【").append(doll[0]).append("，").append(doll[1]).append("，").append(doll[2]).append("，").append(doll[3]).append("】");
        sbd.append("\\n");
        sbd.append("回避值合计为(").append(avoSum).append(dollSum<0?"":"+").
                append(dollSum).append(avoCorrection!=0?((avoCorrection<0?"":"+") + avoCorrection) : "").
                append(")=").append(avoSum + dollSum + avoCorrection);
        return groupMsg(a.getUsergroup(), sbd.toString());
    }
    public static String upmecha(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        Mecha oldMecha=getMechaByUser(u);
        if(oldMecha!=null){
            Mecha mecha = generateMecha(value);
            mecha.setId(oldMecha.getId());
            mecha.setUid(oldMecha.getUid());
            sbd.append(mDao.updateMecha(mecha)==-1?"更新失败":
                    mecha.getMname()+"的数据已经更新完毕。");
        }else{
            sbd.append("您没有绑定机甲");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String umecha(QQuser u) {
        StringBuilder sbd = new StringBuilder();
        Mecha mecha = getMechaByUser(u);
        if (mecha == null) {
            sbd.append("你尚未绑定机甲");
        } else {
            mecha.setUid(null);
            sbd.append(mDao.updateMecha(mecha) == -1 ? "操作失败" : "成功与" +
                    mecha.getMname() + "解除绑定");
        }
        return groupMsg(u.getUsergroup(), sbd.toString());
    }
    public static String atk(QQuser u, String value,String ordinal) {
        System.out.println("ordinal  "+ordinal);
        StringBuilder sbd = new StringBuilder();
        LinkedHashMap weapon;
        String weaponName;
        int hitCorrect = 0;
        String val = value;
        String sent = null;
        int mechaHit = 0;
        if(value==null){
            sbd.append("请输入攻击数据");
            return groupMsg(u.getUsergroup(), sbd.toString());
        }
        if (value.contains(".")) {
            weaponName = value.substring(0, value.indexOf("."));
            val = value.substring(value.indexOf(".") + 1);
            LinkedHashMap<String, String> map = getSent(val);
            val = map.get("value");
            sent = map.get("sent");
            hitCorrect = getFateNum(val);
        } else {
            if(value.contains(" ")){
                weaponName = value.substring(0, value.indexOf(" "));
                LinkedHashMap<String, String> map = getSent(val);
                //weaponName = map.get("value");
                sent = map.get("sent");
            }
            else weaponName = value;
        }
        LinkedHashMap attMap;
        String atkUnitName;
        if (ordinal != null) {
            String atkName,mechaName;
            if(ordinal.contains(",")){
                atkName = ordinal.substring(0,ordinal.indexOf(","));
                mechaName = ordinal.substring(ordinal.indexOf(",")+1);
                if(mechaName.toCharArray()[0]=='-'||mechaName.toCharArray()[0]=='+'||
                        (mechaName.toCharArray()[0]<'9'&&mechaName.toCharArray()[0]>'0')){
                    mechaHit = getFateNum(mechaName);
                }else{
                    mechaHit = mechaName.equals("") ?0:Integer.parseInt((String)
                            Objects.requireNonNull(getDataByName(mechaName, "机甲")).get("感应"));
                }
            }else{
                atkName = ordinal;
            }
            if (getDataObjectByName(atkName,"人物") == null) {
                sbd.append("查无此人");
                return groupMsg(u.getUsergroup(), sbd.toString());
            } else {
                atkUnitName = Objects.requireNonNull(getDataObjectByName(atkName, "人物")).getName();
                attMap = getDataByName(atkName, "人物");
                weapon = getDataByName(weaponName, "武器");
                if (weapon == null) {
                    sbd.append("没有发现这把武器");
                    return groupMsg(u.getUsergroup(), sbd.toString());
                }
            }
        } else {
            Mecha mecha = getMechaByUser(u);
            if (mecha == null) {
                sbd.append("你尚未绑定机甲");
                return groupMsg(u.getUsergroup(), sbd.toString());
            }
            attMap = u.getUserAttribute();
            atkUnitName = u.getUserName();
            System.out.println("weaponName "+ weaponName);
            if (weaponName.toCharArray()[0] > '9') {
                weapon = getDataByName(weaponName, "武器");
                if (weapon == null) {
                    sbd.append("没有发现这把武器");
                    return groupMsg(u.getUsergroup(), sbd.toString());
                }
            } else {
                int weaponNum = Integer.parseInt(weaponName);
                LinkedHashMap[] weaponData = new LinkedHashMap[5];
                weaponData[1] = mecha.getWeapon1Data();
                weaponData[2] = mecha.getWeapon2Data();
                weaponData[3] = mecha.getWeapon3Data();
                weaponData[4] = mecha.getWeapon4Data();
                if (weaponNum < 1 || weaponNum > 4) {
                    sbd.append("错误的武器位数据，数字应该在1~4之间");
                    return groupMsg(u.getUsergroup(), sbd.toString());
                }
                if (weaponData[weaponNum].get("命中") == null) {
                    sbd.append("该武器位无武器");
                    return groupMsg(u.getUsergroup(), sbd.toString());
                }
                weapon = weaponData[weaponNum];
                mechaHit = mecha.getmSe();
            }
        }
        @SuppressWarnings("unchecked")
        int[] hit = hitOperation(attMap, mechaHit, weapon);
        int hitSSum = hit[0] + hit[1] + hit[2];
        int hitTime = Integer.parseInt((String) weapon.get("最高攻击次数"));
        int hitSection = Integer.parseInt((String) weapon.get("伤害段数"));
        sbd.append(atkUnitName);
        if (sent != null) {
            sbd.append("高喊着:“").append(sent).append("”,");
        }
        sbd.append("使用武器").append(weapon.get("名称")).append("(")
                .append(weapon.get("伤害类型")).append(",")
                .append(weapon.get("武器类型")).append(",可攻击次数:").append(hitTime).append(")攻击：\\n")
                .append("武器命中为:").append(hit[0]).append(",机体命中修正为:")
                .append(hit[1]).append(",人物命中修正为:").append(hit[2]);
        if (hitCorrect != 0) {
            sbd.append(",额外修正为").append(hitCorrect);
        }
        sbd.append("\\n");
        int[] doll = fateArray(4);
        int dollSum = doll[0] + doll[1] + doll[2] + doll[3];
        sbd.append("掷出的命运骰子为【").append(doll[0]).append("，").append(doll[1]).append("，").append(doll[2]).append("，").append(doll[3]).append("】");
        sbd.append("\\n");
        sbd.append("命中值合计为(").append(hitSSum).append(dollSum<0?"":"+").
                append(dollSum).append(hitCorrect!=0?((hitCorrect<0?"":"+") + hitCorrect) : "").
                append(")=").append(hitSSum + dollSum + hitCorrect);
        int[] damage = fateArray(2);
        int weaponDamage = Integer.parseInt((String) weapon.get("伤害"));
        sbd.append("\\n");
        sbd.append("如果命中，将造成").append(weaponDamage);
        if (hitSection != 1) {
            sbd.append("每段伤害的").append(hitSection).append("段");
        }
        sbd.append("武器伤害+伤害骰子【").append(damage[0]).
                append(",").append(damage[1]).append("】，合计为:").
                append(weaponDamage * hitSection + damage[0] + damage[1]).
                append("点伤害。");
        return groupMsg(u.getUsergroup(), sbd.toString());
    }
    public static String setm(QQuser u, String value) {
        StringBuilder sbd = new StringBuilder();
        if(getMechaByUser(u)==null){
            String re = addm(u,value,u);
            if(re.contains("添加失败")){
                sbd.append("添加失败");
            }else{
                sbd.append("添加机甲成功！已与").append(u.getUserName()).append("绑定！");
            }
        }else{
            sbd.append("您已经绑定过机甲");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String addm(QQuser u, String value,QQuser target) {
        StringBuilder sbd = new StringBuilder();
        if(!u.getUserID().equals("553859318")&&target==null){
            sbd.append("您没有权限这么做");
        }else if(value!=null){
            Mecha mecha = generateMecha(value);
            if(target!=null){
                LinkedHashMap<String,Object> uid = getUidByUser(target);
                mecha.setUid(mapSave(uid));
            }
            sbd.append(mDao.addMecha(mecha)==-1?"添加失败":"已添加机甲："+mecha.getMname());
        }else{
            sbd.append("请输入创建机甲的属性");
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }

}
