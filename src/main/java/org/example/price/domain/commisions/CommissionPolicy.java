package org.example.price.domain.commisions;

import org.example.price.domain.model.Price;

public interface CommissionPolicy {

    Price apply(Price price);

}
