package es.upm.frameworkeducativoactivity.domain.model;


import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Value
public class CreateActivityOrder {
    List<String> groupId;
    String name;
    Timestamp maxDate;
}
