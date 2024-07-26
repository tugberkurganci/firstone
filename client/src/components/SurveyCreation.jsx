// SurveyCreation.js
import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import { useSelector } from 'react-redux';
import './SurveyStyles.css';
import http from '../lib/http';

const validationSchema = Yup.object({
  surveyTitle: Yup.string().required('Title is required'),
  surveyDescription: Yup.string().required('Description is required'),
});

function SurveyCreation({ onSurveyCreated, onSubmit }) {
  const auth = useSelector(state => state.auth);

  const handleCreateSurvey = async (values, { resetForm }) => {
    try {
      onSubmit(true);
      const response = await http.post('/api/surveys', { ...values, creatorId: auth.id });
      onSurveyCreated(response.data); // Notify parent of new survey
      resetForm();
      alert('Survey created successfully!');
    } catch (error) {
      console.error('Failed to create survey', error);
      alert('Failed to create survey');
    }
    onSubmit(false);
  };

  return (
    <div className="container">
      <h2>Create Survey</h2>
      <Formik
        initialValues={{ surveyTitle: '', surveyDescription: '' }}
        validationSchema={validationSchema}
        onSubmit={handleCreateSurvey}
      >
        {({ isSubmitting }) => (
          <Form className="form">
            <div className="formGroup">
              <label htmlFor="surveyTitle">Title:</label>
              <Field type="text" name="surveyTitle" className="input" />
              <ErrorMessage name="surveyTitle" component="div" className="error" />
            </div>
            <div className="formGroup">
              <label htmlFor="surveyDescription">Description:</label>
              <Field as="textarea" name="surveyDescription" className="textarea" />
              <ErrorMessage name="surveyDescription" component="div" className="error" />
            </div>
            <button type="submit" disabled={isSubmitting} className="button">
              Create Survey
            </button>
          </Form>
        )}
      </Formik>
    </div>
  );
}

export default SurveyCreation;
