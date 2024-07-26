import React from 'react';
import './SurveyStyles.css';
import SurveyDetail from './SurveyDetail';

function SurveyList({ onSurveySelected, surveys, selectedSurvey, onSurveyUpdated, comments, setSelectedSurvey, onSubmit }) {
  return (
    <div className="container">
      <h2>Survey List</h2>
      <ul className="list">
        {surveys?.map((survey, index) => (
          <React.Fragment key={index}>
            <li
              onClick={() => { onSurveySelected(survey); }}
              className={`listItem ${selectedSurvey && selectedSurvey.id === survey.id ? 'selected' : ''}`}
            >
              Survey Title: {survey.surveyTitle}
            </li>
            {selectedSurvey && selectedSurvey.id === survey.id && (
              <div className="survey-detail-container">
                <SurveyDetail
                  survey={selectedSurvey}
                  onSurveyUpdated={onSurveyUpdated}
                  comments={comments}
                  onSubmit={onSubmit}
                  setSelectedSurvey={setSelectedSurvey}
                />
              </div>
            )}
          </React.Fragment>
        ))}
      </ul>
    </div>
  );
}

export default SurveyList;

