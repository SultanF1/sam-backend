package org.sultan.Sam.embed.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sultan.Sam.embed.service.EmbedService;

@RestController
@AllArgsConstructor
class UploadController {
  private final Logger logger = LoggerFactory.getLogger(UploadController.class);
  private final EmbedService embedService;

  @PostMapping("/upload/{market}")
  public ResponseEntity<UploadResponse> uploadFile(
      MultipartFile file, @PathVariable String market) {
    logger.info("Received file upload request for market {}", market);
    embedService.embedFile(file, market);
    return ResponseEntity.ok(new UploadResponse(true, "File Uploaded"));
  }

  @PostMapping("/text")
  public ResponseEntity<UploadResponse> chat(
      @RequestParam String message, @RequestParam String market) {
    embedService.embedText(message, market);
    return ResponseEntity.ok(new UploadResponse(true, "Chat Completed"));
  }

  record UploadResponse(Boolean success, String message) {}
}
