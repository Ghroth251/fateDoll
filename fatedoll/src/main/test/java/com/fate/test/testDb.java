package com.fate.test;

import cc.plural.jsonij.JSON;
import com.fate.Dao.DiceDao;
import com.fate.Dao.UserDao;
import com.fate.Service.UserService;
import com.fate.bean.QQuser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class testDb {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao ud;

    @Autowired
    public UserService uSV;

    @Autowired
    DiceDao dd;

    @Test
    public void test1() throws SQLException {
//        //System.out.println(dataSource);
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
        System.out.println(uSV.getUserList("QQ"));
        System.out.println(uSV.getUserByUser(new QQuser("553859318","618409993")));
        System.out.println(uSV.getUserList("QQ"));
    }

    @Test
    public void testAdd(){
//        QQuser q=new QQuser("810","114514");
//        QQuser q2 = ud.save(q);
//        System.out.println(q2);
//        System.out.println(dd.findOne(1L));
        ud.save(new QQuser(113,"445630541","阿斯顿马丁","风林戈","650903307",null
        ,null,null,null,"QQ"));
    }

//    @Test
//    public void jsonTest(){
//        String value ="{\n" +
//                "  \"username\": \"盘尼西林不会过敏\",\n" +
//                "  \"nick\": \"KP\",\n" +
//                "  \"sex\": \"0\",\n" +
//                "  \"age\": \"23\",\n" +
//                "  \"error\": \"0\",\n" +
//                "  \"act\": \"2\",\n" +
//                "  \"fromGroup\": \"790664778\",\n" +
//                "  \"fromGroupName\": \"机甲整备站\",\n" +
//                "  \"fromQQ\": \"553859318\",\n" +
//                "  \"subType\": \"1\",\n" +
//                "  \"sendTime\": \"22714\",\n" +
//                "  \"fromAnonymous\": \"\",\n" +
//                "  \"msg\": \"但星期六是肯定开的\",\n" +
//                "  \"font\": \"48763032\"\n" +
//                "}";
//        LinkedHashMap<String,String> h = new LinkedHashMap<>();
////        value = value.replace("\n","");
//        value=value.substring(2,value.indexOf("}")-1);
//        value = value.replace(" ","");
//        value = value.replace("\"","");
//        String values = value;
//        while(values.contains("\n")){
//            String val2 = values.substring(0,values.indexOf("\n")+1);
//            String key = val2.substring(0,val2.indexOf(":"));
//            String val = val2.substring(val2.indexOf(":")+1,val2.indexOf("\n")-1);
//            h.put(key,val);
//            values = values.substring(values.indexOf("\n")+1);
//        }
//        System.out.println(h.get("nick"));
//    }

}
