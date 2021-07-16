package app.services;

import app.models.common.DefaultRespTemplate;
import app.models.common.Status;
import app.models.resp.GCSEvent;
import app.utils.GCSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InvalidClassException;

@Service
public class DocumentProcessing implements ProcessingInterface {

    @Resource(name="projectId")
    private String projectId;

    @Autowired
    private GCSUtils gcsUtils;

    @Override
    public Object process(Object parameter) throws InvalidClassException {

        GCSEvent event = null;
        if(parameter instanceof GCSEvent){
            event = (GCSEvent)  parameter;
        } else {
            throw new InvalidClassException("Parameter is not an instance of GCSEvent");
        }
        gcsUtils.getObject(projectId, event.getBucket(), event.getName(), "test.png");
        return DefaultRespTemplate.builder()
                .status(Status.ok)
                .errorMsg("")
                .payload(null)
                .build();

    }
}
