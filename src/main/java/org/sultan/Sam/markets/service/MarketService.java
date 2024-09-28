package org.sultan.Sam.markets.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MarketService {
  void registerMarket(
      List<MultipartFile> files,
      String market,
      String prompt,
      String temperature,
      String entryQuestions,
      String email);

  List<String> getAllMarketNames();

  String getMarketPrompt(String market);

  Double getMarketTemperature(String market);

  String getEntryQuestions(String market);
}
