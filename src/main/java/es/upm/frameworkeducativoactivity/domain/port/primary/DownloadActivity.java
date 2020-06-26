package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult;

public interface DownloadActivity {
    DownloadActivityResult download(DownloadActivityOrder downloadActivityOrder);
}
