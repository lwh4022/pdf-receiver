package app.models.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseTemplate {

    private final RESP_STATUS status;
    private final String msg;

    public enum RESP_STATUS{
        OK,
        ERR
    }
}


