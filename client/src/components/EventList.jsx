import React from 'react';
import { ListGroup } from 'react-bootstrap';
import '../components/SurveyStyles.css'; 
import EventDetail from './EventDetail'; 

const EventList = ({ events, onEventSelected, selectedEvent, handleEventSelected, onEventUpdated, setSelectedEvent }) => {
  return (
    <div className="container">
      <h2>Events</h2>
      <ListGroup className="list">
        {events?.map((event, index) => (
          <React.Fragment key={index}>
            <ListGroup.Item
              onClick={() => onEventSelected(event.id)}
              className={`listItem ${selectedEvent && selectedEvent.id === event.id ? 'selected' : ''}`}
            >
              {event?.eventName}
            </ListGroup.Item>
            {selectedEvent && selectedEvent.id === event.id && (
              <div className="event-detail-container">
                <EventDetail 
                  event={selectedEvent} 
                  onEventUpdated={onEventUpdated} 
                  setSelectedEvent={setSelectedEvent}
                  handleEventSelected={handleEventSelected}
                />
              </div>
            )}
          </React.Fragment>
        ))}
      </ListGroup>
    </div>
  );
};

export default EventList;
