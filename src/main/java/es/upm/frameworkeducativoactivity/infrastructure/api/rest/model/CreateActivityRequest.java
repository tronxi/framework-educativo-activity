package es.upm.frameworkeducativoactivity.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateActivityRequest {
    List<String> groupId;
    String name;
    Timestamp maxDate;
}
