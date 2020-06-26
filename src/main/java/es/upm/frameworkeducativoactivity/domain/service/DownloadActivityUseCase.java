package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.DownloadActivity;
import es.upm.frameworkeducativoactivity.domain.port.secondary.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadActivityUseCase implements DownloadActivity {

    private final FileRepository fileRepository;

    @Override
    public DownloadActivityResult download(DownloadActivityOrder downloadActivityOrder) {
        return fileRepository.get(downloadActivityOrder);
    }
}
