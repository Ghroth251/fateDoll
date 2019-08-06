package webController;

import com.ghroth251.Service.DiceService;
import com.ghroth251.Service.MechaService;
import com.ghroth251.Service.UserService;
import com.ghroth251.bean.QQuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import webService.client.JcqWebService;
import webService.client.JcqWebServiceService;

import static com.ghroth251.util.StaticObjectUtils.*;
import static webService.fateClient.linkService;
import static webService.fateService.WebServiceStart;

@Controller
@RequestMapping("/page")
public class PageController {
    public static JcqWebServiceService jcqService;
    public static JcqWebService jcqServer;

    @Autowired
    UserService uSV;
    @Autowired
    DiceService diceSV;
    @Autowired
    MechaService mSV;






    @RequestMapping("/list")
    public void getUsers(){
        uSv = uSV;
        diceSv = diceSV;
        mSv = mSV;
        myDice = diceSv.diceLoad();
        groupList = uSv.getUserList("QQ");
        ARPGList =  uSv.getUserList(myDice.getState());
        dList = dDao.datafind(null,null);
        for(QQuser A: groupList){
			System.out.println(A);
		}
        WebServiceStart();
		linkService();
		//        List<QQuser> users = uSv.getUserList("QQ");
//        for (QQuser user : users) {
//            System.out.println(user);
//        }
    }





}
