package ink.verge.utils.checkin.run;

import ink.verge.utils.checkin.entity.User;
import ink.verge.utils.checkin.service.impl.UserServiceImpl;

import ink.verge.utils.checkin.utils.YibanUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class RunCheckin {
    private final UserServiceImpl userService;
    private final YibanUtils yibanUtils;

    // 每日的打卡分钟 {1-59}
    public static int dayMinute;

    @Autowired
    public RunCheckin(UserServiceImpl userService, YibanUtils yibanUtils) {
        this.userService = userService;
        this.yibanUtils = yibanUtils;
    }

    /**
     * 晨间签到
     */
    //@Scheduled(cron = "0 0 6-8 * * *")
    public void morCheck() throws InterruptedException {
        log.info("开始执行晨间签到");

        List<User> morUndoneUser = userService.getMornUncheckUser();
        for (User user : morUndoneUser) {
            if (yibanUtils.checkin(user,1)) userService.setCheckinStatus(user.getUid(),true,1);
            Thread.sleep(1000);
        }

    }

    /**
     * 午间签到
     */
    //@Scheduled(cron = "0 0 12-14 * * *")
    public void noonCheck() throws InterruptedException{
        log.info("开始执行午间签到");

        List<User> noonUndoneUserList = userService.getNoonUncheckUser();
        for (User user : noonUndoneUserList) {
            if (yibanUtils.checkin(user,2)) userService.setCheckinStatus(user.getUid(),true,2);
            Thread.sleep(1000);
        }
    }

    /**
     * 假期签到
     */
//    @Scheduled(cron = "0 0 8-14 * * *")
    public void holidayCheckin(){
        List<User> list = userService.getMornUncheckUser();
        for (User user : list) {
            if (yibanUtils.checkin(user)) userService.setCheckinStatus(user.getUid(),true,1);
        }
    }

    /**
     * 8-14点的每分钟进行打卡检查
     * 5 秒是为了防止两边时间计算不一样从而导致一分钟连续打两次
     */
    @Scheduled(cron = "5 * 8-14 * * *")
    public void randomCheck(){
        // 时间符合
        if (Calendar.getInstance().get(Calendar.MINUTE)==dayMinute){
            // 进行打卡
            List<User> list = userService.getMornUncheckUser();
            for (User user : list) {
                if (yibanUtils.checkin(user)) userService.setCheckinStatus(user.getUid(),true,1);
            }
        }
    }


}
