package com.apsiyon.tb.dto;



import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private List<Long> conciergeIds; // Eklenen concierge ID'leri
}
