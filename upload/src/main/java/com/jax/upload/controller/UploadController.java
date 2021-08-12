package com.jax.upload.controller;

import com.jax.upload.service.UploadService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UploadController {

    @Autowired
    UploadService uploadService;

    @SneakyThrows
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file")MultipartFile file) {
        uploadService.save(file);
    }

    @SneakyThrows
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "acb";
    }
}
