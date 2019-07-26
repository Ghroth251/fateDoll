/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import com.fate.bean.*;
import com.fate.dao.DataDao;
import com.fate.dao.MechaDao;
import com.fate.dao.UserDao;
import com.fate.dao.UnitsDao;

import static com.fate.util.FateUtils.*;
import static com.fate.util.MapUtils.*;
import static com.fate.util.MechaUtils.*;
import static lemocclient.Lemocclient.*;



/**
 *
 * @author Zhaoxun
 */
public class FateController {
	private static boolean fateState = true;
	private static HashMap<String,String[]> cardMode;
	private static UserDao sqlDao;
	private static UnitsDao uDao;
	private static DataDao dDao;
	private static MechaDao mDao;
	static {
		cardMode = new HashMap<>();
		sqlDao = new UserDao();
		uDao = new UnitsDao();
		dDao = new DataDao();
		mDao = new MechaDao();
	}
	public static String groupOrder(QQuser u,String msg){
		String comder;
		String value = null;
		String ordinal =null;
		//&#91;='['
		//&#93;=']'
		if(msg.contains("&#91;")) {
			ordinal=msg.substring(msg.indexOf("&#91;")+5,msg.indexOf("&#93;"));
			String fMsg = msg.substring(0,msg.indexOf("&#91;"));
			String lMsg = msg.substring(msg.indexOf("&#93;")+5);
			msg = fMsg+lMsg;
		}
		if (msg.contains(".")) {
			comder = msg.substring(0, msg.indexOf("."));
			value = msg.substring(msg.indexOf(".") + 1);
		} else if (msg.contains("。")) {
			if (msg.toCharArray()[msg.length() - 1] != '。') {
				comder = msg.substring(0, msg.indexOf("。"));
				value = msg.substring(msg.indexOf("。") + 1);
			} else {
				comder = msg.substring(0, msg.indexOf("。"));
				value = null;
			}
		} else {
			comder = msg;
		}
		comder = reAllSpace(comder);
		if(value != null){
			value = reSpace(value);
		}
		QQuser a = getARPGUserByUser(u);
		//	System.out.println("a: "+a);
		if(fateState){
			if(a!=null){
				if ("setnew".equals(comder)) {
					return setnew(a, value);
				} else if ("set".equals(comder)) {
					return set(a, value);
				} else if ("tra".equals(comder)) {
					return tra(a, value);
				} else if ("addItem".equals(comder)) {
					return addItem(a, value);
				} else if ("utList".equals(comder)) {
					return uList(a, value);
				} else if ("fd".equals(comder)) {
					return fd(a, value);
				} else if ("hf".equals(comder)) {
					return hf(a, value);
				} else if ("setu".equals(comder)) {
					return setu(a, value);
				} else if ("fr".equals(comder)) {
					return fr(a, value);
				} else if ("fz".equals(comder)) {
					return fz(a, msg);
				} else if ("dc".equals(comder)) {
					return dc(a, value);
				}
				switch (myDice.getState()) {
					case "mf":
						if (comder.equals("mfZZ")) {
							return mfZZ(a);
						} else if (comder.equals("mfQH")) {
							return mfQH(a, value);
						}
						break;
					case "jj":
						switch (comder) {
							case "setm":
								return setm(a, value);
							case "addm":
								return addm(a, value, null);
							case "atk":
								return atk(a, value,ordinal);
							case "umecha":
								return umecha(a);
							case "upmecha":
								return upmecha(a, value);
							case "avo":
								return avo(value, ordinal, a);
						}
						break;
					default:
						break;
				}
			}
			if ("fate".equals(comder)) {
				return fate(u, value, null);
			} else if ("fnn".equals(comder)) {
				return fnn(u, value);
			} else if ("Foff".equals(comder)) {
				return foff(u, value);
			} else if ("DiceQH".equals(comder)) {
				return diceQH(u, value);
			} else if ("Cset".equals(comder)) {
				return cset(u, value);
			}else if ("jrrp".equals(comder)) {
				return jrrp(u);
			} else if ("fatedbLink".equals(comder)){
				return fatedbLink(u);
			}else if ("addPC".equals(comder)) {
				return addPC(u, value);
			}

		}else if(comder.equals("Fon")){
			return fon(u, value);
		}
		return null;
	}

	private static String addPC(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		if(value!=null&&u.getUserID().equals("553859318")) {
            value = reAllSpace(value);
            QQuser u2 = sqlDao.getUserByUser(new QQuser(value, u.getUsergroup()));
            if (u2 != null&& 	ARPGList.indexOf(u2)==-1) {
                ARPGList.add(u2);
                sbd.append("添加参团角色完成");
                sqlDao.addQQuser(u2,myDice.getState());
            } else {
                sbd.append("出错了！");
            }
        }else{
            sbd.append("你没有权限这么做");
        }
		return groupMsg(u.getUsergroup(), sbd.toString());
	}

	private static String jrrp(QQuser u) {
		StringBuilder sbd = new StringBuilder();
		LinkedHashMap<String,Object> map = u.getUserData()==null?
				new LinkedHashMap<>():u.getUserData();
		if(map.get("Date")==null||!map.get("Date").equals(getTodayDate())){
			int[] doll = fateArray(100);
			int[] dollNum = new int[3];
			for(int i=0;i<doll.length;i++){
				switch (doll[i]) {
					case -1:
						dollNum[0]++;
						break;
					case 0:
						dollNum[1]++;
						break;
					default:
						dollNum[2]++;
						break;
				}
			}
			map.put("Date",getTodayDate());
			u.setUserData(map);
			sqlDao.saveData(map,u,"QQ");
			sbd.append("在一百次的命运中，" +u.getUserName()+"得到了"+dollNum[2]+"次的‘1’"+
					dollNum[1]+"次的‘0’与"+dollNum[0]+"次的‘-1’。\\n"+u.getUserName()+
					"的命运合计为【"+(dollNum[2]-dollNum[0])+"】。");
		}else{
			sbd.append("今日已经测过了。");
		}
		return groupMsg(u.getUsergroup(), sbd.toString());
	}

