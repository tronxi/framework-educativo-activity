package es.upm.frameworkeducativoactivity.infrastructure.api.rest.resource;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.DownloadActivity;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper.DownloadActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping(value = "activity-service/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("authenticated")
@RequiredArgsConstructor
public class DownloadFileActivityResource {

    private final DownloadActivity downloadActivity;
    private final DownloadActivityMapper downloadActivityMapper;

    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    @GetMapping(value = "/{activityId}/student/{studentId}/download")
    public ResponseEntity<Resource> download (@PathVariable String activityId, @PathVariable String studentId) {

        DownloadActivityOrder downloadActivityOrder = downloadActivityMapper.toDomain(activityId, studentId);
        DownloadActivityResult downloadActivityResult = downloadActivity.download(downloadActivityOrder);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadActivityResult.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(downloadActivityResult.getInputStreamResource());
    }
}
