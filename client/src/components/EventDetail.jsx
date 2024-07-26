import React from 'react';
import { Form, Button, ListGroup } from 'react-bootstrap';
import http from '../lib/http';
import { useSelector } from 'react-redux';
import '../components/SurveyStyles.css'; 

const EventDetail = ({ event, onEventUpdated, setSelectedEvent, handleEventSelected }) => {
  const auth = useSelector(state => state.auth);

  const handleJoinEvent = async (e) => {
    e.preventDefault();
    try {
      await http.post(`/api/user-events/${event.id}/users/${auth.id}`);
      onEventUpdated(); // Update events list
      handleEventSelected(event.id); // Update the selected event
      alert('Successfully joined the event!');
    } catch (error) {
      console.error('Failed to join event', error);
      alert('Failed to join event: ' + error.response.data.message);
    }
  };

  return (
    <div>
      <h3>Event name: {event.eventName}</h3>
      <p>Description: {event.eventDescription}</p>
      <p><strong>Time:</strong> {new Date(event.eventTime).toLocaleString('en-GB', { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false }).replace(',', '')}</p>
      <h4>Participants</h4>
      <ListGroup>
        {event.userIds?.map((id, index) => (
          <ListGroup.Item key={index}>User id:{id} </ListGroup.Item>
        ))}
          {event.userNames?.map((name, index) => (
          <ListGroup.Item key={index}>User Name:{name} </ListGroup.Item>
        ))}
      </ListGroup>
      <Form onSubmit={handleJoinEvent} className="mt-3">
        <Button type="submit" className="button">Join Event</Button>
      </Form>
      <Button onClick={() => setSelectedEvent(null)} className="button mt-3">Close</Button>
    </div>
  );
};

export default EventDetail;

