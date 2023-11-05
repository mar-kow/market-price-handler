package org.example.price.domain.commisions;

import org.example.price.domain.model.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.price.domain.model.Instruments.EURJPY;
import static org.example.price.domain.model.Instruments.EURUSD;

class FlatRateCommissionPolicyTest {

    final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
    final CommissionPolicy commissionPolicy = new FlatRateCommissionPolicy(new BigDecimal("0.01"));

    @Test
    void shouldApplyCommission() {
        assertThat(
                commissionPolicy.apply(
                        new Price("1", EURUSD, new BigDecimal("1.07309"), new BigDecimal("1.07322"), now)
                )
        ).isEqualTo(
                new Price("1", EURUSD, new BigDecimal("1.07298"), new BigDecimal("1.07333"), now)
        );

        assertThat(
                commissionPolicy.apply(
                        new Price("2", EURJPY, new BigDecimal("160.279"), new BigDecimal("160.314"), now)
                )
        ).isEqualTo(
                new Price("2", EURJPY, new BigDecimal("160.263"), new BigDecimal("160.330"), now)
        );

    }

}