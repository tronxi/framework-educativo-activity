package es.upm.frameworkeducativoactivity.domain.port.secondary;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;

public interface FileRepository {
    DownloadActivityResult get(DownloadActivityOrder downloadActivityOrder);
}
