package com.apsiyon.tb.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ConciergeRequest {
    private String serviceName;
    private String description;
    private BigDecimal price;
    private boolean subscriptionBased;
    private Integer processTimeInHours;
    private List<Long> employeeIds; // Employee ID'lerini liste olarak alalım
}
