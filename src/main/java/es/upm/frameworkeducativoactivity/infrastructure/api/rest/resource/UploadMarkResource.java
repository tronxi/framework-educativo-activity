package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadMark;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.UploadMarkMapper;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.UploadMarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class UploadMarkResource {

    private final UploadMark uploadMark;
    private final UploadMarkMapper uploadMarkMapper;

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping(value = "/{activityId}/student/{studentId}/mark")
    public ResponseEntity uploadMark(@PathVariable String studentId, @PathVariable String activityId,
                                     @RequestBody UploadMarkRequest mark) {
        UploadMarkOrder uploadMarkOrder = uploadMarkMapper.toDomain(studentId, activityId, mark);
        uploadMark.upload(uploadMarkOrder);
        return ResponseEntity.ok().build();
    }
}
