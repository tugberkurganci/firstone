import axios from "axios";
import { loadToken, storeToken } from "../store/storage";


const http = axios.create({
  baseURL: "https://prvt.onrender.com",
});

http.interceptors.request.use((config) => {
  if (authToken) {
    config.headers["Authorization"] = `Bearer ${authToken}`;
  } 
    return config;
})
let authToken = loadToken();

export function setToken(token) {
  authToken = token;
  storeToken(token);
}



export default http;