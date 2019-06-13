import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.fasfdfs.server.FastDFSClient;

import java.util.concurrent.TimeUnit;

//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;

//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.fasfdfs.Main;

/**
 * Test
 *
 * @author zhoujunwen
 * @date 2019-06-13
 * @time 13:40
 * @desc
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Main.class})
@Slf4j
public class Test {

    @Autowired
    private FastDFSClient fastDFSClient;

    //    @org.junit.Test
    public void test() {
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                String path = "/Users/zhoujunwen/Desktop/20140102095833296.png";
                String fileId = fastDFSClient.upload(path, null);
            }).start();
        }
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                String path = "/Users/zhoujunwen/Desktop/20140102095833296.png";
                String fileId = fastDFSClient.upload(path, null);
            }).start();
        }

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
