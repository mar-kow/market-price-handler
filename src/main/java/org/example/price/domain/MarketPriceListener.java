package org.example.price.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.price.domain.model.Price;

@Slf4j
public class MarketPriceListener {

    private final PriceService priceService;

    public MarketPriceListener(PriceService priceService) {
        this.priceService = priceService;
    }

    public void onPrice(Price price) {
        log.info("Received price={}", price);
        priceService.updateLatest(price);
    }

}
