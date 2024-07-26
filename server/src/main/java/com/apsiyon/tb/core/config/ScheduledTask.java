package com.apsiyon.tb.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class ScheduledTask {

    @Scheduled(fixedRate = 30000) // 1 dakika (60.000 milisaniye)
    public void doTask() {
        String url = "https://prvt.onrender.com/";
        RestTemplate restTemplate=new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("HTTP isteği yapıldı. Yanıt: " + response);
    }
}