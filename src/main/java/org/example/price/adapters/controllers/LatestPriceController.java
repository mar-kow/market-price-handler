package org.example.price.adapters.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.price.domain.model.Instrument;
import org.example.price.domain.PriceService;

/**
 * This is a rough example of a controller.
 * I wanted to keep the codebase and its dependencies as small as possible to focus on design and clean code.
 * That's why annotations are limited and commented out.
 * In real life I would depend on mechanisms and classes provided by Spring Boot.
 */
@Slf4j
// @RestController
@RequiredArgsConstructor
public class LatestPriceController {

    private final PriceService priceService;

    // todo eur/usd? EUR/USD?
    // @GetMapping("/price/latest/{instrumentName}")
    public HttpResponse getLatestPrice(
            /* @PathVariable */ String instrumentName
    ) {
        Instrument instrument = Instrument.forName(instrumentName);
        log.info("Received get latest price request for instrument={}", instrument);
        return priceService.findLatest(instrument)
                .map(price -> HttpResponse.ok(Mappers.asDto(price)))
                .orElse(HttpResponse.notFound());
    }

}
