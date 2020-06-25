package es.upm.frameworkeducativoactivity.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadActivityRequest {
    String name;
    MultipartFile file;
}
