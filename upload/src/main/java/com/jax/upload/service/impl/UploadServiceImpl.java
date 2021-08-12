package com.jax.upload.service.impl;

import com.jax.upload.service.UploadService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public void save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }


        try {
            Files.copy(file.getInputStream(), Paths.get("uploads").resolve(file.getOriginalFilename()));
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());
            System.out.println(file.getContentType());

            if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            {
                System.out.println(true);
            }

//            FileInputStream fileInputStream = new FileInputStream(file.);
            Workbook work = new XSSFWorkbook((file.getInputStream()));
            System.out.println(work.getNumberOfSheets());

            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("uploads/daily.xlsx")));
            System.out.println(workbook.getNumberOfSheets());
            Sheet sheet = workbook.getSheetAt(0);
            sheet.isColumnHidden(1);
            sheet.getRow(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

    }
}
