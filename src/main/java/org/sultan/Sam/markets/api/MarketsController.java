package org.sultan.Sam.markets.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.sultan.Sam.markets.service.MarketService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/markets")
public class MarketsController {
  private final MarketService marketService;

  @GetMapping("/names")
  public List<String> getAllMarkets() {
    return marketService.getAllMarketNames();
  }

  @GetMapping("{market}/entry-questions")
  public String getEntryQuestions(@PathVariable String market) {
    return marketService.getEntryQuestions(market);
  }

  @PostMapping("/register")
  ResponseEntity<RegisterResponse> registerMarket(
      List<MultipartFile> files, String market, String prompt, String temperature, String entryQuestions) {
    log.info(
        "Received register request for market {}, prompt {}, temperature {}",
        market,
        prompt,
        temperature);
    marketService.registerMarket(files, market, prompt, temperature, entryQuestions);
    return ResponseEntity.ok(new RegisterResponse(true, "Market Registered"));
  }

  record RegisterResponse(Boolean success, String message) {}
}
