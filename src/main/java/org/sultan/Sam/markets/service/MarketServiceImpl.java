package org.sultan.Sam.markets.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.sultan.Sam.embed.service.EmbedService;
import org.sultan.Sam.markets.data.Market;
import org.sultan.Sam.markets.data.MarketEntityRepository;
import org.sultan.Sam.markets.exception.MarketNotFoundException;

@Service
@AllArgsConstructor
class MarketServiceImpl implements MarketService {
  private final EmbedService embedService;
  private final MarketEntityRepository marketEntityRepository;

  private Market getMarketByName(String market) {
    final Optional<Market> optionalMarket = marketEntityRepository.findByName(market);
    optionalMarket.orElseThrow(() -> new MarketNotFoundException(market));
    return optionalMarket.get();
  }

  @Override
  public void registerMarket(
      List<MultipartFile> files,
      String market,
      String prompt,
      String temperature,
      String entryQuestions,
      String email) {
    embedService.embedFiles(files, market);
    marketEntityRepository.save(new Market(market, prompt, temperature, entryQuestions, email));
  }

  @Override
  public List<String> getAllMarketNames() {
    return marketEntityRepository.findAll().stream().map(Market::getName).toList();
  }

  @Override
  public String getMarketPrompt(String market) {
    return getMarketByName(market).getPrompt();
  }

  @Override
  public Double getMarketTemperature(String market) {
    return Double.valueOf(getMarketByName(market).getTemperature());
  }

  @Override
  public String getEntryQuestions(String market) {
    return getMarketByName(market).getEntryQuestions();
  }
}
