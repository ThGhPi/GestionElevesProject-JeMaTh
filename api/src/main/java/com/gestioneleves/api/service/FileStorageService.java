package com.gestioneleves.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileStorageService {
    
    @Value("${app.file.storage.path}")
    private String uploadDir;

    @Value("${app.public.file.url}")
    private String publicFileUrl;

    public String storeFile(MultipartFile file, String fileName) throws IOException {
        try {
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    
            return publicFileUrl + fileName; // return the file URL
        } catch (IOException e) {
            throw new RuntimeException(
                "Could not save : " + fileName.split("/")[0] 
                + " with id : " + fileName.split("_")[-1], e
                );
        }
    }

}
