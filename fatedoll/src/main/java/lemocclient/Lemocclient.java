/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package lemocclient;

import java.util.ArrayList;

import com.fate.Dao.DataDao;
import com.fate.Dao.UserDao;
import com.fate.Dao.UnitsDao;
import com.fate.bean.DataMsg;
import com.fate.bean.Dice;
import com.fate.bean.QQuser;
import com.fate.bean.Unit;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static com.fate.util.StaticObjectUtils.*;

/**
*
* @author noname
*/
public class Lemocclient extends Application {

	public static ArrayList<QQuser> groupList = new ArrayList<>();
    public static ArrayList<QQuser> ARPGList = new ArrayList<>();
	public static ArrayList<Unit> uList = new ArrayList<>();
	public static ArrayList<DataMsg> dList = new ArrayList<>();
	public static Dice myDice = diceSV.diceLoad();

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
    	groupList = uSv.getUserList("QQ");
    	ARPGList =  uSv.getUserList(myDice.getState());
    	uList = uDao.loadUnits();
    	dList = dDao.datafind(null,null);

    	for(QQuser A: groupList){
			System.out.println(A);
		}
    	launch(args);
    }

}
