package org.example.price.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InstrumentTest {

    @Test
    void shouldCreateInstrumentWithUpperCaseName() {
        var instrument1 = Instrument.forName("EUR/USD");
        var instrument2 = Instrument.forName("EUR/usd");
        var instrument3 = Instrument.forName("eur/usd");

        assertThat(instrument1.getName()).isEqualTo("EUR/USD");
        assertThat(instrument2.getName()).isEqualTo("EUR/USD");
        assertThat(instrument3.getName()).isEqualTo("EUR/USD");
        assertThat(instrument1).isEqualTo(instrument2).isEqualTo(instrument3);
    }

    @Test
    void shouldNotCreateInstrumentWithNullName() {
        assertThatThrownBy(
                () -> Instrument.forName(null)
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("name is marked non-null but is null");
    }
}