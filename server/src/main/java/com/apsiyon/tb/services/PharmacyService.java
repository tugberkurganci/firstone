package com.apsiyon.tb.services;
import com.apsiyon.tb.entities.PharmacyOnDutyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PharmacyService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String apiUrl = 
    private final String apiKey = // API anahtarınızı buraya ekleyin

    public PharmacyService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public PharmacyOnDutyResponse getPharmaciesOnDuty(String district) {
        // API çağrısını yap ve yanıtı döndür
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("authorization", "apikey " + apiKey);

        // HttpEntity oluştur
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // URL'yi oluştur
        String url = String.format("%s?ilce=%s&il=İstanbul", apiUrl, district); // Şehir adını URL'ye ekle

        // API çağrısını yap ve yanıtı al
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Yanıtı JSON olarak işleyin
        try {
            return objectMapper.readValue(response.getBody(), PharmacyOnDutyResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
