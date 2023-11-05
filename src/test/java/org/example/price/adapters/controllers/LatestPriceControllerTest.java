package org.example.price.adapters.controllers;

import org.example.price.adapters.controllers.dtos.GetLatestPriceResponse;
import org.example.price.domain.PriceService;
import org.example.price.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.price.domain.model.Instruments.EURPLN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LatestPriceControllerTest {

    final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));

    @Mock
    PriceService priceService;

    @InjectMocks
    LatestPriceController latestPriceController;

    @Test
    public void shouldReturnLatestPriceForExistingInstrument() {
        var price = new Price("id", EURPLN, new BigDecimal("4.45544"), new BigDecimal("4.46144"), now);
        when(priceService.findLatest(any())).thenReturn(Optional.of(price));

        var response = latestPriceController.getLatestPrice("EUR/PLN");

        assertThat(response).isEqualTo(
                new HttpResponse(
                        200,
                        new GetLatestPriceResponse(
                                price.id(),
                                price.instrument().getName(),
                                price.bid(),
                                price.ask(),
                                price.timestamp()
                        )
                )
        );
    }

    @Test
    public void shouldReturn404ForNonExistingInstrument() {
        when(priceService.findLatest(any())).thenReturn(Optional.empty());

        var response = latestPriceController.getLatestPrice("EUR/PLN");

        assertThat(response).isEqualTo(
                new HttpResponse(404, null)
        );
    }

}