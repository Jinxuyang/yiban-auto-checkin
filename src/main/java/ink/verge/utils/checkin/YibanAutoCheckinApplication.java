package ink.verge.utils.checkin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling // 启用定时任务
@ComponentScan({"com.fehead.lang.**","ink.verge.utils.checkin"})
@MapperScan("ink.verge.utils.checkin.mapper")
public class YibanAutoCheckinApplication {

    public static void main(String[] args) {
        SpringApplication.run(YibanAutoCheckinApplication.class, args);
    }

}
