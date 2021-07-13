package app.utils;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class GCSUtils {

    public void getObject(String projectId,
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
