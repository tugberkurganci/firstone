import React, { useEffect, useState } from 'react';
import './styles.css';
import RequestListView from '../components/RequestListView';
import RequestCreation from '../components/RequestCreation';
import { ref } from 'yup';



export default function RequestComponent() {
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    setRefresh(false);
  }, [refresh]);

  return (
    <div className="request-container">
      <div className="request-content">
      <div className="request-creation">
          <RequestCreation  setRefresh={setRefresh}/>
        </div>
        <div className="request-list">
          <RequestListView  refresh={refresh} setRefresh={setRefresh} />
        </div>
      
      </div>
    </div>
  );
}
