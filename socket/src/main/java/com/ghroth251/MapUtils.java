package com.ghroth251;

import java.util.LinkedHashMap;
import java.util.Set;

public class MapUtils {

    public static LinkedHashMap<String,Object> mapLoad(String value) {
        LinkedHashMap<String,Object> h = new LinkedHashMap<>();
        if(value!=null){
            String values = value;
            while(values.contains("|")){
                String key = values.substring(0,values.indexOf(":"));
                String val = values.substring(values.indexOf(":")+1,values.indexOf("|"));
                h.put(key,val);
                values = values.substring(values.indexOf("|")+1);
            }
            return h;
        }
        return null;
    }
    public static String mapSave(LinkedHashMap<String,Object> L) {
        StringBuilder sbd = new StringBuilder();
        if(L!=null){
            Set<String> keySet = L.keySet();
            for (String key : keySet) {
                Object val = L.get(key);
                sbd.append(key).append(":").append(val).append("|");
            }
            return sbd.toString();
        }
        return null;
    }

    public static LinkedHashMap<String,String> JSONLoad(String json){
        LinkedHashMap<String,String> h = new LinkedHashMap<>();
        json = json.substring(2,json.indexOf("}")-1);
        json = json.replace(" ","");
        json = json.replace("\"","");
        String values = json;
        while(values.contains("\n")){
            String val2 = values.substring(0,values.indexOf("\n")+1);
            String key = val2.substring(0,val2.indexOf(":"));
            String val = val2.substring(val2.indexOf(":")+1,val2.indexOf("\n")-1);
            h.put(key,val);
            values = values.substring(values.indexOf("\n")+1);
        }
        return h;
    }
}
