/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemocclient;


import cc.plural.jsonij.JSON;
import cc.plural.jsonij.parser.ParserException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import com.fate.dao.UserDao;
import com.fate.controller.FateController;
import com.fate.bean.QQuser;

import javafx.application.Platform;

import static lemocclient.Lemocclient.*;

//https://bitbucket.org/jmarsden/jsonij/wiki/Home
//https://www.java.com/zh_CN/download/windows_xpi.jsp
//jre1.8.0_111
// https://github.com/TooTallNate/Java-WebSocket


/**
 *
 * @author noname
 */
public class FXMLDocumentController implements Initializable {
	public static WebSocketClient cc;
	public static boolean connected = false;
	static UserDao sqlDao = new UserDao();
	String LemocUrl = "ws://localhost:25303";

	@FXML
	ListView<String> msgList;

	@FXML
	ListView<String> jsonList;

	@FXML
	TextField tQQ;

	@FXML
	TextField tInput;

	@FXML
	Button bCon;

	@FXML
	Button bSend;

	@FXML
	private void OnConAction(ActionEvent event) {
		//System.out.println("You clicked me!");
		try {   
			// cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
			//默认使用draft_17, java_websocket支持Draft_17, Draft_10, Draft_76, Draft_75
			cc = new WebSocketClient( new URI( LemocUrl ), (Draft) new Draft_17() ) {
				@Override
				public void onMessage( String message ) {
					AppendToJsonList(message);
					try {
						JSON json = JSON.parse(message);
						System.out.println(String.format("%s", json.get("act")));
						if(String.format("%s", json.get("act")).trim().equals("21"))
						{
							String msg = String.format("%s", json.get("fromQQ")) + "对你说： " + String.format("%s", json.get("msg"));
							System.out.println(msg);
							AppendToMsgList(msg);
						}
						//接受到的群消息
						else if(String.format("%s", json.get("act")).trim().equals("2"))
						{
							String qmsg = String.format("%s", json.get("msg"));
							String msg = String.format("%s", json.get("fromQQ")) + "在群" + String.format("%s", json.get("fromGroup")) + "里说: " + qmsg;
							String QQ = String.format("%s", json.get("fromQQ"));
							String groupID = String.format("%s", json.get("fromGroup"));
							int groupIndex = 0;
							if((groupList.indexOf(new QQuser(QQ,groupID))) == -1){
								String userName = String.format("%s", json.get("username"));
								groupList.add(new QQuser(QQ,userName,groupID,userName));
								sqlDao.addQQuser(groupList.get(groupList.indexOf(new QQuser(QQ,groupID))),"QQ");
							}
							groupIndex = groupList.indexOf(new QQuser(QQ,groupID));
							AppendToMsgList(msg);
							String json2 = FateController.groupOrder(groupList.get(groupIndex), qmsg);
							if(json2!=null){
								System.out.println(json2);
								AppendToJsonList( json2 );
								cc.send( json2 );
								//tInput.setText( "" );
								//tInput.requestFocus(); 							
							}
						}
						else if(String.format("%s", json.get("act")).trim().equals("4"))
						{
							String msg = String.format("%s", json.get("fromQQ")) + "在讨论组" + String.format("%s", json.get("fromDiscuss")) + "里说: " + String.format("%s", json.get("msg"));
							AppendToMsgList(msg);

						}

					} catch (ParserException ex) {
						Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
					}

				}

				@Override
				public void onOpen( ServerHandshake handshake ) {
					AppendToMsgList("你已经连接到: " + getURI());
				}

				@Override
				public void onClose( int code, String reason, boolean remote ) {
					AppendToMsgList("你已经断开连接: " + getURI() + "; Code: " + code + " " + reason);
					bSend.setDisable(true);
					bCon.setDisable(false);
					tQQ.setDisable(true);
					tInput.setDisable(true);
					connected = false;
				}

				@Override
				public void onError( Exception ex ) {
					AppendToMsgList("崩溃 ...\n" + ex);
					bSend.setDisable(true);
					bCon.setDisable(false);
					tQQ.setDisable(true);
					tInput.setDisable(true);
					connected = false;

					ex.printStackTrace();
				}
			};

			bSend.setDisable(false);
			bCon.setDisable(true);
			tQQ.setDisable(false);
			tInput.setDisable(false);
			connected = true;
			cc.connect();
		} catch ( URISyntaxException ex ) {
			System.out.println( LemocUrl + " 不是个有效的websocket服务器地址\n");
		}
	}   

	@FXML
	private void OnSendAction(ActionEvent event) {
		if(tQQ.getText() == null || tQQ.getText().trim().length() <= 0)
		{
			AppendToMsgList("请输入QQ号");
			return;
		}
		if(tInput.getText() == null || tInput.getText().trim().length() <= 0)
		{
			AppendToMsgList("请输入内容");
			return;
		}
		
		String json = "{\"act\": \"101\", \"groupid\": \"" + tQQ.getText().trim() + "\", \"msg\":\"" + tInput.getText().trim()+ "\"}";
		//String json = "{\"act\": \"106\", \"QQID\": \"" + tQQ.getText().trim() + "\", \"msg\":\"" + tInput.getText().trim()+ "\"}";

		AppendToJsonList( json );
		AppendToMsgList("你对" + tQQ.getText().trim() + "说： " + tInput.getText().trim());

		System.out.println(json);

		cc.send( json );
		tInput.setText( "" );
		tInput.requestFocus(); 
	}




	private void AppendToMsgList(String msg)
	{
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				msgList.getItems().add(msgList.getItems().size(), msg);
				msgList.scrollTo(msgList.getItems().size() - 1);
			}});   
	}

	private void AppendToJsonList(String msg)
	{
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				jsonList.getItems().add(jsonList.getItems().size(), msg);
				jsonList.scrollTo(jsonList.getItems().size() - 1);
			}});   
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		WebSocketImpl.DEBUG = true;
		bSend.setDisable(true);
		bCon.setDisable(false);
		tQQ.setDisable(true);
		tInput.setDisable(true);
		connected = false;
	}       


}


