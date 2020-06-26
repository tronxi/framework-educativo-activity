package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.core.io.InputStreamResource;

@Value
@Builder
public class DownloadActivityResult {
    InputStreamResource inputStreamResource;
    String name;
}
