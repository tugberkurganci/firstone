import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Badge, Card, Button } from 'react-bootstrap';
import RequestCancel from './RequestCancel';
import UpdateRequest from './UpdateRequest';
import '../pages/styles.css';

const RequestView = ({ request, setRefresh }) => {
  const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);

  const openUpdateModal = () => {
    setIsUpdateModalOpen(true);
  };

  const closeUpdateModal = () => {
    setIsUpdateModalOpen(false);
  };

  return (
    <Card className="mb-3">
      <Card.Body>
        <Card.Title>Request Details</Card.Title>
        <Card.Text><strong>ID:</strong> {request?.id}</Card.Text>
        <Card.Text><strong>Service Name:</strong> {request?.serviceName}</Card.Text>
        <Card.Text><strong>Start Time:</strong> {new Date(request?.startTime).toLocaleString()}</Card.Text>
        <Card.Text><strong>End Time:</strong> {new Date(request?.endTime).toLocaleString()}</Card.Text>
        <Card.Text><strong>Started:</strong> {request?.started ? 'Yes' : 'No'}</Card.Text>
        <Card.Text><strong>Employee Name:</strong> {request?.employeeNames.join(', ')}</Card.Text>
        <Card.Text><strong>User Name:</strong> {request?.username}</Card.Text>
        <Card.Text><strong>User ID:</strong> {request?.userId}</Card.Text>
        <Card.Text><strong>Status:</strong> <Badge className="badge-primary">{request?.status}</Badge></Card.Text>
        <RequestCancel requestId={request?.id} setRefresh={setRefresh} />
        <Button variant="primary" onClick={openUpdateModal}>
          Update Request
        </Button>
      </Card.Body>
      {isUpdateModalOpen && (
        <UpdateRequest
          request={request}
          setRefresh={setRefresh}
          closeUpdateModal={closeUpdateModal}
        />
      )}
    </Card>
  );
};

RequestView.propTypes = {
  request: PropTypes.object.isRequired,
  setRefresh: PropTypes.func.isRequired,
};

export default RequestView;
