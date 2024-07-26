package com.apsiyon.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConciergeResponse {
    private Long id;
    private String serviceName;
    private String description;
    private BigDecimal price;
    private boolean subscriptionBased;
    private Integer processTimeInHours;
    private List<Long> employeeIds; // Employee ID'lerini tutmak için
}