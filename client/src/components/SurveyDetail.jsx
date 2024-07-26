import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import http from '../lib/http';
import './SurveyStyles.css';

function SurveyDetail({ survey, onSurveyUpdated, comments, onSubmit, setSelectedSurvey }) {
  const [comment, setComment] = useState('');
  const [response, setResponse] = useState(false);
  const [totalVotes, setTotalVotes] = useState({ yes: 0, no: 0 });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const auth = useSelector(state => state.auth);

  useEffect(() => {
    // Calculate the total votes
    if (comments) {
      const votes = comments.reduce(
        (acc, curr) => {
          if (curr.response) acc.yes += 1;
          else acc.no += 1;
          return acc;
        },
        { yes: 0, no: 0 }
      );
      setTotalVotes(votes);
    }
  }, [comments]);

  const handleVote = async (e) => {
    setIsSubmitting(true);
    e.preventDefault();

    const vote = { surveyId: survey.id, comment, response, userId: auth.id };
    try {
      onSubmit(true);
      await http.post('/api/responses', vote);
      alert('Vote submitted successfully!');
      // Notify parent of survey update
      onSurveyUpdated();
      setComment('');
    } catch (error) {
      console.error('Failed to submit vote', error);
      alert('Failed to submit vote');
    }
    setIsSubmitting(false);
  };

  return (
    <div>
      <div className="header">
        <h2>Survey Title: {survey.surveyTitle}</h2>
        <button onClick={() => setSelectedSurvey(null)} className="closeButton">CLOSE</button>
      </div>
      <h4>Description: {survey.surveyDescription}</h4>

      <div className="voteSummary">
        <h4>Vote Summary</h4>
        <p>Total Yes Votes: {totalVotes.yes}</p>
        <p>Total No Votes: {totalVotes.no}</p>
      </div>
      <form onSubmit={handleVote} className="form">
        <div className="formGroup">
          <label>Comment:</label>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            required
            className="textarea"
          ></textarea>
        </div>
        <div className="formGroup">
          <label>Vote:</label>
          <div className="voteOption">
            <input
              type="radio"
              value={true}
              checked={response === true}
              onChange={() => setResponse(true)}
            />
            Yes
          </div>
          <div className="voteOption">
            <input
              type="radio"
              value={false}
              checked={response === false}
              onChange={() => setResponse(false)}
            />
            No
          </div>
        </div>
        <button type="submit" disabled={isSubmitting} className="button">
          {isSubmitting ? 'Submitting...' : 'Submit'}
        </button>
      </form>
      <div>
      <h3>Comments</h3>
      <ul className="list">
        {comments?.map((res, index) => (
          <li key={index} className="listItem">
            {res.comment} - {res.response ? 'Yes' : 'No'}
          </li>
        ))}
      </ul>
      </div>
    </div>
  );
}

export default SurveyDetail;

