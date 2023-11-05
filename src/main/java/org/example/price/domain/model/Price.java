package org.example.price.domain.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record Price(
        String id,
        Instrument instrument,
        BigDecimal bid,
        BigDecimal ask,
        ZonedDateTime timestamp
) {

}