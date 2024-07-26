import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import SurveyCreation from '../components/SurveyCreation';
import SurveyList from '../components/SurveyList';
import SurveyDetail from '../components/SurveyDetail';
import http from '../lib/http';
import '../components/SurveyStyles.css';
import { useLocation } from 'react-router-dom';
import axios from 'axios';

Modal.setAppElement('#root'); // Ensure this matches your root element ID

export default function Survey() {
  const location = useLocation();
let counter=0;
  const [selectedSurvey, setSelectedSurvey] = useState(location.state?.selectedSurvey || null);
  const [surveys, setSurveys] = useState([]);
  const [comments, setComments] = useState([]);
  const [onSubmit, setOnSubmit] = useState(false); // State to handle submission status
  const [isModalOpen, setIsModalOpen] = useState(false); // State to control modal visibility

  const handleSurveyCreated = (newSurvey) => {
    setSurveys([...surveys, newSurvey]);
    setIsModalOpen(false); // Close modal after survey is created
  };

  const handleSurveySelected = (survey) => {
    let selected = surveys.filter(s => s.id === survey.id);
    setSelectedSurvey(selected[0]);
  };

  const handleSurveyUpdated = async () => {
    try {
      const response = await http.get('/api/surveys');
      setSurveys(response.data);
    } catch (error) {
      console.error('Failed to update surveys', error);
    }
  };

  const fetchComments = async (survey) => {
    if (survey) {
      try {
        let response = await http.get(`/api/responses/${survey.id}`);
        setComments(response.data);
      } catch (error) {
        console.error('Failed to fetch comments', error);
      }
    }
  };

  useEffect(() => {
    
    if(counter>0){ fetchComments(selectedSurvey);
      
      handleSurveySelected(selectedSurvey);
      setOnSubmit(false)}
   

    counter++


  }, [onSubmit]);

  useEffect(() => {
    console.log(selectedSurvey)
    handleSurveyUpdated();
    fetchComments(selectedSurvey);
  }, [selectedSurvey]);


 
  return (
    <div className='container'>
      <div>
        <button onClick={() => setIsModalOpen(true)} className="button">Create Survey</button>
        <Modal
          isOpen={isModalOpen}
          onRequestClose={() => setIsModalOpen(false)}
          contentLabel="Create Survey"
          className="ReactModal__Content"
          overlayClassName="ReactModal__Overlay"
        >
          <SurveyCreation onSurveyCreated={handleSurveyCreated} onSubmit={setOnSubmit} />
          <button onClick={() => setIsModalOpen(false)} className="button">Close</button>
        </Modal>
        <SurveyList 
          onSurveySelected={handleSurveySelected} 
          surveys={surveys} 
          selectedSurvey={selectedSurvey}
          onSurveyUpdated={handleSurveyUpdated}
          comments={comments}
          setSelectedSurvey={setSelectedSurvey}
          onSubmit={setOnSubmit}
        />
      </div>
    </div>
  );
}

