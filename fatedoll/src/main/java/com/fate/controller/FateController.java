/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fate.controller;

import com.fate.bean.*;

import java.util.HashMap;

import static com.fate.controller.impl.AUserOlderImpl.*;
import static com.fate.controller.impl.PublicOrderImpl.*;
import static com.fate.controller.impl.MfOrderImpl.*;
import static com.fate.controller.impl.JjOrderImpl.*;
import static com.fate.util.FateUtils.*;
import static com.fate.util.StaticObjectUtils.fateState;
import static lemocclient.Lemocclient.myDice;


/**
 *
 * @author Zhaoxun
 */
public class FateController {


    public static String groupOrder(QQuser u,String msg){
        HashMap<String,String> orderMap= splitMsg(msg);
        QQuser a = getARPGUserByUser(u);
        return OrderList(u,a,orderMap);
    }
    private static String OrderList(QQuser u,QQuser a,HashMap<String,String> orderMap) {
        String returnMsg = null;
        if(fateState){
            if(a!=null){
                returnMsg = aUserOrderList(a,orderMap);
            }
            returnMsg = returnMsg==null?publicOrderList(u,orderMap):returnMsg;
        }else if(orderMap.get("comder").equals("Fon")){
            returnMsg = fon(u, orderMap.get("value"));
        }
        return returnMsg;
    }
    private static String aUserOrderList(QQuser a,HashMap<String,String> orderMap) {
        switch (orderMap.get("comder")) {
            case "setnew":
                return setnew(a,orderMap.get("value"));
            case "set":
                return set(a,orderMap.get("value"));
            case "tra":
                return tra(a,orderMap.get("value"));
            case "addItem":
                return addItem(a,orderMap.get("value"));
            case "utList":
                return uList(a,orderMap.get("value"));
            case "fd":
                return fd(a,orderMap.get("value"));
            case "hf":
                return hf(a,orderMap.get("value"));
            case "setu":
                return setu(a, orderMap.get("value"));
            case "fr":
                return fr(a, orderMap.get("value"));
            case "fz":
                return fz(a);
            case "dc":
                return dc(a,orderMap.get("value"));
            default:
                switch (myDice.getState()) {
                    case "mf":
                        return MfOrderList(a,orderMap);
                    case "jj":
                        return JjOrderList(a,orderMap);
                }
        }
        return null;
    }
    private static String publicOrderList(QQuser u,HashMap<String,String> orderMap){
        if ("fate".equals(orderMap.get("comder"))) {
            return fate(u, orderMap.get("value"), null);
        } else if ("fnn".equals(orderMap.get("comder"))) {
            return fnn(u, orderMap.get("value"));
        } else if ("Foff".equals(orderMap.get("comder"))) {
            return foff(u, orderMap.get("value"));
        } else if ("DiceQH".equals(orderMap.get("comder"))) {
            return diceQH(u, orderMap.get("value"));
        } else if ("Cset".equals(orderMap.get("comder"))) {
            return cset(u, orderMap.get("value"));
        }else if ("jrrp".equals(orderMap.get("comder"))) {
            return jrrp(u);
        } else if ("fatedbLink".equals(orderMap.get("comder"))){
            return fatedbLink(u);
        }else if ("addPC".equals(orderMap.get("comder"))) {
            return addPC(u, orderMap.get("value"));
        }
        return null;
    }
    private static String MfOrderList(QQuser a,HashMap<String,String> orderMap){
        if (orderMap.get("comder").equals("mfZZ")) {
            return mfZZ(a);
        } else if (orderMap.get("comder").equals("mfQH")) {
            return mfQH(a, orderMap.get("value"));
        }
        return null;
    }
    private static String JjOrderList(QQuser a,HashMap<String,String> orderMap){
        switch (orderMap.get("comder")) {
            case "setm":
                return setm(a, orderMap.get("value"));
            case "addm":
                return addm(a, orderMap.get("value"), null);
            case "atk":
                return atk(a, orderMap.get("value"),orderMap.get("ordinal"));
            case "umecha":
                return umecha(a);
            case "upmecha":
                return upmecha(a, orderMap.get("value"));
            case "avo":
                return avo(orderMap.get("value"),orderMap.get("ordinal"),a);
        }
        return null;
    }
}
