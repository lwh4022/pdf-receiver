package app.handler;

import app.models.resp.GCSEvent;
import app.models.resp.ResponseTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.file.Paths;

@RestController
@Slf4j
public class WebHandler {

    @Resource(name="projectId")
    private String projectId;

    @RequestMapping(value="/receivePDF", method = {RequestMethod.POST})
    public ResponseTemplate receivePdf(@RequestBody final GCSEvent event) throws JsonProcessingException {
        log.info(event.toJsonString());
        //getObject(projectId, );
        return new ResponseTemplate(ResponseTemplate.RESP_STATUS.OK, "");
    }

    private void getObject(String projectId,
                           String bucketName,
                           String objectName,
                           String destFilePath){

        Storage storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .build()
                .getService();

        Blob blob = storage.get(BlobId.of(bucketName, objectName));

        blob.downloadTo(Paths.get(destFilePath));
    }
}

