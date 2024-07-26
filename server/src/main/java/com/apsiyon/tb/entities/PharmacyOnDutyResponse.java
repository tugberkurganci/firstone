package com.apsiyon.tb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PharmacyOnDutyResponse {
    private boolean success; // JSON'daki "success" alanı boolean tipinde
    private List<Pharmacy> result; // JSON'daki "result" alanı liste içeriyor

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pharmacy {
        private String name; // JSON'daki "name" alanı
        private String dist; // JSON'daki "dist" alanı
        private String address; // JSON'daki "address" alanı
        private String phone; // JSON'daki "phone" alanı
        private String loc; // JSON'daki "loc" alanı
    }
}
