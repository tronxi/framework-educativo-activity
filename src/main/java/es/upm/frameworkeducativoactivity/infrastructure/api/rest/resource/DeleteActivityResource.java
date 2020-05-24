package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class DeleteActivityResource {

    private final DeleteActivity deleteActivity;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{activityId}/group/{groupId}")
    public ResponseEntity findActivitiesByGroupId(@PathVariable String activityId, @PathVariable String groupId) {
        deleteActivity.deleteByGroupId(activityId, groupId);
        return ResponseEntity.ok().build();
    }
}
