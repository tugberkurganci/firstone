import React from 'react';
import { Navbar as BootstrapNavbar, Nav, Container, NavDropdown } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { logoutSuccess } from '../store/authslice';
import { Link } from 'react-router-dom';
import http from '../lib/http';

export default function CustomNavbar() {
  const auth = useSelector(state => state.auth);
  const dispatch = useDispatch();

  const handleLogout = () => {
    // Add your code here to handle logout
    http
      .post("/api/v1/logout")
      .then(response => {
        // Handle success response
        dispatch(logoutSuccess());
      })
      .catch(error => {
        // Handle error response
        console.error("Logout failed:", error);
      });
  };

  return (
    <BootstrapNavbar bg="light" expand="lg">
      <Container>
        <BootstrapNavbar.Brand as={Link} to="/">Home</BootstrapNavbar.Brand>
        <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
        <BootstrapNavbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
         {auth.role && auth.role === 'MANAGER' && (<Nav.Link as={Link} to="/employee">Employees</Nav.Link>)} 
            <Nav.Link as={Link} to="/request">Request</Nav.Link>
          <Nav.Link as={Link} to="/survey-event">Survey & Event</Nav.Link>
            <Nav.Link as={Link} to="/register-vehicle">Register Vehicle</Nav.Link>
            <Nav.Link as={Link} to="/pharmacy-view">Pharmacy View</Nav.Link>
            <Nav.Link as={Link} to="/taxi-call">Taxi Call</Nav.Link>
            
          </Nav>
          <Nav>
            {auth.id !== 0 ? (
              <>
                <NavDropdown title={auth.email} id="basic-nav-dropdown">
                  <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
                </NavDropdown>
              </>
            ) : (
              <>
                <Nav.Link as={Link} to="/login">Login</Nav.Link>
                <Nav.Link as={Link} to="/signup">Sign Up</Nav.Link>
              </>
            )}
          </Nav>
        </BootstrapNavbar.Collapse>
      </Container>
    </BootstrapNavbar>
  );
}
