import React, { useState, useEffect } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import Modal from 'react-modal';
import http from '../lib/http';
import { useSelector } from 'react-redux';
import '../pages/styles.css';
import { useLocation } from 'react-router-dom';

const RequestCreation = ({setRefresh}) => {
  
  const location = useLocation();
  const [modalIsOpen, setModalIsOpen] = useState(location.state?true:false);
  const [selectedService, setSelectedService] = useState(location.state?.selectedService || null);
  const [services, setServices] = useState([]);
  const auth = useSelector(state => state.auth);

  useEffect(() => {
    http.get('/api/concierge-services')
      .then(response => {
        setServices(response.data);
      })
      .catch(error => {
        console.error('Error fetching services:', error);
      });
  }, []);

  const initialValues = {
    serviceId: '',
    startTime: '',
    endTime: '',
  };

  const validationSchema = Yup.object().shape({
    serviceId: Yup.string().required('Service is required'),
    startTime: Yup.string().required('Start Time is required'),
    endTime: Yup.string().required('End Time is required'),
  });

  const onSubmit = (values, { resetForm }) => {
  
    http.post('/api/requests', { ...values, userId: auth.id })
      .then(response => {
        resetForm();
        setRefresh(true); 
        closeModal();
  
      })
      .catch(error => {
        console.error('Error creating request:', error);
      });
  
  };

  const handleServiceChange = (event) => {
    const selectedServiceId = event.target.value;
    const service = services.find(s => s.id === parseInt(selectedServiceId));
    setSelectedService(service);
  };

  const openModal = () => {
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  return (
    <div>
      <button className="btn btn-primary" onClick={openModal}>
        Request Ekle
      </button>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        className="request-modal"
        overlayClassName="request-modal-overlay"
        contentLabel="Request Creation Modal"
        ariaHideApp={false}
      >
        <div className="request-creation-module">
          <button className="btn-close" onClick={closeModal}>X</button>
          <h2>Request Creation</h2>
          <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={onSubmit}
          >
            {({ setFieldValue }) => (
              <Form>
                <div className="mb-3">
                  <label htmlFor="serviceId" className="form-label">
                    Select a Service
                  </label>
                  <Field as="select" id="serviceId" name="serviceId" className="form-control" onChange={(e) => {
                    handleServiceChange(e);
                    setFieldValue('serviceId', e.target.value);
                  }}>
                    <option value="">Select a service</option>
                    {services.map(service => (
                      <option key={service.id} value={service.id}>{service.serviceName}</option>
                    ))}
                  </Field>
                  <ErrorMessage name="serviceId" component="div" className="text-danger" />
                </div>

                {selectedService && (
                  <div className="mb-3">
                    <p><strong>Description:</strong> {selectedService.description}</p>
                    <p><strong>Process Time in Hours:</strong> {selectedService.processTimeInHours}</p>
                    <p><strong>Price:</strong> {selectedService.price}</p>
                  </div>
                )}

                <div className="mb-3">
                  <label htmlFor="startTime" className="form-label">
                    Start Time
                  </label>
                  <Field type="datetime-local" id="startTime" name="startTime" className="form-control" />
                  <ErrorMessage name="startTime" component="div" className="text-danger" />
                </div>

                <div className="mb-3">
                  <label htmlFor="endTime" className="form-label">
                    End Time
                  </label>
                  <Field type="datetime-local" id="endTime" name="endTime" className="form-control" />
                  <ErrorMessage name="endTime" component="div" className="text-danger" />
                </div>

                <button type="submit" className="btn btn-primary">
                  Create Request
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </Modal>
    </div>
  );
};

export default RequestCreation;
