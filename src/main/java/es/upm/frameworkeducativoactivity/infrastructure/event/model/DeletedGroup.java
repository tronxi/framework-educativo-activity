package es.upm.frameworkeducativoactivity.infrastructure.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeletedGroup {
    private String id_group;
    private String id_subject;
    private String name;
}
