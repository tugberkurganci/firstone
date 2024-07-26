import React, { useEffect, useState } from 'react';
import http from '../lib/http';
import { Link } from 'react-router-dom';
import '../components/SurveyStyles.css'; // Import the CSS file

const districts = [
  'Adalar', 'Arnavutköy', 'Ataşehir', 'Avcılar', 'Bağcılar', 'Bahçelievler', 'Bakırköy', 'Başakşehir', 'Beylikdüzü',
  'Beyoğlu', 'Büyükçekmece', 'Çatalca', 'Esenler', 'Esenyurt', 'Eyüp', 'Fatih', 'Gaziosmanpaşa', 'Güngören', 'Kadıköy',
  'Kağıthane', 'Kartal', 'Küçükçekmece', 'Maltepe', 'Pendik', 'Sancaktepe', 'Sarıyer', 'Silivri', 'Şişli', 'Ümraniye',
  'Üsküdar', 'Zeytinburnu'
];

function PharmacyView() {
  const [pharmacies, setPharmacies] = useState([]);
  const [selectedDistrict, setSelectedDistrict] = useState('Maltepe');
  const apiKey = import.meta.env.VITE_API_KEY;

  useEffect(() => {
    async function fetchPharmacies() {
      try {
        const response = await http.get('/api/pharmacies', {
          params: {
            ilce: selectedDistrict
          }
        });
        setPharmacies(response.data.result);
        console.log(response.data);
      } catch (error) {
        console.error('Failed to fetch pharmacies', error);
      }
    }

    fetchPharmacies();
  }, [selectedDistrict]);


  
  return (
    <div className="container">
      <h2 className="header">Nöbetçi Eczaneler</h2>
      <div className="formGroup">
        <label htmlFor="district-select">İlçe Seçin:</label>
        <select
          id="district-select"
          value={selectedDistrict}
          onChange={(e) => setSelectedDistrict(e.target.value)}
          className="input"
        >
          {districts.map((district) => (
            <option key={district} value={district}>
              {district}
            </option>
          ))}
        </select>
      </div>
      <ul className="list">
        {pharmacies.map((pharmacy, index) => (
          <li key={index} className="listItem">
            <div className="header">
              <h3>{pharmacy.name}</h3>
            </div>
            <p>{pharmacy.address}</p>
            <p>Telefon: {pharmacy.phone}</p>
            <p>Ilçe: {pharmacy.dist || 'Bilgi Yok'}</p>
            <div>
              <Link 
                to={`https://www.google.com/maps?q=${pharmacy.loc.split(',')[0]},${pharmacy.loc.split(',')[1]}`} 
                target="_blank" 
                rel="noopener noreferrer"
                className="button"
              >
                Haritada Gör
              </Link>
              <iframe
               width="300"
                height="200"
                src={`https://www.google.com/maps/embed/v1/place?key=${apiKey}&q=${pharmacy.loc}`}
                allowFullScreen
                title={`Harita: ${pharmacy.name}`}
                className="iframe"
              ></iframe>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PharmacyView;
