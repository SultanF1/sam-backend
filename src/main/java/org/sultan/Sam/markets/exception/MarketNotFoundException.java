package org.sultan.Sam.markets.exception;

import lombok.Getter;

@Getter
public class MarketNotFoundException extends RuntimeException {

  private final String id;

  public MarketNotFoundException(String id) {
    super(id + " is not found");
    this.id = id;
  }
}
