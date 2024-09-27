package com.ensah.core.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @PostMapping()
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        String fileName = file.getOriginalFilename();
//        String newFilename = generateNewFilename(originalFilename);
        String filePath = "/rapport/" + fileName; // Adjust path based on your property

        File dest = new File(filePath);
        file.transferTo(dest);

        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }
}
