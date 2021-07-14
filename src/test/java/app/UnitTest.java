package app;

import app.conf.Config;
import app.utils.DocumentAIUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Config.class, DocumentAIUtils.class})
public class UnitTest {

    @Autowired
    private DocumentAIUtils utils;

    @Test
    public void test() throws Exception {

        utils.getResult("졸업증명서.pdf");

    }

}
