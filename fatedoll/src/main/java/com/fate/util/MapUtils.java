package com.fate.util;




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

}
