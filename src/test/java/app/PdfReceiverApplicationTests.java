package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PdfReceiverApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {

        String jsonData = "{\n" +
                "        \"kind\": \"storage#object\",\n" +
                "        \"id\": \"ocr-req/cv83.png/1626151224841316\",\n" +
                "        \"selfLink\": \"https://www.googleapis.com/storage/v1/b/ocr-req/o/cv83.png\",\n" +
                "        \"mediaLink\": \"https://content-storage.googleapis.com/download/storage/v1/b/ocr-req/o/cv83.png?generation=1626151224841316&alt=media\",\n" +
                "        \"name\": \"cv83.png\",\n" +
                "        \"bucket\": \"ocr-req\",\n" +
                "        \"generation\": \"1626151224841316\",\n" +
                "        \"metageneration\": \"1\",\n" +
                "        \"contentType\": \"image/png\",\n" +
                "        \"storageClass\": \"STANDARD\",\n" +
                "        \"size\": \"813034\",\n" +
                "        \"md5Hash\": \"b/yoKT2LbJaRjTSdwVG+GQ==\",\n" +
                "        \"crc32c\": \"F06O3Q==\",\n" +
                "        \"etag\": \"COSo7rCd3/ECEAE=\",\n" +
                "        \"timeCreated\": \"2021-07-13T04:40:24.843Z\",\n" +
                "        \"updated\": \"2021-07-13T04:40:24.843Z\",\n" +
                "        \"timeStorageClassUpdated\": \"2021-07-13T04:40:24.843Z\"\n" +
                "        }";

        this.mockMvc.perform(post("/receivePDF")
                             .content(jsonData)
                             .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(matchAll(
                            status().isOk(),
                            content().json("{\"status\":\"OK\",\"msg\":\"\"}"))
                    );
    }

}
