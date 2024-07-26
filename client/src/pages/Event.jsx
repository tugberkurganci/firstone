import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import EventCreation from '../components/EventCreation';
import EventList from '../components/EventList';
import EventDetail from '../components/EventDetail'; 
import http from '../lib/http';
import '../components/SurveyStyles.css'; 
import { useLocation } from 'react-router-dom';

Modal.setAppElement('#root');

export default function Event() {
  const location = useLocation();

  const [selectedEvent, setSelectedEvent] = useState(location.state?.selectedEvent || null);
  const [events, setEvents] = useState([]);
  const [onSubmit, setOnSubmit] = useState(false); 
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleEventCreated = (newEvent) => {
    setEvents(prevEvents => [...prevEvents, newEvent]);
    setIsModalOpen(false);
  };

  const handleEventSelected = (id) => {
    setSelectedEvent(events.find(event => event.id === id));
  };

  const handleEventUpdated = async () => {
    try {
      const response = await http.get('/api/user-events');
      const updatedEvents = response.data;
      
      setEvents(updatedEvents);
      
      // Update selectedEvent if it still exists in the updated list
      if (selectedEvent && updatedEvents.some(event => event.id === selectedEvent.id)) {
        setSelectedEvent(updatedEvents.find(event => event.id === selectedEvent.id));
      } else {
        setSelectedEvent(null);
      }
    } catch (error) {
      console.error('Failed to update events', error);
    }
  };

  useEffect(() => {
    handleEventUpdated();
  }, [onSubmit]);

  useEffect(() => {
    console.log(selectedEvent);
  }, [selectedEvent]);

  return (
    <div className='container'>
      <button onClick={() => setIsModalOpen(true)} className="button">Create Event</button>
      <Modal
        isOpen={isModalOpen}
        onRequestClose={() => setIsModalOpen(false)}
        contentLabel="Create Event"
        className="ReactModal__Content"
        overlayClassName="ReactModal__Overlay"
      >
        <EventCreation onEventCreated={handleEventCreated} onSubmit={setOnSubmit} />
        <button onClick={() => setIsModalOpen(false)} className="button">Close</button>
      </Modal>
      <EventList 
        onEventSelected={handleEventSelected} 
        events={events} 
        selectedEvent={selectedEvent}
        handleEventSelected={handleEventSelected}
        onEventUpdated={handleEventUpdated}
        setSelectedEvent={setSelectedEvent}
      />
    </div>
  );
}
