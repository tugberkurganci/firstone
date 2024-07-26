import React, { useState, useEffect } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import '../components/SurveyStyles.css'; // Import the CSS file
import { useSelector } from 'react-redux';
import http from '../lib/http';

const VehicleManager = () => {
  const [vehicles, setVehicles] = useState([]);
  const auth = useSelector(state => state.auth);

  useEffect(() => {
    const fetchVehicles = async () => {
      try {
        const response = await http.get((auth.role === "USER" && auth.id !== 0) ? `/api/vehicles/user/${auth.id}` : `/api/vehicles`);
        console.log(response.data);
        setVehicles(response.data);
      } catch (error) {
        console.error('Failed to fetch vehicles', error);
      }
    };

    fetchVehicles();
  }, [auth]);

  const handleVehicleCreated = (newVehicle) => {
    setVehicles([...vehicles, newVehicle]);
  };

  const handleDeleteVehicle = async (vehicleId) => {
    try {
      await http.delete(`/api/vehicles/${vehicleId}`);
      setVehicles(vehicles.filter(vehicle => vehicle.id !== vehicleId));
    } catch (error) {
      console.error('Failed to delete vehicle', error);
      alert('Failed to delete vehicle');
    }
  };

  return (
    <div className="container">
      <h1>Vehicle Management</h1>
      <Formik
        initialValues={{
          userId: '',
          vehicleMake: '',
          vehicleModel: '',
          licensePlate: '',
         
        }}
        validationSchema={Yup.object({
          
          vehicleMake: Yup.string().required('Vehicle make is required'),
          vehicleModel: Yup.string().required('Vehicle model is required'),
          licensePlate: Yup.string().required('License plate is required'),
         
        })}
        onSubmit={async (values, { setSubmitting, resetForm }) => {
          console.log('Form submitted with values:', values);
          try {
            const response = await http.post('/api/vehicles', auth.role === "USER" ? {...values, userId: auth.id} : values);
            console.log('Vehicle created response:', response.data);
            handleVehicleCreated(response.data);
            resetForm();
            alert('Vehicle added successfully!');
          } catch (error) {
            console.error('Failed to add vehicle', error);
            alert('Failed to add vehicle');
          }
          setSubmitting(false);
        }}
      >
        {({ isSubmitting }) => (
          <Form className="form">
            {auth.role === "MANAGER" && (
              <div className="formGroup">
                <label>User ID</label>
                <Field name="userId" type="number" className="input" />
                <ErrorMessage name="userId" component="div" className="error" />
              </div>
            )}
            <div className="formGroup">
              <label>Vehicle Make</label>
              <Field name="vehicleMake" type="text" className="input" />
              <ErrorMessage name="vehicleMake" component="div" className="error" />
            </div>
            <div className="formGroup">
              <label>Vehicle Model</label>
              <Field name="vehicleModel" type="text" className="input" />
              <ErrorMessage name="vehicleModel" component="div" className="error" />
            </div>
            <div className="formGroup">
              <label>License Plate</label>
              <Field name="licensePlate" type="text" className="input" />
              <ErrorMessage name="licensePlate" component="div" className="error" />
            </div>

            <button type="submit" className="button" disabled={isSubmitting}>Add Vehicle</button>
          </Form>
        )}
      </Formik>
      <div>
        <h2>Vehicles</h2>
        <ul className="list">
          {vehicles.map(vehicle => (
            <li key={vehicle.id} className="listItem">
              <div className="header">
                <span>{vehicle.vehicleMake} {vehicle.vehicleModel} - {vehicle.licensePlate} (User ID: {vehicle.userId})</span>
                <button onClick={() => handleDeleteVehicle(vehicle.id)} className="closeButton">×</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default VehicleManager;
