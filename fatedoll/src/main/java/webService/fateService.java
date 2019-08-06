package webService;

import com.ghroth251.bean.QQuser;
import com.ghroth251.order.FateOrder;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import java.util.LinkedHashMap;

import static com.ghroth251.util.MapUtils.mapLoad;
import static com.ghroth251.util.StaticObjectUtils.groupList;
import static com.ghroth251.util.StaticObjectUtils.uSv;

@WebService
public class fateService {

    public String returnMsg(String requstMsg){
        LinkedHashMap<String,Object> msgMap = mapLoad(requstMsg);
        int groupIndex;
        long fromGroup = 0;
        if((msgMap.get("Act")).equals("group")){
            fromGroup =  Long.parseLong((String)msgMap.get("fromGroup"));
        }else if ((msgMap.get("Act")).equals("QQ")){
            fromGroup =  1L;
        }else if((msgMap.get("Act")).equals("discuss")){
            fromGroup = -(Long.parseLong((String)msgMap.get("fromDiscuss")));
        }
        long fromQQ = Long.parseLong((String)msgMap.get("fromQQ"));
        String cardName = (String) msgMap.get("cardName");
        String msg = (String) msgMap.get("msg");
        if((groupList.indexOf(new QQuser(fromQQ,fromGroup))) == -1){
            groupList.add(new QQuser(fromQQ,cardName,fromGroup,cardName));
            uSv.addQQuser(groupList.get(groupList.indexOf(new QQuser(fromQQ,fromGroup))),"QQ");
        }
        groupIndex = groupList.indexOf(new QQuser(fromQQ,fromGroup));
        return FateOrder.groupOrder(groupList.get(groupIndex), msg);

    }
    public static void WebServiceStart() {
        Endpoint.publish("http://localhost:11451/fate",new fateService());
        System.out.println("WebSerivice骰子服务----服务器端启动,监听的端口是11451....");
    }
}
