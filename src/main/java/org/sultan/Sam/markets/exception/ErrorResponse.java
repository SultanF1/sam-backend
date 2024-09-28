package org.sultan.Sam.markets.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

  private final String message;
  private final String code;
  private final int status;

  @Builder
  public ErrorResponse(String message, String code, int status) {
    this.message = message;
    this.code = code;
    this.status = status;
  }
}