	private static String avo(String value, String ordinal, QQuser a) {
		StringBuilder sbd = new StringBuilder();
		String avoerName,sent = null;
		int userAvo;
		int avoCorrection = 0;
		int mechaAvo = 0;
		if(value!=null){
			if(value.contains(" ")){
				avoCorrection = getFateNum(value.substring(0, value.indexOf(" ")));
				LinkedHashMap<String, String> map = getSent(value);
				sent = map.get("sent");
			}else{
				avoCorrection = getFateNum(value);
			}
		}
		if(ordinal!=null){
			String mechaData;
			if(ordinal.contains(",")){
				avoerName = ordinal.substring(0,ordinal.indexOf(","));
				mechaData = ordinal.substring(ordinal.indexOf(",")+1);
				mechaAvo = getDataMechaAvo(mechaData);
			}else{
				avoerName = ordinal;
			}
			if ( getDataObjectByName(avoerName,"人物")== null) {
				sbd.append("查无此人");
				return groupMsg(a.getUsergroup(), sbd.toString());
			}else{
				LinkedHashMap<String,Object> userAtt = getDataObjectByName(avoerName,"人物").getDdata();
				userAvo = getUserAvo(userAtt);
			}
		}else{
			Mecha mecha = getMechaByUser(a);
			if (mecha == null) {
				sbd.append("你尚未绑定机甲");
				return groupMsg(a.getUsergroup(), sbd.toString());
			}else{
				userAvo = getUserAvo(a.getUserAttribute());
				mechaAvo = getMechaAvo(mecha.getmMo(),mecha.getmSe());
				avoerName = a.getUserName();
			}
		}
		int avoSum =avoCorrection+userAvo+mechaAvo;
		sbd.append(avoerName);
		if (sent != null) {
			sbd.append("高喊着:“").append(sent).append("”,");
		}
		sbd.append("进行回避：\\n")
				.append("机甲回避为:")
				.append(mechaAvo).append(",人物回避修正为:").append(userAvo);
		if (avoCorrection != 0) {
			sbd.append(",额外回避修正为").append(avoCorrection);
		}
		sbd.append("\\n");
		int[] doll = fateArray(4);
		int dollSum = doll[0] + doll[1] + doll[2] + doll[3];
		sbd.append("掷出的命运骰子为【").append(doll[0]).append("，").append(doll[1]).append("，").append(doll[2]).append("，").append(doll[3]).append("】");
		sbd.append("\\n");
		sbd.append("回避值合计为(").append(avoSum).append(dollSum<0?"":"+").
				append(dollSum).append(avoCorrection!=0?((avoCorrection<0?"":"+") + avoCorrection) : "").
				append(")=").append(avoSum + dollSum + avoCorrection);
		return groupMsg(a.getUsergroup(), sbd.toString());
	}

