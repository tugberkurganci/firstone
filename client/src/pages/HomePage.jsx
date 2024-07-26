import React, { useState, useEffect } from 'react';
import http from '../lib/http';
import { Container, ListGroup, Card, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import Survey from './Survey';
import './HomePage.css';

const HomePage = () => {
  const [services, setServices] = useState([]);
  const [selectedService, setSelectedService] = useState(null);
  const [events, setEvents] = useState([]);
  const [surveys, setSurveys] = useState([]);
  const navigate=useNavigate();

  useEffect(() => {
    // Axios ile servisleri almak için GET isteği
    http.get('/api/concierge-services')
      .then(response => {
        setServices(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error fetching services:', error);
      });
      

    // Axios ile eventleri almak için GET isteği
    http.get('/api/user-events')
      .then(response => {
        setEvents(response.data.slice(0, 2)); // Fetch and limit to 3 events
      })
      .catch(error => {
        console.error('Error fetching events:', error);
      });

    // Axios ile surveyleri almak için GET isteği
    http.get('/api/surveys')
      .then(response => {
        setSurveys(response.data.slice(0, 3)); // Fetch and limit to 3 surveys
      })
      .catch(error => {
        console.error('Error fetching surveys:', error);
      });
  }, []);

  const handleServiceClick = (service) => {
    if (selectedService && selectedService.id === service.id) {
      setSelectedService(null);
    } else {
      setSelectedService(service);
    }
  };

  const handleRequestClick = (service) => {
    navigate('/request', { state: { selectedService: service } });
  };

  const handleSurveyClick = (survey) => {
    navigate('/survey-event', { state: { selectedSurvey: survey } });
  };

  const handleEventClick = (event) => {
    navigate('/survey-event', { state: { selectedEvent: event } });
  };

  return (
    <Container fluid className="homepage-container">
   <h1 className="text-center">Ana Sayfaya Hoş Geldiniz</h1>
<p className="text-center">Konut sitemizde size en iyi hizmeti sunmak için buradayız. Aşağıda etkinliklerimize, anketlerimize ve çeşitli hizmetlerimize ulaşabilirsiniz. Site sakinlerimiz için en iyi yaşam alanını oluşturmak adına sürekli çalışıyoruz.</p>

      <div className="d-flex justify-content-around my-1">
        <div className="event-container">
          <h2 className="text-center event-title">User Events</h2>
          {events.map(event => (
            <div key={event.id} className="event-item">
              <h3>Event : {event.eventName}</h3>
              <p>Event Content: {event.eventDescription}</p>
              <p>Event Date: {event.eventTime}</p>
              <p>Participants Number: {event.userIds.length}</p>
              
              <button onClick={() => handleEventClick(event)} className="mt-2 custom-green-button">Join Event</button>
              
            </div>
          ))}
        </div>

        <div className="survey-container">
          <h2 className="text-center survey-title">User Surveys</h2>
          {surveys.map(survey => (
            <div key={survey.id} className="survey-item">
              <h3>Survey : {survey.surveyTitle}</h3>
              <p>Survey Content: {survey.surveyDescription}</p>
              
              <button onClick={() => handleSurveyClick(survey)} className="mt-2 custom-green-button">Comment and Vote</button>
             
            </div>
          ))}
        </div>
      </div>

      <h2 className="services-heading">Concierge Services</h2>
      <ListGroup className="service-list">
        {services.map(service => (
          <React.Fragment key={service.id}>
            <ListGroup.Item
              onClick={() => handleServiceClick(service)}
              className={`service-item ${selectedService && selectedService.id === service.id ? 'selected-service-item' : ''}`}>
              <strong>{service.serviceName}</strong>
            </ListGroup.Item>
            {selectedService && selectedService.id === service.id && (
              <Card className="selected-service-card">
                <Card.Body>
                  <Card.Title>Selected Service:</Card.Title>
                  <Card.Text><strong>Service Name:</strong> {selectedService.serviceName}</Card.Text>
                  <Card.Text><strong>Description:</strong> {selectedService.description}</Card.Text>
                  <Card.Text><strong>Process Time in Hours:</strong> {selectedService.processTimeInHours}</Card.Text>
                  
                  <button onClick={() => handleRequestClick(selectedService)} className="mt-2 custom-green-button">Request</button>
             
                </Card.Body>
              </Card>
            )}
          </React.Fragment>
        ))}
      </ListGroup>
    </Container>
  );
};

export default HomePage;

