package app.utils;

import app.models.documentai.DocuAIRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.cloud.documentai.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DocumentAIUtils {

    @Resource(name="docuAITemplate")
    private DocuAIRequest request;

    public void getResult(final String filePath) throws Exception {
        DocumentProcessorServiceClient client = DocumentProcessorServiceClient.create();


        byte[] fileData = Files.readAllBytes(Paths.get(filePath));

        ByteString content = ByteString.copyFrom(fileData);

        RawDocument document =
                RawDocument.newBuilder().setContent(content).setMimeType(CommonUtils.getMimeType(filePath)).build();

        String name = String.format("projects/%s/locations/%s/processors/%s",
                request.getProjectId(), request.getLocation(), request.getProcessorId());

        ProcessRequest request =
                ProcessRequest.newBuilder().setName(name).setRawDocument(document).build();

        ProcessResponse result = client.processDocument(request);


        File file = new File("result.json");


        Document documentResponse = result.getDocument();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getTextStylesList()));
        log.info("=========================================================");
        log.info("=========================================================");

        for (Document.Page page : documentResponse.getPagesList()) {
            try {
                Map<String, Object> tempMap = new HashMap<>();

                tempMap.put("trasnformList", page.getTransformsList());
                tempMap.put("dimension", page.getDimension());
                tempMap.put("layout", page.getLayout());

                tempMap.put("detectedLanguageList", page.getDetectedLanguagesList());
                tempMap.put("blocks", page.getBlocksList());
                tempMap.put("paragraphs", page.getParagraphsList());
                tempMap.put("lines", page.getLinesList());
                tempMap.put("tokens", page.getTokensList());
                tempMap.put("visualElements", page.getVisualElementsList());
                tempMap.put("tables", page.getTablesList());
                tempMap.put("formFields", page.getFormFieldsList());

                log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tempMap));
            } catch (JsonProcessingException e) {
                StringWriter sw  = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                log.error(sw.toString());
            }
        }

        log.info("=========================================================");
        log.info("=========================================================");
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getEntitiesList()));
        log.info("=========================================================");
        log.info("=========================================================");
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getEntityRelationsList()));
        log.info("=========================================================");
        log.info("=========================================================");
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getTextChangesList()));
//        log.info("=========================================================");
//        log.info("=========================================================");
//        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getShardInfo()));
        log.info("=========================================================");
        log.info("=========================================================");
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getError().getCode()));
        log.info("=========================================================");
        log.info("=========================================================");
        log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(documentResponse.getRevisionsList()));

    }

}
