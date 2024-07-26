
import React from 'react';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import './App.css'
import PharmacyView from './pages/PharmacyView';
import TaxiCall from './pages/TaxiCall';
import SurveyEvent from './pages/SurveyEvent';
import RequestComponent from './pages/RequestComponent';
import VehicleManager from './pages/VehicleManager';
import { Login } from './pages/Login';
import { SignUp } from './pages/SignUp';
import CustomNavbar from './components/CustomNavbar';
import { Container } from 'react-bootstrap';
import HomePage from './pages/HomePage';
import Employee from './pages/Employee';
import EmployeeRoute from './routes/EmployeeRoute';

function App() {
  return (
    <>
      <Router  basename="/prvt2/">
        <CustomNavbar />
        <Container className="mt-4">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/employee" element={<EmployeeRoute><Employee /></EmployeeRoute>} />
            <Route path="/request" element={<RequestComponent />} />
            <Route path="/survey-event" element={<EmployeeRoute><SurveyEvent /></EmployeeRoute>} />
            <Route path="/pharmacy-view" element={<PharmacyView />} />
            <Route path="/taxi-call" element={<TaxiCall />} />
            <Route path="/register-vehicle" element={<EmployeeRoute><VehicleManager /></EmployeeRoute>} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<SignUp />} />
          </Routes>
        </Container>
      </Router>
    </>
  );
}



export default App;



