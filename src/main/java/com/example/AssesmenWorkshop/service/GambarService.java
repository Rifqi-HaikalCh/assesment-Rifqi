package com.example.AssesmenWorkshop.service;

import com.example.AssesmenWorkshop.model.Gambar;
import com.example.AssesmenWorkshop.repository.GambarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GambarService {

    @Autowired
    private GambarRepository gambarRepository;

    public Gambar saveFile(MultipartFile file) throws IOException {
        Gambar gambar = new Gambar();
        gambar.setFileName(file.getOriginalFilename());
        gambar.setFileData(file.getBytes());
        return gambarRepository.save(gambar);
    }

    public Gambar getFile(Long id) {
        return gambarRepository.findById(id).orElse(null);
    }
}
