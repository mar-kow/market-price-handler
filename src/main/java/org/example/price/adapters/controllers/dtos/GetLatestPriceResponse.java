package org.example.price.adapters.controllers.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record GetLatestPriceResponse(
        String priceId,
        String instrument,
        BigDecimal bid,
        BigDecimal ask,
        ZonedDateTime timestamp
) {
}
