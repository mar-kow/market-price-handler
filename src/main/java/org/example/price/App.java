package org.example.price;

import org.example.price.configuration.Configuration;

public class App {

    public static void main(String[] args) {
        var configuration = new Configuration();
        var marketPriceFeed = configuration.marketPriceFeed();

        marketPriceFeed.feedMarketPrices("market-price-feed.csv");
    }

}