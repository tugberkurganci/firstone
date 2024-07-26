import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../components/SurveyStyles.css'; // Import the CSS file
import http from '../lib/http';

const TaxiCall = () => {
  const [latitude, setLatitude] = useState(null);
  const [longitude, setLongitude] = useState(null);
  const [radius, setRadius] = useState(2000);
  const [taxiStands, setTaxiStands] = useState([]);

  useEffect(() => {
    // Kullanıcının konumunu almak için geolocation API'ini kullan
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        setLatitude(position.coords.latitude);
        setLongitude(position.coords.longitude);
      }, (error) => {
        console.error("Geolocation error:", error);
        // Konum alınamadığında varsayılan değerler kullanabiliriz
        setLatitude(40.7128); // Varsayılan: New York'un enlem ve boylamı
        setLongitude(-74.0060);
      });
    } else {
      console.error("Geolocation is not supported by this browser.");
    }
  }, []);

  const handleSearch = async () => {
    if (latitude && longitude) {
      try {
        const response = await http.get(`/api/searchNearby`, {
          params: {
            latitude,
            longitude,
            radius
          }
        });
        setTaxiStands(response.data.places);
      } catch (error) {
        console.error("Error fetching taxi stands:", error);
      }
    } else {
      console.error("Location not available.");
    }
  };

  return (
    <div className="container">
      <h1 className="header">Taxi Stand Search</h1>
      <button onClick={handleSearch} className="button">Find Nearest Taxi Stands</button>
      <ul className="list">
        {taxiStands.map((stand, index) => (
          <li key={index} className="listItem">
            <h2>{stand.displayName.text}</h2>
            <p>{stand.formattedAddress}</p>
            <p>Phone: {stand.internationalPhoneNumber}</p>
            <a href={stand.googleMapsUri} target="_blank" rel="noopener noreferrer" className="button">
              View on Google Maps
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TaxiCall;
