/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lemocclient;

import com.fate.Service.UserService;
import com.fate.bean.DataMsg;
import com.fate.bean.Dice;
import com.fate.bean.QQuser;
import com.fate.bean.Unit;
import com.fate.util.StaticObjectUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

import static com.fate.util.StaticObjectUtils.*;

/**
*
* @author noname
*/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")

public class Lemocclient extends Application {

//  public static ArrayList<QQuser> groupList = new ArrayList<>();
//  public static ArrayList<QQuser> ARPGList = new ArrayList<>();
//	public static ArrayList<Unit> uList = new ArrayList<>();
//	public static ArrayList<DataMsg> dList = new ArrayList<>();
//	public static Dice myDice = diceSV.diceLoad();

	@Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.setWidth(750); 
        stage.setHeight(720); 
        stage.setResizable(false);
        stage.setTitle("LEMOC - Client");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                if(FXMLDocumentController.connected)
                {
                    System.out.println("close websocket");
                    FXMLDocumentController.cc.close();
                }
                //
            }
        });

    }

    public static void main(String[] args){
    	//groupList = IOUtils.readQQuser();
 //   	myDice = uSv.diceLoad();
//        StaticObjectUtils su = new StaticObjectUtils();
//        System.out.println(uSv);
//    	groupList = uSv.getUserList("QQ");
//    	ARPGList =  uSv.getUserList(myDice.getState());
//    	uList = uDao.loadUnits();
//    	dList = dDao.datafind(null,null);
//    	for(QQuser A: groupList){
//			System.out.println(A);
//		}

        launch(args);
    }

}
