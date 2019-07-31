package webController;

import cc.plural.jsonij.JSON;
import cc.plural.jsonij.parser.ParserException;
import com.fate.Service.DiceService;
import com.fate.Service.UserService;
import com.fate.bean.QQuser;
import com.fate.controller.FateController;
import lemocclient.FXMLDocumentController;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.fate.util.MapUtils.JSONLoad;
import static com.fate.util.StaticObjectUtils.*;
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    UserService uSV;
    @Autowired
    DiceService diceSV;

    public static WebSocketClient cc;
    public static boolean connected = false;
    String LemocUrl = "ws://localhost:25303";
    public static LinkedHashMap<String,String> msgMap;

    private void OnConAction() {
        try {
            cc = new WebSocketClient( new URI( LemocUrl ), (Draft) new Draft_17() ){

                @Override
                public void onMessage(String message) {
                    System.out.println(message);
                    try {
                        JSON json = JSON.parse(message);
                        msgMap = JSONLoad(message);
//                        System.out.println(String.format("%s", json.get("act")));

                        if(msgMap.get("act").equals("21"))
                        {
                            String msg = String.format("%s", json.get("fromQQ")) + "对你说： " + String.format("%s", json.get("msg"));
                            System.out.println(msg);
//                            AppendToMsgList(msg);
                        }
                        //接受到的群消息
                        else if(msgMap.get("act").equals("2"))
                        {
//
//                            String msg = String.format("%s", json.get("fromQQ")) + "在群" + String.format("%s", json.get("fromGroup")) + "里说: " + qmsg;
                            String qmsg = msgMap.get("msg");
                            String QQ = msgMap.get("fromQQ");
                            String groupID = msgMap.get("fromGroup");
                            int groupIndex;
                            if((groupList.indexOf(new QQuser(QQ,groupID))) == -1){
                                String userName = String.format("%s", json.get("username"));
                                groupList.add(new QQuser(QQ,userName,groupID,userName));
                                uSv.addQQuser(groupList.get(groupList.indexOf(new QQuser(QQ,groupID))),"QQ");
                            }
                            groupIndex = groupList.indexOf(new QQuser(QQ,groupID));
//                            AppendToMsgList(msg);
                            String json2 = FateController.groupOrder(groupList.get(groupIndex), qmsg);
                            if(json2!=null){
                                System.out.println(json2);
//                                AppendToJsonList( json2 );
                                cc.send( json2 );
                                //tInput.setText( "" );
                                //tInput.requestFocus();
                            }
                        }
                        else if(String.format("%s", json.get("act")).trim().equals("4"))
                        {
                            String msg = String.format("%s", json.get("fromQQ")) + "在讨论组" + String.format("%s", json.get("fromDiscuss")) + "里说: " + String.format("%s", json.get("msg"));
//                            AppendToMsgList(msg);

                        }
                    } catch (ParserException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("已经连接到骰子");
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("骰子已断开连接");
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("骰子姬遇到问题崩溃+\n"+e);
                    e.printStackTrace();
                }
            };
            connected = true;
            cc.connect();
        }catch (URISyntaxException e){
            System.out.println("连接失败+\n"+e);
        }
    }
    @RequestMapping("/list")
    public void getUsers(){
        uSv = uSV;
        diceSv = diceSV;
        myDice = diceSv.diceLoad();
        groupList = uSv.getUserList("QQ");
        ARPGList =  uSv.getUserList(myDice.getState());
        uList = uDao.loadUnits();
        dList = dDao.datafind(null,null);
        System.out.println("sad");
        for(QQuser A: groupList){
			System.out.println(A);
		}
        OnConAction();
        //        List<QQuser> users = uSv.getUserList("QQ");
//        for (QQuser user : users) {
//            System.out.println(user);
//        }
    }





}
