import React, { useState, useEffect } from 'react';
import http from '../lib/http';
import RequestView from './RequestView';
import { useSelector } from 'react-redux';
import '../pages/styles.css';

const RequestListView = ({ refresh, setRefresh }) => {
  const [requests, setRequests] = useState([]);
  const auth = useSelector(state => state.auth);

  useEffect(() => {
    if (auth.role === 'MANAGER') {
      http.get(`/api/requests`)
        .then(response => {
          setRequests(response.data);
        })
        .catch(error => {
          console.error('Error fetching requests:', error);
        });
    } else if (auth.role === 'USER') {
      http.get(`/api/requests/${auth.id}`)
        .then(response => {
          setRequests(response.data);
        })
        .catch(error => {
          console.error('Error fetching requests:', error);
        });
    } else if (auth.role === 'EMPLOYEE') {
      http.get(`/api/requests/employee/${auth.id}`)
        .then(response => {
          setRequests(response.data);
        })
        .catch(error => {
          console.error('Error fetching requests:', error);
        });
    }
  }, [refresh, auth]);

  const renderRequestRows = () => {
    const rows = [];
    for (let i = 0; i < requests.length; i += 2) {
      rows.push(
        <div key={i} className="request-row">
          <RequestView request={requests[i]} setRefresh={setRefresh} />
          {requests[i + 1] && <RequestView request={requests[i + 1]} setRefresh={setRefresh} />}
        </div>
      );
    }
    return rows;
  };

  return (
    <div>
      <h2>Request List</h2>
      {requests.length === 0 ? (
        <p>No requests found.</p>
      ) : (
        <div>
          {renderRequestRows()}
        </div>
      )}
    </div>
  );
};

export default RequestListView;
