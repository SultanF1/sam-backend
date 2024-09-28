package org.sultan.Sam.markets.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.sultan.Sam.embed.service.EmbedService;
import org.sultan.Sam.markets.data.Market;
import org.sultan.Sam.markets.data.MarketEntityRepository;

@Service
@AllArgsConstructor
class MarketServiceImpl implements MarketService {
  private final EmbedService embedService;
  private final MarketEntityRepository marketEntityRepository;

  @Override
  public void registerMarket(
      List<MultipartFile> files, String market, String prompt, String temperature) {
    files.forEach(file -> embedService.embedFile(file, market));
    marketEntityRepository.save(new Market(market, prompt, temperature));
  }

  @Override
  public List<String> getAllMarketNames() {
    return marketEntityRepository.findAll().stream().map(Market::getName).toList();
  }

  @Override
  public String getMarketPrompt(String market) {
    return marketEntityRepository.findByName(market).getPrompt();
  }

  @Override
  public Double getMarketTemperature(String market) {
    return Double.valueOf(marketEntityRepository.findByName(market).getTemperature());
  }
}
