package com.mygym.services;


import com.mygym.config.FileStorageProperties;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Getter
public class FileStorageService {

    private final Path download;
    private final Path googleapi;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.download = Paths.get(fileStorageProperties.getDownload())
                .toAbsolutePath().normalize();
        this.googleapi = Paths.get(fileStorageProperties.getGoogleapi())
                .toAbsolutePath().normalize();
    }

}