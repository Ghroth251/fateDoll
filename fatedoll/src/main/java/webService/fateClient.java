package webService;

import webService.client.JcqWebService;
import webService.client.JcqWebServiceService;

import java.util.LinkedHashMap;

import static com.ghroth251.util.MapUtils.mapSave;
import static webController.PageController.jcqService;
import static webController.PageController.jcqServer;

public class fateClient {



    public static void linkService(){
        System.out.println("等待中");
        jcqService = LinkJcqService();
        System.out.println("已连接");
        jcqServer = jcqService.getJcqWebServicePort();
        jcqServer.linkSocket("link");
    }
    public static JcqWebServiceService LinkJcqService() {
        JcqWebServiceService sv;
        try {
            sv=new JcqWebServiceService();
        }catch (Exception e){
            sv = LinkJcqService();
        }
        return sv;
    }
    public static String sendServiceMsg(String requstMsg) {
        String reMsg;
        try {
            reMsg = jcqServer.sendJcqMsg(requstMsg);
        }catch (Exception e){
            LinkJcqService();
            reMsg = sendServiceMsg(requstMsg);
        }
        return reMsg;
    }

    /**
     * 测试用
     * @param args
     */
    public static void main(String[] args) {
        linkService();
        LinkedHashMap<String,Object> msgMap = new LinkedHashMap<>();
        String requstMsg;
        msgMap.put("Act","group");
        msgMap.put("fromGroup",650903307);
        msgMap.put("msg","小哥哥");
        requstMsg = mapSave(msgMap);
        System.out.println(sendServiceMsg(requstMsg));
    }

}
