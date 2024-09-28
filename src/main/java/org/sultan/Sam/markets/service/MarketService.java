package org.sultan.Sam.markets.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MarketService {
    void registerMarket(List<MultipartFile> files, String market, String prompt, String temperature, String entryQuestions);
    List<String> getAllMarketNames();
    String getMarketPrompt(String market);
    Double getMarketTemperature(String market);
    String getEntryQuestions(String market);
}
