package com.ghroth251;

import com.ghroth251.fateService.FateServiceService;
import static com.ghroth251.Socket.server;
import static com.ghroth251.Socket.service;
public class SocketUtils {

    public static void linkService(){
        System.out.println("等待中");
        service = LinkFateService();
        System.out.println("已连接");
        server = service.getFateServicePort();
    }
    public static FateServiceService LinkFateService() {
        FateServiceService sv;
        try {
            sv=new FateServiceService();
        }catch (Exception e){
            sv = LinkFateService();
        }
        return sv;
    }
    public static String sendServiceMsg(String requstMsg) {
        String reMsg;
        try {
            reMsg = server.returnMsg(requstMsg);
        }catch (Exception e){
            LinkFateService();
            reMsg = sendServiceMsg(requstMsg);
        }
        return reMsg;
    }
}
