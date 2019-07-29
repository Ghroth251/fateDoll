package com.fate.controller.impl;

import com.fate.bean.QQuser;

import java.util.LinkedHashMap;
import java.util.Random;

import static com.fate.util.FateUtils.groupMsg;
import static com.fate.util.StaticObjectUtils.sqlDao;
import static lemocclient.Lemocclient.myDice;

public class MfOrderImpl {
    public static String mfQH(QQuser u, String value) {
        Random ra = new Random();
        int qh = ra.nextInt(99)+1;
        int magicQH;
        StringBuilder sbd = new StringBuilder();
        if(value==null){
            if(qh<=5){
                magicQH = 1;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人被称为魔力绝缘者。他们无法储").append("存和使用魔力，但一般拥有过人的肉体强度。\\n(魔力压力上限为0，体魄，打斗，运动+1）");
            }else if(qh<=25){
                magicQH = 2;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人只能感受到少许魔力，一般无法").append("参与魔法相关的工作，大部分普通人处于这一等级。\\n(魔力压力上限为1，体魄，运动+1）");
            }else if(qh<=70){
                magicQH = 3;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人具备释放魔法的能力，但大多").append("仅仅只能释放一两个法术，大部分使用魔力进行辅助的战士处于这一等级。\\n(魔力压力上限为2，体魄+1）");
            }else if(qh<=80){
                magicQH = 4;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人完全具备了作为一名魔法师的资质").append("。\\n(魔力压力上限为4）");
            }else if(qh<=89){
                magicQH = 5;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人是魔法师中的佼佼者，更高的魔力储").append("量虽然损害了身体，但提供了更长久的魔力输出。\\n(魔力压力上限为7，体魄-1）");
            }else if(qh<=97){
                magicQH = 6;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人在魔力方面的天赋是世间顶尖的").append("其强大的魔力让其他魔法师望而生畏。\\n(魔力压力上限为10，魔法+1，体魄-2）");
            }else {
                magicQH = 7;
                sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人只在各种传说中有被提及").append("他们是真正的被魔力所眷顾的天才。\\n(魔力压力上限为14，魔法，察觉+1，体魄-2，运动-1）");
            }
        }else{
            if(value.equals("m")){
                if(qh<=40){
                    magicQH = 4;
                    sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人完全具备了作为一名魔法师的资质").append("。\\n(魔力压力上限为4）");
                }else if(qh<=75){
                    magicQH = 5;
                    sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人是魔法师中的佼佼者，更高的魔力储").append("量虽然损害了身体，但提供了更长久的魔力输出。\\n(魔力压力上限为7，体魄-1）");
                }else if(qh<=95){
                    magicQH = 6;
                    sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人在魔力方面的天赋是世间顶尖的").append("其强大的魔力让其他魔法师望而生畏。\\n(魔力压力上限为10，魔力+1，体魄-2）");
                }else {
                    magicQH = 7;
                    sbd.append(u.getUserName()).append("的魔力亲和为").
                            append(magicQH).append(",").append(magicQH).
                            append("级魔力亲和的人只在各种传说中有被提及").
                            append("他们是真正的被魔力所眷顾的天才。" +
                                    "\\n(魔力压力上限为14，魔力，察觉+1，体魄-2，运动-1）");
                }
            }
        }
        return groupMsg(u.getUsergroup(),sbd.toString());
    }
    public static String mfZZ(QQuser u) {
        Random ra = new Random();
        //		LinkedHashMap<String, Object> check = u.getUserData();
        if(u.getUserData()==null||u.getUserData().get("火元素")==null){
            StringBuilder sbd = new StringBuilder(u.getUserName()+"的魔法资质为\\n");
            int[] inborn = new int[8];
            int index = 0;
            do{
                inborn[0] = ra.nextInt(99)+1;
                inborn[1] = 100-inborn[0];
                index++;
            }
            while(inborn[0]>100||inborn[1]>100||(inborn[0]>80||inborn[1]>80)&&index<5);

            for(int i=2;i<=6;i++){
                index = 0;
                do{
                    inborn[i] = ra.nextInt((i>4?inborn[1]:inborn[0])/8+35)+16+((i>4?inborn[1]:inborn[0])/2);
                    index++;
                }
                while(inborn[i]>100||((inborn[i]>=80&&index<3)||(inborn[i]>=90&&index<8)));
            }

            index = 0;
            do{
                inborn[7] = ra.nextInt(50)+1+(inborn[1]/5);
                index++;
            }
            while(inborn[7]>100||((inborn[7]>=20&&index<15)||(inborn[6]>=30&&index<25)||(inborn[6]>=40&&index<50)));
            sbd.append("    亲光元素: {\\n");
            sbd.append("      火元素: ").append(inborn[2]).append("%\\n");
            sbd.append("      风元素: ").append(inborn[3]).append("%\\n");
            sbd.append("      雷元素: ").append(inborn[4]).append("%\\n");
            sbd.append("    }\\n");
            sbd.append("    亲暗元素: {\\n");
            sbd.append("      地元素: ").append(inborn[5]).append("%\\n");
            sbd.append("      水元素: ").append(inborn[6]).append("%\\n");
            sbd.append("      金元素: ").append(inborn[7]).append("%\\n");
            sbd.append("    }\\n");
            System.out.println(sbd.toString());
            LinkedHashMap<String, Object> L = (u.getUserData()==null? new LinkedHashMap<>():u.getUserData());
            L.put("火元素", inborn[2]+"%");
            L.put("风元素", inborn[3]+"%");
            L.put("雷元素", inborn[4]+"%");
            L.put("地元素", inborn[5]+"%");
            L.put("水元素", inborn[6]+"%");
            L.put("金元素", inborn[7]+"%");
            u.setUserData(L);
            sqlDao.saveData(u.getUserData(), u,myDice.getState());
            return groupMsg(u.getUsergroup(),sbd.toString());
        }else{
            return groupMsg(u.getUsergroup(),"已经测过资质啦，不能重复测试。");
        }
    }
}
