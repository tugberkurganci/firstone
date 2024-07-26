package com.apsiyon.tb.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {
    private String name;
    private String email;
    private String password;
    private List<Long> conciergeIds; // Concierge ID'lerini liste olarak alalım
}
