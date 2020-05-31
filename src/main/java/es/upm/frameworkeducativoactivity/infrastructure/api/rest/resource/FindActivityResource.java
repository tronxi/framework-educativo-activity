package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.domain.model.DeliveryOrder;
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindActivity;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindDelivery;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.ActivityMapper;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.DeliveryMapper;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.ActivityResponse;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.DeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class FindActivityResource {

    private final FindActivity findActivity;
    private final ActivityMapper activityMapper;
    private final FindDelivery findDelivery;
    private final DeliveryMapper deliveryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/group/{groupId}")
    public ResponseEntity<List<ActivityResponse>> findActivitiesByGroupId(@PathVariable String groupId) {
        List<ActivityResult> activityResultList = findActivity.findByGroupId(groupId);
        return ResponseEntity.ok(activityMapper.toResponse(activityResultList));
    }

    @GetMapping(value = "/{activityId}/student/{studentId}")
    public ResponseEntity<DeliveryResponse> findDeliveryByUserId(@PathVariable String activityId, @PathVariable String studentId) {
        DeliveryOrder deliveryOrder = deliveryMapper.toDomain(activityId, studentId);
        DeliveryResult deliveryResult = findDelivery.findById(deliveryOrder);
        return ResponseEntity.ok(deliveryMapper.toResponse(deliveryResult));
    }
}
