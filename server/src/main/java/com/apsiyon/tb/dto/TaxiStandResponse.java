package com.apsiyon.tb.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TaxiStandResponse {
    private List<Place> places;

    // Getters and setters
    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public static class Place {
        @JsonProperty("nationalPhoneNumber")
        private String nationalPhoneNumber;
        @JsonProperty("internationalPhoneNumber")
        private String internationalPhoneNumber;
        @JsonProperty("formattedAddress")
        private String formattedAddress;
        @JsonProperty("location")
        private Location location;
        @JsonProperty("googleMapsUri")
        private String googleMapsUri;
        @JsonProperty("displayName")
        private DisplayName displayName;

        // Getters and setters
        public String getNationalPhoneNumber() {
            return nationalPhoneNumber;
        }

        public void setNationalPhoneNumber(String nationalPhoneNumber) {
            this.nationalPhoneNumber = nationalPhoneNumber;
        }

        public String getInternationalPhoneNumber() {
            return internationalPhoneNumber;
        }

        public void setInternationalPhoneNumber(String internationalPhoneNumber) {
            this.internationalPhoneNumber = internationalPhoneNumber;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getGoogleMapsUri() {
            return googleMapsUri;
        }

        public void setGoogleMapsUri(String googleMapsUri) {
            this.googleMapsUri = googleMapsUri;
        }

        public DisplayName getDisplayName() {
            return displayName;
        }

        public void setDisplayName(DisplayName displayName) {
            this.displayName = displayName;
        }
    }

    public static class Location {
        @JsonProperty("latitude")
        private double latitude;
        @JsonProperty("longitude")
        private double longitude;

        // Getters and setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class DisplayName {
        @JsonProperty("text")
        private String text;
        @JsonProperty("languageCode")
        private String languageCode;

        // Getters and setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }
    }
}
