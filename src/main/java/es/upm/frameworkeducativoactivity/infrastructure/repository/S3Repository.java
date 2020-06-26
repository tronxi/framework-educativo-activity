package es.upm.frameworkeducativoactivity.infrastructure.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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
            amazonS3.putObject(bucketName, getName(uploadActivityOrder), file);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing file");
        }
    }

    public DownloadActivityResult get(String name) {
        S3Object s3object = amazonS3.getObject(bucketName, findCompleteName(name));
        return DownloadActivityResult.builder()
                .name(findCompleteName(name))
                .inputStreamResource(new InputStreamResource(s3object.getObjectContent()))
                .build();
    }

    private String getName(UploadActivityOrder uploadActivityOrder) {
        String ext = "." + FilenameUtils.getExtension(uploadActivityOrder.getName());
        return uploadActivityOrder.getActivityId() + "-" + uploadActivityOrder.getStudentId() + ext;
    }

    private String findCompleteName(String name) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        return objectListing.getObjectSummaries().stream()
                .filter(os -> os.getKey().startsWith(name))
                .map(S3ObjectSummary::getKey)
                .findFirst().orElseThrow(RuntimeException::new);
    }

}
