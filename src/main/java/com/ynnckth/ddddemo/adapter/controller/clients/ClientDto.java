package com.ynnckth.ddddemo.adapter.controller.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientDto {
    String id;
    String displayName;
    AmountDto assetsUnderManagement;
    AmountDto netRevenue;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class AmountDto {
        String value;
        String currency;
    }
}
