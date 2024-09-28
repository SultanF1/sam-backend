package org.sultan.Sam.markets.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionMapper {

  @ExceptionHandler(MarketNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleMarketNotFoundException(MarketNotFoundException e) {
    log.error("Market not found exception", e);
    return new ErrorResponse(e.getMessage(), "MarketNotFound", HttpStatus.NOT_FOUND.value());
  }
}
