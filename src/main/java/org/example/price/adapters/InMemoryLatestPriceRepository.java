package org.example.price.adapters;

import lombok.NonNull;
import org.example.price.domain.model.Instrument;
import org.example.price.domain.model.Price;
import org.example.price.domain.ports.LatestPriceRepository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryLatestPriceRepository implements LatestPriceRepository {

    private final ConcurrentMap<Instrument, Price> prices = new ConcurrentHashMap<>();

    @Override
    public void save(@NonNull Price price) {
        prices.put(price.instrument(), price);
    }

    @Override
    public Optional<Price> find(@NonNull Instrument instrument) {
        return Optional.ofNullable(prices.get(instrument));
    }
}
