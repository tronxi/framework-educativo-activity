package es.upm.frameworkeducativoactivity.infrastructure.repository;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.secondary.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileRepositoryAdapter implements FileRepository {

    private final S3Repository s3Repository;

    @Override
    public DownloadActivityResult get(DownloadActivityOrder downloadActivityOrder) {
        return s3Repository.get(getName(downloadActivityOrder));
    }

    private String getName(DownloadActivityOrder downloadActivityOrder) {
        return downloadActivityOrder.getActivityId() + "-" + downloadActivityOrder.getStudentId() + ".";
    }
}
