package es.upm.frameworkeducativoactivity.infrastructure.repository;

import com.amazonaws.services.s3.AmazonS3;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Repository {
    @Value("${aws-s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    public void save(UploadActivityOrder uploadActivityOrder) {
        File file = new File("file", ".temp");
        try {
            FileUtils.copyInputStreamToFile(uploadActivityOrder.getFile(), file);
            amazonS3.putObject(bucketName, getName(uploadActivityOrder),file);
        } catch (IOException e) {
           throw new RuntimeException("Error parsing file");
        }
    }

    private String getName(UploadActivityOrder uploadActivityOrder) {
        String ext = "." + FilenameUtils.getExtension(uploadActivityOrder.getName());
        return uploadActivityOrder.getActivityId() + "-" + uploadActivityOrder.getStudentId() + ext;
    }
}
