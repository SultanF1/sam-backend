package org.sultan.Sam.markets.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sultan.Sam.markets.service.MarketService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/markets")
public class RegisterController {
  private final MarketService marketService;

  @GetMapping("/names")
  public List<String> getAllMarkets() {
    return marketService.getAllMarketNames();
  }

  @PostMapping("/register")
  ResponseEntity<RegisterResponse> registerMarket(
      List<MultipartFile> files, String market, String prompt, String temperature) {
    log.info(
        "Received register request for market {}, prompt {}, temperature {}",
        market,
        prompt,
        temperature);
    marketService.registerMarket(files, market, prompt, temperature);
    return ResponseEntity.ok(new RegisterResponse(true, "Market Registered"));
  }

  record RegisterResponse(Boolean success, String message) {}
}
