package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

import java.io.InputStream;

@Builder
@Value
public class UploadActivityOrder {
    String name;
    String studentId;
    String activityId;
    InputStream file;
}
