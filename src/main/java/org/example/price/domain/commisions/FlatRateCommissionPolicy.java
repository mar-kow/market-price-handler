package org.example.price.domain.commisions;

import lombok.extern.slf4j.Slf4j;
import org.example.price.domain.model.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class FlatRateCommissionPolicy implements CommissionPolicy {

    private final static int PERCENTAGE_SCALE = 6;

    private final BigDecimal percentage;

    public FlatRateCommissionPolicy(BigDecimal percentage) {
        this.percentage = normalize(percentage);
    }

    @Override
    public Price apply(Price price) {
        log.info("Applying commission for priceId={}", price.id());
        var newBid = calculateBid(price);
        var newAsk = calculateAsk(price);
        return new Price(price.id(), price.instrument(), newBid, newAsk, price.timestamp());
    }

    private BigDecimal calculateBid(Price price) {
        BigDecimal bid = price.bid();
        var commission = calculateCommission(bid);
        return bid.subtract(commission);
    }

    private BigDecimal calculateAsk(Price price) {
        var ask = price.ask();
        var commission = calculateCommission(ask);
        return ask.add(commission);
    }

    private BigDecimal calculateCommission(BigDecimal price) {
        return price.multiply(percentage).setScale(price.scale(), RoundingMode.HALF_UP);
    }

    private BigDecimal normalize(BigDecimal percentage) {
        return percentage.divide(BigDecimal.valueOf(100), PERCENTAGE_SCALE, RoundingMode.HALF_UP);
    }

}
