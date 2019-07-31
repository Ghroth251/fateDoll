package lemocclient;


import com.fate.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MainClass {
    @Autowired
    public UserService uSv;

    @Test
    public void out1(){
        System.out.println(uSv.getUserList("QQ"));
    }



}
