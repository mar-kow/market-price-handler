package org.example.price.domain.ports;

import org.example.price.domain.model.Instrument;
import org.example.price.domain.model.Price;

import java.util.Optional;

public interface LatestPriceRepository {

    void save(Price price);

    Optional<Price> find(Instrument instrument);

}
