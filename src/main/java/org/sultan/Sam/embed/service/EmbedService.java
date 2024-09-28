package org.sultan.Sam.embed.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmbedService {
    void embedText(String message, String market);
    void embedFile(MultipartFile file, String market);
    void embedFiles(List<MultipartFile> files, String market);
}
