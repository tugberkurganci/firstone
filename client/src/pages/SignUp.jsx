import { useEffect, useMemo, useState } from "react";

import { Input } from "../components/Input";
import { Alert } from "../components/Alert";

import { Button } from "../components/Button";
import http from "../lib/http";

export function SignUp() {

    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [passwordRepeat, setPasswordRepeat] = useState();
    const [apiProgress, setApiProgress] = useState();
    const [successMessage, setSuccessMessage] = useState();
    const [errors, setErrors] = useState({});
    const [generalError, setGeneralError] = useState();

  

  
    useEffect(() => {
      setErrors(function (lastErrors) {
        return {
          ...lastErrors,
          email: undefined,
        };
      });
    }, [email]);
  
    useEffect(() => {
      setErrors(function (lastErrors) {
        return {
          ...lastErrors,
          password: undefined,
        };
      });
    }, [password]);
  
    const onSubmit = async (event) => {
      event.preventDefault();
      setSuccessMessage();
      setGeneralError();
      setApiProgress(true);
  
      try {
        const response = await http.post("/api/v1/users",{

          email,
          password,
          role:"u"
        });
        setSuccessMessage(response.data.message);
      } catch (axiosError) {
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
  
    const passwordRepeatError = useMemo(() => {
      if (password && password !== passwordRepeat) {
        return "passwordMismatch";
      }
      return "";
    }, [password, passwordRepeat]);
  
    return (
      <div className="container">
        <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
          <form className="card" onSubmit={onSubmit}>
            <div className="text-center card-header">
              <h1>{"signUp"}</h1>
            </div>
            <div className="card-body">

              <Input
                id="email"
                label={"email"}
                error={errors.email}
                onChange={(event) => setEmail(event.target.value)}
              />
              <Input
                id="password"
                label={"password"}
                error={errors.password}
                onChange={(event) => setPassword(event.target.value)}
                type="password"
              />
              <Input
                id="passwordRepeat"
                label={"passwordRepeat"}
                error={passwordRepeatError}
                onChange={(event) => setPasswordRepeat(event.target.value)}
                type="password"
              />
              {successMessage && <Alert>{successMessage}</Alert>}
              {generalError && <Alert styleType="danger">{generalError}</Alert>}
              <div className="text-center">
                <Button
                  disabled={!password || password !== passwordRepeat}
                  apiProgress={apiProgress}
                >
                  {"signUp"}
                </Button>
              </div>
            </div>
          </form>
        </div>
      </div>
    );
  }