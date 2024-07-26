import React, { useEffect, useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import http from '../lib/http';
import './PopularEvent.css'; // Yeni CSS dosyasını içe aktar

export default function PopularEvent() {
  const [events, setEvents] = useState([]);

  const fetchEvents = async () => {
    try {
      const response = await http.get('/api/user-events');
      setEvents(response.data.slice(0, 3)); // Fetch and limit to 3 events
    } catch (error) {
      console.error('Failed to fetch events', error);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);

  return (
  
    <Container className="event-container">
      <Row className="my-4">
        <Col>
          <h1 className="text-center">User Event App</h1>
        </Col>
      </Row>
      <Row>
        <Col md={6}>
          {events.map(event => (
            <div key={event.id} className="event-item">
              <h2>{event.name}</h2>
              <p>{event.content}</p>
              <p>Participants: {event.participants}</p>
            </div>
          ))}
        </Col>
      </Row>
    </Container>
  );
}

