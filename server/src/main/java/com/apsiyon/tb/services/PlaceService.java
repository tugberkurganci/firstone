package com.apsiyon.tb.services;


import com.apsiyon.tb.dto.TaxiStandResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class PlaceService {

    private final OkHttpClient client = new OkHttpClient();

    @Value("${google.api.key}")
    private String apiKey;

    private ObjectMapper mapper = new ObjectMapper();
    public TaxiStandResponse searchNearby(double latitude, double longitude, double radius) throws IOException {
        // JSON gövdesini oluşturun
        JSONObject jsonRequestBody = new JSONObject();
        jsonRequestBody.put("maxResultCount", 5);

        // Arama kriterlerini ayarlayın
        JSONObject locationRestriction = new JSONObject();
        JSONObject circle = new JSONObject();
        JSONObject center = new JSONObject();

        center.put("latitude", latitude);
        center.put("longitude", longitude);
        circle.put("center", center);
        circle.put("radius", radius);
        locationRestriction.put("circle", circle);

        jsonRequestBody.put("locationRestriction", locationRestriction);
        jsonRequestBody.put("includedTypes", new String[]{ "taxi_stand"}); // Genel türler kullanın

        // Request Body'yi ayarlayın
        RequestBody body = RequestBody.create(
                jsonRequestBody.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        // Request oluşturun
        Request request = new Request.Builder()
                .url("https://places.googleapis.com/v1/places:searchNearby")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Goog-Api-Key", apiKey)
                .addHeader("X-Goog-FieldMask", "places.displayName,places.internationalPhoneNumber,places.nationalPhoneNumber,places.googleMapsUri,places.formattedAddress,places.location")
                .build();

        // İstek gönderin ve yanıtı işleyin
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            TaxiStandResponse x = mapper.readValue(response.body().string(), TaxiStandResponse.class);
            return x ;
        }
    }}