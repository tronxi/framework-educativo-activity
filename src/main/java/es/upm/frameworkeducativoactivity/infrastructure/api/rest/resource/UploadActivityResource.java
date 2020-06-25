package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadActivity;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.UploadActivityMapper;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.UploadActivityRequest;
import es.upm.frameworkeducativoactivity.infrastructure.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class UploadActivityResource {

    private final UploadActivityMapper uploadActivityMapper;
    private final UploadActivity uploadActivity;
    private final JwtService jwtService;

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping(value = "/{activityId}/student/{studentId}", consumes = "multipart/form-data")
    public ResponseEntity uploadActivity(@PathVariable String studentId, @PathVariable String activityId,
                                         @ModelAttribute UploadActivityRequest uploadActivityRequest,
                                         @RequestHeader("authorization") String authorization) {

        String user = jwtService.user(authorization);

        UploadActivityOrder uploadActivityOrder =
                uploadActivityMapper.toDomain(studentId, activityId, uploadActivityRequest);
        UploadActivityResult uploadActivityResult
                = uploadActivity.uploadActivity(uploadActivityOrder);

        return uploadActivityResult.isDone() ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

    }
}
