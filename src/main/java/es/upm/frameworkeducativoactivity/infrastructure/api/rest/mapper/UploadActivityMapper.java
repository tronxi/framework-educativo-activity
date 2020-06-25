package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.UploadActivityRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UploadActivityMapper {

    public UploadActivityOrder toDomain(String studentId, String activityId,
                                        UploadActivityRequest uploadActivityRequest)  {
        try {
            return UploadActivityOrder.builder()
                    .activityId(activityId)
                    .studentId(studentId)
                    .name(uploadActivityRequest.getFile().getOriginalFilename())
                    .file(uploadActivityRequest.getFile().getInputStream())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Error parsing file");
        }
    }
}
