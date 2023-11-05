package org.example.price.adapters.controllers;

import org.example.price.adapters.controllers.dtos.GetLatestPriceResponse;
import org.example.price.domain.model.Price;

class Mappers {

    private Mappers() {
    }

    static GetLatestPriceResponse asDto(Price price) {
        return new GetLatestPriceResponse(
                price.id(),
                price.instrument().getName(),
                price.bid(),
                price.ask(),
                price.timestamp()
        );
    }

}
