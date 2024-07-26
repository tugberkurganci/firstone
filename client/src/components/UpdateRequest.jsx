import React, { useState, useEffect } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import Modal from 'react-modal';
import http from '../lib/http';
import { useSelector } from 'react-redux';
import '../pages/styles.css';

const UpdateRequest = ({ request, setRefresh, closeUpdateModal }) => {
  const auth = useSelector(state => state.auth);

  const initialValues = {
    startTime: request?.startTime || '',
    endTime: request?.endTime || '',
    started: request?.started || false,
    status: request?.status || '',
  };

  const validationSchema = Yup.object().shape({
    startTime: Yup.string().required('Start Time is required'),
    endTime: Yup.string().required('End Time is required'),
    started: Yup.boolean().required('Started status is required'),
    status: Yup.string().required('Status is required'),
  });

  const onSubmit = async (values, { resetForm }) => {
    try {
      await http.put(`/api/requests`, { ...values, id:request.id });
      resetForm();
      setRefresh(true);
      closeUpdateModal();
    } catch (error) {
      console.error('Error updating request:', error);
    }
  };

return (
    <Modal
        isOpen={true}
        onRequestClose={closeUpdateModal}
        className="request-modal"
        overlayClassName="request-modal-overlay"
        contentLabel="Update Request Modal"
        ariaHideApp={false}
    >
        <div className="request-creation-module">
            <button className="btn-close" onClick={closeUpdateModal}>X</button>
            <h2>Update Request</h2>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={onSubmit}
                enableReinitialize
            >
                {() => (
                    <Form>
                        <div className="mb-3">
                            <label htmlFor="startTime" className="form-label">Start Time</label>
                            <Field type="datetime-local" id="startTime" name="startTime" className="form-control" />
                            <ErrorMessage name="startTime" component="div" className="text-danger" />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="endTime" className="form-label">End Time</label>
                            <Field type="datetime-local" id="endTime" name="endTime" className="form-control" />
                            <ErrorMessage name="endTime" component="div" className="text-danger" />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="started" className="form-label">Started</label>
                            <Field as="select" id="started" name="started" className="form-control">
                                <option value="">Select status</option>
                                <option value="true">Yes</option>
                                <option value="false">No</option>
                            </Field>
                            <ErrorMessage name="started" component="div" className="text-danger" />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="status" className="form-label">Status</label>
                            <Field as="select" id="status" name="status" className="form-control">
                                <option value="">Select status</option>
                                {/* Add your request statuses here */}
                                <option value="CREATED">Created</option>
                                <option value="IN_PROGRESS">In Progress</option>
                                <option value="COMPLETED">Completed</option>
                                <option value="CANCELED">Canceled</option>
                             
                            </Field>
                            <ErrorMessage name="status" component="div" className="text-danger" />
                        </div>

                        <button type="submit" className="btn btn-primary">Update Request</button>
                    </Form>
                )}
            </Formik>
        </div>
    </Modal>
);
};

export default UpdateRequest;
