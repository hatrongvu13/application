package com.jax.upload.service;

import org.springframework.web.multipart.MultipartFile;


public interface UploadService {
    public void save(MultipartFile file);
}
