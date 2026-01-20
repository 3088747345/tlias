package javaweb.tlias;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    //日志技术
    //1.引入Logback依赖、配置logback.xml
    //2.定义日志记录对象，记录日志
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){

        log.debug("开始测试");
        //System.out.println(LocalDateTime.now() + " : 开始计算...");

        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        
        //System.out.println("计算结果为: "+sum);
        log.info("计算结果为: {}", sum);
        //System.out.println(LocalDateTime.now() + "结束计算...");
        log.debug("测试结束");
    }

}
