import React from 'react'
import Survey from './Survey'
import Event from './Event'

export default function SurveyEvent() {
  return (
    <div style={{ display: 'flex', justifyContent: 'space-between', padding: '20px' }}>
      <div style={{ width: '45%' }}>
        <Event />
      </div>
      <div style={{ width: '45%' }}>
      <Survey />
      </div>
    </div>
  );
}