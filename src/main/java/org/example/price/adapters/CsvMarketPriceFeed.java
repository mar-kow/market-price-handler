package org.example.price.adapters;

import lombok.RequiredArgsConstructor;
import org.example.price.domain.model.Instrument;
import org.example.price.domain.model.Price;
import org.example.price.domain.MarketPriceListener;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CsvMarketPriceFeed {

    private final MarketPriceListener marketPriceListener;
    private final DateTimeFormatter dateTimeFormatter = priceFeedDateTimeFormatter();


    public void feedMarketPrices(String filename) {
        try (Scanner scanner = new Scanner(getInputStream(filename))) {
            while (scanner.hasNextLine()) {
                process(scanner.nextLine());
            }
        }
    }

    private void process(String line) {
        var price = asPrice(line);
        marketPriceListener.onPrice(price);
    }

    private InputStream getInputStream(String filename) {
        InputStream inputStream = requireNonNull(getClass().getClassLoader().getResourceAsStream(filename));
        return new BufferedInputStream(inputStream);
    }

    private Price asPrice(String line) {
        var tokens = line.split(",");

        return new Price(
                tokens[0],
                Instrument.forName(tokens[1]),
                new BigDecimal(tokens[2]),
                new BigDecimal(tokens[3]),
                ZonedDateTime.parse(tokens[4], dateTimeFormatter)
        );
    }

    private static DateTimeFormatter priceFeedDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS")
                .withZone(ZoneId.of("UTC"));
    }

}
