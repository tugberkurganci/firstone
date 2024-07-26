import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { Form as BootstrapForm, Button } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import http from '../lib/http';
import '../components/SurveyStyles.css'; // Import the CSS file

const EventCreation = ({ onEventCreated, onSubmit }) => {
  const auth = useSelector(state => state.auth);

  return (
    <Formik
      initialValues={{
        eventName: '',
        eventDescription: '',
        eventTime: '',
      }}
      validationSchema={Yup.object({
        eventName: Yup.string().required('Event name is required'),
        eventDescription: Yup.string().required('Event description is required'),
        eventTime: Yup.date().required('Event time is required'),
      })}
      onSubmit={async (values, { setSubmitting, resetForm }) => {
        try {
          onSubmit(true);
          const response = await http.post('/api/user-events', { ...values, organizerId: auth.id });
          onEventCreated(response.data);
          resetForm();
          alert('Event created successfully!');
        } catch (error) {
          console.error('Failed to create event', error);
          alert('Failed to create event');
        }
        onSubmit(false);
        setSubmitting(false);
      }}
    >
      {({ isSubmitting }) => (
        <Form as={BootstrapForm}>
          <BootstrapForm.Group controlId="eventName">
            <BootstrapForm.Label>Event Name</BootstrapForm.Label>
            <Field name="eventName" type="text" className="form-control" />
            <ErrorMessage name="eventName" component="div" className="text-danger" />
          </BootstrapForm.Group>
          <BootstrapForm.Group controlId="eventDescription">
            <BootstrapForm.Label>Event Description</BootstrapForm.Label>
            <Field name="eventDescription" type="text" className="form-control" />
            <ErrorMessage name="eventDescription" component="div" className="text-danger" />
          </BootstrapForm.Group>
          <BootstrapForm.Group controlId="eventTime">
            <BootstrapForm.Label>Event Time</BootstrapForm.Label>
            <Field name="eventTime" type="datetime-local" className="form-control" />
            <ErrorMessage name="eventTime" component="div" className="text-danger" />
          </BootstrapForm.Group>
          <Button type="submit" disabled={isSubmitting} className="button">Create Event</Button>
        </Form>
      )}
    </Formik>
  );
};

export default EventCreation;
