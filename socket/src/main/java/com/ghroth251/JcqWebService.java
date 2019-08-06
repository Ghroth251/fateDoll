package com.ghroth251;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.LinkedHashMap;

import static com.ghroth251.MapUtils.mapLoad;
import static com.ghroth251.SocketUtils.linkService;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;

@WebService
public class JcqWebService {
    public static boolean linkState =false;

    public String getDataByComder(String comder){
        String rData = null;
        return rData;
    }
    public String sendJcqMsg(String sdMsg){
        LinkedHashMap<String,Object> msgMap = mapLoad(sdMsg);
        String msg = (String) msgMap.get("msg");
        if((msgMap.get("Act")).equals("group")){
            long fromGroup =  Long.parseLong((String)msgMap.get("fromGroup"));
            System.out.println(msg);
            CQ.sendGroupMsg(fromGroup,msg);
        }else if ((msgMap.get("Act")).equals("QQ")){
            long fromQQ =  Long.parseLong((String)msgMap.get("fromQQ"));
            CQ.sendPrivateMsg(fromQQ,msg);
        }else if((msgMap.get("Act")).equals("discuss")){
            long fromDiscuss =  Long.parseLong((String)msgMap.get("fromDiscuss"));
            CQ.sendDiscussMsg(fromDiscuss,msg);
        }
        return "true";
    }
    public String linkSocket(String linkMsg){
        if(linkMsg!=null){
            linkService();
            linkState = true;
            return "true";
        }
        linkState = false;
        return "false";
    }
    public static void WebServiceStart() {
        Endpoint.publish("http://localhost:51419/fate",new JcqWebService());
        System.out.println("WebSerivice骰子服务----服务器端启动,监听的端口是51419....");
    }
}
