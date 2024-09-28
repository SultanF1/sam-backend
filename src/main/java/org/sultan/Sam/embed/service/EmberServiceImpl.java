package org.sultan.Sam.embed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Slf4j
class EmberServiceImpl implements EmbedService {
  private static final String MARKET_KEY = "market";
  private final Logger logger = LoggerFactory.getLogger(EmberServiceImpl.class);
  private final VectorStore vectorStore;

  @Override
  public void embedText(String message, String market) {
    logger.info("Embedding text {}", message);
    List<Document> documents = new ArrayList<>();
    try {
      documents.add(new Document(message, Map.of(MARKET_KEY, market)));
      vectorStore.add(documents);
    } catch (Exception e) {
      logger.error("Error while embedding text {}", message);
    }
  }

  @Override
  public void embedFile(MultipartFile file, String market) {
    switch (file.getContentType()) {
      case "application/pdf":
        embedPdfFile(file.getResource(), market);
        break;
      case "text/plain":
        embedTextFile(file.getResource(), market);
        break;
      case null:
        break;
      default:
        logger.error("Unsupported file type {}", file.getContentType());
    }
  }

  @Override
  public void embedFiles(List<MultipartFile> files, String market) {
    files.forEach(file -> embedFile(file, market));
  }

  private void embedPdfFile(Resource resource, String market) {
    PagePdfDocumentReader pdfReader =
        new PagePdfDocumentReader(
            resource,
            PdfDocumentReaderConfig.builder()
                .withPageTopMargin(0)
                .withPageExtractedTextFormatter(
                    ExtractedTextFormatter.builder().withNumberOfTopTextLinesToDelete(0).build())
                .withPagesPerDocument(1)
                .build());

    List<Document> documents = pdfReader.get();
    documents.forEach(document -> document.getMetadata().put(MARKET_KEY, market.toLowerCase()));

    vectorStore.add(documents);
  }

  private void embedTextFile(Resource resource, String market) {
    List<Document> documents = new TextReader(resource).get();

    documents.forEach(document -> document.getMetadata().put(MARKET_KEY, market.toLowerCase()));

    vectorStore.add(documents);
  }
}
