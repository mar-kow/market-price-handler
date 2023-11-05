package org.example.price.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.price.domain.commisions.CommissionPolicy;
import org.example.price.domain.model.Instrument;
import org.example.price.domain.model.Price;
import org.example.price.domain.ports.LatestPriceRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PriceService {

    private final LatestPriceRepository latestPriceRepository;

    private final CommissionPolicy commissionPolicy;

    public void updateLatest(Price price) {
        var priceWithMargin = commissionPolicy.apply(price);
        log.info("Updating latest price={}", priceWithMargin);
        latestPriceRepository.save(priceWithMargin);
    }

    public Optional<Price> findLatest(Instrument instrument) {
        return latestPriceRepository.find(instrument);
    }

}