	private static String upmecha(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		Mecha oldMecha=getMechaByUser(u);
		if(oldMecha!=null){
			Mecha mecha = generateMecha(value);
			mecha.setId(oldMecha.getId());
			mecha.setUid(oldMecha.getUid());
			sbd.append(mDao.updateMecha(mecha)==-1?"更新失败":
					mecha.getMname()+"的数据已经更新完毕。");
		}else{
			sbd.append("您没有绑定机甲");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}

	private static String umecha(QQuser u) {
		StringBuilder sbd = new StringBuilder();
		Mecha mecha = getMechaByUser(u);
		if (mecha == null) {
			sbd.append("你尚未绑定机甲");
		} else {
			mecha.setUid(null);
			sbd.append(mDao.updateMecha(mecha) == -1 ? "操作失败" : "成功与" +
					mecha.getMname() + "解除绑定");
		}
		return groupMsg(u.getUsergroup(), sbd.toString());
	}
	private static String atk(QQuser u, String value,String ordinal) {
		System.out.println("ordinal  "+ordinal);
		StringBuilder sbd = new StringBuilder();
		LinkedHashMap weapon;
		String weaponName;
		int hitCorrect = 0;
		String val = value;
		String sent = null;
		int mechaHit = 0;
		if(value==null){
			sbd.append("请输入攻击数据");
			return groupMsg(u.getUsergroup(), sbd.toString());
		}
		if (value.contains(".")) {
			weaponName = value.substring(0, value.indexOf("."));
			val = value.substring(value.indexOf(".") + 1);
			LinkedHashMap<String, String> map = getSent(val);
			val = map.get("value");
			sent = map.get("sent");
			hitCorrect = getFateNum(val);
		} else {
			if(value.contains(" ")){
				weaponName = value.substring(0, value.indexOf(" "));
				LinkedHashMap<String, String> map = getSent(val);
				//weaponName = map.get("value");
				sent = map.get("sent");
			}
			else weaponName = value;
		}
		LinkedHashMap attMap;
		String atkUnitName;
		if (ordinal != null) {
			String atkName,mechaName;
			if(ordinal.contains(",")){
				atkName = ordinal.substring(0,ordinal.indexOf(","));
				mechaName = ordinal.substring(ordinal.indexOf(",")+1);
				if(mechaName.toCharArray()[0]=='-'||mechaName.toCharArray()[0]=='+'||
						(mechaName.toCharArray()[0]<'9'&&mechaName.toCharArray()[0]>'0')){
					mechaHit = getFateNum(mechaName);
				}else{
					mechaHit = mechaName==""?0:Integer.parseInt((String)
							getDataByName(mechaName, "机甲").get("感应"));
				}
			}else{
				atkName = ordinal;
			}
			if (getDataObjectByName(atkName,"人物") == null) {
				sbd.append("查无此人");
				return groupMsg(u.getUsergroup(), sbd.toString());
			} else {
				atkUnitName = getDataObjectByName(atkName, "人物").getName();
				attMap = getDataByName(atkName, "人物");
				weapon = getDataByName(weaponName, "武器");
				if (weapon == null) {
					sbd.append("没有发现这把武器");
					return groupMsg(u.getUsergroup(), sbd.toString());
				}
			}
		} else {
			Mecha mecha = getMechaByUser(u);
			if (mecha == null) {
				sbd.append("你尚未绑定机甲");
				return groupMsg(u.getUsergroup(), sbd.toString());
			}
			attMap = u.getUserAttribute();
			atkUnitName = u.getUserName();
			System.out.println("weaponName "+ weaponName);
			if (weaponName.toCharArray()[0] > '9') {
				weapon = getDataByName(weaponName, "武器");
				if (weapon == null) {
					sbd.append("没有发现这把武器");
					return groupMsg(u.getUsergroup(), sbd.toString());
				}
			} else {
				int weaponNum = Integer.parseInt(weaponName);
				LinkedHashMap[] weaponData = new LinkedHashMap[5];
				weaponData[1] = mecha.getWeapon1Data();
				weaponData[2] = mecha.getWeapon2Data();
				weaponData[3] = mecha.getWeapon3Data();
				weaponData[4] = mecha.getWeapon4Data();
				if (weaponNum < 1 || weaponNum > 4) {
					sbd.append("错误的武器位数据，数字应该在1~4之间");
					return groupMsg(u.getUsergroup(), sbd.toString());
				}
				if (weaponData[weaponNum].get("命中") == null) {
					sbd.append("该武器位无武器");
					return groupMsg(u.getUsergroup(), sbd.toString());
				}
				weapon = weaponData[weaponNum];
				mechaHit = mecha.getmSe();
			}
		}
		int[] hit = hitOperation(attMap, mechaHit, weapon);
		int hitSSum = hit[0] + hit[1] + hit[2];
		int hitTime = Integer.parseInt((String) weapon.get("最高攻击次数"));
		int hitSection = Integer.parseInt((String) weapon.get("伤害段数"));
		sbd.append(atkUnitName);
		if (sent != null) {
			sbd.append("高喊着:“").append(sent).append("”,");
		}
		sbd.append("使用武器").append(weapon.get("名称")).append("(")
				.append(weapon.get("伤害类型")).append(",")
				.append(weapon.get("武器类型")).append(",可攻击次数:").append(hitTime).append(")攻击：\\n")
				.append("武器命中为:").append(hit[0]).append(",机体命中修正为:")
				.append(hit[1]).append(",人物命中修正为:").append(hit[2]);
		if (hitCorrect != 0) {
			sbd.append(",额外修正为").append(hitCorrect);
		}
		sbd.append("\\n");
		int[] doll = fateArray(4);
		int dollSum = doll[0] + doll[1] + doll[2] + doll[3];
		sbd.append("掷出的命运骰子为【").append(doll[0]).append("，").append(doll[1]).append("，").append(doll[2]).append("，").append(doll[3]).append("】");
		sbd.append("\\n");
		sbd.append("命中值合计为(").append(hitSSum).append(dollSum<0?"":"+").
				append(dollSum).append(hitCorrect!=0?((hitCorrect<0?"":"+") + hitCorrect) : "").
				append(")=").append(hitSSum + dollSum + hitCorrect);
		int[] damage = fateArray(2);
		int weaponDamage = Integer.parseInt((String) weapon.get("伤害"));
		sbd.append("\\n");
		sbd.append("如果命中，将造成").append(weaponDamage);
		if (hitSection != 1) {
			sbd.append("每段伤害的").append(hitSection).append("段");
		}
		sbd.append("武器伤害+伤害骰子【").append(damage[0]).
				append(",").append(damage[1]).append("】，合计为:").
				append(weaponDamage * hitSection + damage[0] + damage[1]).
				append("点伤害。");
		return groupMsg(u.getUsergroup(), sbd.toString());
	}
	private static String setm(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		if(getMechaByUser(u)==null){
			String re = addm(u,value,u);
			if(re.contains("添加失败")){
				sbd.append("添加失败");
			}else{
				sbd.append("添加机甲成功！已与").append(u.getUserName()).append("绑定！");
			}
		}else{
			sbd.append("您已经绑定过机甲");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}

	private static String addm(QQuser u, String value,QQuser target) {
		StringBuilder sbd = new StringBuilder();
		if(!u.getUserID().equals("553859318")&&target==null){
			sbd.append("您没有权限这么做");
		}else if(value!=null){
			Mecha mecha = generateMecha(value);
			if(target!=null){
				LinkedHashMap<String,Object> uid = getUidByUser(target);
				mecha.setUid(mapSave(uid));
			}
			sbd.append(mDao.addMecha(mecha)==-1?"添加失败":"已添加机甲："+mecha.getMname());
		}else{
			sbd.append("请输入创建机甲的属性");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}

	private static String dc(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder("");
		if(value==null){
			sbd.append("请输入抽牌的序号");
		}else{
			int num = Integer.parseInt(value);
			if(value.equals("")){
				sbd.append("请输入抽牌的序号");
			}else if(cardMode.get(u.getUsergroup())==null){
				sbd.append("没有洗入命运卡牌");
			}else if(num<1||num>9){
				sbd.append("请输入1到9之间的序号");
			}else{
				sbd.append("你抽到的卡牌是： ");
				sbd.append(cardMode.get(u.getUsergroup())[num]);
			}
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}
	private static String cset(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder("");
		if(u.getUserID().equals("553859318")){
			System.out.println("进入1");
			int level = value!=null&&!value.equals("") ?Integer.parseInt(value):4;
			String[] card;
			String cardNum;
			switch(level){
				case 1:{
					card = new String[]{"大成功","成功","成功","成功","成功","成功","成功","成功","失败"};
					cardNum = "1张大成功，7张成功，1张失败";
					break;
				}
				case 2:{
					card = new String[]{"大成功","成功","成功","成功","成功","成功","成功","失败","失败"};
					cardNum = "1张大成功，6张成功，2张失败";
					break;
				}
				case 3:{
					card = new String[]{"大成功","成功","成功","成功","成功","成功","失败","失败","失败"};
					cardNum = "1张大成功，5张成功，3张失败";
					break;
				}
				case 4:{
					card = new String[]{"大成功","成功","成功","成功","成功","失败","失败","失败","大失败"};
					cardNum = "1张大成功，4张成功，3张失败，1张大失败";
					break;
				}
				case 5:{
					card = new String[]{"大成功","成功","成功","成功","失败","失败","失败","失败","大失败"};
					cardNum = "1张大成功，3张成功，4张失败，1张大失败";
					break;
				}
				case 6:{
					card = new String[]{"成功","成功","成功","失败","失败","失败","失败","失败","大失败"};
					cardNum = "3张成功，5张失败，1张大失败";
					break;
				}
				case 7:{
					card = new String[]{"成功","成功","失败","失败","失败","失败","失败","失败","大失败"};
					cardNum = "2张成功，6张失败，1张大失败";
					break;
				}
				case 8:{
					card = new String[]{"成功","失败","失败","失败","失败","失败","失败","失败","大失败"};
					cardNum = "1张成功，7张失败，1张大失败";
					break;
				}
				default:{
					card = new String[]{"大成功","成功","成功","成功","成功","失败","失败","失败","大失败"};
					cardNum = "1张大成功，4张成功，3张失败，1张大失败";
					break;
				}
			}
			Random ra = new Random();
			String[] cardDeck = new String[9];
			int[] num1 = new int[]{0,0,0,0,0,0,0,0,0};
			int raNum;
			for(int i=0;i<9;i++){
				do{
					raNum = ra.nextInt(9)+1;
				}
				while(arrayRE(num1,raNum));
				num1[i] = raNum;
				cardDeck[num1[i]-1]=card[i];
			}
			cardMode.put(u.getUsergroup(),cardDeck);
			sbd.append("抽卡已经设定完成,已洗入");
			sbd.append(cardNum);
			sbd.append("，请抽取");
			for (String aCardDeck : cardDeck) {
				System.out.println(aCardDeck);
			}
		}else{
			sbd.append("只有GM才能开始洗牌！");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}
	private static String diceQH(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder("");
		if(value!=null&&u.getUserID().equals("553859318")){
			switch (value) {
				case "mf":
					sbd.append("成功切换到魔法模式");
					break;
				case "jj":
					sbd.append("成功切换到机甲模式");
					break;
				default:
					return groupMsg(u.getUsergroup(), "模式错误！");
			}
			sqlDao.saveDiceState(value);
			myDice = sqlDao.diceLoad();
			ARPGList = sqlDao.allQQuser(myDice.getState());
		}else{
			sbd.append("您没有权限这么做！");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}
	private static String fz(QQuser u, String msg) {
		Random ra = new Random();
		int[] doll = new int[2];
		for(int i=0;i<doll.length;i++){
			doll[i] = ra.nextInt(3)-1;
		}
		String fzmsg = msg + u.getUserName() + "掷出的命运骰子为（" + doll[0] + "，" + doll[1] + "），" +
				"合计为:" + (doll[0] + doll[1]);
		return groupMsg(u.getUsergroup(), fzmsg);
	}
	private static String fon(QQuser u, String value) {
		if(value!=null&&u.getUserID().equals("553859318")&&value.equals(myDice.getCode())){
			fateState = true;
			return groupMsg(u.getUsergroup(),myDice.getName()+"已开启");
		}else{
			return groupMsg(u.getUsergroup(),"你没有权限这么做");
		}
	}
	private static String foff(QQuser u, String value) {
		if(value!=null&&u.getUserID().equals("553859318")&&value.equals(myDice.getCode())){
			fateState = false;
			return groupMsg(u.getUsergroup(),myDice.getName()+"已关闭");
		}else{
			return groupMsg(u.getUsergroup(),"你没有权限这么做");
		}
	}
	private static String fr(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		if(value!=null){
			String sent = null;
			String val;
			int num = 0;
			if(value.contains(" ")){
				if(value.contains("+")){
					val = value.substring(0,value.indexOf("+"));
					num = Integer.parseInt(value.substring(value.indexOf("+")+1,value.indexOf(" ")));
					sent = value.substring(value.indexOf(" ")+1);
				}else if(value.contains("-")){
					val = value.substring(0,value.indexOf("-"));
					num = -Integer.parseInt(value.substring(value.indexOf("-")+1,value.indexOf(" ")));
					sent = value.substring(value.indexOf(" ")+1);
				}else{
					val = value.substring(0,value.indexOf(" "));
					sent = value.substring(value.indexOf(" ")+1);
				}
				sent = reflSpace(sent);
			}else{
				if(value.contains("+")){
					val = value.substring(0,value.indexOf("+"));
					num = Integer.parseInt(value.substring(value.indexOf("+")+1));
				}else if(value.contains("-")){
					val = value.substring(0,value.indexOf("-"));
					num = -Integer.parseInt(value.substring(value.indexOf("-")+1));
				}else{
					val = value;
				}
			}
			int sVal;
			if(u.getUserAttribute().get(val)!=null){
				sVal = Integer.parseInt(u.getUserAttribute().get(val).toString())+(u.getUserAttribute().get(val+"X")==null?0:Integer.parseInt(u.getUserAttribute().get(val+"X").toString()));
				sVal += num;
				sbd.append(sVal);
				if(sent!=null){
					sbd.append(" ");
					sbd.append(sent);
				}
				return fate(u,sbd.toString(),val);
			}else{
				sbd.append("技能名错误");
				return groupMsg(u.getUsergroup(),sbd.toString());
			}
		}else{
			sbd.append("请输入要检定的技能名");
			return groupMsg(u.getUsergroup(),sbd.toString());
		}
	}
	private static String setu(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		String 	defineAtt = "默认值:0|";
		if(myDice.getState().equals("mf")){
			defineAtt = "魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|" +
					"欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|" +
					"亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|";
		}else if(myDice.getState().equals("jj")){
			defineAtt = "感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|" +
					"欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|" +
					"电子:0|驾驶:0|机师等级:1|";
		}
		if(value!=null){
			String att = value.substring(0,value.indexOf("-"));
			String fdata = value.substring(value.indexOf("-")+1);
			LinkedHashMap<String, Object> L1 = mapLoad(att);
			LinkedHashMap<String, Object> L2 = mapLoad(defineAtt);
			LinkedHashMap<String, Object> L3 = mapLoad(fdata);
			Set<String> keySet = L1.keySet();
			for (String k : keySet) {
				Object v = L1.get(k);
				L2.put(k, v);
			}
			if(myDice.getState().equals("mf")){
				int qh = Integer.parseInt(L2.get("魔力亲和").toString());
				if(qh==1){
					L2.put("体魄X",1);
					L2.put("打斗X",1);
					L2.put("运动X",1);
				}else if(qh==2){
					L2.put("体魄X",1);
					L2.put("运动X",1);
				}else if(qh==3){
					L2.put("体魄X",1);
				}else if(qh==5){
					L2.put("体魄X",-1);
				}else if(qh==6){
					L2.put("体魄X",-2);
					L2.put("魔法X",1);
				}else if(qh==7){
					L2.put("体魄X",-2);
					L2.put("运动X",-1);
					L2.put("魔法X",1);
					L2.put("察觉X",1);
				}
				int sl = Integer.parseInt(L2.get("体魄").toString())+(L2.get("体魄X")==null?0:Integer.parseInt(L2.get("体魄X").toString()));
				stress(sl, L3,"生理压力");
				int xl = Integer.parseInt(L2.get("意志").toString())+(L2.get("意志X")==null?0:Integer.parseInt(L2.get("意志X").toString()));
				stress(xl, L3,"心理压力");
				Unit u1 = new Unit(u.getUserName(),L3.get("生理压力").toString(),L3.get("心理压力").toString(),Integer.parseInt(L3.get("魔力压力").toString()),Integer.parseInt(L3.get("魔力压力").toString()),0,u.getUserID(),u.getUsergroup());
				if(uDao.findUnit(u1)!=0){
					uDao.saveUnit(u1);
				}else{
					uDao.saveUnit2(u1);
				}
			}else if(myDice.getState().equals("jj")){
				int sl = Integer.parseInt(L2.get("体魄").toString());
				stress(sl, L3,"生理压力");
				int xl = Integer.parseInt(L2.get("意志").toString());
				stress(xl, L3,"心理压力");
			}
			u.setUserAttribute(L2);
			u.setUserData(L3);
			sqlDao.saveData(u.getUserData(), u,myDice.getState());
			sqlDao.saveAtt(u.getUserAttribute(), u);
			sbd.append("导卡成功！");
		}else{
			sbd.append("导卡指令错误！");
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}

	private static String mfQH(QQuser u, String value) {
		Random ra = new Random();
		int qh = ra.nextInt(99)+1;
		int magicQH;
		StringBuilder sbd = new StringBuilder();
		if(value==null){
			if(qh<=5){
				magicQH = 1;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人被称为魔力绝缘者。他们无法储").append("存和使用魔力，但一般拥有过人的肉体强度。\\n(魔力压力上限为0，体魄，打斗，运动+1）");
			}else if(qh<=25){
				magicQH = 2;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人只能感受到少许魔力，一般无法").append("参与魔法相关的工作，大部分普通人处于这一等级。\\n(魔力压力上限为1，体魄，运动+1）");
			}else if(qh<=70){
				magicQH = 3;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人具备释放魔法的能力，但大多").append("仅仅只能释放一两个法术，大部分使用魔力进行辅助的战士处于这一等级。\\n(魔力压力上限为2，体魄+1）");
			}else if(qh<=80){
				magicQH = 4;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人完全具备了作为一名魔法师的资质").append("。\\n(魔力压力上限为4）");
			}else if(qh<=89){
				magicQH = 5;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人是魔法师中的佼佼者，更高的魔力储").append("量虽然损害了身体，但提供了更长久的魔力输出。\\n(魔力压力上限为7，体魄-1）");
			}else if(qh<=97){
				magicQH = 6;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人在魔力方面的天赋是世间顶尖的").append("其强大的魔力让其他魔法师望而生畏。\\n(魔力压力上限为10，魔法+1，体魄-2）");
			}else {
				magicQH = 7;
				sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人只在各种传说中有被提及").append("他们是真正的被魔力所眷顾的天才。\\n(魔力压力上限为14，魔法，察觉+1，体魄-2，运动-1）");
			}
		}else{
			if(value.equals("m")){
				if(qh<=40){
					magicQH = 4;
					sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人完全具备了作为一名魔法师的资质").append("。\\n(魔力压力上限为4）");
				}else if(qh<=75){
					magicQH = 5;
					sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人是魔法师中的佼佼者，更高的魔力储").append("量虽然损害了身体，但提供了更长久的魔力输出。\\n(魔力压力上限为7，体魄-1）");
				}else if(qh<=95){
					magicQH = 6;
					sbd.append(u.getUserName()).append("的魔力亲和为").append(magicQH).append(",").append(magicQH).append("级魔力亲和的人在魔力方面的天赋是世间顶尖的").append("其强大的魔力让其他魔法师望而生畏。\\n(魔力压力上限为10，魔力+1，体魄-2）");
				}else {
					magicQH = 7;
					sbd.append(u.getUserName()).append("的魔力亲和为").
							append(magicQH).append(",").append(magicQH).
							append("级魔力亲和的人只在各种传说中有被提及").
							append("他们是真正的被魔力所眷顾的天才。" +
									"\\n(魔力压力上限为14，魔力，察觉+1，体魄-2，运动-1）");
				}
			}
		}
		return groupMsg(u.getUsergroup(),sbd.toString());
	}
	private static String hf(QQuser u, String value) {
		if(u.getUserID().equals("553859318")){
			StringBuffer sbf = new StringBuffer("自然恢复列表：\\n");
			switch (value) {
				case "mp":
					for (Unit A : uList) {
						if (A.getuType() == 2) {
							if (A.getuMp() < A.getuMaxmp()) {
								A.setuMp(A.getuMp() + 1);
								sbf.append(A.getuFromUser()).append("的").append(A.getuName()).append("充能了1魔力，当前").append(A.getuMp()).append("点\\n");
							}
						} else {
							if (A.getuMp() < A.getuMaxmp()) {
								A.setuMp(A.getuMaxmp());
								sbf.append(A.getuName()).append("的魔力恢复完毕\\n");
							}
						}
					}
					break;
				case "hp":
					for (Unit A : uList) {
						if (A.getuType() != 2 && (A.getuHp().contains("√") || A.getuSp().contains("√"))) {
							A.setuHp(A.getuHp().replace("√", "□"));
							A.setuSp(A.getuSp().replace("√", "□"));
							sbf.append(A.getuName()).append("的生理压力与精神压力恢复完毕\\n");
						}
					}
					break;
				default:
					sbf.append("输入错误\\n");
					break;
			}
			uDao.saveUnitsMsg(uList);
			sbf = new StringBuffer(sbf.substring(0,sbf.length()-2));
			return groupMsg(u.getUsergroup(),sbf.toString());
		}else{
			return groupMsg(u.getUsergroup(),"你没有权限这么做");
		}
	}
	private static String fd(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		if(value!=null){
			String type;
			if(value.contains(".")){
				type =value.substring(0,value.indexOf("."));
				value = value.substring(value.indexOf(".")+1);
				value = replaceQuotes(value);
				if(!value.equals("err")){
					ArrayList<DataMsg> dList = dDao.datafind(value,type);
					DataMsg dataMsg = new DataMsg();
					int index = 0;
					for(DataMsg d:dList){
						index++;
						if(index == 1){
							dataMsg = d;
						}
					}
					if(index == 1){
						sbd.append("条目名:").append(dataMsg.getName()).append("\\n");
						sbd.append("类型:").append(dataMsg.getType()).append("\\n");
						sbd.append(dDao.huan(dataMsg.getMsg()));
					}else if(index > 1){
						sbd.append("已找到相关条目").append(index).append("条：\\n");
						for(DataMsg d:dList){
							sbd.append("  ").append(d.getName()).append("[").append(d.getType()).append("]\\n");
						}
						sbd = new StringBuilder(sbd.substring(0,sbd.length()-2));
					}else{
						sbd.append("未找到相关条目");
					}
				}else{
					sbd.append("错误，请不要输入单个英文双引号");
				}
			}
			if(value.equals("")||value.equals(" ")){
				sbd.append("请输入要查询的关键字。");
			}
			return groupMsg(u.getUsergroup(),sbd.toString());
		}else{
			return groupMsg(u.getUsergroup(),"请输入要查询的关键字。");
		}
	}
	private static String uList(QQuser u, String value) {
		ArrayList<Unit> userUnit = new ArrayList<>();
		ArrayList<Unit> npcUnit = new ArrayList<>();
		ArrayList<Unit> itemUnit = new ArrayList<>();
		StringBuilder sbd = new StringBuilder();
		for(Unit A:uList){
			switch(A.getuType()){
				case 0:
					userUnit.add(A);
					break;
				case 1:
					npcUnit.add(A);
					break;
				case 2:
					itemUnit.add(A);
					break;
			}
		}
		if(value!=null&&value.equals("item")){
			sbd.append("物品状态："+"\\n");
			sbd.append("物品名"+ "\\t\\t" + "魔力压力"+ "\\t" + "所属"+"\\n");
			for(Unit A:itemUnit){
				sbd.append(A.getuName()).append((A.getuName().length() > 2) ? "\\t" : "\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\t\\t").append(A.getuFromUser()).append("\\n");
			}
		}else{
			sbd.append("单位状态："+"\\n");
			sbd.append("单位名"+ "\\t\\t" + "生理压力"+ "\\t" + "心理压力"+ "\\t" + "魔力压力"+"\\n");
			sbd.append("—————————————PC单位——————————————————"+"\\n");
			for(Unit A:userUnit){
				sbd.append(A.getuName()).append((A.getuName().length() > 3) ? "\\t" : "\\t\\t").append(A.getuHp()).append((A.getuHp().length() > 4) ? "\\t" : "\\t\\t").append(A.getuSp()).append("\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\n");
			}
			sbd.append("—————————————NPC单位——————————————————"+"\\n");
			for(Unit A:npcUnit){
				sbd.append(A.getuName()).append((A.getuName().length() > 3) ? "\\t" : "\\t\\t").append(A.getuHp()).append((A.getuHp().length() > 4) ? "\\t" : "\\t\\t").append(A.getuSp()).append("\\t\\t").append(A.getuMp()).append("/").append(A.getuMaxmp()).append("\\n");
			}
		}
		uDao.saveUnitsMsg(uList);
		return groupMsg(u.getUsergroup(),sbd.toString());
	}
	private static String addItem(QQuser u, String value) {
		if(value!=null){
			LinkedHashMap<Item,Integer>  h = (u.getUserItem()==null)? new LinkedHashMap<>():u.getUserItem();
			if(value.contains("-")){
				String key = value.substring(0,value.indexOf(","));
				int val = Integer.valueOf(value.substring(value.indexOf(",")+1));
				String name = key.substring(0,key.indexOf("-"));
				key = key.substring(key.indexOf("-")+1);
				int weight = Integer.valueOf(key.substring(0,key.indexOf("-")));
				key = key.substring(key.indexOf("-")+1);
				int price = Integer.valueOf(key);
				name = reAllSpace(name);
				h.put(new Item(name,weight,price),(val==0)?1:val);
				u.setUserItem(h);
				sqlDao.saveItem(u.getUserItem(), u);
				return groupMsg(u.getUsergroup(),"新增物品-"+name+"成功！");
			}else{
				String key = value.substring(0,value.indexOf(","));
				int val = Integer.valueOf(value.substring(value.indexOf(",")+1));
				key = reAllSpace(key);
				h.put(new Item(key,0,0),(val==0)?1:val);
				u.setUserItem(h);
				sqlDao.saveItem(u.getUserItem(), u);
				return groupMsg(u.getUsergroup(),"新增物品-"+key+"成功！");
			}
		}else{
			return groupMsg(u.getUsergroup(),"请输入要新增的物品信息！！");
		}
	}
	private static String mfZZ(QQuser u) {
		Random ra = new Random();
		//		LinkedHashMap<String, Object> check = u.getUserData();
		if(u.getUserData()==null||u.getUserData().get("火元素")==null){
			StringBuilder sbd = new StringBuilder(u.getUserName()+"的魔法资质为\\n");
			int[] inborn = new int[8];
			int index = 0;
			do{
				inborn[0] = ra.nextInt(99)+1;
				inborn[1] = 100-inborn[0];
				index++;
			}
			while(inborn[0]>100||inborn[1]>100||(inborn[0]>80||inborn[1]>80)&&index<5);

			for(int i=2;i<=6;i++){
				index = 0;
				do{
					inborn[i] = ra.nextInt((i>4?inborn[1]:inborn[0])/8+35)+16+((i>4?inborn[1]:inborn[0])/2);
					index++;
				}
				while(inborn[i]>100||((inborn[i]>=80&&index<3)||(inborn[i]>=90&&index<8)));
			}

			index = 0;
			do{
				inborn[7] = ra.nextInt(50)+1+(inborn[1]/5);
				index++;
			}
			while(inborn[7]>100||((inborn[7]>=20&&index<15)||(inborn[6]>=30&&index<25)||(inborn[6]>=40&&index<50)));
			sbd.append("    亲光元素: {\\n");
			sbd.append("      火元素: ").append(inborn[2]).append("%\\n");
			sbd.append("      风元素: ").append(inborn[3]).append("%\\n");
			sbd.append("      雷元素: ").append(inborn[4]).append("%\\n");
			sbd.append("    }\\n");
			sbd.append("    亲暗元素: {\\n");
			sbd.append("      地元素: ").append(inborn[5]).append("%\\n");
			sbd.append("      水元素: ").append(inborn[6]).append("%\\n");
			sbd.append("      金元素: ").append(inborn[7]).append("%\\n");
			sbd.append("    }\\n");
			System.out.println(sbd.toString());
			LinkedHashMap<String, Object> L = (u.getUserData()==null? new LinkedHashMap<>():u.getUserData());
			L.put("火元素", inborn[2]+"%");
			L.put("风元素", inborn[3]+"%");
			L.put("雷元素", inborn[4]+"%");
			L.put("地元素", inborn[5]+"%");
			L.put("水元素", inborn[6]+"%");
			L.put("金元素", inborn[7]+"%");
			u.setUserData(L);
			sqlDao.saveData(u.getUserData(), u,myDice.getState());
			return groupMsg(u.getUsergroup(),sbd.toString());
		}else{
			return groupMsg(u.getUsergroup(),"已经测过资质啦，不能重复测试。");
		}
	}
	private static String fatedbLink(QQuser u) {
		if(u.getUserID().equals("553859318")){
			groupList = new ArrayList<>();
			groupList = sqlDao.allQQuser("QQ");
			ARPGList = new ArrayList<>();
			ARPGList = sqlDao.allQQuser(myDice.getState());
			uList = new ArrayList<>();
			uList = uDao.loadUnits();
			return groupMsg(u.getUsergroup(),"与数据库同步成功");
		}else{
			return groupMsg(u.getUsergroup(),"你没有权限这么做");
		}
	}
	private static String set(QQuser u, String value) {
		if(value!=null){
			if((value.indexOf(":"))!=-1){
				String key = value.substring(0,value.indexOf(":"));
				String val = value.substring(value.indexOf(":")+1);
				return set(u,key,val);
			}else{
				return groupMsg(u.getUsergroup(),"格式不正确，格式set.xxx:vvv");
			}
		}else{
			return groupMsg(u.getUsergroup(),"请输入要设置的属性哦，格式set.xxx:vvv");
		}
	}
	private static String set(QQuser u, String key,String val) {
		key = reflSpace(key);
		val = reflSpace(val);
		if(u.getUserData()!=null&&u.getUserData().get(key)!=null){
			u.setUserData(key,val);
			sqlDao.saveData(u.getUserData(), u,myDice.getState());
			return groupMsg(u.getUsergroup(),"修改成功");
		}else{
			return groupMsg(u.getUsergroup(),"未找到修改项属性");
		}


	}
	private static String tra(QQuser u, String value) {
		StringBuilder sbd = new StringBuilder();
		if(value!=null){
			int index;
			if((index = groupList.indexOf(new QQuser(value,u.getUsergroup()))) != -1){
				Set<String> keySet = groupList.get(index).getUserData().keySet();
				for (String key : keySet) {
					Object val = groupList.get(index).getUserData().get(key);
					sbd.append(key).append(":\\t").append(val).append("\\n");//+"\n"
				}
				return groupMsg(u.getUsergroup(),groupList.get(index).getUserName()+"的属性为：\\n"+sbd.toString());
			}else{
				return groupMsg(u.getUsergroup(),"该QQ在本群的人物不存在哦！");
			}
		}else{
			if(u.getUserData()!=null){
				Set<String> keySet = u.getUserData().keySet();
				for (String key : keySet) {
					Object val = u.getUserData().get(key);
					sbd.append(key).append(":\\t").append(val).append("\\n");
				}
				sbd.append("装备为："+"\\n");
				Set<String> keySet3 = u.getUserEquip().keySet();
				if(u.getUserEquip()!=null){
					for (String key : keySet3) {
						Object val = u.getUserEquip().get(key);
						sbd.append(key).append(":\\t").append(val).append("\\n");
					}
				}
				if(u.getUserItem()!=null){
					Set<Item> keySet2 = u.getUserItem().keySet();
					sbd.append("持有的物品为："+"\\n");
					sbd.append("物品名"+ "\\t" + "重量"+ "\\t" + "价格"+ "\\t" + "数量"+"\\n");
					int sumW = 0;
					int sumP = 0;
					for (Item key : keySet2) {
						int val = u.getUserItem().get(key);
						sbd.append(key.getItemName()).append("\\t").append(key.getItemWeight()).append("kg\\t").append(key.getItemprice()).append("G\\t").append(val).append("\\n");
						sumW+=key.getItemWeight()*val;
						sumP+=key.getItemprice()*val;
					}
					sbd.append("总重量为").append(sumW).append("kg   总价格为").append(sumP).append("G");
				}
				return groupMsg(u.getUsergroup(),u.getUserName()+"的属性为：\\n"+sbd.toString());
			}else{
				return groupMsg(u.getUsergroup(),u.getUserName()+"没有数据！");
			}

		}
	}
	private static String setnew(QQuser u, String value) {
		LinkedHashMap<String,Object> h = new LinkedHashMap<>();
		if(value!=null){
			h.put("姓名",u.getUserName());
			String values = value;
			while(values.contains("|")){
				String key = values.substring(0,values.indexOf(":"));
				String val = values.substring(values.indexOf(":")+1,values.indexOf("|"));
				//System.out.println("key为："+key);
				key = reflSpace(key);
				val = reflSpace(val);
				h.put(key,val);
				values = values.substring(values.indexOf("|")+1);
			}
			u.setUserData(h);
			sqlDao.saveData(u.getUserData(), u,myDice.getState());
			return groupMsg(u.getUsergroup(),u.getUserName()+"的属性保存成功！");
		}else{
			return groupMsg(u.getUsergroup(),"请输入要设置的属性哦，格式setnew.xxx:vvv|xxx:vvv...");
		}
	}
	private static String fnn(QQuser u, String value) {
		QQuser a = getARPGUserByUser(u);
		if(value!=null){
			u.setUserName(value);
			sqlDao.saveName(value,u,"QQ");
			if(a!=null){
				a.setUserName(value);
				sqlDao.saveName(value,u,myDice.getState());
			}
			return groupMsg(u.getUsergroup(),u.getUserOldName()+"更改名字为："+u.getUserName());
		}else{
			u.setUserName(u.getUserOldName());
			sqlDao.saveName(u.getUserOldName(),u,"QQ");
			if(a!=null){
				a.setUserName(u.getUserOldName());
				sqlDao.saveName(u.getUserOldName(),u,myDice.getState());
			}
			return groupMsg(u.getUsergroup(),u.getUserOldName()+"已恢复原名");
		}
	}
	private static String fate(QQuser u, String value,String skill) {
		int num;
		String sent;
		if (value != null) {
			LinkedHashMap<String,String> map = getSent(value);
			value = map.get("value");
			sent = map.get("sent");
			num = getFateNum(value);
			return groupMsg(u.getUsergroup(),FateRoll(u.getUserName(),num,sent,skill));
		}else{
			return groupMsg(u.getUsergroup(),FateRoll(u.getUserName(),0, null,skill));
		}
	}
}
