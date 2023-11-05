package org.example.price.domain;

import org.example.price.domain.commisions.CommissionPolicy;
import org.example.price.domain.model.Price;
import org.example.price.domain.ports.LatestPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.price.domain.model.Instruments.EURUSD;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));

    @Mock
    CommissionPolicy commissionPolicy;

    @Mock
    LatestPriceRepository latestPriceRepository;

    @InjectMocks
    PriceService priceService;

    @Test
    void shouldApplyCommissionAndStoreLatestPrice() {
        var originalPrice = new Price("1", EURUSD, new BigDecimal("1.07309"), new BigDecimal("1.07322"), now);
        var priceWithMargin = new Price("2", EURUSD, new BigDecimal("1.07300"), new BigDecimal("1.07333"), now);
        Mockito.when(commissionPolicy.apply(any())).thenReturn(priceWithMargin);

        priceService.updateLatest(originalPrice);

        Mockito.verify(commissionPolicy).apply(originalPrice);
        Mockito.verify(latestPriceRepository).save(priceWithMargin);
    }

    @Test
    void shouldReturnLatestPrice() {
        var price = new Price("1", EURUSD, new BigDecimal("1.07309"), new BigDecimal("1.07322"), now);
        Mockito.when(latestPriceRepository.find(any())).thenReturn(Optional.of(price));

        var result = priceService.findLatest(EURUSD);

        assertThat(result).isNotEmpty().contains(price);
        Mockito.verify(latestPriceRepository).find(EURUSD);
    }

}