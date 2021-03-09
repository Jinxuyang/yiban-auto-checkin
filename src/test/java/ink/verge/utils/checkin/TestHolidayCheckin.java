package ink.verge.utils.checkin;

import ink.verge.utils.checkin.run.RunCheckin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;

/**
 * @Author Verge
 * @Date 2021/1/16 15:56
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHolidayCheckin {
    @Resource
    private RunCheckin runCheckin;
    @Test
    public void test(){
        runCheckin.noonCheck();
    }

}
