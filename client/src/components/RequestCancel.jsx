import React from 'react';
import PropTypes from 'prop-types';

import { Button } from 'react-bootstrap';
import http from '../lib/http';

const RequestCancel = ({ requestId ,setRefresh}) => {
  const handleCancel = async () => {
    try {
      await http.delete(`/api/requests/${requestId}`);
      alert('Request successfully canceled');
      setRefresh(true)
      // Optionally, add logic to update the UI after cancellation
    } catch (error) {
      console.error('Error canceling request:', error);
      alert('Failed to cancel request');
    }
  };

  return (
    <Button variant="danger" onClick={handleCancel}>
      Cancel Request
    </Button>
  );
};



export default RequestCancel;
