package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.CreateActivity;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.CreateActivityMapper;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.CreateActivityRequest;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.CreateActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class CreateActivityResource {

    private final CreateActivity createActivity;
    private final CreateActivityMapper createActivityMapper;

    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CreateActivityResponse> createActivity(@RequestBody CreateActivityRequest createActivityRequest) {

        CreateActivityOrder createActivityOrder = createActivityMapper.toDomain(createActivityRequest);
        CreateActivityResult createActivityResult = createActivity.createActivity(createActivityOrder);
        return ResponseEntity.ok(createActivityMapper.toResponse(createActivityResult));
    }
}
