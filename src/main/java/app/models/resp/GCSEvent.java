package app.models.resp;

import app.utils.ModelHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@JsonDeserialize(builder = GCSEvent.GCSEventBuilder.class)
public class GCSEvent extends ModelHelper {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("bucket")
    private final String bucket;

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GCSEventBuilder {

    }
}

//        {
//        "kind": "storage#object",
//        "id": "ocr-req/cv83.png/1626151224841316",
//        "selfLink": "https://www.googleapis.com/storage/v1/b/ocr-req/o/cv83.png",
//        "mediaLink": "https://content-storage.googleapis.com/download/storage/v1/b/ocr-req/o/cv83.png?generation=1626151224841316&alt=media",
//        "name": "cv83.png",
//        "bucket": "ocr-req",
//        "generation": "1626151224841316",
//        "metageneration": "1",
//        "contentType": "image/png",
//        "storageClass": "STANDARD",
//        "size": "813034",
//        "md5Hash": "b/yoKT2LbJaRjTSdwVG+GQ==",
//        "crc32c": "F06O3Q==",
//        "etag": "COSo7rCd3/ECEAE=",
//        "timeCreated": "2021-07-13T04:40:24.843Z",
//        "updated": "2021-07-13T04:40:24.843Z",
//        "timeStorageClassUpdated": "2021-07-13T04:40:24.843Z"
//        }