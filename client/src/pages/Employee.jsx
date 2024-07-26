// src/pages/EmployeeManager.js

import React, { useState, useEffect } from 'react';
import http from '../lib/http';
import '../components/SurveyStyles.css';

const EmployeeManager = () => {
  const [employees, setEmployees] = useState([]);
  const [conciergeServices, setConciergeServices] = useState([]);
  const [newEmployee, setNewEmployee] = useState({ name: '', role: '', email: '', password: '', conciergeIds: [] });
  const [error, setError] = useState('');

  useEffect(() => {
    fetchEmployees();
    fetchConciergeServices();
  }, []);

  const fetchEmployees = async () => {
    try {
      const response = await http.get('/api/employees');
      setEmployees(response.data);
    } catch (error) {
      console.error('Error fetching employees:', error);
    }
  };

  const fetchConciergeServices = async () => {
    try {
      const response = await http.get('/api/concierge-services');
      setConciergeServices(response.data);
    } catch (error) {
      console.error('Error fetching concierge services:', error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEmployee({ ...newEmployee, [name]: value });
  };

  const handleCheckboxChange = (e) => {
    const { value, checked } = e.target;
    setNewEmployee((prev) => {
      const conciergeIds = checked
        ? [...prev.conciergeIds, parseInt(value)]
        : prev.conciergeIds.filter((id) => id !== parseInt(value));
      return { ...prev, conciergeIds };
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!newEmployee.name || !newEmployee.email || !newEmployee.password) {
      setError('All fields are required');
      return;
    }
    setError('');
    try {
      await http.post('/api/employees', newEmployee);
      setNewEmployee({ name: '', email: '', password: '', conciergeIds: [] });
      fetchEmployees();
    } catch (error) {
      console.error('Error adding employee:', error);
    }
  };

  return (
    <div className="container">
      <h2>Employee Manager</h2>
      <form className="form" onSubmit={handleSubmit}>
        <div className="formGroup">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            name="name"
            className="input"
            value={newEmployee.name}
            onChange={handleInputChange}
          />
        </div>

        <div className="formGroup">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            className="input"
            value={newEmployee.email}
            onChange={handleInputChange}
          />
        </div>

        <div className="formGroup">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            className="input"
            value={newEmployee.password}
            onChange={handleInputChange}
          />
        </div>

        <div className="formGroup">
          <label>Concierge Services</label>
          {conciergeServices.map((service) => (
            <div key={service.id}>
              <input
                type="checkbox"
                id={`service-${service.id}`}
                name="conciergeIds"
                value={service.id}
                checked={newEmployee.conciergeIds.includes(service.id)}
                onChange={handleCheckboxChange}
              />
              <label htmlFor={`service-${service.id}`}>{service.serviceName}</label>
            </div>
          ))}
        </div>

        {error && <div className="error">{error}</div>}
        <button type="submit" className="button">
          Add Employee
        </button>
      </form>

      <h2>Employee List</h2>
      <ul className="list">
        {employees.map((employee) => (
          <li key={employee.id} className="listItem">
            <div>
              <strong>Name:</strong> {employee.name}
            </div>

            <div>
              <strong>Email:</strong> {employee.email}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EmployeeManager;
