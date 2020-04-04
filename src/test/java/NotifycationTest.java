import domain.Notifycation;
import mapper.NotifycationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;


/**
 * 这是通知公告的单元测试
 * @author TOSHIBA
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:Spring-Context.xml",
        "classpath:Spring-Mybatis.xml"
})
public class NotifycationTest {

    //属性
    @Resource
    NotifycationMapper notifycationMapper;

    @Test
    public void NotifycationTest1(){
        Notifycation notifycation=new Notifycation();

        notifycation=notifycationMapper.selectByPrimaryKey(0);

        if(notifycation != null)
            System.out.println(notifycation.toString());
        else
            System.out.println("没有此查询结果!");
    }

    @Test
    public void NotifycationTest2(){

        List<Notifycation> list=notifycationMapper.selectByBuildId(4);

        if(list != null)
            for(int i=0; i<list.size(); i++)
                System.out.println(list.get(i).toString());
        else
            System.out.println("没有此查询结果!");
    }

    @Test
    public void testTime(){
        Date date=new Date();
        System.out.println(date);
    }
}
