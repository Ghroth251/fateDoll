package com.ghroth251;



import com.ghroth251.fateService.FateService;
import com.ghroth251.fateService.FateServiceService;

import java.util.LinkedHashMap;

import static com.ghroth251.MapUtils.mapSave;
import static com.ghroth251.SocketUtils.LinkFateService;


public class Test {

    public static void main(String[] args) {
        FateServiceService service;
        System.out.println("等待中");
        service = LinkFateService();
        System.out.println("已连接");
        //通过服务视图找到对应的端口类型，即(portType)
        FateService server = service.getFateServicePort();
        //远程调用方法
        String reMsg;
        LinkedHashMap<String,Object> msgMap = new LinkedHashMap<>();
        String requstMsg;
        msgMap.put("fromQQ",553859318);
        msgMap.put("fromGroup",650903307);
        msgMap.put("cardName","三千！！");
        msgMap.put("msg","atk.1");
        requstMsg = mapSave(msgMap);
        reMsg = server.returnMsg(requstMsg);
        System.out.println("查询到的天气是:"+reMsg);
    }
}
