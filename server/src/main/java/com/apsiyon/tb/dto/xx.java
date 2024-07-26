package com.apsiyon.tb.dto;


import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;



    public class xx {
        public static void main(String[] args) {
            OkHttpClient client = new OkHttpClient();

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("includedTypes", new String[]{"taxi_stand"});
            jsonRequestBody.put("maxResultCount", 5);

            JSONObject locationRestriction = new JSONObject();
            JSONObject circle = new JSONObject();
            JSONObject center = new JSONObject();

            center.put("latitude", 40.9357);
            center.put("longitude", 29.1550);
            circle.put("center", center);
            circle.put("radius", 2000.0);
            locationRestriction.put("circle", circle);

            jsonRequestBody.put("locationRestriction", locationRestriction);

            RequestBody body = RequestBody.create(
                    jsonRequestBody.toString(),
                    MediaType.get("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url("https://places.googleapis.com/v1/places:searchNearby")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-Goog-Api-Key", "AIzaSyCYdT6KvMS-qX-EH1od_RJ5PxxpiONSwns")
                    .addHeader("X-Goog-FieldMask", "places.displayName,places.internationalPhoneNumber,places.nationalPhoneNumber,places.googleMapsUri,places.formattedAddress,places.location")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response.body().string());
                }
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


