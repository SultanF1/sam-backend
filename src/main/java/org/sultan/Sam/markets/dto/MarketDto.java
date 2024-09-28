package org.sultan.Sam.markets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.sultan.Sam.markets.data.Market;

import java.io.Serializable;

/** DTO for {@link Market} */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MarketDto(String name, String prompt, String temperature) implements Serializable {}
