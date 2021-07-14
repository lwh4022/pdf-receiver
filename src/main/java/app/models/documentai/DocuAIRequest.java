package app.models.documentai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class DocuAIRequest {

    private final String projectId;
    private final DocumentAILocation location;
    private final String processorId;
}
