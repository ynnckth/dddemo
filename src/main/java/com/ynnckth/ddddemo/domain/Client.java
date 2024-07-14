package com.ynnckth.ddddemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

/**
 * This would be considered an Entity in DDD terminology and is therefore mutable
 */
@AllArgsConstructor
@Getter
public class Client {
    String id;
    String displayName;

    Amount assetsUnderManagement;
    Amount netRevenue;

    public List<Currency> getAllDistinctUsedCurrencies() {
        return Stream.of(assetsUnderManagement.currency(), netRevenue.currency())
                .distinct()
                .toList();
    }

    public void convertKeyFigures(List<ExchangeRate> relevantExchangeRates) {
        assetsUnderManagement = assetsUnderManagement.findApplicableExchangeRateAndConvert(relevantExchangeRates);
        netRevenue = netRevenue.findApplicableExchangeRateAndConvert(relevantExchangeRates);
    }
}
