package org.example.price.configuration;

import org.example.price.adapters.CsvMarketPriceFeed;
import org.example.price.adapters.InMemoryLatestPriceRepository;
import org.example.price.domain.PriceService;
import org.example.price.domain.MarketPriceListener;
import org.example.price.domain.commisions.CommissionPolicy;
import org.example.price.domain.commisions.FlatRateCommissionPolicy;
import org.example.price.domain.ports.LatestPriceRepository;

import java.math.BigDecimal;

public class Configuration {

    private final LatestPriceRepository latestPriceRepository = new InMemoryLatestPriceRepository();
    private final CommissionPolicy commissionPolicy = new FlatRateCommissionPolicy(new BigDecimal("0.01"));
    private final PriceService priceService = new PriceService(latestPriceRepository, commissionPolicy);
    private final MarketPriceListener marketPriceListener = new MarketPriceListener(priceService);
    private final CsvMarketPriceFeed marketPriceFeed = new CsvMarketPriceFeed(marketPriceListener);

    public CsvMarketPriceFeed marketPriceFeed() {
        return marketPriceFeed;
    }
}
