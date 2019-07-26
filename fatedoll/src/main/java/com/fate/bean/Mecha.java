package com.fate.bean;


import java.util.LinkedHashMap;

public class Mecha {

    private int id;
    private String mname;
    private String uid;
    private int mEn;
    private int mSt;
    private int mMo;
    private int mSe;
    private LinkedHashMap<String,Object> attData;
    private LinkedHashMap<String,Object> weapon1Data;
    private LinkedHashMap<String,Object> weapon2Data;
    private LinkedHashMap<String,Object> weapon3Data;
    private LinkedHashMap<String,Object> weapon4Data;

    public Mecha() {
    }

    public Mecha(int id, String mname, String uid, int mEn, int mSt, int mMo, int mSe, LinkedHashMap<String, Object> attData, LinkedHashMap<String, Object> weapon1Data, LinkedHashMap<String, Object> weapon2Data, LinkedHashMap<String, Object> weapon3Data, LinkedHashMap<String, Object> weapon4Data) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LinkedHashMap<String, Object> getAttData() {
        return attData;
    }

    public void setAttData(LinkedHashMap<String, Object> attData) {
        this.attData = attData;
    }

    public LinkedHashMap<String, Object> getWeapon1Data() {
        return weapon1Data;
    }

    public void setWeapon1Data(LinkedHashMap<String, Object> weapon1Data) {
        this.weapon1Data = weapon1Data;
    }

    public LinkedHashMap<String, Object> getWeapon2Data() {
        return weapon2Data;
    }

    public void setWeapon2Data(LinkedHashMap<String, Object> weapon2Data) {
        this.weapon2Data = weapon2Data;
    }

    public LinkedHashMap<String, Object> getWeapon3Data() {
        return weapon3Data;
    }

    public void setWeapon3Data(LinkedHashMap<String, Object> weapon3Data) {
        this.weapon3Data = weapon3Data;
    }

    public LinkedHashMap<String, Object> getWeapon4Data() {
        return weapon4Data;
    }

    public void setWeapon4Data(LinkedHashMap<String, Object> weapon4Data) {
        this.weapon4Data = weapon4Data;
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
