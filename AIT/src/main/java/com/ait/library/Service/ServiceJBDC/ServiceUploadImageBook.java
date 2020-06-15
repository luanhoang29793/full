package com.ait.library.Service.ServiceJBDC;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
@Component
public interface ServiceUploadImageBook {
    public void init();

    public void save(MultipartFile file,int id);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
