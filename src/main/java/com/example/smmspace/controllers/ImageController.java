package com.example.smmspace.controllers;

import com.example.smmspace.models.Image;
import com.example.smmspace.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Image image = imageRepository.getOne(id);
        response.setContentType(image.getContentType());
        response.getOutputStream().write(Files.readAllBytes(Paths.get(image.getPath() + "/" + image.getName())));
        response.getOutputStream().close();
    }

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/my-file")
    public ResponseEntity<Resource> getFile() {
        // Получаем абсолютный путь к файлу
        String filePath = request.getServletContext().getRealPath("/path/to/file.txt");
        File file = new File(filePath);

        // Создаем ресурс из файла и возвращаем его в ResponseEntity
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }
}
