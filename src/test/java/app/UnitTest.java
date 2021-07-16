package app;

import app.conf.Config;
import app.utils.DocumentAIUtils;
import app.utils.PubSubUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = {Config.class, PubSubUtils.class})
public class UnitTest {

    @Autowired
    private PubSubUtils utils;

    @Resource(name="projectId")
    private String projectId;

    @Test
    public void test() throws Exception {
        System.out.println(projectId);

        for(String topics : utils.listTopics(projectId)){
            System.out.println("topics : " + topics);
        };
    }

}
