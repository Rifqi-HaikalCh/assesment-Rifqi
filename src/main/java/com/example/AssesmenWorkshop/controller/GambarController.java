package com.example.AssesmenWorkshop.controller;

import com.example.AssesmenWorkshop.model.Gambar;
import com.example.AssesmenWorkshop.service.GambarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/gambar")
public class GambarController {

    @Autowired
    private GambarService gambarService;

    // Endpoint untuk upload file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Gambar gambar = gambarService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully with ID: " + gambar.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    // Endpoint untuk download file
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Gambar gambar = gambarService.getFile(id);

        if (gambar == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gambar.getFileName() + "\"")
                .body(gambar.getFileData());
    }
}
