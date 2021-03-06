package app.conf;

import app.models.documentai.DocuAIRequest;
import app.models.documentai.DocumentAILocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name="projectId")
    public String getProjectId(){
        return "gcp-practice-315505";
    }

    @Bean(name="mapper")
    public ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean(name="docuAITemplate")
    public DocuAIRequest getDocuAIReqeuest(){
        return DocuAIRequest.builder()
                .location(DocumentAILocation.us)
                .processorId("5bc92f04bc135f8e")
                .projectId("gcp-practice-315505")
                .build();
    }
}
