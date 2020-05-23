package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "activity-service/activity")
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class CreateActivityResource {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/estado")
    public String loadStudent() {
        return "ok";
    }
}
