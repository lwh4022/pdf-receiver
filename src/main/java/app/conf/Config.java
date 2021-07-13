package app.conf;

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
}
