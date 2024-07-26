// src/Login.js
import { useEffect, useState } from 'react';
import { Alert } from '../components/Alert';
import { Input } from '../components/Input';
import { Button } from '../components/Button';
import { useDispatch} from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { loginSuccess } from '../store/authslice';
import http from '../lib/http';

export function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const [apiProgress, setApiProgress] = useState();
  useEffect(() => {
    if (errors?.email) {
      setErrors((prev) => ({ ...prev, email: undefined }));
    }
  }, [email]);

  useEffect(() => {
    if (errors?.password) {
      setErrors((prev) => ({ ...prev, password: undefined }));
    }
  }, [password]);


  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setApiProgress(true);

    try {
        const response = await http.post("/api/v1/auth",{ email, password })
        dispatch(loginSuccess(response.data))
        console.log(response.data)
        navigate("/")
    } catch (axiosError) {
        console.log(axiosError)
        if (axiosError.response?.data) {
          if (axiosError.response.data.status === 400) {
            setErrors(axiosError.response.data.validationErrors);
          } else {
            setGeneralError(axiosError.response.data.message);
          }
        } else {
          setGeneralError("genericError");
        }
    } finally {
        setApiProgress(false);
    }
  };
  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1>{'login'}</h1>
          </div>
          <div className="card-body">
            <Input
              id="email"
              label={'email'}
              error={errors?.email}
              onChange={(event) => setEmail(event.target.value)}
            />
            <Input
              id="password"
              label={'password'}
              error={errors?.password}
              onChange={(event) => setPassword(event.target.value)}
              type="password"
            />
            {generalError && <Alert styleType="danger">{generalError}</Alert>}
            <div className="text-center">
              <Button disabled={!email || !password} apiProgress={apiProgress}>
                {'login'}
              </Button>
            </div>
          </div>

        </form>
      </div>
    </div>
  );
}
