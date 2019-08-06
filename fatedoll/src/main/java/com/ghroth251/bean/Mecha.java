package com.ghroth251.bean;


import javax.persistence.*;
import java.util.LinkedHashMap;

import static com.ghroth251.util.MapUtils.mapLoad;
import static com.ghroth251.util.MapUtils.mapSave;

@Entity
@Table(name = "mecha") //建立实体类和表的映射关系
public class Mecha {

    @Id//声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    private long id;
    private String mname;
    private String uid;
    private int mEn;
    private int mSt;
    private int mMo;
    private int mSe;
    private String attData;
    private String weapon1Data;
    private String weapon2Data;
    private String weapon3Data;
    private String weapon4Data;

    public Mecha() {
    }

    public Mecha(int id, String mname, String uid, int mEn, int mSt, int mMo, int mSe, String attData, String weapon1Data, String weapon2Data, String weapon3Data, String weapon4Data) {
        this.id = id;
        this.mname = mname;
        this.uid = uid;
        this.mEn = mEn;
        this.mSt = mSt;
        this.mMo = mMo;
        this.mSe = mSe;
        this.attData = attData;
        this.weapon1Data = weapon1Data;
        this.weapon2Data = weapon2Data;
        this.weapon3Data = weapon3Data;
        this.weapon4Data = weapon4Data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getmEn() {
        return mEn;
    }

    public void setmEn(int mEn) {
        this.mEn = mEn;
    }

    public int getmSt() {
        return mSt;
    }

    public void setmSt(int mSt) {
        this.mSt = mSt;
    }

    public int getmMo() {
        return mMo;
    }

    public void setmMo(int mMo) {
        this.mMo = mMo;
    }

    public int getmSe() {
        return mSe;
    }

    public void setmSe(int mSe) {
        this.mSe = mSe;
    }


    public void setAttData(String attData) {
        this.attData = attData;
    }



    public void setWeapon1Data(String weapon1Data) {
        this.weapon1Data = weapon1Data;
    }



    public void setWeapon2Data(String weapon2Data) {
        this.weapon2Data = weapon2Data;
    }



    public void setWeapon3Data(String weapon3Data) {
        this.weapon3Data = weapon3Data;
    }



    public void setWeapon4Data(String weapon4Data) {
        this.weapon4Data = weapon4Data;
    }

    public String getAttData() {
        return attData;
    }

    public String getWeapon1Data() {
        return weapon1Data;
    }

    public String getWeapon2Data() {
        return weapon2Data;
    }

    public String getWeapon3Data() {
        return weapon3Data;
    }

    public String getWeapon4Data() {
        return weapon4Data;
    }

    public LinkedHashMap<String,Object> getLAttData() {
        return mapLoad(attData);
    }
    public void setAttData(LinkedHashMap<String,Object> attData) {
        this.attData = mapSave(attData);
    }

    public LinkedHashMap<String,Object> getLWeapon1Data() {
        return mapLoad(weapon1Data);
    }

    public void setWeapon1Data(LinkedHashMap<String,Object> weapon1Data) {
        this.weapon1Data = mapSave(weapon1Data);
    }

    public LinkedHashMap<String,Object> getLWeapon2Data() {
        return mapLoad(weapon2Data);
    }

    public void setWeapon2Data(LinkedHashMap<String,Object> weapon2Data) {
        this.weapon2Data = mapSave(weapon2Data);
    }

    public LinkedHashMap<String,Object> getLWeapon3Data() {
        return mapLoad(weapon3Data);
    }

    public void setWeapon3Data(LinkedHashMap<String,Object> weapon3Data) {
        this.weapon3Data = mapSave(weapon3Data);
    }

    public LinkedHashMap<String,Object> getLWeapon4Data() {
        return mapLoad(weapon4Data);
    }

    public void setWeapon4Data(LinkedHashMap<String,Object> weapon4Data) {
        this.weapon4Data = mapSave(weapon4Data);
    }

    @Override
    public String toString() {
        return "Mecha{" +
                "id=" + id +
                ", mname='" + mname + '\'' +
                ", uid=" + uid +
                ", mEn=" + mEn +
                ", mSt=" + mSt +
                ", mMo=" + mMo +
                ", mSe=" + mSe +
                ", attData=" + attData +
                ", weapon1Data=" + weapon1Data +
                ", weapon2Data=" + weapon2Data +
                ", weapon3Data=" + weapon3Data +
                ", weapon4Data=" + weapon4Data +
                '}';
    }
}
